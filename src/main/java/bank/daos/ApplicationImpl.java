package bank.daos;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.spi.DirStateFactory.Result;

import bank.connection.PostgresAWSConnection;
import bank.models.Application;

public class ApplicationImpl implements ApplicationDAO {
	private static final Logger logr = LogManager.getLogger(ApplicationImpl.class);
//    private int id;
//    private double intialAmount = 0.00D;  
//    private char status;
	@Override
	public boolean addApplication(Application app) {
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "insert into applications(intial_amount,status) values(?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBigDecimal(1, app.getIntialAmount());
			statement.setString(2, String.valueOf(app.getStatus()));
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
	public boolean removeApplication(Application app) {
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "delete from applications where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, app.getId());
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
	public List<Application> getListOfApplication() {
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "select * from applications";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);
			List<Application> appList = new ArrayList<Application>();
			while (result.next()) {
				Application app = new Application(result.getInt("id"), result.getBigDecimal("initial_amount"),
						result.getString("status").charAt(0));
				appList.add(app);
			}
			return appList;
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
	public Application findById(int id) {
		// *
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			/*
			 * / try (Connection conn = MainhubKubeCluster.getConnection()){ //
			 */
			String sql = "select * from applications where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			Application application = new Application(result.getInt("id"), result.getBigDecimal("initial_amount"),
					result.getString("status").charAt(0));
			return application;
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
	public void addCustomer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateApplication(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
