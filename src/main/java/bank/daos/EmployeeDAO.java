package bank.daos;

import java.util.List;

import bank.models.Employee;

public interface EmployeeDAO {
	public boolean addEmployee(Employee e);
	public boolean removeEmployee(Employee e);
	public List<Employee> getListOfEmployee();
	public Employee findById(int id);
}
