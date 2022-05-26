package com.javatechie.crud.example.RepositoryTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;

import com.pepcus.crud.entity.Product;
import com.pepcus.crud.repository.ProductRepository;
import com.pepcus.crud.service.ProductService;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ProductRepoTest {
	@InjectMocks
	  private ProductService service;
	
	@Mock
	ProductRepository dao;

//	  
//	  @Test
//	  @Rollback(false)
//	  public void testCreateProduct() {
//	      Product savedProduct = repo.save(new Product(1, "iPhone 10", 10, 1000.00));
//	       
//	      assertThat(savedProduct.getId()).isGreaterThan(0);
//	  }
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	  
//	  @Test
//		public void createProductTest()
//		{
//			Product product = new Product(1, "iPhone 10", 10, 1000.00);
//
//			service.saveProduct(product);
//		
//
//			verify(dao, times(1)).addProduct(product);
//		}
	

	@Test
	public void findAllProductsTest()
	{
		List<Product> list = new ArrayList<Product>();
		Product proOne = new Product(1, "iPhone 10", 10, 1000.00);
		Product proTwo = new Product(2, "iPhone 11", 111, 1000.00);
		Product proThree = new Product(3, "iPhone 12", 112, 1000.00);

		list.add(proOne);
		list.add(proTwo);
		list.add(proThree);

		when(dao.findAll()).thenReturn(list);

		//test
		List<Product> empList = service.getProducts();

		assertEquals(3, empList.size());
		verify(dao, times(1)).findAll();
	}

}
