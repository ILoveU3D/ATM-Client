package Console;

import Exception.LoanException;

public class LoanCreditAccount extends CreditAccount implements Loanable{
	public double loan;
	public LoanCreditAccount(long id, long passWord, String name, String personId, String email, double ceiling) {
		super(id, passWord, name, personId, email, ceiling);
		this.loan=0;
	}
	@Override
	public Account withdraw(double money) {
		return super.withdraw(money);
	}
	@Override
	public void requesLoan(double money) throws LoanException{
		if(money<0)
			throw new LoanException();
		else {
			loan+=money;
		}
	}
	@Override
	public void payLoan(double money) {
		double value=loan-money;
		if(value<0)
			this.balance+=value;
	}
	@Override
	public double getLoan() {
		return this.loan;
	}
}
