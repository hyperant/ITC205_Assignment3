package tests.library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

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
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
	}

	@Test
	public void testGetBookByID() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual Test to make sure we can get the book by ID
		IBook bookByID =this.bookDAO.getBookByID(this.bookID);
		assertTrue(bookByID instanceof IBook);
		assertEquals(book, bookByID);
	}
	
	@Test
	public void testGetBookByIDNotExist() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual Test to make sure we do not get the book
		IBook bookByID =this.bookDAO.getBookByID(10);
		assertEquals(bookByID, null);
	}

	@Test
	public void testListBooks() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual Test to make sure we do not get the book
		List<IBook> bookList =this.bookDAO.listBooks();
		assertEquals(1, bookList.size());
	}

	@Test
	public void testFindBooksByAuthor() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getAuthor()).thenReturn(this.author);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByAuthor(this.author);
		verify(mockBook).getAuthor();
		assertEquals(1, bookList.size());
	}
	
	@Test
	public void testFindBooksByAuthorNonExistentAuthor() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getAuthor()).thenReturn(this.author);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByAuthor("i do not exist");
		verify(mockBook).getAuthor();
		assertEquals(0, bookList.size());
	}

	@Test
	public void testFindBooksByTitle() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getTitle()).thenReturn(this.title);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByTitle(this.title);
		verify(mockBook).getTitle();
		assertEquals(1, bookList.size());
	}
	
	@Test
	public void testFindBooksByTitleNonExistentTitle() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getTitle()).thenReturn(this.title);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByTitle("i do not exist");
		verify(mockBook).getTitle();
		assertEquals(0, bookList.size());
	}

	@Test
	public void testFindBooksByAuthorTitleMatchByTitleOnly() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getTitle()).thenReturn(this.title);
		when(mockBook.getAuthor()).thenReturn(this.author);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByAuthorTitle("i do not exist", this.title);
		verify(mockBook).getTitle();
		assertEquals(1, bookList.size());
	}
	
	@Test
	public void testFindBooksByAuthorTitleMatchByAuthorOnly() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getTitle()).thenReturn(this.title);
		when(mockBook.getAuthor()).thenReturn(this.author);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByAuthorTitle(this.author, "i do not exist");
		verify(mockBook).getTitle();
		verify(mockBook).getAuthor();
		assertEquals(1, bookList.size());
	}
	
	@Test
	public void testFindBooksByAuthorTitleMatchBoth() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getTitle()).thenReturn(this.title);
		when(mockBook.getAuthor()).thenReturn(this.author);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByAuthorTitle(this.author, this.title);
		verify(mockBook).getTitle();
		assertEquals(1, bookList.size());
	}
	
	@Test
	public void testFindBooksByAuthorTitleNoMatch() {
		//Setup
		IBook mockBook =mock(IBook.class);
		when(this.helper.makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID))).thenReturn(mockBook);
		when(mockBook.getTitle()).thenReturn(this.title);
		when(mockBook.getAuthor()).thenReturn(this.author);
		
		//Execute
		IBook book =this.bookDAO.addBook(this.author, this.title, this.callNumber);
		
		//Verify and assert
		verify(this.helper).makeBook(eq(this.author), eq(this.title), eq(this.callNumber), eq(this.bookID));
		assertEquals(book, mockBook);
		
		//Actual test
		List<IBook> bookList =this.bookDAO.findBooksByAuthorTitle("i do not exist", "i do not exist");
		verify(mockBook).getTitle();
		verify(mockBook).getAuthor();
		assertEquals(0, bookList.size());
	}
}
