package Console;
import Exception.BalanceNotEnoughException;
public class SavingAccount extends Account{
	public SavingAccount(long id, long passWord, String name, String personId, String email) {
		super(id,passWord,name,personId,email);
	}
	public Account withdraw(double money) throws BalanceNotEnoughException{
		if(balance-money<0) {
			throw new BalanceNotEnoughException();
		}else {
			balance-=money;
		}
		return this;
	}
}
