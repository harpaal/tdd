package com.hpst.tdd;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hpst.tdd.repository.CarRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

	@Mock
	CarRepository carRepository;
	
	/**
	 * 
	 * No need to test jpa crud repository methods, this is just to show you to test crud repository interactions
	 * incase you have to test any custom methods
	 */
	@Test
	public void test_FindCar() {
		
		//arrange 
		given(carRepository.findByName(anyString())).willReturn(new Car(1,"farari","hybrid"));
		
     	//act
		carRepository.findByName("farari");
		
		//assertion
		then(carRepository).should().findByName("farari");
	
	
	}
}
