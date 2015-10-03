package library.entities;

import java.util.ArrayList;
import java.util.List;

import library.interfaces.entities.EMemberState;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class Member implements IMember {
	private String firstName;
	private String lastName;
	private String contactPhone;
	private String emailAddress;
	
	private int memberID;
	private float totalFines;
	
	private List<ILoan> loanList;
	private EMemberState memberState;
	
	
	
	public Member(String firstName, String lastName, String contactPhone, String emailAddress, int memberID) {
		if(memberID <1) {
			throw new IllegalArgumentException("memberID can not be less then 1: " +memberID);
		}
		
		if(firstName ==null || lastName ==null || contactPhone ==null || emailAddress ==null) {
			throw new IllegalArgumentException("Bad parameter: null value detected");
		}
		
		if(firstName.isEmpty() || lastName.isEmpty() || contactPhone.isEmpty() || emailAddress.isEmpty()) {
			throw new IllegalArgumentException("Bad parameter: Empty string detected");
		}
		
		this.firstName =firstName;
		this.lastName =lastName;
		this.contactPhone =contactPhone;
		this.emailAddress =emailAddress;
		this.memberID =memberID;
		
		this.totalFines =0;
		this.memberState =EMemberState.BORROWING_ALLOWED;
		this.loanList =new ArrayList<ILoan>();
	}

	@Override
	public boolean hasOverDueLoans() {
		for(ILoan loan : this.loanList) {
			if(loan.isOverDue()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasReachedLoanLimit() {
		if(this.loanList.size() >=LOAN_LIMIT) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean hasFinesPayable() {
		if(this.totalFines >0) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean hasReachedFineLimit() {
		if(this.totalFines >=FINE_LIMIT) {
			return true;
		}
		
		return false;
	}

	@Override
	public float getFineAmount() {
		return this.totalFines;
	}

	@Override
	public void addFine(float fine) {
		if(fine <0) {
			throw new IllegalArgumentException("Bad parameter: fine must be greater then or equal to 0");
		}
		
		this.totalFines +=fine;
		
		this.updateLoanState();
	}

	@Override
	public void payFine(float payment) {
		if(payment <0) {
			throw new IllegalArgumentException("Bad parameter: payment must be greater then or equal to 0");
		}
		
		if(payment >this.totalFines) {
			throw new IllegalArgumentException("Bad parameter: you can not pay more then you owe");
		}
		
		this.totalFines -=payment;
		
		this.updateLoanState();
	}

	@Override
	public void addLoan(ILoan loan) {
		if(loan ==null) {
			throw new IllegalArgumentException("Bad parameter: loan can not be null");
		}
		
		if(this.memberState ==EMemberState.BORROWING_DISALLOWED) {
			throw new IllegalArgumentException("Member is not allowed to borrow");
		}
		
		this.loanList.add(loan);
		this.updateLoanState();
	}

	@Override
	public List<ILoan> getLoans() {
		return this.loanList;
	}

	@Override
	public void removeLoan(ILoan loan) {
		// TODO Auto-generated method stub
		
		this.updateLoanState();
	}

	@Override
	public EMemberState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContactPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void updateLoanState() {
		if (this.hasReachedLoanLimit() || this.hasReachedFineLimit() || this.hasOverDueLoans()) {
			this.memberState =EMemberState.BORROWING_DISALLOWED;
		} else {
			this.memberState =EMemberState.BORROWING_ALLOWED;
		}
	}

}
