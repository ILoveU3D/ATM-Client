package dao.factory;

import dao.impl.AccountDAO;
import dao.impl.AccountDAOFileImpl;

public class AccountDAOFileCreator implements AccountDAOCreator {
	final String src="data.txt";
	@Override
	public AccountDAO createDAO() {
		return new AccountDAOFileImpl(src);
	}

}
