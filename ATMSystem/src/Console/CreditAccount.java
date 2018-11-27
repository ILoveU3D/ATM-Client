package Console;
public class CreditAccount extends Account{
	protected double ceiling;
	public CreditAccount(long id, long passWord, String name, String personId, String email,double ceiling) {
		super(id, passWord, name, personId, email);
		this.ceiling=ceiling;
	}
	@Override
	public Account withdraw(double money){
		if(balance-money<0) {
			this.ceiling=money-balance;
			balance=0;
		}else {
			balance-=money;
		}
		return this;
	}
	public double getCeiling() {
		return ceiling;
	}
}
