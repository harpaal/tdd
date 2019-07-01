package com.hpst.tdd.integration.container.es;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.hpst.tdd.Car;
import com.hpst.tdd.exception.DuplicateCarException;
import com.hpst.tdd.service.CarService;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultCarServiceIT {
  
  @Autowired
  private CarService CarService;

  @Autowired
  ElasticsearchTemplate template;

  @Container
  private static ElasticsearchContainer elasticsearchContainer = new CarElasticsearchContainer();

  @BeforeAll
  static void setUp() {
      elasticsearchContainer.start();
  }

  @BeforeEach
  void testIsContainerRunning() {
      assertTrue(elasticsearchContainer.isRunning());
      recreateIndex();
  }


  @Test
  void testCreateCar() throws DuplicateCarException {
      Car createdCar = CarService.create(createCar(1, "farari", "hybrid"));
      assertNotNull(createdCar);
      assertNotNull(createdCar.getId());
      assertEquals("farari", createdCar.getName());
      assertEquals("hybrid", createdCar.getType());
      
  }
  
  private Car createCar(int id, String carName, String carType) {
        Car car = new Car();
        car.setId(id);
        car.setName(carName);
        car.setType(carType);
        return car;
  }

  private void recreateIndex() {
      if (template.indexExists(Car.class)) {
          template.deleteIndex(Car.class);
          template.createIndex(Car.class);
      }
  }
  
  @AfterAll
  static void destroy() {
      elasticsearchContainer.stop();
  }
}