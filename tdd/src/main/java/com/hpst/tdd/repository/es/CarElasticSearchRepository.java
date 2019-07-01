package com.hpst.tdd.repository.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import com.hpst.tdd.Car;

@Repository	
public interface CarElasticSearchRepository extends ElasticsearchCrudRepository<Car, Integer> {

}
