package bank.controllers;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bank.Main;
import bank.daos.CustomerImpl;
import bank.daos.EmployeeImpl;
import bank.models.Customer;
import bank.models.Employee;
import bank.views.LoginView;
import diwhy.iHateScanner;

public class LoginController {
	private static final Logger logr = LogManager.getLogger(LoginController.class);
    //private final String existingMessage = "That username already exists!";
    //private final String invalidPasswordMessage = "Oops, wrong password!";
    //public static iHateScanner s = new iHateScanner();
    LoginView view = new LoginView();
    EmployeeImpl emp = new EmployeeImpl();
    CustomerImpl cust = new CustomerImpl();
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
        return;
    }
    private void employeeLogin() {
        String[] login = view.getEmployeeForm();
        String userArg = login[0];
        String passArg = login[1];
        try {
        	boolean loginBool = emp.tryLogin(userArg, passArg);
        	if(loginBool) {
        		logr.info(emp.findById(Main.getUserID()).toString());
        		return;
        	}
                logr.info("Something happened! Go back to the start!" + loginBool);
                return;
        } catch (NoSuchElementException e) {
        	logr.error(e.getMessage());
            System.out.println("hmm... employee doesn't exist...Go back to the start!");
            return;
        }
    }

    public void getRegistration() {
        String[] registration = view.getRegistrationForm();
        cust.tryRegister(registration[0], registration[1]);
        return;
    }
    void login(String userArg, String passArg) {
    	boolean succeeded = cust.tryLogin(userArg, passArg);
    	if (succeeded){
    		logr.info(Main.getCustomer().toString());
    		return;
    	}
    	return;
	}
	public void logout() {
		// TODO Auto-generated method stub
		logr.info("logged out");
		Main.setCustomer(null);
		Main.setAccount(null);
		Main.setUserID(-1);
		Main.setAction("login");
		return;
	}
    }
