package Console;
import Exception.*;
import UI.BussinessPanel;
import java.util.ArrayList;
public class Bank {
	private static ArrayList<Account> accounts=new ArrayList<Account>();
	private static long sum=10001l;
	public void register(long passWord, String name, String personId, String email,Boolean ceiling,Boolean loan,double credit) throws RegisterException {
		Account account;
		if(ceiling)
			if(loan)
				account=new LoanCreditAccount(sum,passWord, name, personId, email,credit);
			else
				account=new CreditAccount(sum, passWord, name, personId, email, credit);
		else
			if(loan)
				account=new LoanSavingAccount(sum, passWord, name, personId, email);
			else
				account=new SavingAccount(sum, passWord, name, personId, email);
		sum++;
		accounts.add(account);
		new BussinessPanel(account);
		/*DataBaseDriver.connect();
		DataBaseDriver.sendMessage("");*/
	}
	public boolean modify(long id,long passWord, String name, String personId, String email,Boolean ceiling,Boolean loan,double credit) throws RegisterException{
		for(int i=0;i<accounts.size();i++) {
			if(accounts.get(i).id==id) {
				Account account;
				if(ceiling)
					if(loan)
						account=new LoanCreditAccount(id,passWord, name, personId, email,credit);
					else
						account=new CreditAccount(id, passWord, name, personId, email, credit);
				else
					if(loan)
						account=new LoanSavingAccount(id, passWord, name, personId, email);
					else
						account=new SavingAccount(id, passWord, name, personId, email);
				accounts.set(i, account);
				return true;
			}
		}
		return false;
	}
	public void login(long id,long passWord) throws LogInException{
		
	}
	public void deposit(double money){
		
	}
	public void withdraw(double money) throws BalanceNotEnoughException{
		
	}
	public void loan(double money) throws LoanException{
		
	}
	public void transfer(long to,double money) throws BalanceNotEnoughException {
		
	}
	public static double balance_sum() {
		double sum=0;
		for(Account account:accounts) {
			sum+=account.balance;
		}
		return sum;
	}
	public static double ceiling_sum() {
		double sum=0;
		for(Account account:accounts) {
			CreditAccount temp=(CreditAccount)account;
			sum+=temp.ceiling; 
		}
		return sum;
	}
	private static Bank bank;
	private Bank() {}
	public static Bank getBank() {
		if(bank==null)
			bank=new Bank();
		return bank;
	}
}
