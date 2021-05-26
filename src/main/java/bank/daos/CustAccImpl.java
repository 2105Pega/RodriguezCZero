package bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.connection.PostgresAWSConnection;
import bank.models.Account;
import bank.models.Customer;
import bank.models.CustomerAccount;

public class CustAccImpl implements CustomerAccountDAO {
	private static final Logger logr = LogManager.getLogger(CustAccImpl.class);

	@Override
	public boolean addCustomerAccount(CustomerAccount ca) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCustomerAccount(CustomerAccount ca) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Customer> getListOfCustomersOnAccount(int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> filterByCustomer(int customerID) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){
			String sql ="select * from accounts ac inner join customeraccounts ca on ac.id = ca.account_id inner join customers cu on cu.id = ca.customer_id where cu.id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet result = statement.executeQuery();
			List<Account> accounts = new ArrayList<Account>();
			while(result.next()) {
				Account a = new Account(
						result.getInt("id"),
						result.getString("status").charAt(0),
						result.getDouble("balance"));
				accounts.add(a);
			}
			return accounts;
		}
		// TODO Auto-generated method stub
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		}
		return null;
	}
	
}
