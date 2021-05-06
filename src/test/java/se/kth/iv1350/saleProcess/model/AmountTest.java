package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
    double amount;
    Amount amountInstance;
    Amount anotherInstance;

    @BeforeEach
    void setUp() {
        amount = 5;
        amountInstance = new Amount(amount);
    }

    @AfterEach
    void tearDown() {
        amountInstance = null;
        anotherInstance = null;
        amount = 0;
    }

    @Test
    void testEquals() {
        double amountToCompare = 5;
        anotherInstance = new Amount(amountToCompare);
        boolean result = amountInstance.equals(anotherInstance);
        assertTrue(result,"The two amounts are not equal.");
    }

    @Test
    void testPlus() {
        double amountToAdd = 9;
        anotherInstance = new Amount(amountToAdd);
        Amount expectedResult = new Amount(amount + amountToAdd);
        Amount result = amountInstance.plus(anotherInstance);
        assertEquals(expectedResult, result, "The result of adding two amounts is not correct.");
    }

    @Test
    void testMinus() {
        double amountToSubtract = 4;
        anotherInstance = new Amount(amountToSubtract);
        Amount expectedResult = new Amount(amount - amountToSubtract);
        Amount result = amountInstance.minus(anotherInstance);
        assertEquals(expectedResult,result,"The result of the subtraction is not correct.");
    }

    @Test
    void testMultiplication(){
        double amountToMultiplyBy = 4;
        anotherInstance = new Amount(amountToMultiplyBy);
        Amount expectedResult = new Amount(amount * amountToMultiplyBy);
        Amount result = amountInstance.multiply(anotherInstance);
        assertEquals(expectedResult, result,"The result of the multiplication is not correct.");
    }
}