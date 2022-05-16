package com.bootcamp.ApiFinal.Sales;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bootcamp.ApiFinal.Model.Sale;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesTest {
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	private MockMvc mock;
	
	@Test
	public void SELECT_SALES() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		String JsonResponse = mock.perform(
								get("/api/sales")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
							.andExpect(
									status()
									  .isOk())
							.andReturn()
							.getResponse()
							.getContentAsString();
		Sale[] sales = new ObjectMapper().readValue(JsonResponse, Sale[].class);
		assertNotNull(sales);
	}
	
	@Test
	public void INSERT_SALE() throws Exception{
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		int clientDni = 4356687;
		JSONObject rawClient = new JSONObject();
		rawClient.put("ticket", 15);
		String JsonResponse = mock.perform(
							post("/api/sales/" + clientDni)
							 .content(rawClient.toString())
							 .contentType(MediaType.APPLICATION_JSON)
							 .accept(MediaType.TEXT_PLAIN))
						.andExpect(
								status()
								  .isCreated())
						.andReturn()
						.getResponse()
						.getContentAsString();
		assertTrue(JsonResponse.equals("Created sale"));
	}
	
	@Test
	public void INSERT_SALE_FAIL() throws Exception{
		mock = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		int clientDni = 4356687;
		JSONObject rawClient = new JSONObject();
		rawClient.put("ticket", 15);
		String JsonResponse = mock.perform(
							post("/api/sales/" + clientDni)
							 .content(rawClient.toString())
							 .contentType(MediaType.APPLICATION_JSON)
							 .accept(MediaType.TEXT_PLAIN))
						.andExpect(
								status()
								  .isBadRequest())
						.andReturn()
						.getResponse()
						.getContentAsString();
		assertTrue(JsonResponse.equals("Sale already exists") || JsonResponse.equals("Incomplete data"));
		
	}
}
