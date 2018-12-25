package dao.impl;

import java.io.*;
import com.google.gson.*;
import console.*;
import ui.Constant;

public class AccountDAOFileImpl implements AccountDAO{
	BufferedReader reader;
	BufferedWriter writer;
	String src;
	public AccountDAOFileImpl(String src) {
		this.src=src;
	}
	@Override
	public void insert(Account account, String accontType) {
		String obj=new Gson().toJson(account);
		try {
			System.out.println(obj);
			writer=new BufferedWriter(new FileWriter(src,true));
			writer.write(accontType+"\n");
			writer.write(obj+"\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.err.println("write error");
		}
	}
	@Override
	public void update(Account account) {
		try {
			String type = null,obj;
			Account account2 = search(account.getId());
			if(account2!=null) {
				delete(account2);
				obj=new Gson().toJson(account);
				if(account instanceof SavingAccount)
					type=Constant.SAVING_ACCOUNT;
				else if(account instanceof CreditAccount)
					type=Constant.CREDIT_ACCOUNT;
				else if(account instanceof LoanSavingAccount)
					type=Constant.LOAN_SAVING_ACCOUNT;
				else if(account instanceof LoanCreditAccount)
					type=Constant.lOAN_CREDIT_ACCOUNT;
				writer=new BufferedWriter(new FileWriter(src,true));
				writer.write(type+"\n");
				writer.write(obj+"\n");
				writer.flush();
				writer.close();
			}
		} catch (FileNotFoundException e) {
			System.err.println("file not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Account search(long id) throws Exception {
		reader=new BufferedReader(new FileReader(src));
		String type,obj;
		Account account = null;
		while((type=reader.readLine())!=null) {
			obj=reader.readLine();
			switch (type) {
			case Constant.SAVING_ACCOUNT:
				account=new Gson().fromJson(obj,SavingAccount.class);
				break;
			case Constant.CREDIT_ACCOUNT:
				account=new Gson().fromJson(obj, CreditAccount.class);
				break;
			case Constant.LOAN_SAVING_ACCOUNT:
				account=new Gson().fromJson(obj, LoanSavingAccount.class);
				break;
			case Constant.lOAN_CREDIT_ACCOUNT:
				account=new Gson().fromJson(obj, LoanCreditAccount.class);
				break;
			}
			if(account.getId()==id)
				return account;
		}
		reader.close();
		return account;
	}

	@Override
	public void delete(Account account) {
		try {
			reader=new BufferedReader(new FileReader(src));
			String temp="";
			String type,obj;
			while((type=reader.readLine())!=null) {
				obj=reader.readLine();
				Account account2=null;
				switch (type) {
				case Constant.SAVING_ACCOUNT:
					account2=new Gson().fromJson(obj,SavingAccount.class);
					break;
				case Constant.CREDIT_ACCOUNT:
					account2=new Gson().fromJson(obj, CreditAccount.class);
					break;
				case Constant.LOAN_SAVING_ACCOUNT:
					account2=new Gson().fromJson(obj, LoanSavingAccount.class);
					break;
				case Constant.lOAN_CREDIT_ACCOUNT:
					account2=new Gson().fromJson(obj, LoanCreditAccount.class);
					break;
				}
				if(account.getId()!=account2.getId())
					temp+=type+"\n"+obj+"\n";
			}
			reader.close();
			writer=new BufferedWriter(new FileWriter(src,false));
			writer.write(temp);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
