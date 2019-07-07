package com.hpst.tdd.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;

@EnableCaching
@Configuration
public class CacheConfig {
	
	@Bean("commonCacheManager")
	@Primary
	 public CacheManager defaultCacheManager() {
	  System.out.println("@@@@@@---------default cache--------@@");
	  CaffeineCacheManager cacheManager = new CaffeineCacheManager("car");
	  cacheManager.setAllowNullValues(false);
	  cacheManager.setCaffeine(caffeineCacheBuilder());
	  return cacheManager;
	 }


	Caffeine < Object, Object > caffeineCacheBuilder() {
		System.out.println("@@@@@@---------caffeineCacheBuilder cache--------@@");
		return Caffeine.newBuilder()
	   .initialCapacity(100)
	   .maximumSize(500)
	   .expireAfterAccess(10, TimeUnit.MINUTES)
	   .removalListener(new CustomRemovalListner())
	   .recordStats();
	 }
 
 private class CustomRemovalListner implements RemovalListener<Object, Object>{

	@Override
	public void onRemoval(Object key, Object value, RemovalCause cause) {
		System.out.println("Key got evicted "+key + " cause "+cause.toString()  + " evicted "+  cause.wasEvicted());	
	}
	 
 }
}

