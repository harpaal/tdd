package com.hpst.tdd.repository.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpst.tdd.Car;
import com.hpst.tdd.exception.DuplicateCarException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
@AllArgsConstructor
public class CarEsService {

	@Autowired
	CarElasticSearchRepository carEsRepository;
	
	public Car create(Car car) throws DuplicateCarException{
		System.out.println("@@@@@@@@@@@@@@@@@@@CarESSERVICE @@@@@@"+car);
		Car savedCar = carEsRepository.save(car);
		System.out.println("@@@@@@@@@@@@@@@@@@@CarESSERVICE   savedCar@@@@@"+ savedCar);
		return savedCar;
	}
}
