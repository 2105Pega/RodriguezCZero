package bank.daos;
import java.util.List;

import bank.models.Application;

public interface ApplicationDAO {
	public boolean addApplication(Application e);
	public boolean removeApplication(Application e);
	public List<Application> getListOfApplication();
	public Application findById(int id);
}
