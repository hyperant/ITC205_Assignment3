package tests.integration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
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
	}

	@After
	public void tearDown() throws Exception {
		this.member =null;
		this.helper =null;
		this.memberDAO =null;
	}

	@Test
	public void testMemberDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMemberByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testListMembers() {
		fail("Not yet implemented");
	}

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

}
