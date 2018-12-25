package dao.factory;

import dao.impl.AccountDAO;
import dao.impl.AccountDAOArrayImpl;

public class AccountDAOArrayCreator implements AccountDAOCreator {

	@Override
	public AccountDAO createDAO() {
		return new AccountDAOArrayImpl();
	}

}
