package com.hpst.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.hpst.tdd.repository.CarRepository;
import com.hpst.tdd.repository.es.CarEsService;
import com.hpst.tdd.service.CarService;

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
	CommandLineRunner dummyCLR(CarRepository carRepository , CarService carService, CarEsService carEsService) {
		return args -> {
			addCars(carRepository);
			System.out.println("1111111111111111111111111111");
			carService.getCarDetails("farari");
			carService.getCarDetails("farari");
			System.out.println("1111111111111111111111111111");
			carEsService.create(new Car(11,"farari1","hybrid1"));
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
