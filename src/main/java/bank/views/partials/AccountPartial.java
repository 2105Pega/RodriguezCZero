package bank.views.partials;

import bank.daos.AccountImpl;
import bank.models.Account;

public class AccountPartial {
	public void viewBalance(int id) {
		AccountImpl ai = new AccountImpl();
		Account a = ai.findById(id);
		System.out.println(a.getBalance());
	}
}
