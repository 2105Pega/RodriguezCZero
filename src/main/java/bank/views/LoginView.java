package bank.views;

import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import diwhy.iHateScanner;

public class LoginView {
	private static final Logger logr = LogManager.getLogger(LoginView.class);
    String registerKeyword = "register";
    String employeeKeyword = "emp";
    String returnAction[] = new String[2];
    public static iHateScanner s = new iHateScanner();
    public String[] getLoginForm() {
    	String[] welcomeMessage = new String[]{"Please Login","Or Enter \"Register\" as username to register","Or \"Emp\" to login as an Employee"};
        System.out.println(Arrays.toString(welcomeMessage));
        String[] login = new String[2];
        String input = (s.getLine());
        if (input.toLowerCase().matches("(?i)^"+registerKeyword+"$")){
            returnAction[0] = "!";
            return returnAction;
        }
        if (input.toLowerCase().matches("(?i)^"+employeeKeyword+"$")){
            returnAction[0] = "e";
            return returnAction;
        }
        login[0] = input.toString();
        System.out.println("Password?");
        input =(s.getLine());
        login[1] = input.toString();
        return login;
    }
    public String[] getEmployeeForm() {
    	String[] employeeMessage = new String[]{"Please Use Your Employee Username to Login"};
        System.out.println(Arrays.toString(employeeMessage));
        String[] login = new String[2];
        login[0] = (s.getLine());
        System.out.println("Password?");
        login[1] = (s.getLine());
        return login;
    }
    public String[] getRegistrationForm(){
        String[] login = new String[2];
        System.out.println("Username?");
        login[0] = (s.getLine());
        login[1] = setPassword();
        System.out.println("Now try logging in with your new user :)");
        return login;
    }
    public String setPassword(){
        String password ="";
        String passwordConfimation;
        boolean passwordsMatch = false;
        while (!passwordsMatch){
            System.out.println("Password?");
            password = (s.getLine());
            System.out.println("Confirm Password");
            passwordConfimation = (s.getLine());
            if (String.valueOf(password).equals(passwordConfimation)){
                passwordsMatch = true;
            }
            if (!String.valueOf(password).equals(passwordConfimation)){
                System.out.println("Passwords didn't match!");
            }
        }
        return password;
    }
}
