package bank.daos;

import java.sql.Statement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.connection.MainhubKubeCluster;
import bank.connection.PostgresAWSConnection;
import bank.models.Transaction;

public class TransactionImpl implements TransactionDAO {
	private static final Logger logr = LogManager.getLogger(TransactionImpl.class);
	/**
	 * private int id; 
	 * private int sourceAccountID; 
	 * private int destinationAccountID; 
	 * private BigDecimal amount; 
	 */
//	@Override
//	public boolean addTransaction(Transaction t) {
//		// *
//		try (Connection conn = PostgresAWSConnection.getConnection()) {
//			String sql = "insert into transactions(source_account_id,destination_account_id,amount,succeeded" +
//							"values(?,?,?,?)";
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setInt(1, t.getsourceAccountID());
//			statement.setInt(2, t.getDestinationAccountID());
//			statement.setBigDecimal(3, t.getAmount());
//			statement.execute();
//			return true;
//		}
//		catch (SQLException er) {
//			// TODO Auto-generated catch block
//			logr.error(er.getMessage());
//		} catch (Exception er) {
//			// TODO Auto-generated catch block
//			logr.error(er.getMessage());
//		}
//		return false;
//	}
//
//	@Override
//	public boolean removeTransaction(Transaction t) {
//		// TODO Auto-generated method stub
//
//		try (Connection conn = PostgresAWSConnection.getConnection()) {
//			String sql = "delete from transactions where id = ?";
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setInt(1, t.getId());
//			statement.execute();
//			return true;
//		}
//		catch (SQLException er) {
//			// TODO Auto-generated catch block
//			logr.error(er.getMessage());
//		} catch (Exception er) {
//			// TODO Auto-generated catch block
//			logr.error(er.getMessage());
//		}
//		return false;
//	}

	@Override
	public List<Transaction> getListOfTransaction() {
		// TODO Auto-generated method stub

		try (Connection conn = PostgresAWSConnection.getConnection()) {
			String sql = "select * from transactions";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Transaction> transactions = new ArrayList<Transaction>();
			while(result.next()) {
				Transaction t = new Transaction(
						result.getInt("id"),
						result.getInt("source_account_id"),
						result.getInt("destination_account_id"),
						result.getBigDecimal("amount"));
				transactions.add(t);
			}
			return transactions;
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
	public Transaction findById(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			String sql = "select * from transaction where id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			Transaction t = new Transaction(
					result.getInt("id"),
					result.getInt("source_account_id"),
					result.getInt("destination_account_id"),
					result.getBigDecimal("amount"));
			return t;
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
	public List<Transaction> filterBySourceAccount(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			String sql = "select * from transactions where source_account_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery(sql);
			List<Transaction> transactions = new ArrayList<Transaction>();
			while(result.next()) {
				Transaction t = new Transaction(
						result.getInt("id"),
						result.getInt("source_account_id"),
						result.getInt("destination_account_id"),
						result.getBigDecimal("amount"));
				transactions.add(t);
			}
			return transactions;
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
	public List<Transaction> filterByDestinationAccount(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()) {
			String sql = "select * from transactions where destination_account_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery(sql);
			List<Transaction> transactions = new ArrayList<Transaction>();
			while(result.next()) {
				Transaction t = new Transaction(
						result.getInt("id"),
						result.getInt("source_account_id"),
						result.getInt("destination_account_id"),
						result.getBigDecimal("amount"));
				transactions.add(t);
			}
			return transactions;
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
	public boolean withdraw(int id, BigDecimal amount) {
		try (Connection conn = PostgresAWSConnection.getConnection()){
			String sql = "select * from new_withdrawl(?,?)";
			PreparedStatement statement = conn.prepareStatement(sql); 
			statement.setInt(1, id);
			statement.setBigDecimal(2, amount);
			ResultSet r = statement.executeQuery();
			logr.info(r.next());
			return r.getBoolean(0);
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
	public boolean deposit(int id, BigDecimal amount) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){
			String sql = "select * from new_deposit(?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setBigDecimal(2, amount);
			ResultSet r = statement.executeQuery();
			logr.info(r.next());
			return r.getBoolean(0);
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
	public boolean transfer(int sourceID, int destinationID, BigDecimal amount) {
		// TODO Auto-generated method stub
		try (Connection conn = PostgresAWSConnection.getConnection()){
			String sql = "select * from transfer(?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, sourceID);
			statement.setInt(2, destinationID);
			statement.setBigDecimal(3, amount);
			ResultSet r = statement.executeQuery();
			logr.info(r.next());
			return r.getBoolean(0);
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

}
