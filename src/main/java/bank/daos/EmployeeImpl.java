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

import bank.Main;
import bank.connection.PostgresAWSConnection;
import bank.models.Customer;
import bank.models.Employee;

public class EmployeeImpl implements EmployeeDAO {
	private static final Logger logr = LogManager.getLogger(EmployeeImpl.class);

	@Override
	public boolean addEmployee(Employee e) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){
			
			String sql = "insert into employees(f_name, l_name, roles_id)"
					+ "values(?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, e.getUsername()); 
			statement.setString(2, e.getPassword());
			statement.execute();
			
			return true;
			
		}
		catch (SQLException er) {
			// TODO Auto-generated catch block
			logr.error(er.getMessage());
		} catch (Exception er) {
			// TODO Auto-generated catch block
			logr.error(er.getMessage());
		}
		return false;
	}

	@Override
	public boolean removeEmployee(Employee e) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){			
			String sql = "Delete from employees where id = ?";			
			PreparedStatement statement = conn.prepareStatement(sql);			
			statement.setInt(1, e.getId());
			statement.execute();			
			return true;
			
		}
		catch (SQLException er) {
			// TODO Auto-generated catch block
			logr.error(er.getMessage());
		} catch (Exception er) {
			// TODO Auto-generated catch block
			logr.error(er.getMessage());
		}
		return false;
	}

	@Override
	public List<Employee> getListOfEmployee() {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){
			
			String sql = "select * from employees";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Employee> empList = new ArrayList<Employee>();
			
			while(result.next())
			{
				Employee e = new Employee (
						result.getInt("id"),
						result.getString("username"),
						result.getString("password")
				);
				
				empList.add(e);
			}
			
			return empList;
		}
		catch (SQLException er) {
			// TODO Auto-generated catch block
			logr.error(er.getMessage());
		} catch (Exception er) {
			// TODO Auto-generated catch block
			logr.error(er.getMessage());
		}
		
		return null;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){
			
			String sql = "select * from employees where id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id); //Inputs id value into the first "?"
			
			ResultSet result = statement.executeQuery();
			
			result.next(); //Gets the first row from our select statement
			
			Employee e = new Employee(
					result.getInt("id"),
					result.getString("username"),
					result.getString("password")
			);
			
			return e;
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean tryLogin(String username, String password) {
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			String sql = "select id,username,password from employees where lower(username) = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username.toLowerCase());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Employee e = new Employee(result.getInt("id"), result.getString("username"),
						result.getString("password"));
				if (password.equals(e.getPassword())) {
					Main.setSession("emp");
					Main.setAction("home");
					Main.setUserID(e.getId());
					logr.info("successful");
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

}
