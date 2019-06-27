package com.hpst.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hpst.tdd.Car;
import com.hpst.tdd.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	CarRepository carRepository;
	
	@Cacheable("cars")
	public Car getCarDetails(String name) {
		return carRepository.findByName(name);
	}

}
