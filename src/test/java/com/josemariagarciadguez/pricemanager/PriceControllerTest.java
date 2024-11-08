package com.josemariagarciadguez.pricemanager;


import com.josemariagarciadguez.pricemanager.domain.Price;
import com.josemariagarciadguez.pricemanager.infrastructure.repository.PriceRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PriceRepository priceRepository;

	@Test
	public void testDatabaseInitialization() {
		List<Price> prices = priceRepository.findAll();
		System.out.println("Datos de prueba en la base de datos: " + prices);
		assertFalse("Los datos de prueba no fueron cargados en la base de datos.", prices.isEmpty());
		assertEquals("Verificado numero de precios (esperable)", 4L, prices.size());
	}


	@Test
	public void testGetPriceAt14thJune10AM() throws Exception {
		mockMvc.perform(get("/api/prices/get-price")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-14T10:00:00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(1))
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	public void testGetPriceAt14thJune16PM() throws Exception {
		mockMvc.perform(get("/api/prices/get-price")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-14T16:00:00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(2))
				.andExpect(jsonPath("$.price").value(25.45));
	}

	@Test
	public void testGetPriceAt14thJune21PM() throws Exception {
		mockMvc.perform(get("/api/prices/get-price")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-14T21:00:00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(1))
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	public void testGetPriceAt15thJune10AM() throws Exception {
		mockMvc.perform(get("/api/prices/get-price")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-15T10:00:00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(3))
				.andExpect(jsonPath("$.price").value(30.50));
	}

	@Test
	public void testGetPriceAt16thJune21PM() throws Exception {
		mockMvc.perform(get("/api/prices/get-price")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-16T21:00:00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.priceList").value(4))
				.andExpect(jsonPath("$.price").value(38.95));
	}
}
