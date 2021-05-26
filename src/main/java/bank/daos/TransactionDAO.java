package bank.daos;

import java.math.BigDecimal;
import java.util.List;

import bank.models.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public interface TransactionDAO {
//	public boolean addTransaction(Transaction t);
//	public boolean removeTransaction(Transaction t);
	public List<Transaction> getListOfTransaction();
	public Transaction findById(int id);
	public List<Transaction> filterBySourceAccount(int id);
	public List<Transaction> filterByDestinationAccount(int id);
	public boolean withdraw(int id, BigDecimal amount);
	public boolean deposit(int id, BigDecimal amount);
	public boolean transfer(int sourceID, int destinationID, BigDecimal amount);
}
