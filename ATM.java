import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;
import java.text.*;
class Account{
    private int customerNumber = 0;
    private int pin = 0;
    private double currentBalance = 20000;
    private double savingBalance = 1000;
    DecimalFormat df1 = new DecimalFormat("###,##0.00' Rupee'");
    DecimalFormat df2 = new DecimalFormat("###,##0.00' Doller'");
    Scanner sc = new Scanner(System.in);

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

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    double getCurrentBalance(){
        return currentBalance;
    }
    void setCurrentBalance(double bal){
        currentBalance = bal;
    }
    void getCurrentDepositInput(){
        System.out.println("Enter Deposit amount :");
        double deposit = sc.nextInt();
        double cBal = getCurrentBalance();
        if((currentBalance+deposit)>=0){
            setCurrentBalance(cBal + deposit);
        }else{
            System.out.println("Invalid Amount");
        }
    }
    double getCurrentDepositOutput(){
        return getCurrentBalance();
    }
    void getCurrentWithdrawInput(){
        System.out.println("Enter withdrawl amount :");
        double w = sc.nextInt();
        if((currentBalance-w)>=0){
            setCurrentBalance(getCurrentBalance() - w);
        }else{
            System.out.println("Insufficient Balance");
        }
    }
    double getCurrentWithdrawOutput(){
        return getCurrentBalance();
    }

    void getSavingDepositInput(){
        System.out.println("Enter Deposit amount :");
        double deposit = sc.nextInt();
        double cBal = getSavingBalance();
        if((savingBalance+deposit)>=0){
            setSavingBalance(cBal + deposit);
        }else{
            System.out.println("Invaid Amount");
        }
    }
    double getSavingDepositOutput(){
        return getSavingBalance();
    }
    void getSavingWithdrawInput(){
        System.out.println("Enter withdrawl amount :");
        double w = sc.nextInt();
        if((savingBalance-w)>=0){
            setSavingBalance(getSavingBalance() - w);
        }else{
            System.out.println("Insufficient Balance");
        }
    }
    double getSavingWithdrawOutput(){
        return getSavingBalance();
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
        System.out.println("Current Account :>");
        System.out.println("1.view balance\n2.withdraw money\n3.deposit money\n4.exit");
        int ch = sc.nextInt();
        switch (ch){
            case 1:
                System.out.println("Current Balance : "+df1.format(getCurrentBalance()));
                getAccountType();
                break;
            case 2:
                getCurrentWithdrawInput();
                getCurrentWithdrawOutput();
                getAccountType();
                break;
            case 3:
                getCurrentDepositInput();
                getCurrentDepositOutput();
                getAccountType();
                break;
            case 4:
                System.out.println("\nThank you visit again..");
                getAccountType();
                break;
            default:
                System.err.println("Invalid choice...");
                getCurrent();
        }
    }
    public void getSaving() {
        System.out.println("Current Account :>");
        System.out.println("1.view balance\n2.withdraw money\n3.deposit money\n4.exit");
        int ch = sc.nextInt();
        switch (ch){
            case 1:
                System.out.println("Saving Balance : "+df1.format(getSavingBalance()));
                getAccountType();
                break;
            case 2:
                getSavingWithdrawInput();
                getSavingWithdrawOutput();
                getAccountType();
                break;
            case 3:
                getSavingDepositInput();
                getSavingDepositOutput();
                getAccountType();
                break;
            case 4:
                System.out.println("\nThank you visit again..");
                getAccountType();
                break;
            default:
                System.err.println("Invalid choice...");
                getSaving();
        }
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
                getCurrent();
                break;
            case 2:
                accType = "Saving";
                getSaving();
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
