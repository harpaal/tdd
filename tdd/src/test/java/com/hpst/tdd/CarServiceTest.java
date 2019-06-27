/**
 * 
 */
package com.hpst.tdd;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.hpst.tdd.service.CarService;

/**
 * @author harpal singh @link {@link CarService}
 *
 */

@RunWith(SpringRunner.class)
public class CarServiceTest {
	
   @Mock
   CarService carService;
   
  
   @Test
   public void test_getCarDetails() {
	   carService.getCarDetails("farari");
	   verify(carService, times(1)).getCarDetails("farari");
   }
	
}
