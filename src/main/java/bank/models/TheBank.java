package bank.models;

import java.io.Serializable;

public class TheBank extends Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private double balance = Double.MAX_VALUE;
    private int ID = 0;
    private char status = 'o';

    
    public TheBank() {
    }
        /**cash withdrawls also come from the bank */
    @Override
    public void deposit(double deposit) {
        System.out.println("money withdrawn");
    }
    /**cash deposits come from the bank. */
    @Override
    public void withdraw(double withdrawl) {
        System.out.println("Money deposited");
    }
}
