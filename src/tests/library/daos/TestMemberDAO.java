package tests.library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import library.daos.BookDAO;
import library.daos.MemberDAO;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMemberDAO {
	
	private String firstName;
	private String lastName;
	private String contactPhone;
	private String emailAddress;
	private int memberID;
	
	private IMemberHelper helper;
	private IMemberDAO memberDAO;

	@Before
	public void setUp() throws Exception {
		this.firstName ="Jason";
		this.lastName ="Fletcher";
		this.contactPhone ="666666";
		this.emailAddress ="no@no.com";
		this.memberID =1;
		
		this.helper =mock(IMemberHelper.class);
		this.memberDAO =new MemberDAO(this.helper);
	}

	@After
	public void tearDown() throws Exception {
		this.helper =null;
		this.memberDAO =null;
	}
	
	@Test
	public void testConstructor() {
		MemberDAO memberDAO =new MemberDAO(this.helper);
		assertTrue(memberDAO instanceof IMemberDAO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorHelperNull() {
		MemberDAO memberDAO =new MemberDAO(null);
	}

	@Test
	public void testAddMember() {
		//Setup
		IMember mockMember =mock(IMember.class);
		when(this.helper.makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), any(Integer.class))).thenReturn(mockMember);

		//Execute
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		//Verify and assert
		verify(this.helper).makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), any(Integer.class));
		assertEquals(mockMember, member);
	}

	@Test
	public void testGetMemberByID() {
		//Setup
		IMember mockMember =mock(IMember.class);
		when(this.helper.makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), eq(this.memberID))).thenReturn(mockMember);

		//Execute
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		//Verify and assert
		verify(this.helper).makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), eq(this.memberID));
		assertEquals(mockMember, member);
		
		//Actual Test to make sure we can get the book by ID
		IMember memberByID =this.memberDAO.getMemberByID(this.memberID);
		assertTrue(memberByID instanceof IMember);
		assertEquals(member, memberByID);
	}
	
	@Test
	public void testGetMemberByIDNotExist() {
		//Setup
		IMember mockMember =mock(IMember.class);
		when(this.helper.makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), eq(this.memberID))).thenReturn(mockMember);

		//Execute
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		//Verify and assert
		verify(this.helper).makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), eq(this.memberID));
		assertEquals(mockMember, member);
		
		//Actual Test to make sure we can get the book by ID
		IMember memberByID =this.memberDAO.getMemberByID(10);
		assertEquals(null, memberByID);
	}

	@Test
	public void testListMembers() {
		//Setup
		IMember mockMember =mock(IMember.class);
		when(this.helper.makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), eq(this.memberID))).thenReturn(mockMember);

		//Execute
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		//Verify and assert
		verify(this.helper).makeMember(eq(this.firstName), eq(this.lastName), eq(this.contactPhone), eq(this.emailAddress), eq(this.memberID));
		assertEquals(mockMember, member);
		
		//Actual Test to make sure we do not get the book
		List<IMember> memberList =this.memberDAO.listMembers();
		assertEquals(1, memberList.size());
	}

	/*
	@Test
	public void testFindMembersByLastName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMembersByEmailAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMembersByNames() {
		fail("Not yet implemented");
	}
*/
}
