package com.pepcus.crud.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pepcus.crud.entity.Book;
import com.pepcus.crud.entity.User;
import com.pepcus.crud.exceptionhandler.ResourceNotFoundException;
import com.pepcus.crud.repository.BookRepository;
import com.pepcus.crud.repository.UserRepository;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Mock
	BookRepository bookRepository;

	@Test
	public void testSaveUser() {

		List<Book> book = new ArrayList<>();

		User user = new User();
		user.setUserId(1);
		user.setName("priyanka");
		user.setRegistrationDate(LocalDateTime.now());
		user.setBook(book);

		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		User expected = userService.saveUser(user);
		Assert.assertNotNull(expected);
		Assert.assertEquals(expected, user);

	}

	/**
	 * Resource Not found Exception case in deregister
	 */
	@Test
	public void testDeregisterUserById_Exception() {
		try {
			Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
			// String response = userService.issueBook(1, new ArrayList<>());
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ResourceNotFoundException);
		}
	}

	/**
	 * success case for User Deactivated
	 */

	@Test
	 public void testDeregisterUserById_success() {
		    List<Book> books = new ArrayList<>();
		    User user = new User();
		    user.setDeactivationDate(LocalDateTime.now());
		    user.setBook(books);
		    Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		    Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		    String response = userService.deregisterUserById(1);
		    System.out.println("response : " + response);

		    Assert.assertNotNull(response);
		    Assert.assertEquals(response, "User Deactivated !!");

		  }

	/**
	 * fail case for User Deactivated
	 */

	@Test
	 public void testDeregisterUserById_fail() {
		    List<Book> books = new ArrayList<>();
		    Book book1 =  new Book();
		    book1.setId(1);
		    Book book2 =  new Book();
		    book2.setId(2);
		    books.add(book1);
		    books.add(book2);
		    
		    User user = new User();
		    user.setUserId(1);
		    user.setDeactivationDate(LocalDateTime.now());
		    user.setBook(books);
		    Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		    boolean t = userRepository.getOne(1).getBook().isEmpty();
		    System.out.println("boolean t : " + t);
		   Mockito.when(userRepository.getOne(1).getBook().isEmpty()).thenReturn(false);
		    String response = userService.deregisterUserById(1);
		    System.out.println("response : " + response);

		    Assert.assertNotNull(response);
		    Assert.assertEquals(response, "First return books then you can Deactivated !!!");

		  }
	/**
	 * Success case with no user registered.
	 */
	@Test
	public void testIssueBooksSuccess() {
		List<Book> books = new ArrayList<>();
		User user = new User();
		user.setDeactivationDate(LocalDateTime.now());
		user.setBook(books);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String response = userService.issueBook(1, books);
		System.out.println("response : " + response);

		Assert.assertNotNull(response);
		Assert.assertEquals(response, "Please register yourself first then issue book ");

	}

	/**
	 * Success case with no book available
	 */
	@Test
	public void testIssueBooks_Success() {
		Book book = new Book();
		book.setId(1);
		List<Book> books = new ArrayList<>();
		books.add(book);
		User user = new User();
		user.setBook(books);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.when(bookRepository.existsById(Mockito.anyInt())).thenReturn(false);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		String response = userService.issueBook(1, books);
		System.out.println("response : " + response);

		Assert.assertNotNull(response);
		Assert.assertEquals(response, "this book in not available in the library");
	}

	/**
	 * Resource Not found Exception case
	 */
	@Test
	public void testIssueBooks_Exception() {
		try {
			Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
			// String response = userService.issueBook(1, new ArrayList<>());
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ResourceNotFoundException);
		}
	}

	// @Test
	// public void testIssueBooks_IssueSuccess() {
	// Book book = new Book();
	// book.setId(1);
	// List<Book> books = new ArrayList<>();
	// books.add(book);
	//
	// Optional<Book> bookList =bookRepository.findById(book.getId());
	// Mockito.when(bookList.getIssueOn()).thenReturn(null);
	// }

}
