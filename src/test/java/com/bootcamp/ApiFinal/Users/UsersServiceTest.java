package com.bootcamp.ApiFinal.Users;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.ApiFinal.Model.MyUser;
import com.bootcamp.ApiFinal.Repository.UsersRepository;
import com.bootcamp.ApiFinal.Services.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

		@InjectMocks
		private UsersService service;
		
		@Mock
		private PasswordEncoder passwordEncoder;
		
		@Mock
		private UsersRepository repositoryMock;
		
		private MyUser user = new MyUser(1, "ariel@algo.com", "ariel", "Santangelo", "1234");
		
		@Test
		public void GET_ALL() {
			when(repositoryMock.findAll()).thenReturn(new ArrayList<MyUser>());
			List<MyUser> users = service.getAll();
			assertNotNull(users);
			assertEquals(users, new ArrayList<MyUser>());
		}
		
		@Test
		public void SAVE_USER() {
			when(repositoryMock.existsByEmail(user.getEmail())).thenReturn(false);
			when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
			when(repositoryMock.save(user)).thenReturn(user);
			boolean result = service.save(user);
			assertTrue(result);
		}
		
		@Test
		public void SAVE_USER_FAIL() {
			when(repositoryMock.existsByEmail(user.getEmail())).thenReturn(true);
			boolean result = service.save(user);
			assertFalse(result);
		}
		
		@Test
		public void UPDATE_USER() {
			when(repositoryMock.existsById(user.getId())).thenReturn(true);
			when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
			when(repositoryMock.save(user)).thenReturn(user);
			boolean result = service.update(user);
			assertTrue(result);
		}
		
		@Test
		public void UPDATE_USER_FAIL() {
			when(repositoryMock.existsById(user.getId())).thenReturn(false);
			boolean result = service.update(user);
			assertFalse(result);
		}
		
		@Test
		public void DELETE_USER() {
			when(repositoryMock.existsById(user.getId())).thenReturn(true);
			boolean result = service.delete(user.getId());
			assertTrue(result);
		}
		
		@Test
		public void DELETE_USER_FAIL() {
			when(repositoryMock.existsById(user.getId())).thenReturn(false);
			boolean result = service.delete(user.getId());
			assertFalse(result);
		}
}
