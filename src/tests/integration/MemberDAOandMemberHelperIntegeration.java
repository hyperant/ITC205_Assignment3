package tests.integration;

import static org.junit.Assert.*;
import library.daos.MemberDAO;
import library.daos.MemberHelper;
import library.interfaces.daos.IMemberDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemberDAOandMemberHelperIntegeration {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeMember() {
		IMemberDAO memberDAO =new MemberDAO(new MemberHelper());
		assertTrue(memberDAO instanceof IMemberDAO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMakeMemberNullHelper() {
		IMemberDAO memberDAO =new MemberDAO(null);
	}

}
