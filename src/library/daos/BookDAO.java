package library.daos;

import java.util.ArrayList;
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
		int bookID =++this.nextBookID;
		IBook book =this.bookhelper.makeBook(author, title, callNo, bookID);
		this.bookMap.put(bookID, book);
		return book;
	}

	@Override
	public IBook getBookByID(int id) {
		if(this.bookMap.containsKey((Integer)id)) {
			return this.bookMap.get((Integer)id);
		}
		
		return null;
	}

	@Override
	public List<IBook> listBooks() {
		return new ArrayList<IBook>(this.bookMap.values());
	}

	@Override
	public List<IBook> findBooksByAuthor(String author) {
		List<IBook> bookList =new ArrayList<IBook>();
		
		for(IBook book : this.bookMap.values()) {
			if(book.getAuthor().equals(author)) {
				bookList.add(book);
			}
		}
		
		return bookList;
	}

	@Override
	public List<IBook> findBooksByTitle(String title) {
		List<IBook> bookList =new ArrayList<IBook>();
		
		for(IBook book : this.bookMap.values()) {
			if(book.getTitle().equals(title)) {
				bookList.add(book);
			}
		}
		
		return bookList;
	}

	@Override
	public List<IBook> findBooksByAuthorTitle(String author, String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
