/*
Title: ATMTest.java
Description: Test the functions in the ATM class
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    @Test
    void setATM() {
        ATM temp = new ATM("Bank");
        assertNotEquals(temp.getATM(), "new Bank");
        temp.setATM(123, "new Bank");
        assertEquals(temp.getATM(), "new Bank");
    }

    @Test
    void addFund() {
        ATM temp = new ATM("Bank");
        assertNotEquals(temp.getFunds(), 400.00);
        temp.addFund(400.00);
        assertEquals(temp.getFunds(), 500.00);
    }

    @Test
    void withdrawal() {
        ATM machine2 = new ATM(200,"BOA","Library");
        machine2.withdrawal("Robert",2323,0);
        assertEquals(machine2.getFunds(), 100);
    }

    @Test
    void checkIdentity() {
        ATM machine2 = new ATM(200,"BOA","Library");
        assertTrue(machine2.checkIdentity("Owen",4455));
        assertFalse(machine2.checkIdentity("Alice",1234));
    }
    @Test
    void isCustomer() {
        ATM temp = new ATM("Bank");
        assertTrue(temp.isCustomer("Alice"));
        assertFalse(temp.isCustomer("Abi"));
    }

    @Test
    void deposit() {
        ATM machine2 = new ATM(200,"BOA","Library");
        machine2.addFund(200);
        machine2.deposit("Chris",8787,5);
        assertEquals(machine2.getFunds(), 305 );
    }

    @Test
    void transfer() {
        ATM machine1 = new ATM("OtterUnion");
        machine1.setATM(100, "BIT");
        machine1.addFund(400);
        assertTrue(machine1.transfer("Alice",1234,10,"Tom",2000));
        assertFalse(machine1.transfer("Chris",8787,10, "Tom",2000));
    }

    @Test
    void Customer(){
        Customer customer = new Customer();
        assertEquals(customer.getName()," ");
        assertEquals(customer.getBanks()," ");
        assertEquals(customer.getPinNumber(),0);
        assertEquals(customer.getBalance(),0.00);
    }
    @Test
    void settersAndGetters(){
        Customer customer = new Customer();
        customer.setName("Customer");
        assertEquals(customer.getName(),"Customer");
        customer.setBalance(6);
        assertEquals(customer.getBalance(),6);
        customer.setBanks("Bank");
        assertEquals(customer.getBanks(),"Bank");
        customer.setPinNumber(1234);
        assertEquals(customer.getPinNumber(),1234);
    }
}