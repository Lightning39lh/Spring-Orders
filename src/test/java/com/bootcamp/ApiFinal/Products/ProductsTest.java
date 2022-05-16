package com.bootcamp.ApiFinal.Products;

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
import com.bootcamp.ApiFinal.Model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsTest {
	
	
	@Autowired
	private WebApplicationContext aplicationContext;
	
	private MockMvc mock;
	
	@Test
	void SAVE_PRODUCT_ALREADY_EXISTS() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(aplicationContext).build();
		JSONObject rawProduct = new JSONObject();
		rawProduct.put("name", "Apple");
		rawProduct.put("price", 7.65);
		rawProduct.put("code", 5676);
		rawProduct.put("stock", 8);
		String JsonResponse = mock.perform(post("/api/products")
				.content(rawProduct.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN))
				.andExpect(
						status()
						.isBadRequest())
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertTrue(JsonResponse.equals("The product already exists"));
	}
	
	@Test
	void SAVE_PRODUCT_INCOMPLETE_DATA() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(aplicationContext).build();
		JSONObject rawProduct = new JSONObject();
		rawProduct.put("name", "Apple");
		rawProduct.put("code", 5676);
		rawProduct.put("stock", 8);
		String JsonResponse = mock.perform(post("/api/products")
				.content(rawProduct.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN))
				.andExpect(
						status()
						.isBadRequest())
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertTrue(JsonResponse.equals("Incomplete data"));
	}
	
	@Test
	void SAVE_PRODUCT() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(aplicationContext).build();
		JSONObject rawProduct = new JSONObject();
		rawProduct.put("name", "Apple");
		rawProduct.put("price", 7.65);
		rawProduct.put("code", 5676);
		rawProduct.put("stock", 8);
		String JsonResponse = mock.perform(post("/api/products")
				.content(rawProduct.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN))
				.andExpect(
					status()
					.isCreated())
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertTrue(JsonResponse.equals("Created product"));
	}
	
	
	@Test
	public void SELECT_PRODUCTS() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(aplicationContext).build();
		String JsonResponse = mock.perform(get("/api/products")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(
					status()
					.isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		Product[] products = new ObjectMapper().readValue(JsonResponse, Product[].class);
		assertNotNull(products);
		System.out.println(JsonPath.parse(JsonResponse));
	}
	
}
