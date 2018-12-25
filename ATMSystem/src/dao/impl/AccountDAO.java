package dao.impl;
import console.Account;
public interface AccountDAO {
	void insert(Account account,String accontType);
	void update(Account account);
	Account search(long id) throws Exception;
	void delete(Account account);
}
