package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import console.Account;

public class AccountDAOCollectionImpl implements AccountDAO{
	private ArrayList<Account> arr;
	public AccountDAOCollectionImpl() {
		arr=new ArrayList<Account>();
	}
	@Override
	public void insert(Account account, String accontType) {
		arr.add(account);
	}

	@Override
	public void update(Account account) {
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i).getId()==account.getId())
				arr.set(i, account);
		}
	}

	@Override
	public Account search(long id) throws SQLException {
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i).getId()==id) {
				return arr.get(i);
			}
		}
		return null;
	}

	@Override
	public void delete(Account account) {
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i).getId()==account.getId())
				arr.remove(i);
		}
	}

}
