package se.kth.iv1350.saleProcess.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.model.Amount;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {

    @Test
    void testItemsEqual() {
        ItemDTO instance = new ItemDTO("milk", new Amount(25), 12, new ItemIdentifier(1));
        ItemDTO otherInstance = new ItemDTO("milk", new Amount(25), 12, new ItemIdentifier(1));
        boolean condition = instance.equals(otherInstance);
        assertTrue(condition,"Items are not equal.");
    }

    @Test
    void testItemsNotEqual(){
        ItemDTO instance = new ItemDTO("milk", new Amount(25), 12, new ItemIdentifier(1));
        ItemDTO otherInstance = new ItemDTO("pasta", new Amount(25), 6, new ItemIdentifier(56));
        boolean condition = instance.equals(otherInstance);
        assertFalse(condition,"The items are equal.");
    }


    @Test
    void testItemToCompareNotInstanceOfTheClass(){
        ItemDTO instance = new ItemDTO("milk", new Amount(25), 12, new ItemIdentifier(1));
        ItemIdentifier otherInstance = new ItemIdentifier(4);
        boolean expResult = false;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult, result,"The compared item is an instance of the same class.");
    }

    @Test
    void testItemToCompareIsNull(){
        ItemDTO instance = new ItemDTO("milk", new Amount(25), 12, new ItemIdentifier(1));
        ItemDTO otherInstance = null;
        boolean expResult = false;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult,result, "Compared item is not null.");
    }
}