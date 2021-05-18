package bank.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import diwhy.Serializer;

public class Application implements Serializable{
    private static ArrayList<Application> allApplications = new ArrayList<>();
    public static void setAllApplications(ArrayList<Application> allApplications) {
		Application.allApplications = allApplications;
	}
	private static AtomicInteger uniqueId=new AtomicInteger();
    private static final long serialVersionUID = 1L;
    private int id;
    private Collection<Integer> customers = new ArrayList<>();
    private double intialAmount = 0.00D;
    
    private static final String file = "applications.txt";
    /**
     * p pending
     * a approved
     * d denied
     */
    private char status;
    private char statusConverter(char a){
        switch(Character.valueOf(a)){
            case 'p':
            return 'p';
            case 'a':
            return 'o';
            case 'd':
            return 'c';
            default:
            return 'p';
        }
    }

    public Application(){
        super();
        this.id = uniqueId.getAndIncrement();
    }
    public Application(Collection<Integer> customers, char status) {
        this.id = uniqueId.getAndIncrement();
        this.customers = customers;
        this.status = status;
        char accountStatus = this.statusConverter(status);
        Account newAccount = new Account(accountStatus,customers);
        Account.addAccount(newAccount);
    }
    public Application(int customer, char status) {
        this.id = uniqueId.getAndIncrement();
        this.customers.add(customer);
        this.status = status;
        char accountStatus = this.statusConverter(status);
        Account newAccount = new Account(accountStatus,customers);
        Account.addAccount(newAccount);
    }
    public Application(Collection<Integer> customers, char status, double intialAmount) {
        this.id = uniqueId.getAndIncrement();
        this.customers = customers;
        this.status = status;
        this.intialAmount = intialAmount;
        char accountStatus = this.statusConverter(status);
        Account newAccount = new Account(accountStatus,customers, intialAmount);
        Account.addAccount(newAccount);
    }
    public Application(int customer, char status, double intialAmount) {
        this.id = uniqueId.getAndIncrement();
        this.customers.add(customer);
        this.status = status;
        this.intialAmount = intialAmount;
        char accountStatus = this.statusConverter(status);
        Account newAccount = new Account(accountStatus,customer, intialAmount);
        Account.addAccount(newAccount);
    }
    public Application(int customer) {
        this.id = uniqueId.getAndIncrement();
        this.customers.add(customer);
        this.status = 'p';
        this.intialAmount = 0.00D;
        char accountStatus = this.statusConverter(status);
        Account newAccount = new Account(accountStatus,customers, intialAmount);
        Account.addAccount(newAccount);        
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        uniqueId.set(id);
        this.id = id;
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
    public void removeCustomer(Integer customer){
        customers.stream().filter(x -> x == customer).findFirst();
        customers.removeIf(x -> x == customer);
        this.customers.remove(customer);
    }
    public char getStatus() {
        return status;
    }
    public void setStatus(char status) {
        this.status = status;
    }
    public static void addApplication(Application appArg){
        allApplications.add(appArg);
        try {
        	Serializer.write(allApplications, file);
        }
        catch(Exception e) {
        	e.getMessage();
        }
    }
    public static ArrayList<Application> getApplications(){
        return allApplications;
    }
    @Override
    public String toString() {
        return "Application [customers=" + customers + ", id=" + id + ", status=" + status + "]";
    }
}
