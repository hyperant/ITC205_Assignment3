package tests.library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import library.daos.BookDAO;
import library.daos.LoanDAO;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.entities.IBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBookDAO {
	private IBookHelper helper;
	private IBookDAO bookDAO;
	
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
		
		this.helper =mock(IBookHelper.class);
		this.bookDAO =new BookDAO(this.helper);
	}

	@After
	public void tearDown() throws Exception {
		this.helper =null;
		this.bookDAO =null;
	}
	
	@Test
	public void testConstructor() {
		BookDAO bookDAO =new BookDAO(this.helper);
		assertTrue(bookDAO instanceof IBookDAO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorHelperNull() {
		BookDAO bookDAO =new BookDAO(null);
	}

	@Test
	public void testAddBook() {
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
	}

	/*
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
