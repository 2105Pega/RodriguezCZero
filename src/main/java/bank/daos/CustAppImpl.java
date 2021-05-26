package bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.connection.PostgresAWSConnection;
import bank.models.Account;
import bank.models.Application;
import bank.models.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustAppImpl implements CustAppDAO{
	private static final Logger logr = LogManager.getLogger(CustAppImpl.class);

	@Override
	public List<Application> filterByCustomerId(int customerID) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){
			String sql ="select * from applications ap where cu.id = ? inner join customerapplications ca on ap.id = ca.account_id inner join customers cu on cu.id = ca.customer_id";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet result = statement.executeQuery();
			List<Application> applications = new ArrayList<Application>();
			while(result.next()) {
				Application a = new Application(
						result.getInt("id"),
						result.getBigDecimal("balance"),
						result.getString("status").charAt(0)
						);
				applications.add(a);
			}
			return applications;
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

	@Override
	public List<Customer> filterByApplicationId(int applicationID) {
		try (Connection conn = PostgresAWSConnection.getConnection()){
			String sql ="select * from applications inner join customerapplications on applications.id = customerapplications.account_id inner join customers on customers.id = customerapplications.customer_id where customers.id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, applicationID);
			ResultSet result = statement.executeQuery();
			List<Customer> customers = new ArrayList<Customer>();
			while(result.next()) {
				Customer c = new Customer(
						result.getInt("id"),
						result.getString("username"),
						result.getString("password")
						);
				customers.add(c);
			}
			return customers;
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
