package bank.views;

import bank.models.Account;
import bank.models.Customer;
import bank.views.partials.AccountPartial;
import diwhy.iHateScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionView {
	private static final Logger logr = LogManager.getLogger(TransactionView.class);
	private AccountPartial ap = new AccountPartial();
	public static iHateScanner s = new iHateScanner();
	public double depositView(Customer custArg, Account acctArg) {
		ap.viewBalance(acctArg.getID());
		System.out.println("How much?");
		double deposit = Double.parseDouble(s.getLine());
		return deposit;
	}
	public double withdrawlView(Customer custArg, Account accArg) {
		// TODO Auto-generated method stub
		ap.viewBalance(accArg.getID());
		System.out.println("How much?");
		double withdrawl = Double.parseDouble(s.getLine());
		return withdrawl;
	}
}
