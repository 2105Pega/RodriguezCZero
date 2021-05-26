package bank.daos;

import java.util.List;

import bank.models.Application;
import bank.models.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface CustAppDAO {
	public List<Application> filterByCustomerId(int customerID);
	public List<Customer> filterByApplicationId(int applicationID);
}
