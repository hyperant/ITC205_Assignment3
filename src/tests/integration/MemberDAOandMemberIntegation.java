package tests.integration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import library.daos.MemberDAO;
import library.entities.Member;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemberDAOandMemberIntegation {

	private IMember member;
	private IMemberDAO memberDAO;
	private IMemberHelper helper;
	
	@Before
	public void setUp() throws Exception {
		this.member =new Member("Jason", "Fletcher", "666666", "no@no.com", 1);
		this.helper =mock(IMemberHelper.class);
		this.memberDAO =new MemberDAO(this.helper);
		
		when(this.helper.makeMember(any(String.class), any(String.class), any(String.class), any(String.class), any(Integer.class))).thenReturn(this.member);
	
		this.memberDAO.addMember(this.member.getFirstName(), this.member.getLastName(), this.member.getContactPhone(), this.member.getEmailAddress());
	}

	@After
	public void tearDown() throws Exception {
		this.member =null;
		this.helper =null;
		this.memberDAO =null;
	}

	@Test
	public void testGetMemberByID() {
		assertEquals(this.member, this.memberDAO.getMemberByID(this.member.getID()));
	}
	
	@Test
	public void testGetMemberByIDNotFound() {
		assertNull(this.memberDAO.getMemberByID(10));
	}

	@Test
	public void testListMembers() {
		List<IMember> memberList =this.memberDAO.listMembers();
		
		assertEquals(1, memberList.size());
		assertEquals(this.member, memberList.get(0));
	}

	@Test
	public void testFindMembersByLastName() {
		List<IMember> memberList =this.memberDAO.findMembersByLastName(this.member.getLastName());
		
		assertEquals(1, memberList.size());
		assertEquals(this.member, memberList.get(0));
	}
	
	@Test
	public void testFindMembersByLastNameNoMath() {
		List<IMember> memberList =this.memberDAO.findMembersByLastName("I do not exist");
		
		assertEquals(0, memberList.size());
	}

	@Test
	public void testFindMembersByEmailAddress() {
		List<IMember> memberList =this.memberDAO.findMembersByEmailAddress(this.member.getEmailAddress());
		
		assertEquals(1, memberList.size());
		assertEquals(this.member, memberList.get(0));
	}
	
	@Test
	public void testFindMembersByEmailAddressNoMatch() {
		List<IMember> memberList =this.memberDAO.findMembersByEmailAddress("i do not exist");
		
		assertEquals(0, memberList.size());
	}

	@Test
	public void testFindMembersByNames() {
		List<IMember> memberList =this.memberDAO.findMembersByNames(this.member.getFirstName(), this.member.getLastName());
		
		assertEquals(1, memberList.size());
		assertEquals(this.member, memberList.get(0));
	}
	
	@Test
	public void testFindMembersByNamesNoMatchFirstName() {
		List<IMember> memberList =this.memberDAO.findMembersByNames("i do not exist", this.member.getLastName());
		
		assertEquals(0, memberList.size());
	}
	
	@Test
	public void testFindMembersByNamesNoMatchLastName() {
		List<IMember> memberList =this.memberDAO.findMembersByNames(this.member.getFirstName(), "i do not exist");
		
		assertEquals(0, memberList.size());
	}
	
	@Test
	public void testFindMembersByNamesNoMatchFirstNameLastName() {
		List<IMember> memberList =this.memberDAO.findMembersByNames("i do not exist", "i do not exist");
		
		assertEquals(0, memberList.size());
	}
}
