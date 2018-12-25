package dao.factory;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import dao.impl.AccountDAO;
import dao.impl.AccountDAOJDBCImpl;
import java.util.Properties;
public class AccountDAOJDBCCreator implements AccountDAOCreator {
	InputStream in;
	Properties properties;
	final String src="config.dll";
	@Override
	public AccountDAO createDAO() {
		AccountDAO JDBCImpl=null;
		try {
			in=this.getClass().getResourceAsStream(src);
			properties=new Properties();
			properties.load(in);
			String driver=properties.getProperty("driver");
			String source=properties.getProperty("source");
			String user=properties.getProperty("user");
			String pwd=properties.getProperty("password");
			Class.forName(driver);
			JDBCImpl=new AccountDAOJDBCImpl(DriverManager.getConnection(source, user, pwd));
		}catch (NullPointerException e) {
			System.err.println("file not found");
		} catch (IOException e) {
			System.err.println("load error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return JDBCImpl;
	}

}
