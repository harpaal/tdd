package com.hpst.tdd;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hpst.tdd.repository.CarRepository;
import com.hpst.tdd.service.CarService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

	@Mock
	CarRepository carRepository;
	
	@Mock
	CarService carService;
	/**
	 * 
	 * No need to test jpa crud repository methods, this is just to show you to test crud repository interactions
	 * incase you have to test any custom methods
	 */
	@Test
	public void test_FindCar() {
		
		//arrange 
		Car inputCar = mock(Car.class);
		//for given condition , it should always return inputCar
		given(carRepository.findByName(anyString())).willReturn(inputCar);
	
		//act 
		//call method on mocked object
		Car expectedCar = carRepository.findByName("farari");
		
		//verify behavior of mocked object post act
		//for example check if mockedObject carRepository have been called twice 
		verify(carRepository , times(1)).findByName("farari");
		verify(carRepository , never()).findAll();
		
		assertEquals(expectedCar , inputCar);
		
	}
}
