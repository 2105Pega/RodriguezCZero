package bank.views;

import bank.models.Account;
import bank.models.Customer;
import diwhy.iHateScanner;

public class TransactionView {
	public static iHateScanner s = new iHateScanner();
	public double depositView(Customer custArg, Account acctArg) {
		System.out.println("How much?");
		double deposit = s.getDouble();
		return deposit;
	}
}
