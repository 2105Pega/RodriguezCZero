package bank.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import diwhy.Serializer;

public class Account implements Serializable {
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    
    public static void setAllAccounts(ArrayList<Account> allAccounts) {
		Account.allAccounts = allAccounts;
	}
	private static final long serialVersionUID = 1L;
    private static AtomicInteger uniqueId=new AtomicInteger();
    private int ID;
    /**
     * o open
     * c closed
     * p pending
     */
    private char status;
    private Collection<Integer> customers = new ArrayList<>();
    private double balance = 0.00D;
    private static final String file = "accounts.txt";

    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void deposit(double deposit){
        this.balance += deposit;
    }
    public void withdraw(double withdrawl){
        if(this.balance < withdrawl){
            System.out.println("oops");
            return;
        }
        this.balance -= withdrawl;
    }
    public int getID() {
        return this.ID;
    }
    
    public Account() {
        super();
        this.ID = uniqueId.getAndIncrement();
    }
    
    public Account(char status, Collection<Integer> customers, double balance) {
        this.ID = uniqueId.incrementAndGet();
        this.status = status;
        this.customers = customers;
        this.balance = balance;
    }
    public Account(char status, Collection<Integer> customers) {
        this.ID = uniqueId.incrementAndGet();
        this.status = status;
        this.customers = customers;
        this.balance = 0.00D;
    }
    public Account(char status, int customerArg, double balance) {
        this.ID = uniqueId.incrementAndGet();
        this.status = status;
        this.customers.add(customerArg);
        this.balance = balance;
    }
    public Account(char status, int customerArg){
        this.ID = uniqueId.incrementAndGet();
        this.status = status;
        this.customers.add(customerArg);
        this.balance = 0.00D;
    }
    // public void setID(int iD) {
    //     ID = iD;
    // }
    public char getStatus() {
        return status;
    }
    public void setStatus(char status) {
        this.status = status;
    }
    public Collection<Integer> getCustomers() {
        return customers;
    }
    public void setCustomers(Collection<Integer> customers) {
        this.customers = customers;
    }
    public void addCustomer(int customer){
        this.customers.add(customer);
    }
    public void removeCustomer(int customer){
        this.customers.remove(customer);
    }
    public static void addAccount(Account accArg){
        allAccounts.add(accArg);
        try {
        	Serializer.write(allAccounts, file);
        }
        catch(Exception e) {
        	e.getMessage();
        }
    }
    public static ArrayList<Account> getAccounts(){
        return allAccounts;
    }

	@Override
	public String toString() {
		return "Account [ID=" + ID + ", status=" + status + ", customers=" + customers + ", balance=" + balance + "]";
	}

}
