package console;
import exception.BalanceNotEnoughException;
public class CreditAccount extends Account{
	protected double ceiling;
	public CreditAccount(long id, long passWord, String name, String personId, String email,double ceiling) {
		super(id, passWord, name, personId, email);
		this.ceiling=ceiling;
	}
	@Override
	public Account withdraw(double money) throws BalanceNotEnoughException{
		if(money>balance+ceiling) {
			throw new BalanceNotEnoughException();
		}else {
			balance-=money;
		}
		return this;
	}
	public double getCeiling() {
		return ceiling;
	}
}
