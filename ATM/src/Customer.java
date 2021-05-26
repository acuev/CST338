/*
Title: Customer.java
Description: Class for the 10 customers
 */
import java.util.Objects;

public class Customer {
    private String name;
    private int pinNumber;
    private double balance;
    private String banks;

    public Customer(){
        name = " ";
        pinNumber = 0;
        balance = 0.00;
        banks = " ";

    }
    public Customer(String name, int pinNumber,double balance, String banks ){
        this.name = name;
        this.pinNumber = pinNumber;
        this.balance = balance;
        this.banks = banks;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getPinNumber() == customer.getPinNumber() && Objects.equals(getName(), customer.getName()) && Objects.equals(getBanks(), customer.getBanks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPinNumber(), getBanks());
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBanks() {
        return banks;
    }
    public void setBanks(String banks) {
        this.banks = banks;
    }

    @Override
    public String toString() {
        return  name + ": Balance $" + balance;
    }
}
