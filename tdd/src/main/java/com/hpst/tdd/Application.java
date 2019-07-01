package com.hpst.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.hpst.tdd.repository.CarRepository;

/**
 * @author harpal singh
 *
 */
/**
 * @param carRepository
 * @return
 */
@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner dummyCLR(CarRepository carRepository) {
		return args -> {
			addCars(carRepository);
			carRepository.findAll().forEach(System.out::println);
		};
	}

	private void addCars(CarRepository carRepository) {
		
		  List<Car> carList = new ArrayList<>(Arrays.asList(
                  new Car(1, "farari", "hybrid"),
                  new Car(2, "porche", "sports"),
                  new Car(3, "rolls royals", "royal"),
                  new Car(4, "ducati", "luxury")
                  ));
		  	carList.forEach(car->carRepository.save(car));
	}
	
}
