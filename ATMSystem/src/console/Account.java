package console;
import exception.BalanceNotEnoughException;
public abstract class Account {
	protected long id;
	protected long passWord;
	protected String name;
	protected String personId;
	protected String email;
	protected double balance;
	public Account(long id, long passWord, String name, String personId, String email) {
		this.id = id;
		this.passWord = passWord;
		this.name = name;
		this.personId = personId;
		this.email = email;
		this.balance = 0;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPassWord(long passWord) {
		this.passWord = passWord;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getPassWord() {
		return passWord;
	}
	public String getName() {
		return name;
	}
	public String getPersonId() {
		return personId;
	}
	public String getEmail() {
		return email;
	}
	public double getBalance() {
		return balance;
	}
	public final Account depoist(double money) {
		balance+=money;
		return this;
	}
	public abstract Account withdraw(double money) throws BalanceNotEnoughException;
}
