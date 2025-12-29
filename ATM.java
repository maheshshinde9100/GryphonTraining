import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;
class Account{
    private int customerNumber = 0;
    private int pin = 0;
    private double currentBalance = 0;
    private double savingBalance = 0;
    void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }
    long getCustomerNumber(){
        return customerNumber;
    }
    void setPinNumber(int pin){
        this.pin= pin;
    }
    int getPinNumber(){
        return pin;
    }
    double getSavingBalance(){
        return savingBalance;
    }
    double getCurrentBalance(){
        return currentBalance;
    }

    void getCurrentDepositInput(){

    }
    void getSavingDepositInput(){

    }
}
class OptionMenu extends Account{
    Scanner sc = new Scanner(System.in);
    int customerNumber;
    int pin;
    String accType = "";
    Map<Integer,Integer> map;
    OptionMenu(Map<Integer,Integer> map){
        this.map = map;
    }
    public void getLogin() {
        try {
            System.out.println("\nWelcome to the ATM");
            System.out.println("Enter Customer Number:");
            customerNumber = sc.nextInt();
            setCustomerNumber(customerNumber);

            System.out.println("Enter PIN :");
            pin = sc.nextInt();
            setPinNumber(pin);

            if(map.containsKey(customerNumber) && map.get(customerNumber)==pin){
                    System.out.println("Login Succesful...");
                    getAccountType();
                System.out.println("Your Account Type : "+accType);
            }else{
                    System.out.println("Login Failed..Do you want to Try Again (true/false)");
//                    boolean bool = sc.nextBoolean();
                    getLogin();
            }
        }catch (InputMismatchException e){
            System.err.println("Enter only numbers.\nCharacters and symbols are not allowed..");
            sc.next();
            getLogin();
        }

    }
    public void getCurrent() {
    }
    public void getSaving() {

    }

    public void getAccountType() {
        int ch;
        System.out.println("Account Type : \n");
        System.out.println("Choice 1: current Account");
        System.out.println("Choice 2: saving Account");
        System.out.println("choice 3: exit");
        ch = sc.nextInt();
        switch(ch){
            case 1:
                accType = "Current";
                break;
            case 2:
                accType = "Saving";
                break;
            case 3:
                System.out.println("Thank you for VISITING.");
                System.err.println("Visit Again");
                return;
            default:
                System.err.println("Invalid Choice");
                getAccountType();
        }
    }
}


public class ATM {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(001,123); //customer number, pin
        map.put(002,123);

        OptionMenu obj = new OptionMenu(map);
        do {
            obj.getLogin();
        }while(true);
    }
}