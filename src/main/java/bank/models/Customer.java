package bank.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import diwhy.Serializer;

public class Customer implements Serializable {
    private static ArrayList<Customer> allCustomers = new ArrayList<>();
    public static void setAllCustomers(ArrayList<Customer> allCustomers) {
		Customer.allCustomers = allCustomers;
	}
	private static AtomicInteger uniqueId=new AtomicInteger();
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private Collection<Integer> accounts;
    private String password;
    private static final String file = "customers.txt";

    /** Might have to use this one when reading persisted objects */
    public Customer(int id, String usernameArg,String passwordArg, Collection<Integer> accountsArg) {
        this.id = uniqueId.getAndIncrement();
        this.username = usernameArg;
        this.password = passwordArg;
        this.accounts = accountsArg;
    }
    public Customer(String usernameArg, String passwordArg){
        this.id = uniqueId.getAndIncrement();
        this.username = usernameArg;
        this.password = passwordArg;
    }
    public Customer(String usernameArg,String passwordArg) {
        this.username = usernameArg;
        this.password = passwordArg;
    }
    public Customer(){
        super();
    }
    @Override
    public String toString() {
        return "Customer [accounts=" + accounts + ", id=" + id + ", username=" + username + "]";
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String usernameArg) {
        this.username = usernameArg;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String passwordArg){
        this.password = passwordArg;
    }
    public static void addCustomer(Customer custArg){
        allCustomers.add(custArg);
        try {
        	Serializer.write(allCustomers, file);
        }
        catch(Exception e) {
        	e.getMessage();
        }
    }
    public static ArrayList<Customer> getCustomers(){
        return allCustomers;
    }
    public static Customer getCustomer(int idArg) {
    	return allCustomers.stream().filter(c-> c.getId() == idArg).findFirst().get();
    }
    //public static Customer searchCustomer() {
    //}
}
