package com.hpst.tdd;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hpst.tdd.repository.CarRepository;
import com.hpst.tdd.service.CarService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@AutoConfigureCache
public class CarServiceCacheTest {
	
		@InjectMocks
		CarService carService;

		@MockBean
		CarRepository carRepository;
		
		@BeforeEach
		public void init() throws Exception {
		      MockitoAnnotations.initMocks(this);
		}
		
		@Test
		
		public void test_carCache() {
			//arrange
			given(carRepository.findByName(anyString()))
			.willReturn(new Car(1,"farari","hybrid"));
			//act
			carService.getCarDetails("farari");
			carService.getCarDetails("farari");
			//assertion
			verify(carRepository,times(1)).findByName("farari");
			
		}
		
		
		  

}
