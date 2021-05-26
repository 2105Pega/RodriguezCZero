package bank.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import bank.Main;
import bank.daos.AccountImpl;
import bank.daos.CustAccImpl;
import bank.daos.CustAppImpl;
import bank.models.Account;
import bank.models.Application;
import bank.models.Customer;
import bank.views.partials.AccountPartial;
import diwhy.iHateScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerView {
	private static final Logger logr = LogManager.getLogger(CustomerView.class);
	public static iHateScanner s = new iHateScanner();
	public CustAccImpl ca = new CustAccImpl();
	public CustAppImpl custapp = new CustAppImpl();
	public AccountImpl account = new AccountImpl();
	
	public String getCustomerHome(Customer custArg) {
		String response = "";
		System.out.println("Welcome home, " + custArg.getUsername());
		//List<Account> custAccounts = ca.filterByCustomer(Main.getUserID());
		List<Account> custAccounts = ca.filterByCustomer(custArg.getId());
		
		if (custAccounts.size() == 0) {
			System.out.println("You have no accounts!!!");
		}
		if (custAccounts.size() > 0) {
			for (Account account : custAccounts) {
				System.out.println(account.toString());
			}
		}
		List<Application> custApplications = custapp.filterByCustomerId(custArg.getId());
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
	public double getWithHome(Customer customer) {
		// TODO Auto-generated method stub
		List<Account> custAccounts = ca.filterByCustomer(customer.getId());
		if (custAccounts.size() == 0) {
			System.out.println("ಠ_ಠ you have no accounts");
			return 0;
		}
		HashMap<Integer,Integer> accounts = new HashMap<Integer,Integer>();
		for (int i = 0; i < custAccounts.size(); i++) {
			int accountID = custAccounts.get(i).getID();
			accounts.put(i+1, accountID);
			System.out.print(i + " ");
			new AccountPartial().viewBalance(accountID);
		}
		System.out.println("Which account do you want to withdraw from?");
		int choice = 0;
		double amount = 0;
		try {
			choice = Integer.parseInt(s.getLine());
		} catch(NumberFormatException e) {
			logr.error("Expected int, got " + choice);
			System.out.println("ummmmm go back to home");
			return 0;
		}
		System.out.println("How much?");
		try {
			amount = Double.parseDouble(s.getLine());
		} catch (NumberFormatException e) {
			logr.error("Expected int, got " + amount);
			System.out.println("ummmmm go back to home");
			return 0;
		}
		if (amount > account.findById(accounts.get(choice)).getBalance()){
			System.out.println("You don't got that much!");
			System.out.println("Go back home");
			return 0;
		}
		Main.setAccount(account.findById(choice));
		return amount;
	}
	public double getDepositScreen(Customer customer) {
		// TODO Auto-generated method stub
		List<Account> custAccounts = ca.filterByCustomer(customer.getId());
		if (custAccounts.size() == 0) {
			System.out.println("ಠ_ಠ you have no accounts");
			return 0;
		}
		HashMap<Integer,Integer> accounts = new HashMap<Integer,Integer>();
		for (int i = 0; i < custAccounts.size(); i++) {
			int accountID = custAccounts.get(i).getID();
			accounts.put(i+1, accountID);
			System.out.print(i + " ");
			new AccountPartial().viewBalance(accountID);
		}
		System.out.println("Which account do you want to deposit to?");
		int choice = 0;
		double amount = 0;
		try {
			choice = Integer.parseInt(s.getLine());
		} catch(NumberFormatException e) {
			logr.error("Expected int, got " + choice);
			System.out.println("ummmmm go back to home");
			return 0;
		}
		System.out.println("How much?");
		try {
			amount = Double.parseDouble(s.getLine());
		} catch (NumberFormatException e) {
			logr.error("Expected int, got " + amount);
			System.out.println("ummmmm go back to home");
			return 0;
		}
		Main.setAccount(account.findById(choice));
		return amount;
	}
}
