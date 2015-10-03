package library.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class BookDAO implements IBookDAO {
	private IBookHelper bookhelper;
	
	private int nextBookID;
	
	private Map<Integer, IBook> bookMap;
	
	public BookDAO(IBookHelper bookHelper) {
		if(bookHelper ==null) {
			throw new IllegalArgumentException("Bad parameter: bookHelper can not be null");
		}
		
		this.bookhelper =bookHelper;
		this.nextBookID =0;
		this.bookMap =new HashMap<Integer, IBook>();
	}

	@Override
	public IBook addBook(String author, String title, String callNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBook getBookByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> listBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByAuthorTitle(String author, String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
