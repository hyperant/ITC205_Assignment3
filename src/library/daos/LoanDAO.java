package library.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class LoanDAO implements ILoanDAO {
	private ILoanHelper helper;
	private Map<Integer, ILoan> loanMap;
	
	private int nextLoanID;
	
	public LoanDAO(ILoanHelper helper) {
		if(helper ==null) {
			throw new IllegalArgumentException("helper cannot be null");
		}
		
		this.helper =helper;
		this.nextLoanID =0;
		this.loanMap =new HashMap<Integer, ILoan>();
	}

	@Override
	public ILoan createLoan(IMember borrower, IBook book) {
		if(borrower ==null || book ==null) {
			throw new IllegalArgumentException("borrow or book cannot be null");
		}
		
		Calendar cal =Calendar.getInstance();
		Date borrowDate =cal.getTime();
		cal.add(Calendar.DATE, ILoan.LOAN_PERIOD);
		Date dueDate =cal.getTime();
		
		return this.helper.makeLoan(book, borrower, borrowDate, dueDate);
	}

	@Override
	public void commitLoan(ILoan loan) {
		int loanID =this.nextLoanID++;
		loan.commit(loanID);
		
		this.loanMap.put(loanID, loan);
	}

	@Override
	public ILoan getLoanByID(int id) {
		if(this.loanMap.containsKey((Integer)id)) {
			return this.loanMap.get((Integer)id);
		}
		
		return null;
	}

	@Override
	public ILoan getLoanByBook(IBook book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ILoan> listLoans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ILoan> findLoansByBorrower(IMember borrower) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ILoan> findLoansByBookTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOverDueStatus(Date currentDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ILoan> findOverDueLoans() {
		// TODO Auto-generated method stub
		return null;
	}

}
