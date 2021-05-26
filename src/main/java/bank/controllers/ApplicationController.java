package bank.controllers;

import java.util.List;

import bank.models.Customer;
import bank.views.ApplicationView;
import diwhy.iHateScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationController {
	private static final Logger logr = LogManager.getLogger(ApplicationController.class);
	//ApplicationView view = new ApplicationView();
    public static iHateScanner s = new iHateScanner();
    public static void newApplicationView() {
    	
    }
	public static void newApplicationView(List<Customer> customers) {
		
	}
	public static void viewApplication(int customerID) {
		
	}
	public static void updateIntialAmount(int applicationID, double newAmount) {
		
	}
}
