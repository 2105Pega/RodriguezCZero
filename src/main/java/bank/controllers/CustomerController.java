package bank.controllers;

import java.util.HashMap;

import bank.Main;
import bank.daos.CustomerImpl;
import bank.models.Customer;
import bank.views.CustomerView;
import diwhy.iHateScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerController {
	private static final Logger logr = LogManager.getLogger(CustomerController.class);
    public static iHateScanner s = new iHateScanner();
	CustomerView view = new CustomerView();
	CustomerImpl data = new CustomerImpl();
	TransactionController trans = new TransactionController();
	public void getHomeScreen() {
		String action =	view.getCustomerHome(Main.getCustomer());
		switch(action.toLowerCase()) {
		case "with":
			Main.setAction("with");
			break;
		case "depo":
			Main.setAction("depo");
			break;
		case "apply":
			Main.setAction("apply");
			break;
		case "logout":
			new LoginController().logout();
		default:
			System.out.println("I'm sorry, I didn't quite catch that");
			return;
		}
	}
	public void getWithdrawlScreen() {
		double withdrawl = 0;
		withdrawl = view.getWithHome(Main.getCustomer());
		if (withdrawl == 0) {
			System.out.println("Going Home");
			Main.setAction("home");
		}
		trans.getDepositForm(Main.getCustomer(),Main.getAccount(),withdrawl );
		Main.setAction("home");
	}
	public void getDepositScreen() {
		double deposit = view.getDepositScreen(Main.getCustomer());
		if (deposit == 0) {
			System.out.println("Going Home");
			Main.setAction("home");			
		}
		trans.getDepositForm(Main.getCustomer(), Main.getAccount(), deposit);
	}
}
