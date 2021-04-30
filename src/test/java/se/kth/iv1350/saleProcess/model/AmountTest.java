package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
    int amount;
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
        int amountToCompare = 5;
        anotherInstance = new Amount(amountToCompare);
        boolean expectedResult = true;
        boolean result = amountInstance.equals(anotherInstance);
        assertEquals(expectedResult, result,"The two amounts are not equal.");
    }

    @Test
    void testPlus() {
        int amountToAdd = 9;
        anotherInstance = new Amount(amountToAdd);
        Amount expectedResult = new Amount(amount + amountToAdd);
        Amount result = amountInstance.plus(anotherInstance);
        assertEquals(expectedResult, result, "The result of adding two amounts is not correct.");
    }

    @Test
    void testMinus() {
        int amountToSubtract = 4;
        anotherInstance = new Amount(amountToSubtract);
        Amount expectedResult = new Amount(amount - amountToSubtract);
        Amount result = amountInstance.minus(anotherInstance);
        assertEquals(expectedResult,result,"The result of the subtraction is not correct.");
    }
}