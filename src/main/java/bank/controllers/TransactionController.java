package bank.controllers;

import java.util.ArrayList;

import bank.models.Account;
import bank.models.Customer;
import bank.models.Transaction;
import bank.views.TransactionView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionController {
	public static final Logger logger = LogManager.getLogger(TransactionController.class);
	TransactionView view = new TransactionView();
	public void getDepositForm(Customer custArg, Account accArg) {
		double deposit = view.depositView(custArg, accArg);
		Transaction newTransaction = new Transaction(0, accArg.getID(), deposit);
		Transaction.addTransaction(newTransaction);
	}
	public void getHome(Customer custArg) {
		//view to select which account... for now, just get the first one.
		ArrayList<Account> custAccounts = new ArrayList<Account>();
		ArrayList<Account> accounts = Account.getAccounts();
		for (Account account : accounts) {
			for (Integer custID : account.getCustomers()) {
				custAccounts.add(account);
			}
		}
		if (custAccounts.size() == 0) {
			System.out.println("You have no accounts!!!");
			return;
		}
		getDepositForm(custArg, custAccounts.get(0));
	}
}
