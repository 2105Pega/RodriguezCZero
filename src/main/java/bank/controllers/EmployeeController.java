package bank.controllers;

import bank.views.EmployeeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeController {
	private static final Logger logr = LogManager.getLogger(EmployeeController.class);
	EmployeeView view = new EmployeeView();
	public void getAdmin() {
		view.adminView();
	}
}
