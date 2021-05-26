package bank.daos;

import java.util.List;

import bank.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface CustomerDAO {
	public boolean addCustomer(Customer c);
	public boolean removeCustomer(Customer c);
	public List<Customer> getListOfCustomer();
	public Customer findById(int id);
	public boolean tryLogin(String username, String password);
	public boolean tryRegister(String username, String password);
}
