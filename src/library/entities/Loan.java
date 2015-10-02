package library.entities;

import java.util.Calendar;
import java.util.Date;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.entities.ELoanState;

public class Loan implements ILoan {
	private IBook book;
	private IMember member;
	private ELoanState loanState;
	
	private Date borrowDate;
	private Date dueDate;
	
	private int loanID;
	
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
		this.loanID =0;
		
		this.loanState =ELoanState.PENDING;
	}

	@Override
	public void commit(int loanID) {
		if(loanID <=0) {
			throw new IllegalArgumentException("Loan ID cannot be less then or equal to zero");
		}
		
		if(this.loanState !=ELoanState.PENDING) {
			throw new RuntimeException("Current loan state is not pending");
		}
		
		this.loanID =loanID;
		
		this.loanState =ELoanState.CURRENT;
		this.book.borrow(this);
		member.addLoan(this);
	}

	@Override
	public void complete() {
		if(this.loanState ==ELoanState.COMPLETE || this.loanState ==ELoanState.PENDING) {
			throw new RuntimeException("Current loan state is not current or overdue: " +this.loanState);
		}
		
		this.loanState =ELoanState.COMPLETE;
	}

	@Override
	public boolean isOverDue() {
		return this.loanState ==ELoanState.OVERDUE;
	}

	@Override
	public boolean isCurrent() {
		return this.loanState ==ELoanState.CURRENT;
	}

	@Override
	public boolean checkOverDue(Date currentDate) {
		if(this.loanState ==ELoanState.COMPLETE || this.loanState ==ELoanState.PENDING) {
			throw new RuntimeException("Current loan state is not current or overdue: " +this.loanState);
		}
		
		if(currentDate.compareTo(this.dueDate) >0) {
			this.loanState =ELoanState.OVERDUE;
		}
		
		return this.loanState ==ELoanState.OVERDUE;
	}

	@Override
	public IMember getBorrower() {
		return this.member;
	}

	@Override
	public IBook getBook() {
		return this.book;
	}

	@Override
	public int getID() {
		return this.loanID;
	}

}
