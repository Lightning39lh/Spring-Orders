package com.bootcamp.ApiFinal.Users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.bootcamp.ApiFinal.Model.MyUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersTest {
	
	@Autowired
	private WebApplicationContext aplicationContext;
	
	private MockMvc mock;

	@Test
	public void SELECT_USERS() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(aplicationContext).build();
		String responseJson = mock.perform(
							get("/api/users")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andReturn()
						.getResponse()
						.getContentAsString();
		MyUser[] users = new ObjectMapper().readValue(responseJson, MyUser[].class);
		assertNotNull(users);
	}
	
	@Test
	public void UPDATE_USER() throws Exception{
		mock = MockMvcBuilders.webAppContextSetup(aplicationContext).build();
		JSONObject rawUser = new JSONObject();
		rawUser.put("id", 1);
		rawUser.put("name", "Ariel");
		rawUser.put("last_name", "Santangelo");
		rawUser.put("email", "ariel@algo.com");
		rawUser.put("password", "1234");
		String JsonResponse = mock.perform(
							put("/api/users")
							.content(rawUser.toString())
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isOk())
					.andReturn()
					.getResponse()
					.getContentAsString();
		assertTrue(JsonResponse.equals("Updated user"));
	}
	
	@Test
	public void UPDATE_USER_FAIL() throws Exception {
		mock = MockMvcBuilders.webAppContextSetup(aplicationContext).build();
		JSONObject rawUser = new JSONObject();
		rawUser.put("name", "Ariel");
		rawUser.put("last_name", "Santangelo");
		rawUser.put("email", "ariel@algo.com");
		rawUser.put("password", "1234");
		String JsonResponse = mock.perform(
							put("/api/users")
							.content(rawUser.toString())
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isBadRequest())
					.andReturn()
					.getResponse()
					.getContentAsString();
		assertTrue(JsonResponse.equals("The user doesn't exists") || JsonResponse.equals("Incomplete data"));
	}
}
