package bank.connection;

import java.sql.Connection;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;

/** interfaces can't be static.
 *  So, I guess, copy and paste this for each connection. Lame
 **/
public class PostgresAWSConnection {

	public static Connection getConnection() throws Exception {
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/mainhub.properties");
			//FileInputStream fis = new FileInputStream("src/main/resources/postgresqlAWS.properties");
			Properties prop = new Properties();
			prop.load(fis);
			String driver = (String)prop.get("DRIVER");
			String url = (String)prop.get("URL");
			String username = (String)prop.get("USER");
			String pass = (String)prop.get("PASS");
			Class.forName(driver);
			return DriverManager.getConnection(url,username,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		
	}

}
