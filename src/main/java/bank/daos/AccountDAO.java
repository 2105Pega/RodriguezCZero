package bank.daos;

import java.util.List;

import bank.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface AccountDAO {
	public boolean addAccount(Account e);
	public boolean removeAccount(Account e);
	public List<Account> getListOfAccounts(); 
	public Account findById(int id);
}
