package com.bootcamp.ApiFinal.Products;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.bootcamp.ApiFinal.Model.Product;
import com.bootcamp.ApiFinal.Repository.ProductsRepository;
import com.bootcamp.ApiFinal.Services.ProductsService;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsServiceTest {
 
	@InjectMocks
	private ProductsService service;
	
	@Mock
	private ProductsRepository mockRepository;
	
	private Product product = new Product(3234, 34, "Banana", 12.5);
	
	@Test
	public void SELECT_CLIENTS() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<Product>());
		List<Product> products = service.getAll();
		assertNotNull(products);
	}
	
	@Test
	public void SAVE_PRODUCT() {
		when(mockRepository.existsByCodeAndState(product.getCode(), true)).thenReturn(false);
		when(mockRepository.save(product)).thenReturn(product);
		boolean result = service.save(product);
		assertTrue(result);
	}
	
	@Test
	public void SAVE_PRODUCT_FAIL() {
		when(mockRepository.existsByCodeAndState(product.getCode(), true)).thenReturn(true); 
		boolean result = service.save(product);
		assertFalse(result);
	}
	
	@Test
	public void UPDATE_PRODUCT() {
		when(mockRepository.existsByIdAndState(product.getId(), true)).thenReturn(true);
		when(mockRepository.save(product)).thenReturn(product);
		boolean result = service.update(product);
		assertTrue(result);
	}
	
	@Test
	public void UPDATE_PRODUCT_FAIL() {
		when(mockRepository.existsByIdAndState(product.getId(), true)).thenReturn(false);
		boolean result = service.update(product);
		assertFalse(result);
	}
	
	@Test
	public void DELETE_PRODUCT() {
		when(mockRepository.existsByIdAndState(product.getId(), true)).thenReturn(true);
		boolean result = service.delete(product.getId());
		assertTrue(result);
	}
	
	@Test
	public void DELETE_PRODUCT_FAIL() {
		when(mockRepository.existsByIdAndState(product.getId(), true)).thenReturn(false);
		boolean result = service.delete(product.getId());
		assertFalse(result);
	}
}