package bank;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import bank.controllers.CustomerController;
import bank.controllers.EmployeeController;
import bank.controllers.LoginController;
import bank.models.Account;
import bank.models.Customer;
import bank.models.Employee;
import bank.models.TheBank;
import diwhy.Serializer;
import diwhy.iHateScanner;

public class Main {
    Serializer ser = new Serializer();
    //CustomerView custView = new CustomerView();
    public static iHateScanner s = new iHateScanner();
    private static String session = "none";
    private static int userID = -1;
	public static void main(String[] args) {
		LoginController sessionController = new LoginController();
		CustomerController custCon = new CustomerController();
		EmployeeController empCon = new EmployeeController();
		startup();
		do{sessionController.getLogin();}
		while(userID == -1);
		switch(session) {
		case "cust":
			custCon.getHomeScreen(Customer.getCustomer(userID));
			break;
		case "emp":
			empCon.getAdmin();
			break;
		}
	}
    static boolean startup(){
        boolean successful = false;
        System.out.println("loading");
        HashMap<String,String> textFiles = new HashMap<>();
        textFiles.put("Account", "accounts.txt");
        textFiles.put("Application", "applications.txt");
        textFiles.put("Customer", "customers.txt");
        textFiles.put("Employee", "employees.txt");
        textFiles.put("Transaction", "transactions.txt");
        /**
         * https://stackoverflow.com/a/9620718
         * It will throw a FileNotFoundException if the file doesn't exist 
         * and **cannot** be created 
         * http://docs.oracle.com/javase/6/docs/api/java/io/FileOutputStream.html#FileOutputStream%28java.lang.String,%20boolean%29
         * but it will create it if it can. 
         * To be sure you probably should first test that the file exists before you create the 
         * FileOutputStream (and create with createNewFile() if it doesn't):
         */
        textFiles.forEach((k,v) -> {
            File file = new File(v);
            try{
                file.createNewFile();
            }catch(Exception e){
            	String[] errorMessage = new String[] {"Probably a permission error",e.getMessage()};
                System.out.println(errorMessage);
            }
        });
        File file = new File(textFiles.get("Account"));
        if (file.length() == 0){
            System.out.println("Uh oh, gotta populate test data!");
            Account theBank = new TheBank();
            Account.addAccount(theBank);
            System.out.println("added bank");
            Customer bob = new Customer("bob", "Secret");
            Customer steve = new Customer("steve", "apple");
            Customer eve = new Customer("eve","P@44WO4D");
            Customer susie = new Customer("susie","xwf28T9&^wToyg");
            ArrayList<Customer> customers = new ArrayList<>(Arrays.asList(bob,steve,eve,susie));
            try {
                Serializer.write(customers, textFiles.get("Customers"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            for (Customer customer : customers) {
				Customer.addCustomer(customer);
			}
            //Stream<Customer> customerStream = customers.stream();
            int bobCustId = customers.stream().filter((s) -> s.getUsername().equals("bob")).findFirst().get().getId();
            int steveCustId = customers.stream().filter((s) -> s.getUsername().equals("steve")).findFirst().get().getId(); 
            int eveCustId = customers.stream().filter((s) -> s.getUsername().equals("eve")).findFirst().get().getId();
            int susieCustId = customers.stream().filter((s) -> s.getUsername().equals("susie")).findFirst().get().getId();
            System.out.println("Looked for customers");
            ArrayList<Integer> jointIDs = new ArrayList<Integer>(Arrays.asList(steveCustId,eveCustId));
            Account a1 = new Account('p', jointIDs , 0.0D);
            Account a2 = new Account('o', susieCustId, 300.0);
            Account a3 = new Account('p', susieCustId);
            Account a4 = new Account('c', bobCustId);
            Account a5 = new Account('o', eveCustId, 500.00);
            ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(a1,a2,a3,a4,a5));
            try {
                Serializer.write(accounts, textFiles.get("Account"));
            } catch (Exception e) {
            	System.out.println("Serilized Account null?");
                System.out.println(e.getMessage());
            }
            for (Account account : accounts) {
				Account.addAccount(account);
			}
            Employee e1 = new Employee("dude","dude");
            try {
                Serializer.write(e1, textFiles.get("Employee"));
                System.out.println(e1);
            } catch (Exception e){
            	System.out.println("Serialized Employee null?");
                System.out.println(e.getMessage());
            }
            Employee.addEmployee(e1);
            return successful = true;
        }
        try {
            Customer.setAllCustomers(Serializer.read(textFiles.get("Customer")));
        }
        catch(Exception e) {
        	e.getMessage();
        }
        try {
        	Account.setAllAccounts(Serializer.read(textFiles.get("Account")));
        }catch(Exception e) {
        	e.getMessage();
        }
        try {
        	Employee.setAllEmployees(Serializer.read(textFiles.get("Employee")));
        } catch (Exception e) {
        	e.getMessage();
        }
        System.out.println("Returning to main . . .");
        return successful;
        //List<String> textFiles = Arrays.asList("accounts.txt","applications.txt","");
    }
    public static void setSession(String arg){
        session = arg;
    }
    public static String getSession(){
        return session;
    }
    public static void setUserID(int arg){
        userID = arg;
    }
    public static int getUserID(){
        return userID;
    }
}
