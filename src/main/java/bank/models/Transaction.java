package bank.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.numbers.core.Precision;

import diwhy.Serializer;

/**
 * 
 * https://www.baeldung.com/java-round-decimal-number
 *
 */
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
    private BigDecimal amount;
    
    public Transaction(){
        super();
        this.id = uniqueId.getAndIncrement();
    }
    public Transaction(int id,int sourceArg, int destinationArg, BigDecimal amountArg){
        this.id = id;        
        this.sourceAccountID = sourceArg;
        this.destinationAccountID = destinationArg;
        this.amount = amountArg.setScale(2 );
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
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
    	amount = amount.setScale(2);
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
