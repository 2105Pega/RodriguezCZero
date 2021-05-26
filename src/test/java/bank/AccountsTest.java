package bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bank.controllers.LoginController;
import bank.daos.CustomerImpl;

class AccountsTest {
	String unimp = "Not yet implemented";
	CustomerImpl cust = new CustomerImpl();
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	// A registered user can login with their username and password
	@Test
	void test() {
		assertTrue(cust.tryLogin("eve", "password"));
	}
	
	@Test
	// An unregistered user can register by creating a username and password
	@DisplayName("Unregistered User")
	void intruder() {
		assertTrue(cust.tryRegister("test", "test"));
		assertFalse(cust.tryRegister("eve", "password"));
	}
	@Test
	// An Admin can view, create, update, and delete all users.
	void godMode() {
		fail(unimp);
	}
	@Test
	// A user can view their own existing accounts and balances.
	void userManage() {
		fail(unimp);
	}
	@Test
	// A user can create an account.
	void apply() {
		fail(unimp);
	}
	@Test
	// A user can delete an account if it is empty.
	void deleteOnEmpy() {
		fail(unimp);
	}
	@Test
	// A user can add to or withdraw from an account.
	void deposit() {
		fail(unimp);
	}
	@Test
	void withdraw() {
		fail(unimp);
	}
	@Test
	void overage() {
		fail(unimp);
	}
	//@Test
	// A user can execute multiple deposits or withdrawals in a session.
	// ummmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm. ok.
	@Test
	// A user can logout.
	void logout() {
		LoginController session = new LoginController();
		session.logout();
		assertTrue(Main.getUserID() == -1);
	}
	@Test
	// Reject wrong passwords
	void getTheStickyNote() {
		assertFalse(cust.tryRegister("eve", "dude"));
	}
	@Test
	// A user may view transaction history
	void whyIsTheRumGone() {
		fail(unimp);
	}
}
