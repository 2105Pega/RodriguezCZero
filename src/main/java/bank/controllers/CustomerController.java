package bank.controllers;

import bank.models.Customer;
import bank.views.CustomerView;
import diwhy.iHateScanner;

public class CustomerController {
    public static iHateScanner s = new iHateScanner();
	CustomerView view = new CustomerView();
	ApplicationController appCon = new ApplicationController();
	TransactionController transCon = new TransactionController();
	public void getHomeScreen(Customer custArg) {
		String action =	view.getCustomerView(custArg);
		switch(action.toLowerCase()) {
		case "with":
			break;
		case "depo":
			transCon.getHome(custArg);
			break;
		case "apply":
			appCon.newApplictionView(custArg);
			break;
		default:
			System.out.println("I'm sorry, I didn't quite catch that");
			return;
		}
	}
}
