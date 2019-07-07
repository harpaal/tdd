package com.hpst.tdd.repository.es;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories
public class ElasticConfiguration  {
	//@Value("${elasticsearch.host}")
	  
		private String host="192.168.99.100";

	  private int port=9300;

	  //@Value("${elasticsearch.clusterName}")
	  private String clusterName="elasticsearch";

	  @Bean
	  public Client client() throws Exception {
	    Settings settings = Settings.builder().put("cluster.name", clusterName).build();

	    TransportClient client = new PreBuiltTransportClient(settings);
	    client.addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
	    return client;
	  }

	  @Bean
	  public ElasticsearchOperations elasticsearchTemplate() throws Exception {
	    return new ElasticsearchTemplate(client());
	  }

	}