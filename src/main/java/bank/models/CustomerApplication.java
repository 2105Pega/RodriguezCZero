package bank.models;

public class CustomerApplication {
	private int CustomerID;
	private int ApplicationID;
	
	public CustomerApplication(int customerID, int applicationID) {
		super();
		CustomerID = customerID;
		ApplicationID = applicationID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getApplicationID() {
		return ApplicationID;
	}
	public void setApplicationID(int applicationID) {
		ApplicationID = applicationID;
	}
}
