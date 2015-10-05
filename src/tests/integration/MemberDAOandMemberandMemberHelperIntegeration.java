package tests.integration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import library.daos.MemberDAO;
import library.daos.MemberHelper;
import library.entities.Member;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemberDAOandMemberandMemberHelperIntegeration {
	private IMemberHelper helper;
	private IMemberDAO memberDAO;
	
	private String firstName;
	private String lastName;
	private String contactPhone;
	private String emailAddress;
	private int memberID;
	
	@Before
	public void setUp() throws Exception {
		this.firstName ="Jason";
		this.lastName ="Fletcher";
		this.contactPhone ="666666";
		this.emailAddress ="no@no.com";
		this.memberID =1;
		
		this.helper =new MemberHelper();
		this.memberDAO =new MemberDAO(this.helper);
	}

	@After
	public void tearDown() throws Exception {
		this.helper =null;
		this.memberDAO =null;
	}

	@Test
	public void testAddMember() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		assertTrue(member instanceof IMember);
	}
	@Test
	public void testGetMemberByID() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		assertEquals(member, this.memberDAO.getMemberByID(this.memberID));
	}
	
	@Test
	public void testGetMemberByIDNotFound() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		assertNull(this.memberDAO.getMemberByID(10));
	}

	@Test
	public void testListMembers() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		
		List<IMember> memberList =this.memberDAO.listMembers();
		
		assertEquals(1, memberList.size());
		assertEquals(member, memberList.get(0));
	}

	@Test
	public void testFindMembersByLastName() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByLastName(this.lastName);
		
		assertEquals(1, memberList.size());
		assertEquals(member, memberList.get(0));
	}
	
	@Test
	public void testFindMembersByLastNameNoMath() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByLastName("I do not exist");
		
		assertEquals(0, memberList.size());
	}

	@Test
	public void testFindMembersByEmailAddress() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByEmailAddress(this.emailAddress);
		
		assertEquals(1, memberList.size());
		assertEquals(member, memberList.get(0));
	}
	
	@Test
	public void testFindMembersByEmailAddressNoMatch() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByEmailAddress("i do not exist");
		
		assertEquals(0, memberList.size());
	}

	@Test
	public void testFindMembersByNames() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByNames(this.firstName, this.lastName);
		
		assertEquals(1, memberList.size());
		assertEquals(member, memberList.get(0));
	}
	
	@Test
	public void testFindMembersByNamesNoMatchFirstName() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByNames("i do not exist", this.lastName);
		
		assertEquals(0, memberList.size());
	}
	
	@Test
	public void testFindMembersByNamesNoMatchLastName() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByNames(this.firstName, "i do not exist");
		
		assertEquals(0, memberList.size());
	}
	
	@Test
	public void testFindMembersByNamesNoMatchFirstNameLastName() {
		IMember member =this.memberDAO.addMember(this.firstName, this.lastName, this.contactPhone, this.emailAddress);
		List<IMember> memberList =this.memberDAO.findMembersByNames("i do not exist", "i do not exist");
		
		assertEquals(0, memberList.size());
	}
}
