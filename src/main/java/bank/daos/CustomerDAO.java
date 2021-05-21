package bank.daos;

import java.util.List;

import bank.models.Customer;

public interface CustomerDAO {
	public boolean addCustomer(Customer e);
	public boolean removeEmployee(Customer e);
	public List<Customer> getListOfCustomer();
	public Customer findById(int id);
}
