package tests.library.daos;

import static org.junit.Assert.*;
import library.daos.BookHelper;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBookHelper {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeBook() {
		IBookHelper helper =new BookHelper();
		IBook book =helper.makeBook("fred", "Programming 101", "PRO 101", 2);
		
		assertTrue(book instanceof IBook);
		assertEquals(book.getAuthor(), "fred");
		assertEquals(book.getTitle(), "Programming 101");
		assertEquals(book.getCallNumber(), "PRO 101");
		assertEquals(book.getID(), 2);
	}

}
