package bank.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.Main;
import bank.connection.PostgresAWSConnection;
import bank.models.Customer;

public class CustomerImpl implements CustomerDAO {
//    private String username;
//    private String password;
	private static final Logger logr = LogManager.getLogger(CustomerImpl.class);

	@Override
	public boolean addCustomer(Customer c) {
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "insert into customers(username,password) values (?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, c.getUsername());
			statement.setString(2, c.getPassword());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			logr.info(rs.toString());
			System.out.println("Now try logging in!");
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		}

		return false;
	}

	@Override
	public boolean removeCustomer(Customer c) {
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "delete from customers where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, c.getId());
			statement.execute();
			return true;
		}
		// TODO Auto-generated method stub
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean tryLogin(String username, String password) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			String sql = "select * from customers where lower(username) = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username.toLowerCase());
			logr.info("Entered Password: "+ password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Customer c = new Customer(result.getInt("id"), result.getString("username"),
						result.getString("password"));
				logr.info(c.toString());
				if (password.equals(c.getPassword())) {
					logr.info("Passwords Matched!");
					Main.setSession("cust");
					Main.setUserID(c.getId());
					Main.setCustomer(c);
					Main.setAction("home");
					logr.info(Main.getUserID());
					return true;
				}
				return false;
			}
			System.out.println("Account not found!");
		}
		// TODO Auto-generated method stub
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		}

		return false;
	}

	@Override
	public List<Customer> getListOfCustomer() {
		// TODO Auto-generated method stub
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "select * from customers";
			Statement statement = conn.createStatement();
			List<Customer> custlist = new ArrayList<Customer>();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Customer c = new Customer(result.getInt("id"), result.getString("username"),
						result.getString("password"));
				custlist.add(c);
			}
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
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "select * from customers where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			Customer c = new Customer(result.getInt("id"), result.getString("username"), result.getString("password"));
			return c;
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
	public boolean tryRegister(String username, String password) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			String sql = "select username from customers where lower(username) = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username.toLowerCase());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				logr.error("User Exists");
				return false;
			}
			CustomerImpl custimpl = new CustomerImpl();
			custimpl.addCustomer(new Customer(username, password));
			return true;
		}
		// TODO Auto-generated method stub
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logr.error(e.getMessage());
		}
		return false;
	}

}
