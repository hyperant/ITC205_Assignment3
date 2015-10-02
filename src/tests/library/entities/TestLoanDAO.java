package tests.library.entities;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Date;

import library.daos.LoanDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoanDAO {
	private ILoanDAO loanDAO;
	private ILoanHelper helper;

	@Before
	public void setUp() throws Exception {
		this.helper =mock(ILoanHelper.class);
		this.loanDAO =new LoanDAO(this.helper);
	}

	@After
	public void tearDown() throws Exception {
		this.loanDAO =null;
		this.helper =null;
	}
	
	@Test
	public void testConstructor() {
		LoanDAO loanDAO =new LoanDAO(this.helper);
		assertTrue(loanDAO instanceof ILoanDAO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorHelperNull() {
		LoanDAO loanDAO =new LoanDAO(null);
	}

	@Test
	public void testCreateLoan() {
		//Setup requerd classes
		IMember member =mock(IMember.class);
		IBook book =mock(IBook.class);
		ILoan mockLoan =mock(ILoan.class);
		
		when(this.helper.makeLoan(eq(book), eq(member), any(Date.class), any(Date.class))).thenReturn(mockLoan);

		//execute the function we want to test
		ILoan loan =this.loanDAO.createLoan(member, book);
		
		//Check to make sure everything is correct
		verify(this.helper).makeLoan(eq(book), eq(member), any(Date.class), any(Date.class));
		assertEquals(mockLoan, loan);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateLoanBadParamMember() {
		IBook book =mock(IBook.class);
		ILoan loan =this.loanDAO.createLoan(null, book);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateLoanBadParamBook() {
		IMember member =mock(IMember.class);
		ILoan loan =this.loanDAO.createLoan(member, null);
	}

	
	@Test
	public void testCommitLoan() {
		//Setup
		ILoan loan =mock(ILoan.class);
		
		//Execute
		this.loanDAO.commitLoan(loan);
		int loanID =loan.getID();
		
		//Verify and assert
		verify(loan).commit(loanID);
		assertEquals(loan, this.loanDAO.getLoanByID(loanID));
	}
	
	@Test
	public void testGetLoanByID() {
		//Setup
		ILoan loan =mock(ILoan.class);
		
		//Execute
		this.loanDAO.commitLoan(loan);
		int loanID =loan.getID();
		
		//Verify and assert
		verify(loan).commit(loanID);
		assertEquals(loan, this.loanDAO.getLoanByID(loanID));
	}
	
	@Test
	public void testGetLoanByIDNoMatch() {
		//Setup
		ILoan loan =mock(ILoan.class);
		
		//Execute
		this.loanDAO.commitLoan(loan);
		int loanID =loan.getID();
		
		//Verify and assert
		verify(loan).commit(loanID);
		assertNull(this.loanDAO.getLoanByID(loanID +10)); //Because the loanID is the last loan commited we can assume +10 will be invalid
	}
	
	/*

	@Test
	public void testGetLoanByBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testListLoans() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindLoansByBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindLoansByBookTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateOverDueStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOverDueLoans() {
		fail("Not yet implemented");
	}
*/
}
