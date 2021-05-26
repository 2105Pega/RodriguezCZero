package bank.daos;

import java.util.HashMap;
import java.util.List;

import bank.models.Account;
import bank.models.Customer;
import bank.models.CustomerAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public interface CustomerAccountDAO {
	public boolean addCustomerAccount(CustomerAccount ca);
	public boolean removeCustomerAccount(CustomerAccount ca);
	public List<Customer> getListOfCustomersOnAccount(int accountID);
	public List<Account> filterByCustomer(int customerID);
}
