package bank.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import diwhy.Serializer;

public class Transaction implements Serializable {
    private static ArrayList<Transaction> allTransactions = new ArrayList<>();
    public static void setAllTransactions(ArrayList<Transaction> allTransactions) {
		Transaction.allTransactions = allTransactions;
		uniqueId.set(allTransactions.get(allTransactions.size()).id);
	}
	private static AtomicInteger uniqueId=new AtomicInteger();
    private static final long serialVersionUID = 1L;
    private int id;
    private int sourceAccountID;
    private int destinationAccountID;
    private double amount;
    private static final String file = "transactions.txt";
    
    public Transaction(){
        super();
        this.id = uniqueId.getAndIncrement();
    }
    public Transaction(int sourceArg, int destinationArg, double amountArg){
        this.id = uniqueId.getAndIncrement();        
        this.sourceAccountID = sourceArg;
        this.destinationAccountID = destinationArg;
        this.amount = amountArg;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
    	uniqueId.set(id);
        this.id = id;
    }
    public int getsourceAccountID() {
        return sourceAccountID;
    }
    public void setsourceAccountID(int sourceAccountID) {
        this.sourceAccountID = sourceAccountID;
    }
    public int getDestinationAccountID() {
        return destinationAccountID;
    }
    public void setdestinationAccountID(int destinationAccountID) {
        this.destinationAccountID = destinationAccountID;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", destinationAccountID=" + destinationAccountID + ", id=" + id
                + ", sourceAccountID=" + sourceAccountID + "]";
    }
    public static void addTransaction(Transaction transArg){
        allTransactions.add(transArg);
        try {
        	Serializer.write(allTransactions, file);
        }
        catch(Exception e) {
        	e.getMessage();
        }
    }
    public ArrayList<Transaction> getTransactions(){
        return allTransactions;
    }
}
