package tests.library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Date;

import library.daos.LoanHelper;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoanHelper {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeLoan() {
		ILoanHelper loanHelper =new LoanHelper();
		ILoan loan =loanHelper.makeLoan(mock(IBook.class), mock(IMember.class), mock(Date.class), mock(Date.class));
		
		assertTrue(loan instanceof ILoan);
		assertEquals(loan.getID(), 0);
	}

}
