package tests.library.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import library.entities.Book;
import library.entities.Member;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMember {
	private String firstName;
	private String lastName;
	private String contactPhone;
	private String emailAddress;
	
	private int memberID;
	
	private IMember member;

	@Before
	public void setUp() throws Exception {
		this.firstName ="Jason";
		this.lastName ="Fletcher";
		this.contactPhone ="666666";
		this.emailAddress ="no@no.com";
		this.memberID =1;
		
		this.member =new Member(this.firstName, this.lastName, this.contactPhone, this.emailAddress, this.memberID);
	}

	@After
	public void tearDown() throws Exception {
		this.member =null;
	}

	@Test
	public void testCreate() {
		IMember member =new Member(this.firstName, this.lastName, this.contactPhone, this.emailAddress, this.memberID);
		assertTrue(member instanceof IMember);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamFirstName() {
		IMember member =new Member(null, this.lastName, this.contactPhone, this.emailAddress, this.memberID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamLastName() {
		IMember member =new Member(this.firstName, null, this.contactPhone, this.emailAddress, this.memberID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamContachPhone() {
		IMember member =new Member(this.firstName, this.lastName, null, this.emailAddress, this.memberID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamEmail() {
		IMember member =new Member(this.firstName, this.lastName, this.contactPhone, null, this.memberID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamIncorrectID() {
		IMember member =new Member(this.firstName, this.lastName, this.contactPhone, this.emailAddress, 0);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamFirstNameEmpty() {
		IMember member =new Member("", this.lastName, this.contactPhone, this.emailAddress, this.memberID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamLastNameEmpty() {
		IMember member =new Member(this.firstName, "", this.contactPhone, this.emailAddress, this.memberID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamContachPhoneEmpty() {
		IMember member =new Member(this.firstName, this.lastName, "", this.emailAddress, this.memberID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBadParamEmailEmpty() {
		IMember member =new Member(this.firstName, this.lastName, this.contactPhone, "", this.memberID);
	}
	
	@Test
	public void testHasOverDueLoans() {
		//Setup
		ILoan loan =mock(ILoan.class);
		when(loan.isOverDue()).thenReturn(true);
		this.member.addLoan(loan);
		
		//Verify and assert
		boolean overDue =this.member.hasOverDueLoans();
		verify(loan).isOverDue();
		assertTrue(overDue);
	}
	
	@Test
	public void testHasOverDueLoansNoneOverDue() {
		//Setup
		ILoan loan =mock(ILoan.class);
		when(loan.isOverDue()).thenReturn(false);
		this.member.addLoan(loan);
		
		//Verify and assert
		boolean overDue =this.member.hasOverDueLoans();
		verify(loan).isOverDue();
		assertFalse(overDue);
	}

	@Test
	public void testHasReachedLoanLimit() {
		//Setup
		for(int i =0; i <10; i++) {
			ILoan loan =mock(ILoan.class);
			this.member.addLoan(loan);
		}

		//Assert
		assertTrue(this.member.hasReachedLoanLimit());
	}
	
	@Test
	public void testHasReachedLoanLimitStillCanBorrow() {
		//Setup
		ILoan loan =mock(ILoan.class);
		this.member.addLoan(loan);

		//Assert
		assertFalse(this.member.hasReachedLoanLimit());
	}

	@Test
	public void testHasFinesPayable() {
		//Setup
		this.member.addFine(1.2f);
		
		//Assert
		assertTrue(this.member.hasFinesPayable());
	}
	
	@Test
	public void testHasFinesPayableNone() {
		
		//Assert
		assertFalse(this.member.hasFinesPayable());
	}

	/*
	@Test
	public void testHasReachedFineLimit() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFineAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFine() {
		fail("Not yet implemented");
	}

	@Test
	public void testPayFine() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLoans() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFirstName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLastName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetContactPhone() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmailAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}
*/
}
