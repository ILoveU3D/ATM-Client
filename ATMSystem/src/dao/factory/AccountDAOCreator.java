package dao.factory;

import dao.impl.AccountDAO;

public interface AccountDAOCreator {
	AccountDAO createDAO();
}
