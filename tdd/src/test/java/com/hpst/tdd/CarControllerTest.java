/**
 * 
 */
package com.hpst.tdd;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hpst.tdd.controller.CarController;
import com.hpst.tdd.exception.CarNotFoundException;
import com.hpst.tdd.service.CarService;

/**
 * @author harpal singh
 *
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)

public class CarControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CarService carService;
	
	@Test
	public void getCars_returnsCarDetails() throws Exception {
		//given(for a given method call on mock object) // it should return a new Car 
		given(carService.getCarDetails(anyString())).willReturn(new Car(1,"farari","hybrid"));
		mockMvc.perform(MockMvcRequestBuilders.get("/cars/farari"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name").value("farari"))
				.andExpect(jsonPath("type").value("hybrid"));
	}
	
	@Test
	public void getCar_notFound() throws Exception {
		given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cars/farari"))
			.andExpect(status().isNotFound());
		
	}
}
