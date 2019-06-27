/**
 * 
 */
package com.hpst.tdd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hpst.tdd.Car;

/**
 * @author harpal singh
 *
 */
@Repository
public interface CarRepository extends CrudRepository<Car,Integer> {
	
	public Car findByName(String name);
}
