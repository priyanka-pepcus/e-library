package com.javatechie.crud.example.RepositoryTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;

import com.pepcus.crud.controller.ProductController;
import com.pepcus.crud.controller.UserController;
import com.pepcus.crud.entity.User;
import com.pepcus.crud.repository.UserRepository;
import com.pepcus.crud.service.ProductService;
import com.pepcus.crud.service.UserService;

@SpringBootTest
public class UserRepoTest {
	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Test
	public void testAddUser() {
		User user = new User();
		user.setName("Arun");
		user.setBook(null);
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);
		User response = userController.saveUser(user);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getName(), user.getName());
	}

}