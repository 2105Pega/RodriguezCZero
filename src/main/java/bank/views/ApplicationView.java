package bank.views;

import java.util.List;

import bank.Main;
import bank.controllers.CustomerController;
import bank.daos.ApplicationImpl;
import bank.models.Application;
import bank.models.Customer;
import diwhy.iHateScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationView {
	private static final Logger logr = LogManager.getLogger(ApplicationView.class);
    public static iHateScanner s = new iHateScanner();
    ApplicationImpl app = new ApplicationImpl();
	public static void newApplication() {
		Main.getCustomer();
		System.out.println("Application submitted!");
		Main.setAction("home");
	}
}
