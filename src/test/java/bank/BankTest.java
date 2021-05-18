package bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import bank.models.Account;
import bank.models.Customer;
import bank.models.Employee;
import diwhy.Serializer;

class BankTest {

	@BeforeAll
	void setUp() throws Exception {
        Customer bob = new Customer("bob", "Secret");
        Customer steve = new Customer("steve", "apple");
        Customer eve = new Customer("eve","P@44WO4D");
        Customer susie = new Customer("susie","xwf28T9&^wToyg");
        ArrayList<Customer> customers = new ArrayList<>(Arrays.asList(bob,steve,eve,susie));
        try {
            Serializer.write(customers, "testCustomers.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Stream<Customer> customerStream = customers.stream();
        int bobCustId = customerStream.filter((s) -> s.getUsername().equals("bob")).findFirst().get().getId();
        int steveCustId = customerStream.filter((s) -> s.getUsername().equals("steve")).findFirst().get().getId(); 
        int eveCustId = customerStream.filter((s) -> s.getUsername().equals("eve")).findFirst().get().getId();
        int susieCustId = customerStream.filter((s) -> s.getUsername().equals("susie")).findFirst().get().getId();
        ArrayList<Integer> jointIDs = new ArrayList<Integer>(Arrays.asList(steveCustId,eveCustId));
        Account a1 = new Account('p', jointIDs , 0.0D);
        Account a2 = new Account('o', susieCustId, 300.0);
        Account a3 = new Account('p', susieCustId);
        Account a4 = new Account('c', bobCustId);
        Account a5 = new Account('o', eveCustId, 500.00);
        ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(a1,a2,a3,a4,a5));
        try {
            Serializer.write(accounts, "testAccounts.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Employee e1 = new Employee("dude","dude");
        try {
            Serializer.write(e1, "testEmployee.txt");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
    @Test
    @Tag("Startup")
    @DisplayName("File Creation and Detection")
    void persistanceTest(){
        assertTrue(Main.startup());   
    }

}
