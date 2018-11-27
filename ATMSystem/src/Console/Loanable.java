package Console;
import Exception.LoanException;
public interface Loanable {
	public void requesLoan(double money) throws LoanException;
	public void payLoan(double money);
	public double getLoan();
}
