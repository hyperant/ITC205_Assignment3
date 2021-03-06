package tests.library.entities;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Calendar;

import library.entities.Loan;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoan {
	private IBook book;
	private IMember member;
	private ILoan loan;
	
	private Date borrowDate;
	private Date dueDate;
	
	private Calendar cal;
	
	@Before
	public void setUp() throws Exception {
		this.book =mock(IBook.class);
		this.member =mock(IMember.class);
		
		this.cal =Calendar.getInstance();
		this.borrowDate =this.cal.getTime();
		this.cal.add(Calendar.DATE, ILoan.LOAN_PERIOD);
		this.dueDate =this.cal.getTime();
		
		this.loan =new Loan(this.book, this.member, this.borrowDate, this.dueDate);
	}

	@After
	public void tearDown() throws Exception {
		this.loan =null;
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
	
	@Test
	public void testIsCurrentPending() {
		assertFalse(this.loan.isCurrent());
	}
	
	@Test
	public void testCommit() {
		int id =1;
		
		this.loan.commit(id);
		
		verify(this.book).borrow(this.loan);
		verify(this.member).addLoan(this.loan);
		
		assertTrue(this.loan.isCurrent());
		assertEquals(id, this.loan.getID());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCommitBadParamLoanIdLessThenOrEqualToZero() {
		this.loan.commit(0);
	}
	
	@Test(expected=RuntimeException.class)
	public void testCommitWhenCurrent() {
		this.loan.commit(1);
		this.loan.commit(1);
	}
	
	@Test
	public void testComplete() {
		this.loan.commit(1);
		assertTrue(this.loan.isCurrent());
		
		this.loan.complete();
		assertFalse(this.loan.isCurrent());
	}
	
	@Test(expected=RuntimeException.class)
	public void testCompleteWhenNotCurrent() {
		this.loan.complete();
	}
	
	@Test
	public void testIsOverDue() {
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.DATE, ILoan.LOAN_PERIOD +5); //Force the current time to be 5 days in front of due date
		Date currentDate =cal.getTime(); 
		
		this.loan.commit(1);
		assertTrue(this.loan.isCurrent());
		assertTrue(this.loan.checkOverDue(currentDate));
		assertTrue(this.loan.isOverDue());
	}
	
	@Test
	public void testIsOverDueNoneOverdue() {
		assertFalse(this.loan.isOverDue());
	}
	
	@Test
	public void testIsCurrent() {
		this.loan.commit(1);
		assertTrue(this.loan.isCurrent());
	}
	
	@Test
	public void testIsCurrentNoneCurrentState() {
		assertFalse(this.loan.isCurrent());
	}

	@Test
	public void testCheckOverDue() {
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.DATE, ILoan.LOAN_PERIOD +5); //Force the current time to be 5 days in front of due date
		Date currentDate =cal.getTime(); 
		
		this.loan.commit(1);
		assertTrue(this.loan.isCurrent());
		assertTrue(this.loan.checkOverDue(currentDate));
		assertTrue(this.loan.isOverDue()); //Make sure we are overdue
	}
	
	@Test
	public void testCheckOverDueStillCurrent() {
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.DATE, ILoan.LOAN_PERIOD -5); //Force the current time to be 5 days before due date
		Date currentDate =cal.getTime();
		
		this.loan.commit(1);
		assertTrue(this.loan.isCurrent());
		assertFalse(this.loan.checkOverDue(currentDate));
		assertTrue(this.loan.isCurrent()); //Make sure we are still current
	}
	
	@Test(expected=RuntimeException.class)
	public void testCheckOverDueWhenNotCurrentOrOverdue() {
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.DATE, ILoan.LOAN_PERIOD -5); //Force the current time to be 5 days before due date
		Date currentDate =cal.getTime();
		
		assertFalse(this.loan.isCurrent());
		assertFalse(this.loan.checkOverDue(currentDate));
	}

	@Test
	public void testGetBorrower() {
		assertEquals(this.member, this.loan.getBorrower());
	}
	
	@Test
	public void testGetBorrowerDoNotMatch() {
		assertNotEquals(mock(IMember.class), this.loan.getBorrower());
	}
	
	@Test
	public void testGetBook() {
		assertEquals(this.book, this.loan.getBook());
	}
	
	@Test
	public void testGetBookDoNotMatch() {
		assertNotEquals(mock(IBook.class), this.loan.getBook());
	}
	
	@Test
	public void testGetID() {
		int id =1;
		this.loan.commit(id);
		
		assertEquals(id, this.loan.getID());
	}
	
	@Test
	public void testGetIDDoNotMatch() {
		this.loan.commit(1);
		
		assertNotEquals(4, this.loan.getID());
	}
}
