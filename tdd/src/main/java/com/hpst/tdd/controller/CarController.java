/**
 * 
 */
package com.hpst.tdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hpst.tdd.Car;
import com.hpst.tdd.exception.CarNotFoundException;
import com.hpst.tdd.service.CarService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author harpal singh
 *
 */

@RestController
@Slf4j
public class CarController {
	
	@Autowired
	CarService carService;
	
	@GetMapping("/cars/{name}")
	
	public Car getCarDetails(@PathVariable String name) {
		log.info("Inside Controller , name is "+name);
		return carService.getCarDetails(name);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void carNotFoundHandler(CarNotFoundException e) {}
	
	 
	
}
