package Console;
import Exception.BalanceNotEnoughException;
import Exception.LoanException;
public class LoanSavingAccount extends SavingAccount implements Loanable{
	protected double loan;
	public LoanSavingAccount(long id, long passWord, String name, String personId, String email) {
		super(id, passWord, name, personId, email);
		this.loan=0;
	}
	@Override
	public Account withdraw(double money) throws BalanceNotEnoughException {
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
