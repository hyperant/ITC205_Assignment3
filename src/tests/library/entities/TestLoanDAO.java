package tests.library.entities;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		
		//Verify and assert
		verify(loan).commit(1);
		assertEquals(loan, this.loanDAO.getLoanByID(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCommitLoanBadParamLoan() {
		this.loanDAO.commitLoan(null);
	}
	
	@Test
	public void testGetLoanByID() {
		//Setup
		ILoan loan =mock(ILoan.class);
		
		//Execute
		this.loanDAO.commitLoan(loan);
		
		//Verify and assert
		verify(loan).commit(1);
		assertEquals(loan, this.loanDAO.getLoanByID(1));
	}
	
	@Test
	public void testGetLoanByIDNoMatch() {
		//Setup
		ILoan loan =mock(ILoan.class);
		
		//Execute
		this.loanDAO.commitLoan(loan);
		
		//Verify and assert
		verify(loan).commit(1);
		assertNull(this.loanDAO.getLoanByID(10));
	}
	
	@Test
	public void testGetLoanByBook() {
		//Setup
		ILoan loan =mock(ILoan.class);
		IBook book =mock(IBook.class);
		
		when(loan.getBook()).thenReturn(book);
		
		//Execute
		this.loanDAO.commitLoan(loan);
		
		//Verify and assert
		verify(loan).commit(1);
		ILoan actualLoan =this.loanDAO.getLoanByBook(book);
		verify(loan).getBook();
		assertEquals(loan, actualLoan);
	}
	
	@Test
	public void testGetLoanByBookNoBookMatch() {
		//Setup
		ILoan loan =mock(ILoan.class);
		IBook book =mock(IBook.class);
		
		when(loan.getBook()).thenReturn(book);
		
		//Execute
		this.loanDAO.commitLoan(loan);
		
		//Verify and assert
		verify(loan).commit(1);
		ILoan actualLoan =this.loanDAO.getLoanByBook(mock(IBook.class));
		verify(loan).getBook();
		assertEquals(null, actualLoan);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetLoanByBookBadParamBook() {
		this.loanDAO.getLoanByBook(null);
	}
	

	@Test
	public void testListLoansSize() {
		//Setup
		List<ILoan> loanList =new ArrayList<ILoan>();
		for(int i =0; i <1; i++) {
			loanList.add(mock(ILoan.class));
		}
		
		//Execute and verify
		for(int i =0; i <loanList.size(); i++) {
			ILoan tmpLoan =loanList.get(i);
			this.loanDAO.commitLoan(tmpLoan);
			verify(loanList.get(i)).commit(i +1);
		}
		
		List<ILoan> actualList =this.loanDAO.listLoans();
		assertEquals(loanList.size(), actualList.size());
	}
	
	@Test
	public void testListLoansMatch() {
		//Setup
		List<ILoan> loanList =new ArrayList<ILoan>();
		for(int i =0; i <1; i++) {
			loanList.add(mock(ILoan.class));
		}
		
		//Execute and verify
		for(int i =0; i <loanList.size(); i++) {
			ILoan tmpLoan =loanList.get(i);
			this.loanDAO.commitLoan(tmpLoan);
			verify(loanList.get(i)).commit(i +1);
		}
		
		List<ILoan> actualList =this.loanDAO.listLoans();
		assertEquals(loanList.size(), actualList.size());
		
		for(int i =0; i <loanList.size(); i++) {
			assertEquals(loanList.get(i), actualList.get(i));
		}
	}
	

	@Test
	public void testFindLoansByBorrowerSize() {
		//Setup
		List<ILoan> loanList =new ArrayList<ILoan>();
		IMember mockBorrower =mock(IMember.class);
		
		for(int i =0; i <1; i++) {
			ILoan tmpLoan =mock(ILoan.class);
			loanList.add(tmpLoan);
			when(tmpLoan.getBorrower()).thenReturn(mockBorrower);
		}
		
		//Execute and verify
		for(int i =0; i <loanList.size(); i++) {
			ILoan tmpLoan =loanList.get(i);
			this.loanDAO.commitLoan(tmpLoan);
			verify(loanList.get(i)).commit(i +1);
		}
		
		List<ILoan> actualList =this.loanDAO.findLoansByBorrower(mockBorrower);
		assertEquals(loanList.size(), actualList.size());
	}
	
	@Test
	public void testFindLoansByBorrowerMatch() {
		//Setup
		List<ILoan> loanList =new ArrayList<ILoan>();
		IMember mockBorrower =mock(IMember.class);
		
		for(int i =0; i <1; i++) {
			ILoan tmpLoan =mock(ILoan.class);
			loanList.add(tmpLoan);
			when(tmpLoan.getBorrower()).thenReturn(mockBorrower);
		}
		
		//Execute and verify
		for(int i =0; i <loanList.size(); i++) {
			ILoan tmpLoan =loanList.get(i);
			this.loanDAO.commitLoan(tmpLoan);
			verify(loanList.get(i)).commit(i +1);
		}
		
		List<ILoan> actualList =this.loanDAO.findLoansByBorrower(mockBorrower);
		assertEquals(loanList.size(), actualList.size());
		
		for(int i =0; i <loanList.size(); i++) {
			assertEquals(loanList.get(i), actualList.get(i));
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindLoansByBorrowerBadParamBorrower() {
		List<ILoan> actualList =this.loanDAO.findLoansByBorrower(null);
	}
	
/*
	@Test
	public void testFindLoansByBookTitle() {
		fail("Not yet implemented");
		
		//Setup
		List<ILoan> loanList =new ArrayList<ILoan>();
		for(int i =0; i <1; i++) {
			ILoan tmpLoan =mock(ILoan.class);
			loanList.add(tmpLoan);
			when(tmpLoan.getBook().getTitle()).thenReturn("title" +i);
		}
		
		//Execute and verify
		for(int i =0; i <loanList.size(); i++) {
			ILoan tmpLoan =loanList.get(i);
			this.loanDAO.commitLoan(tmpLoan);
			verify(loanList.get(i)).commit(i +1);
		}
		
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
