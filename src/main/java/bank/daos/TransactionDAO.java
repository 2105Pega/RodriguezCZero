package bank.daos;

import java.util.List;

import bank.models.Transaction;



public interface TransactionDAO {
	public boolean addTransaction(Transaction e);
	public boolean removeTransaction(Transaction e);
	public List<Transaction> getListOfTransaction();
	public Transaction findById(int id);
}
