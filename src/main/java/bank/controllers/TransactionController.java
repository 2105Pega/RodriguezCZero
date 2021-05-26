package bank.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import bank.daos.CustAccImpl;
import bank.daos.TransactionImpl;
import bank.models.Account;
import bank.models.Customer;
import bank.models.Transaction;
import bank.views.TransactionView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionController {
	public static final Logger logger = LogManager.getLogger(TransactionController.class);
	TransactionView view = new TransactionView();
	TransactionImpl trans = new TransactionImpl();
	public void getDepositForm(Customer custArg, Account accArg,double deposit) {
		//double deposit = view.depositView(custArg, accArg);
		trans.deposit(accArg.getID(), BigDecimal.valueOf(deposit));
	}
	public void getWithdrawlForm(Customer custArg, Account accArg,double withdrawl) {
		//double withdrawl = view.withdrawlView(custArg, accArg);
		if (withdrawl > accArg.getBalance()) {
			logger.info(custArg.getUsername() + "tried to take too much money!");
			return;
		}
		trans.deposit(accArg.getID(), BigDecimal.valueOf(withdrawl));
	}
	public void getHome(Customer custArg) {
		//view to select which account... for now, just get the first one.
		CustAccImpl custacc = new CustAccImpl();
		List<Account> custAccounts = custacc.filterByCustomer(custArg.getId());
		if (custAccounts.size() == 0) {
			System.out.println("You have no accounts!!!");
			return;
		}
		//getDepositForm(custArg, custAccounts.get(0));
	}
}
