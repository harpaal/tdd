package com.hpst.tdd;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.hpst.tdd.cache.CacheConfig;
import com.hpst.tdd.repository.CarRepository;
import com.hpst.tdd.service.CarService;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CarServiceCacheTest{
	
		
	
		@Autowired
		CarService carService;
 
		
		@Autowired
		CarRepository carRepository;
		
		 @Configuration
		 @Import(CacheConfig.class)
		    static class SpringConfig{
		        @Bean
		        public CarService customerService(){
		            return new CarService(customerRepository());
		        }
		        @Bean
		        public CarRepository customerRepository(){
		            return Mockito.mock(CarRepository.class);
		        }
		    }

		
		@BeforeEach
		public void init() throws Exception {
		      MockitoAnnotations.initMocks(this);
		}
		
		@Test
		
		public void test_carCache() throws Exception {
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
