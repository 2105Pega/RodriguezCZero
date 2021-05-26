package bank.daos;
import java.util.List;

import bank.models.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ApplicationDAO {
	public boolean addApplication(Application a);
	public boolean removeApplication(Application a);
	public List<Application> getListOfApplication();
	public Application findById(int id);
	public void addCustomer(int id);
	public boolean updateApplication(int id);
}
