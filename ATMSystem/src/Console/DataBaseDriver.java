package Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class DataBaseDriver {
	private static String driver;
	private static String source;
	private static String user;
	private static String password;
	private static Connection connection=null;
	private static PreparedStatement preparedStatement=null;
	static {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream("src/config.dll");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties properties=new Properties();
		try {
			properties.load(inputStream);
			driver=properties.getProperty("driver");
			source=properties.getProperty("source");
			user=properties.getProperty("user");
			password=properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection=DriverManager.getConnection(source, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet sendMessage(String sql){
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			return preparedStatement.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
