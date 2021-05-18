package bank.controllers;

import bank.views.EmployeeView;

public class EmployeeController {
	EmployeeView view = new EmployeeView();
	public void getAdmin() {
		view.adminView();
	}
}
