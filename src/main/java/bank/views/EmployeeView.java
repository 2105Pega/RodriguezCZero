package bank.views;

import bank.daos.ApplicationImpl;
import bank.daos.CustAccImpl;
import bank.daos.CustAppImpl;
import bank.daos.CustomerImpl;
import bank.models.Account;
import bank.models.Application;
import bank.models.Customer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class EmployeeView {
	private static final Logger logr = LogManager.getLogger(EmployeeView.class);
	private CustAppImpl ca = new CustAppImpl();
	private CustAccImpl cac = new CustAccImpl();
	private ApplicationImpl appDAO = new ApplicationImpl();
	public void adminView() {
		System.out.println("Who do you wanna view?");
		/**
		 * https://stackoverflow.com/questions/2771439/jdbc-pagination
		 */
		System.out.println("Customers with Applications");
		List<Application> apps = appDAO.getListOfApplication();
		for (Application application : apps) {
			List<Customer> customers = ca.filterByApplicationId(application.getId());
			for (Customer customer : customers) {
				System.out.println("-----------------");
				System.out.println(customer.getUsername());
				System.out.println("-------------------");
			}
		}
		System.out.println("All Customers");
		for(Customer c : new CustomerImpl().getListOfCustomer() ) {
			System.out.println("---------------");
			System.out.println(c.getUsername());
			System.out.println("----------------");
		}
	}
}
