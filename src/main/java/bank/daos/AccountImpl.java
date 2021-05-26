package bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.connection.PostgresAWSConnection;
import bank.models.Account;

public class AccountImpl implements AccountDAO {
	private static final Logger logr = LogManager.getLogger(AccountImpl.class);

	@Override
	public boolean addAccount(Account a) {
		// TODO Auto-generated method stub
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "insert into accounts(status,balance) values (?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, String.valueOf(a.getStatus()));
			statement.setDouble(2, a.getBalance());
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeAccount(Account e) {
		// TODO Auto-generated method stub
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "delete from accounts where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, e.getID());
			statement.execute();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> getListOfAccounts() {
		// TODO Auto-generated method stub
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "select * from accounts";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Account> accounts = new ArrayList<Account>();
			while (result.next()) {
				Account a = new Account(result.getInt("id"), result.getString("status").charAt(0),
						result.getDouble("balance"));
				accounts.add(a);
			}
			return accounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub

		try (Connection conn = PostgresAWSConnection.getConnection()) {

			String sql = "select * from accounts where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			Account a = new Account(result.getInt("id"), result.getString("status").charAt(0),
					result.getDouble("balance"));
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
