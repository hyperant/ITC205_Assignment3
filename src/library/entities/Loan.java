package library.entities;

import java.util.Date;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class Loan implements ILoan {
	private IBook book;
	private IMember member;
	
	private Date borrowDate;
	private Date dueDate;
	
	public Loan(IBook book, IMember member, Date borrowDate, Date dueDate) {
		//Make sure we havent been passed any null values
		if(book ==null || member ==null || borrowDate ==null || dueDate ==null) {
			throw new IllegalArgumentException("required params can not be null");
		}
		
		if(dueDate.compareTo(borrowDate) <0) {
			throw new IllegalArgumentException("dueDate can not be less then borrowDate");
		}
		
		this.book =book;
		this.member =member;
		this.borrowDate =borrowDate;
		this.dueDate =dueDate;
	}

	@Override
	public void commit(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void complete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOverDue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCurrent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkOverDue(Date currentDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IMember getBorrower() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBook getBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
