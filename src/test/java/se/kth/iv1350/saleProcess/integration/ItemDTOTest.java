package se.kth.iv1350.saleProcess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.model.Amount;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {
    private ItemDTO instance;

    @BeforeEach
    void setUp() {
        instance = new ItemDTO("milk", new Amount(25), 15, new ItemIdentifier(1,1));
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void testItemsEqual() {
        ItemDTO otherInstance = new ItemDTO("milk", new Amount(25), 15, new ItemIdentifier(1,1));
        boolean expResult = true;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult, result,"Items are not equal.");
    }

    @Test
    void testItemsBarcodeNotEqual(){
        ItemDTO otherInstance = new ItemDTO("milk", new Amount(25), 15, new ItemIdentifier(56,1));
        boolean expResult = false;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult, result,"Items barcodes are equal.");
    }

    @Test
    void testItemIdentifiersQuantityIgnoredWhileComparingTheItems(){
        ItemDTO otherInstance = new ItemDTO("milk", new Amount(25), 15, new ItemIdentifier(1,2));
        boolean expResult = true;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult, result,"Item quantity is not ignored when comparing the items.");
    }

    @Test
    void testComparedItemNotInstanceOfTheClass(){
        ItemIdentifier otherInstance = new ItemIdentifier(4,3);
        boolean expResult = false;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult, result,"Compared item is an instance of the same class.");
    }

    @Test
    void testComparedItemIsNull(){
        ItemDTO otherInstance = null;
        boolean expResult = false;
        boolean result = instance.equals(otherInstance);
        assertEquals(expResult,result, "Compared item is not null.");
    }
}