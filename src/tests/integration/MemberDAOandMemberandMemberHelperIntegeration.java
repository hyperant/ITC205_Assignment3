package tests.integration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
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
	private IMember member;
	private IMemberHelper helper;
	private IMemberDAO memberDAO;
	
	@Before
	public void setUp() throws Exception {
		this.member =new Member("Jason", "Fletcher", "666666", "no@no.com", 1);
		this.helper =new MemberHelper();
		this.memberDAO =new MemberDAO(this.helper);
	}

	@After
	public void tearDown() throws Exception {
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
