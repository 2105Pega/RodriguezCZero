package bank.controllers;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

import bank.Main;
import bank.models.Customer;
import bank.models.Employee;
import bank.views.LoginView;
import diwhy.iHateScanner;

public class LoginController {
    private final String existingMessage = "That username already exists!";
    private final String invalidPasswordMessage = "Oops, wrong password!";
    //public static iHateScanner s = new iHateScanner();
    LoginView view = new LoginView();
    public void getLogin(){
        String[] login =  view.getLoginForm();
        if (login[0].equals("!")){
            getRegistration();
            return;      
        }
        if (login[0].equals("e")){
            employeeLogin();
            return;
        }
        login(login[0], login[1]);
    }
    private void employeeLogin() {
        String[] login = view.getEmployeeForm();
        String userArg = login[0];
        String passArg = login[1];
        ArrayList<Employee> employees = Employee.getEmployees();
        Stream<Employee> employeeStream = employees.stream();
        try {
            Employee sessionEmployee = employeeStream.filter((e)-> e.getName().equals(userArg)).findFirst().get();
            String employeePassword = sessionEmployee.getPassword();
            if(String.valueOf(passArg).equals(employeePassword)){
                Main.setSession("emp");
                Main.setUserID(sessionEmployee.getId());
                return;
            }
            if(!String.valueOf(passArg).equals(employeePassword)){
                System.out.println(invalidPasswordMessage + "go back to the start!");
                return;
            }
        } catch (NoSuchElementException e) {
            System.out.println("hmm... employee doesn't exist...try again");
            return;
        }
    }

    public void getRegistration() {
        String[] registration = view.getRegistrationForm();
        ArrayList<Customer> customers = Customer.getCustomers();
        Stream<Customer> customerStream = customers.stream();
       	if(customerStream.filter((c)->c.getUsername().equals(registration[0].toString())).findFirst().isPresent()) {
           	System.out.println(existingMessage);
           	return;
       	}
        register(registration);
        return;
    }
    public void register(String[] arg){
        Customer newCustomer = new Customer(arg[0].toString(),arg[1].toString());
        System.out.println(newCustomer.toString());
        Customer.addCustomer(newCustomer);
        return;
    }
    void login(String userArg, String passArg) {
        try{
            ArrayList<Customer> customers = Customer.getCustomers();
            Stream<Customer> customerStream = customers.stream();
            Customer sessionCustomer = customerStream.filter((c)->c.getUsername().equalsIgnoreCase(userArg)).findFirst().get();
            String customerPassword = sessionCustomer.getPassword();
            if (String.valueOf(passArg).equals(customerPassword)){
                Main.setSession("cust");
                Main.setUserID(sessionCustomer.getId());
                return;
            }
            if(!String.valueOf(passArg).equals(customerPassword)){
                System.out.println(invalidPasswordMessage);
                return;
            }
        }catch(NullPointerException e){
            System.out.println("User doesn't exit.... Try again or Register!");
            return;
        }
        catch(NoSuchElementException e) {
        	System.out.println("User doesn't exit.... Try again or Register!");
        	return;
        }
    }
}
