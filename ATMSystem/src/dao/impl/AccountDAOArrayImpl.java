package dao.impl;

import console.Account;

public class AccountDAOArrayImpl implements AccountDAO {
	private int length;
	private Account[] arr;
	public AccountDAOArrayImpl() {
		length=0;
		arr=new Account[10000];
	}
	@Override
	public void insert(Account account, String accontType) {
		arr[length]=account;
		length++;
	}

	@Override
	public void update(Account account) {
		for(int i=0;i<length;i++) {
			if(arr[i].getId()==account.getId())
				arr[i]=account;
		}
	}

	@Override
	public Account search(long id) throws Exception {
		for(int i=0;i<length;i++) {
			if(arr[i].getId()==id)
				return arr[i];
		}
		return null;
	}

	@Override
	public void delete(Account account) {
		for(int i=0;i<length;i++) {
			if(arr[i].getId()==account.getId())
				for(int j=i;j<length-1;j++) {
					arr[j]=arr[j+1];
				}
		}
		length--;
	}

}
