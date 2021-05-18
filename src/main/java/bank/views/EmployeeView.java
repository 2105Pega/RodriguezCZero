package bank.views;

import bank.models.Account;
import bank.models.Application;

public class EmployeeView {
	
	public void adminView() {
		System.out.println(Account.getAccounts().toString());
		System.out.println(Application.getApplications().toString());
	}
}
