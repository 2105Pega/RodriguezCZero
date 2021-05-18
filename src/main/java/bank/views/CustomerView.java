package bank.views;

import java.util.ArrayList;

import bank.Main;
import bank.models.Account;
import bank.models.Application;
import bank.models.Customer;
import diwhy.iHateScanner;

public class CustomerView {
	public static iHateScanner s = new iHateScanner();

	public String getCustomerView(Customer custArg) {
		String response = "";
		ArrayList<Account> custAccounts = new ArrayList<Account>();
		ArrayList<Account> accounts = Account.getAccounts();
		for (Account account : accounts) {
			for (Integer custID : account.getCustomers()) {
				if(Customer.getCustomers().stream().filter(c -> c.getId() == custID).findFirst().isEmpty()){
					custAccounts.add(account);
				}
			}
		}
		if (custAccounts.size() == 0) {
			System.out.println("You have no accounts!!!");
		}
		if (custAccounts.size() > 0) {
			for (Account account : custAccounts) {
				System.out.println(account.toString());
			}
		}
		ArrayList<Application> custApplications = new ArrayList<Application>();
		ArrayList<Application> applications = Application.getApplications();
		for (Application application : applications) {
			for (Integer custID : application.getCustomers()) {
				custApplications.add(application);
			}
		}
		if (custApplications.size() == 0) {
			System.out.println("You have no applications!!!");
		}
		if (custApplications.size() > 0) {
			for (Application application : custApplications) {
				application.toString();
			}
		}
		System.out.println("What would you like to do?");
		System.out.println("\"With\" to withdrawl, \"Depo\" to deposit, \"apply\" to make a new application");
		response = s.getLine();
		return response;
	}
}
