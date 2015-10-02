package tests.library.entities;

import static org.junit.Assert.*;
import library.entities.Book;
import library.interfaces.entities.IBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBook {
	private String author;
	private String title;
	private String callNumber;
	private int bookID;

	@Before
	public void setUp() throws Exception {
		this.author ="Henry Fred";
		this.title ="Programming 101";
		this.callNumber ="PRO 101";
		this.bookID =1;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		IBook book =new Book(this.author, this.title, this.callNumber, this.bookID);
		assertTrue(book instanceof IBook);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamAuthor() {
		IBook book =new Book(null, this.title, this.callNumber, this.bookID);
		assertTrue(book instanceof IBook);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamTitle() {
		IBook book =new Book(this.author, null, this.callNumber, this.bookID);
		assertTrue(book instanceof IBook);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamCallNumber() {
		IBook book =new Book(this.author, this.title, null, this.bookID);
		assertTrue(book instanceof IBook);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamBookID() {
		IBook book =new Book(this.author, this.title, this.callNumber, 0);
		assertTrue(book instanceof IBook);
	}
	
	/*
	@Test
	public void testBorrow() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testLose() {
		fail("Not yet implemented");
	}

	@Test
	public void testRepair() {
		fail("Not yet implemented");
	}

	@Test
	public void testDispose() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCallNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}
*/
}
