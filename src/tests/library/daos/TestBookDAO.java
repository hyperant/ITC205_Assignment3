package tests.library.daos;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import library.daos.BookDAO;
import library.daos.LoanDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.daos.ILoanDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBookDAO {
	private IBookHelper helper;

	@Before
	public void setUp() throws Exception {
		this.helper =mock(IBookHelper.class);
	}

	@After
	public void tearDown() throws Exception {
		this.helper =null;
	}
	
	@Test
	public void testConstructor() {
		BookDAO bookDAO =new BookDAO(this.helper);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorHelperNull() {
		BookDAO bookDAO =new BookDAO(null);
	}

	/*
	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBookByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testListBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByAuthorTitle() {
		fail("Not yet implemented");
	}
*/
}
