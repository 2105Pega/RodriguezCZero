package bank.daos;

import java.util.List;

import bank.models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface EmployeeDAO {
	public boolean addEmployee(Employee e);
	public boolean removeEmployee(Employee e);
	public List<Employee> getListOfEmployee();
	public Employee findById(int id);
	public boolean tryLogin(String username, String password);
}
