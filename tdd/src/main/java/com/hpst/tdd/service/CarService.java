package com.hpst.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hpst.tdd.Car;
import com.hpst.tdd.repository.CarRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
@AllArgsConstructor
public class CarService {

	@Autowired
	CarRepository carRepository;
	
	
	@Cacheable(cacheManager = "commonCacheManager",cacheNames = "car" ,key = "{#root.args[0]}")
	public Car getCarDetails(String name) {
		log.info("<--------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@---Getting "+name + "  details from DataBase---------->");

		Car car =carRepository.findByName(name);
		log.info("<--------@@@@@@@@@@@@@@@@@@@@@@@@@@@@------>"+car);
		return car;
	}

	

}
