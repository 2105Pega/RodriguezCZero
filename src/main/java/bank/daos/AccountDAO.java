package bank.daos;

import java.util.List;

import bank.models.Account;

public interface AccountDAO {
	public boolean addEmployee(Account e);
	public boolean removeEmployee(Account e);
	public List<Account> getListOfEmployee();
	public Account findById(int id);
}
