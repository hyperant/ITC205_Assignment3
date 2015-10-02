package tests.library.entities;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Calendar;

import library.entities.Loan;
import library.entities.BookStub;
import library.entities.MemberStub;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoan {
	private IBook book;
	private IMember member;
	
	private Date borrowDate;
	private Date dueDate;
	
	private Calendar cal;
	
	@Before
	public void setUp() throws Exception {
		this.book =new BookStub();
		this.member =new MemberStub();
		
		this.cal =Calendar.getInstance();
		this.borrowDate =this.cal.getTime();
		this.cal.add(Calendar.DATE, ILoan.LOAN_PERIOD);
		this.dueDate =this.cal.getTime();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		ILoan loan =new Loan(this.book, this.member, this.borrowDate, this.dueDate);
		
		assertTrue(loan instanceof Loan);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParemBookNUll() {
		ILoan loan =new Loan(null, this.member, this.borrowDate, this.dueDate);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParemMemberNUll() {
		ILoan loan =new Loan(this.book, null, this.borrowDate, this.dueDate);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParemBorrowDateNUll() {
		ILoan loan =new Loan(this.book, this.member, null, this.dueDate);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParemDueDateNUll() {
		ILoan loan =new Loan(this.book, this.member, this.borrowDate, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParemDueDateLessThenBorrowDate() {
		ILoan loan =new Loan(this.book, this.member, this.dueDate, this.borrowDate);
	}
	
	@Test
	public void testCreateBadParemDueDateEqualsBorrowDate() {
		ILoan loan =new Loan(this.book, this.member, this.borrowDate, this.borrowDate);
		
		assertTrue(loan instanceof Loan);
	}
	
	/*
	@Test
	public void testCommit() {
		fail("Not yet implemented");
	}

	@Test
	public void testComplete() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOverDue() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsCurrent() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckOverDue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}
*/
}
