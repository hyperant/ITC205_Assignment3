package tests.library.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import library.entities.Book;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBook {
	private String author;
	private String title;
	private String callNumber;
	private int bookID;
	
	private IBook book;

	@Before
	public void setUp() throws Exception {
		this.author ="Henry Fred";
		this.title ="Programming 101";
		this.callNumber ="PRO 101";
		this.bookID =1;
		
		this.book =new Book(this.author, this.title, this.callNumber, this.bookID);
	}

	@After
	public void tearDown() throws Exception {
		this.book =null;
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
	
	
	@Test
	public void testBorrow() {
		ILoan loan =mock(ILoan.class);
		
		this.book.borrow(loan);
		assertEquals(this.book.getState(), EBookState.ON_LOAN);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBorrowBadParamLoan() {
		this.book.borrow(null);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBorrowAllreadyOnLoan() {
		ILoan loan =mock(ILoan.class);
		
		this.book.borrow(loan);
		assertEquals(this.book.getState(), EBookState.ON_LOAN);
		
		this.book.borrow(loan);
	}
	

	@Test
	public void testGetLoan() {
		ILoan loan =mock(ILoan.class);
		
		this.book.borrow(loan);
		assertEquals(this.book.getState(), EBookState.ON_LOAN);
		
		ILoan mockLoan =this.book.getLoan();
		
		assertEquals(loan, mockLoan);
	}
	
	@Test
	public void testGetLoanBookNotOnLoan() {		
		ILoan mockLoan =this.book.getLoan();
		assertEquals(mockLoan, null);
	}
	

	@Test
	public void testReturnBookDamaged() {
		ILoan loan =mock(ILoan.class);
		
		this.book.borrow(loan);
		assertEquals(this.book.getState(), EBookState.ON_LOAN);
		
		this.book.returnBook(true);
		assertEquals(this.book.getState(), EBookState.DAMAGED);
	}
	
	@Test
	public void testReturnBookNotDamaged() {
		ILoan loan =mock(ILoan.class);
		
		this.book.borrow(loan);
		assertEquals(this.book.getState(), EBookState.ON_LOAN);
		
		this.book.returnBook(false);
		assertEquals(this.book.getState(), EBookState.AVAILABLE);
	}
	
	@Test(expected=RuntimeException.class)
	public void testReturnBookNotOnLoan() {		
		this.book.returnBook(true);
	}
	
	@Test
	public void testLose() {
		ILoan loan =mock(ILoan.class);
		
		this.book.borrow(loan);
		assertEquals(this.book.getState(), EBookState.ON_LOAN);
		
		this.book.lose();
		assertEquals(this.book.getState(), EBookState.LOST);
	}
	
	@Test(expected=RuntimeException.class)
	public void testLoseNotOnLoan() {		
		this.book.lose();
	}
	
	@Test
	public void testRepair() {
		ILoan loan =mock(ILoan.class);
		
		this.book.borrow(loan);
		assertEquals(this.book.getState(), EBookState.ON_LOAN);
		
		this.book.returnBook(true);
		assertEquals(this.book.getState(), EBookState.DAMAGED);
		
		this.book.repair();
		assertEquals(this.book.getState(), EBookState.AVAILABLE);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRepairNotDamaged() {		
		this.book.repair();
	}	

	/*
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
