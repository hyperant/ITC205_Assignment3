package tests.library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import library.daos.BookDAO;
import library.daos.MemberDAO;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMemberDAO {
	
	private IMemberHelper helper;

	@Before
	public void setUp() throws Exception {
		this.helper =mock(IMemberHelper.class);
	}

	@After
	public void tearDown() throws Exception {
		this.helper =null;
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

	/*
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
*/
}
