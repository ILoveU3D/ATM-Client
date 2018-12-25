package console;
import exception.BalanceNotEnoughException;
import exception.LoanException;
public class LoanCreditAccount extends CreditAccount implements Loanable{
	private double loan;
	public LoanCreditAccount(long id, long passWord, String name, String personId, String email, double ceiling) {
		super(id, passWord, name, personId, email, ceiling);
		this.loan=0;
	}
	@Override
	public Account withdraw(double money) throws BalanceNotEnoughException{
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
		loan-=money;
		if(loan<0) {
			this.balance+=-loan;
			loan=0;
		}
	}
	@Override
	public double getLoan() {
		return this.loan;
	}
}
