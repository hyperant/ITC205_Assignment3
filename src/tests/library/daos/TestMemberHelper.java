package tests.library.daos;

import static org.junit.Assert.*;
import library.daos.BookHelper;
import library.daos.MemberHelper;
import library.interfaces.daos.IBookHelper;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMemberHelper {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeMember() {
		IMemberHelper helper =new MemberHelper();
		IMember member =helper.makeMember("jason", "fred", "666666", "no@no.com", 1);
		
		assertTrue(member instanceof IMember);
		
		assertEquals("jason", member.getFirstName());
		assertEquals("fred", member.getLastName());
		assertEquals("666666", member.getContactPhone());
		assertEquals("no@no.com", member.getEmailAddress());
		assertEquals(1, member.getID());
	}

}
