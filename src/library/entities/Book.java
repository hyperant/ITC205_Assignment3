package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook  {
	private String author;
	private String title;
	private String callNumber;
	
	private int bookID;
	
	private ILoan loan;
	
	private EBookState bookState;
	
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
		
		this.loan =null;
		this.bookState =EBookState.AVAILABLE;
	}

	@Override
	public void borrow(ILoan loan) {
		//Even though this is not in the specification I think that we should do a check for it just in case.
		if(loan ==null) {
			throw new IllegalArgumentException("Bad parameter: loan can not be null");
		}
		
		if(this.bookState !=EBookState.AVAILABLE) {
			throw new RuntimeException("Book is not currently available: " +this.bookState);
		}
		
		this.loan =loan;
		this.bookState =EBookState.ON_LOAN;
	}

	@Override
	public ILoan getLoan() {
		if(this.bookState !=EBookState.ON_LOAN) {
			return null;
		}
		
		return this.loan;
	}

	@Override
	public void returnBook(boolean damaged) {
		if(this.bookState !=EBookState.ON_LOAN) {
			throw new RuntimeException("Book is not currently on loan: " +this.bookState);
		}
		
		if(damaged) {
			this.bookState =EBookState.DAMAGED;
		} else {
			this.bookState =EBookState.AVAILABLE;
		}
	}

	@Override
	public void lose() {
		if(this.bookState !=EBookState.ON_LOAN) {
			throw new RuntimeException("Book is not currently on loan: " +this.bookState);
		}
		
		this.bookState =EBookState.LOST;
	}

	@Override
	public void repair() {
		if(this.bookState !=EBookState.DAMAGED) {
			throw new RuntimeException("Book is not currently damaged: " +this.bookState);
		}
		
		this.bookState =EBookState.AVAILABLE;
	}

	@Override
	public void dispose() {
		if(!(this.bookState ==EBookState.AVAILABLE || this.bookState ==EBookState.DAMAGED || this.bookState ==EBookState.LOST)) {
			throw new RuntimeException("Book is not currently available, damaged, or lost: " +this.bookState);
		}
		
		this.bookState =EBookState.DISPOSED;
	}

	@Override
	public EBookState getState() {
		return this.bookState;
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
