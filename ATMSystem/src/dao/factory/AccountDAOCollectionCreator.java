package dao.factory;

import dao.impl.AccountDAO;
import dao.impl.AccountDAOCollectionImpl;

public class AccountDAOCollectionCreator implements AccountDAOCreator {

	@Override
	public AccountDAO createDAO() {
		return new AccountDAOCollectionImpl();
	}

}
