package dao.impl;

import java.sql.*;
import console.*;
import ui.Constant;
public class AccountDAOJDBCImpl implements AccountDAO{
	private Connection connection;
	private PreparedStatement preparedStatement;
	public AccountDAOJDBCImpl(Connection connection) {
		this.connection=connection;
	}
	@Override
	public void insert(Account account,String accountType) {
		try {
			preparedStatement=connection.prepareStatement("insert into AccountTable(id,password,name,personId,email,balance,accountType,ceiling) values(?,?,?,?,?,?,?,?)");
			preparedStatement.setLong(1, account.getId());
			preparedStatement.setLong(2, account.getPassWord());
			preparedStatement.setString(3, account.getName());
			preparedStatement.setString(4, account.getPersonId());
			preparedStatement.setString(5, account.getEmail());
			preparedStatement.setDouble(6, 0);
			preparedStatement.setString(7, accountType);
			if(account instanceof CreditAccount)
				preparedStatement.setDouble(8, ((CreditAccount) account).getCeiling());
			else 
				preparedStatement.setDouble(8, 0);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}catch (Exception e) {
			System.err.println("insert error");
		}
	}
	@Override
	public void update(Account account) {
		try {
			preparedStatement=connection.prepareStatement("update AccountTable set balance ="+account.getBalance()+" where id="+account.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement=connection.prepareStatement("update AccountTable set password ="+account.getPassWord()+" where id="+account.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement=connection.prepareStatement("update AccountTable set name ='"+account.getName()+"' where id="+account.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement=connection.prepareStatement("update AccountTable set personId ='"+account.getPersonId()+"' where id="+account.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement=connection.prepareStatement("update AccountTable set email ='"+account.getEmail()+"' where id="+account.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			if(account instanceof Loanable) {
				preparedStatement=connection.prepareStatement("update AccountTable set loan ="+(((Loanable) account).getLoan())+" where id="+account.getId());
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			if(account instanceof CreditAccount) {
				preparedStatement=connection.prepareStatement("update AccountTable set ceiling ="+(((CreditAccount) account).getCeiling())+" where id="+account.getId());
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
		} catch (Exception e) {
			System.err.println("update error");
			e.printStackTrace();
		}
	}
	@Override
	public Account search(long id) throws SQLException{
		Account account=null;
		preparedStatement=connection.prepareStatement("select * from AccountTable where id="+id);
		ResultSet resultSet=preparedStatement.executeQuery();
		resultSet.next();
		long passWord= resultSet.getLong("password");
		String name=resultSet.getString("name");
		String personId=resultSet.getString("personId");
		String email=resultSet.getString("email");
		double ceiling=0;
		double balance=resultSet.getDouble("balance");
		double loan=0;
		switch (resultSet.getString("accountType")) {
		case Constant.SAVING_ACCOUNT:
			account=new SavingAccount(id,passWord,name,personId,email);
			account.setBalance(balance);
			break;
		case Constant.CREDIT_ACCOUNT:
			ceiling=resultSet.getDouble("ceiling");
			account=new CreditAccount(id, passWord, name, personId, email, ceiling);
			account.setBalance(balance);
			break;
		case Constant.LOAN_SAVING_ACCOUNT:
			account=new LoanSavingAccount(id, passWord, name, personId, email);
			account.setBalance(balance);
			loan=resultSet.getDouble("loan");
			((Loanable)account).payLoan(loan);
			break;
		case Constant.lOAN_CREDIT_ACCOUNT:
			ceiling=resultSet.getDouble("ceiling");
			account=new LoanCreditAccount(id, passWord, name, personId, email, ceiling);
			account.setBalance(balance);
			loan=resultSet.getDouble("loan");
			((Loanable)account).payLoan(loan);
			break;
		}
		preparedStatement.close();
		return account;
	}
	@Override
	public void delete(Account account) {
		try {
			preparedStatement=connection.prepareStatement("delete from AccountTable where id="+account.getId());
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println("delete error");
		}
	}
}
