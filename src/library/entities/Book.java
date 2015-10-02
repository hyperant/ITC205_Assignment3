package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook  {
	private String author;
	private String title;
	private String callNumber;
	
	private int bookID;
	
	public Book(String author, String title, String callNumber, int bookID) {
		if(bookID <1) {
			throw new IllegalArgumentException("bookID can not be less then 1: " +bookID);
		}
		
		if(author ==null || title ==null || callNumber ==null) {
			throw new IllegalArgumentException("Bad parameter: null value detected");
		}
		
		if(author.isEmpty() || title.isEmpty() || callNumber.isEmpty()) {
			throw new IllegalArgumentException("Bad parameter: Empty string detected");
		}
		
		this.author =author;
		this.title =title;
		this.callNumber =callNumber;
		this.bookID =bookID;
	}

	@Override
	public void borrow(ILoan loan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ILoan getLoan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void returnBook(boolean damaged) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repair() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EBookState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCallNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
