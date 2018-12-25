package console;
import exception.*;
import ui.BussinessPanel;
import ui.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dao.impl.*;
import dao.factory.*;
public class Bank {
	private static long sum;
	private AccountDAOCreator daoCreator;
	private AccountDAO dao;
	private Properties properties;
	public void register(long passWord, String name, String personId, String email,Boolean ceiling,Boolean loan,double credit) throws RegisterException {
		Account account;
		String type;
		if(ceiling)
			if(loan) {
				account=new LoanCreditAccount(sum,passWord, name, personId, email,credit);
				type=Constant.lOAN_CREDIT_ACCOUNT;
			}else {
				account=new CreditAccount(sum, passWord, name, personId, email, credit);
				type=Constant.CREDIT_ACCOUNT;
			}
		else {
			credit=0;
			if(loan) {
				account=new LoanSavingAccount(sum, passWord, name, personId, email);
				type=Constant.LOAN_SAVING_ACCOUNT;
			}
			else {
				account=new SavingAccount(sum, passWord, name, personId, email);
				type=Constant.SAVING_ACCOUNT;
			}
		}
		sum++;
		properties.put("sum", sum);
		new BussinessPanel(account);
		dao.insert(account,type);
	}
	public boolean modify(long id,long passWord, String name, String personId, String email,Boolean ceiling,Boolean loan,double credit) throws RegisterException{
		try {
			Account account=dao.search(id);
			account.passWord=passWord;
			account.name=name;
			account.personId=personId;
			account.email=email;
			if(account instanceof CreditAccount)
				((CreditAccount)account).ceiling=credit;
			dao.update(account);
		} catch (Exception e) {
			System.err.println("modify error");
		}
		return false;
	}
	public void login(long id,long passWord) throws LogInException{
		try {
			Account account=dao.search(id);
			if(account.passWord==passWord) {
				new BussinessPanel(account);
				return;
			}else
				throw new LogInException();
		} catch (Exception e) {
			throw new LogInException();
		}
	}
	public void login(long id) throws LogInException{
		try {
			Account account=dao.search(id);
			new BussinessPanel(account);
		} catch (Exception e) {
			throw new LogInException();
		}
	}
	public void deposit(Account account,double money){
		account.depoist(money);
		dao.update(account);
	}
	public void withdraw(Account account,double money) throws BalanceNotEnoughException{
		account.withdraw(money);
		dao.update(account);
	}
	public void loan(Account account,double money) throws LoanException{
		if(account instanceof Loanable) {
			((Loanable) account).requesLoan(money);
			dao.update(account);
		}else
			throw new LoanException();
	}
	public void payLoan(Account account,double money) throws LoanException{
		if(account instanceof Loanable) {
			((Loanable) account).payLoan(money);
			dao.update(account);
		}else
			throw new LoanException();
	}
	public void transfer(Account account, long to,double money) throws BalanceNotEnoughException ,ATMException{
		try {
			Account target=dao.search(to);
			target.depoist(money);
			dao.update(target);
			account.withdraw(money);
			dao.update(account);
		} catch (BalanceNotEnoughException e) {
			throw new BalanceNotEnoughException();
		} catch (Exception e) {
			throw new ATMException();
		}
	}
	public double getBalanceSum() {
		double balance_sum=0;
		for(long i=100001;i<=sum;i++) {
			try {
				Account account=dao.search(i);
				balance_sum+=account.balance;
			} catch (Exception e) {
				System.err.println("get sum search error");
			}
		}
		return balance_sum;
	}
	public double getCeilingSum() {
		double ceiling_sum=0;
		for(long i=10001;i<=sum;i++) {
			try {
				Account account=dao.search(i);
				if(account instanceof CreditAccount)
					ceiling_sum+=((CreditAccount) account).ceiling;
			} catch (Exception e) {
				System.err.println("get ceiling search error");
			}
		}
		return ceiling_sum;
	}
	private static Bank bank;
	private Bank() {
		try {
			InputStream inputStream=this.getClass().getResourceAsStream("dao_config.dll");
			properties=new Properties();
			properties.load(inputStream);
			String str=properties.getProperty("dao");
			switch (str) {
			case Constant.COLLECTION:
				daoCreator=new AccountDAOCollectionCreator();
				break;
			case Constant.FILE:
				daoCreator=new AccountDAOFileCreator();
				break;
			case Constant.JDBC:
				daoCreator=new AccountDAOJDBCCreator();
				break;
			case Constant.ARRAY:
				daoCreator=new AccountDAOArrayCreator();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		sum=Long.valueOf(properties.getProperty("sum"));
		dao=daoCreator.createDAO();
	}
	public static Bank getBank() {
		if(bank==null)
			bank=new Bank();
		return bank;
	}
}
