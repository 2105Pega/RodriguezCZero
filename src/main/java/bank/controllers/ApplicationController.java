package bank.controllers;

import bank.models.Customer;
import bank.views.ApplicationView;
import diwhy.iHateScanner;
/** Should probably, definitely make these singleton**/
public class ApplicationController {
	ApplicationView view = new ApplicationView();
    public static iHateScanner s = new iHateScanner();
	public void newApplictionView(Customer custArg) {
		view.newApplication(custArg);
	}
}
