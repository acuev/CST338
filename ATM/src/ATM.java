/*
Title: ATM.java
Date: 02/24/2021

 */
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class ATM {

    private String bankName;
    private int serialNumber;
    private String location;
    private double balance;
    private int i = 0, transaction =0, deposit = 0, transfer = 0, j = 0;
    private int success = 0, fail = 0, withdrawals = 0, depSuccess = 0, depfail =0, tranSuccess=0, transFail=0;
    private List<Customer> customers = new ArrayList<>(10);

    public ATM(String bankName){
        addCustomers();
        this.bankName = bankName;
        serialNumber = 0;
        location = "UNKNOWN";
        balance = 100.00;
    }
    public ATM(int serialNumber, String bankName, String location){
        addCustomers();
        this.serialNumber = serialNumber;
        this.bankName = bankName;
        this.location = location;
        balance = 100;
    }

    public void setATM(int serialNumber, String location){
        this.serialNumber = serialNumber;
        this.location = location;
    }
    public String getATM(){return location;}
    //adds the ten customers into the list of customers that are going to be used
    public void addCustomers(){
        customers.add(new Customer("Alice", 1234, 5000.00,"OtterUnion"));
        customers.add(new Customer("Tom", 2000, 200.00,"OtterUnion"));
        customers.add(new Customer("Monica", 3000, 50.00,"OtterUnion"));
        customers.add(new Customer("Michael", 7777, 0.00,"OtterUnion"));
        customers.add(new Customer("John", 8000, 500.00,"OtterUnion"));
        customers.add(new Customer("Jane", 2222, 500.00,"OtterUnion"));
        customers.add(new Customer("Robert", 2323, 200.00,"BOA"));
        customers.add(new Customer("Owen", 4455, 50.00,"BOA"));
        customers.add(new Customer("Chris", 8787, 10.00,"BOA"));
        customers.add(new Customer("Rebecca", 8080, 555.55,"BOA"));
    }
    public void addFund(double balance){
        this.balance += balance;
    }
    //method takes in the customers name, pin, and how much they would like to withdraw, then it
    //checks if the customer is on the list, if it is then it will check that the customer's
    //balance is greater than the number being withdrawn and to make sure that the atm's balance
    //is greater than 100; if it is then this was a success
    public double getFunds(){return balance;}
    public void withdrawal(String name, int pinNumber, double balanceNumber){
        boolean result = checkIdentity(name, pinNumber);
        transaction++;
        withdrawals++;
        if(result) {
            if(customers.get(i).getBalance() > balanceNumber && balanceNumber < balance && balance > 100){
                    System.out.println("Succeed - withdrawl");
                    double customerBalance = customers.get(i).getBalance();
                    customerBalance-=balanceNumber;
                    customers.get(i).setBalance(customerBalance);
                    success++;
                    balance -= balanceNumber;
            } else{
                fail++;
                System.out.println("Fail - withdrawal");
            }
        } else {
            fail++;
            System.out.println("Fail - withdrawal");
        }
    }
    //method takes the name and id, and checks if its on the list,
    // i is also used to identify where in the list the name is on
    public boolean checkIdentity(String name, int pinNumber){
        i = 0;
        int result = 0;
        while(customers.size() > i)
        {
            if (customers.get(i).getName().equals(name) && customers.get(i).getPinNumber() == pinNumber
                    && customers.get(i).getBanks().equals(bankName)) {
                result++;
                break;
            } i++;
        }
        return result != 0;
    }
    //method displays the atm's status
    public void status(){
        System.out.println("\t\tSerial Number: " + serialNumber );
        System.out.println("\t\tBank Name: " + bankName);
        System.out.println("\t\tLocation: " + location);
        System.out.println("\t\tBalance: " + balance);
        System.out.println("\t\t" + transaction + " transactions so far: ");
        System.out.println("\t\t\t Withdrawals: " + withdrawals + " (" + success + " success, " + fail + " fail)");
        System.out.println("\t\t\t Deposit: " + deposit + " (" + depSuccess + " success, " + depfail + " fail)");
        System.out.println("\t\t\t Transfer: " + transfer + " (" + tranSuccess + " success, " + transFail + " fail)");
    }
    //displays the menu
    public void displayMenu(){
        System.out.println("===== ATM Transaction Menu =====");
        System.out.println("\t\t 1. Withdrawal");
        System.out.println("\t\t 2. Deposit");
        System.out.println("\t\t 3. Transfer");
    }
    //checks to see if the customer is on the List<Customer>
    public boolean isCustomer(String name){
        for(j = 0; j < customers.size(); j++){
            if(customers.get(j).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    //returns the customer
    public Customer getCustomer(String name){
        boolean result = isCustomer(name);
        return customers.get(j);
    }
    //method takes in the name, id, and amount of money they would like to deposit
    //then checks if name,bank and id matches any of the customers
    //if it matches, it then checks if the customer has the money to deposit money
    //if the customer does, then it was a success, it not it was a fail
    public void deposit(String name, int id, int money){
        transaction++;
        deposit++;
        boolean result = checkIdentity(name,id);
        if(result) {
            if(customers.get(i).getBalance() > money){
                depSuccess++;
                balance+=money;
                System.out.println("Succeed - deposit");
            } else{
                depfail++;
                System.out.println("Fail - deposit");
            }
        } else{
            depfail++;
            System.out.println("Fail - deposit");
        }
    }
    //method that takes in two customers, checks to see if the customer matches with the List<customer>
    //if it does, it will then check if both customers have the same bankname, if the first customers
    //balance if greater than the money being sent to the second customer. If it is then the transfer was a success.
    public boolean transfer(String transferFrom, int transfersId, double moneySent, String transferTo, int transfererId){
        transfer++;
        transaction++;
        boolean result = checkIdentity(transferFrom,transfersId);
        int firsIndex = i;
        boolean nextResult = checkIdentity(transferTo,transfererId);
        int secondIndex = i;
        if(!result || !nextResult){
            System.out.println("Fail - transfer");
            transFail++;
        }else{
            if(customers.get(firsIndex).getBanks().equals(customers.get(secondIndex).getBanks())
                    && customers.get(firsIndex).getBalance() > moneySent) {
                System.out.println("Succeed - transfer");
                double firstCustomer = customers.get(firsIndex).getBalance();
                double secondCustomer = customers.get(secondIndex).getBalance();
                firstCustomer -= moneySent;
                secondCustomer += moneySent;
                customers.get(firsIndex).setBalance(firstCustomer);
                customers.get(secondIndex).setBalance(secondCustomer);
                //System.out.println(customers.get(firsIndex).getBalance());
                //System.out.println(customers.get(secondIndex).getBalance());
                tranSuccess++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return serialNumber == atm.serialNumber && Double.compare(atm.balance, balance) == 0 && i == atm.i && Objects.equals(bankName, atm.bankName) && Objects.equals(location, atm.location) && Objects.equals(customers, atm.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, serialNumber, location, balance, i, customers);
    }

    @Override
    public String toString() {
        return "Serial Number: " + serialNumber + "\n" +
                "Bank Name: " + bankName + "\n" +
                "Location: " + location + "\n" +
                "Balance: " + balance;
    }

}
