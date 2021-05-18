package bank.views;

import bank.models.Application;
import bank.models.Customer;
import diwhy.iHateScanner;

public class ApplicationView {
    public static iHateScanner s = new iHateScanner();
	public void newApplication(Customer custArg) {
		Application app = new Application(custArg.getId());
		Application.addApplication(app);
		System.out.println("Application submitted!");
	}
}
