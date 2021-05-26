package bank;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import bank.controllers.ApplicationController;
import bank.controllers.CustomerController;
import bank.controllers.EmployeeController;
import bank.controllers.LoginController;
import bank.controllers.TransactionController;
import bank.models.Account;
import bank.models.Application;
import bank.models.Customer;
import bank.models.Employee;
import bank.models.Transaction;
import diwhy.Serializer;
import diwhy.iHateScanner;

public class Main {
	Serializer ser = new Serializer();
	// CustomerView custView = new CustomerView();
	public static iHateScanner s = new iHateScanner();
	private static String session = "none";
	private static int userID = -1;
	static String action = "login";
	static Customer customer;
	static Employee employee;
	static Account account;
	static int CustomerID;
	static int ApplicationID;
	static int AccountID;
	public static void main(String[] args) {
		LoginController sessionController = new LoginController();
		CustomerController custCon = new CustomerController();
		EmployeeController empCon = new EmployeeController();
		TransactionController transCon = new TransactionController();
		ApplicationController appCon = new ApplicationController();
		while (!action.contentEquals("logout")) {
			while (userID == -1) {
				sessionController.getLogin();
			}
			System.out.println("Session changed");
			System.out.println(session);
			switch (session) {
			case "cust":
				switch(action) {
				case "home":
					custCon.getHomeScreen();
					break;
				case "with":
					custCon.getWithdrawlScreen();
					break;
				case "depo":
					custCon.getDepositScreen();
					break;
				case "apply":
					appCon.newApplicationView();
					break;
				}
				break;
			case "emp":
				switch(action) {
				case "home":
					empCon.getAdmin();
					break;
				case "getCustomer":
					break;
				case "updateCustomer":
					break;
				case "deleteCustomer":
					break;
				case "getApplication":
					break;
				case "updateApplication":
					break;
				case "getAccount":
					break;
				case "updateAccount":
					break;
					
				}
				break;
			}
		}
	}


	public static void setSession(String arg) {
		session = arg;
	}

	public static String getSession() {
		return session;
	}

	public static void setUserID(int arg) {
		userID = arg;
	}

	public static int getUserID() {
		return userID;
	}
	public static void setCustomer(Customer cust) {
		customer = cust;
	}
	public static Customer getCustomer() {
		return customer;
	}
	public static void setAction(String arg) {
		Main.action = arg;
	}


	public static Account getAccount() {
		return account;
	}


	public static void setAccount(Account account) {
		Main.account = account;
	}
}
