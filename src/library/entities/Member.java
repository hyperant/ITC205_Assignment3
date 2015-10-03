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
		//need to add exceptions
		this.totalFines +=fine;
		
	}

	@Override
	public void payFine(float payment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLoan(ILoan loan) {
		//To do exceptions...
		this.loanList.add(loan);
	}

	@Override
	public List<ILoan> getLoans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLoan(ILoan loan) {
		// TODO Auto-generated method stub
		
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

}
