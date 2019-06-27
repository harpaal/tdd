package com.hpst.tdd;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hpst.tdd.repository.CarRepository;
import com.hpst.tdd.service.CarService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarCacheTest {

		@Autowired
		CarService carService;

		@MockBean
		CarRepository carRepository;
		
		@Test
		public void test_carCache() {
			//act
			carService.getCarDetails("farari");
			carService.getCarDetails("farari");
			//assertion
			verify(carRepository ,times(1)).findByName("farari");
		}
	
	
}
