package tests.library.entities;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import library.daos.LoanDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.ILoanHelper;

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
	
/*
	@Test
	public void testCreateLoan() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testCommitLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLoanByID() {
		fail("Not yet implemented");
	}

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
