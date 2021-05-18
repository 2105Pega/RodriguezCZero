package bank.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import diwhy.Serializer;

public class Employee implements Serializable {
    private static ArrayList<Employee> allEmployees = new ArrayList<>();
    public static void setAllEmployees(ArrayList<Employee> allEmployees) {
		Employee.allEmployees = allEmployees;
	}
	private static AtomicInteger uniqueId=new AtomicInteger();
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String password;
    private static final String file = "employees.txt";

    public Employee(){
        super();
        this.id = uniqueId.getAndIncrement();
    }
    public Employee(String nameArg,String passwordArg){
        this.id = uniqueId.getAndIncrement();
        this.name = nameArg;
        this.password = passwordArg;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static void addEmployee(Employee empArg){
        allEmployees.add(empArg);
        try {
        	Serializer.write(allEmployees, file);
        }
        catch(Exception e) {
        	e.getMessage();
        }
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static ArrayList<Employee> getEmployees(){
        return allEmployees;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }
}
