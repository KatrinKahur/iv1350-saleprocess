package se.kth.iv1350.saleProcess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.model.Amount;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @AfterEach
    void tearDown() {
        inventory = null;
    }

    @Test
    void testSearchedItemIsFound() {
        ItemIdentifier searchedIdentifier = new ItemIdentifier(5);
        ItemDTO searchedItem = new ItemDTO("meatballs", new Amount(42), 25, new ItemIdentifier(5));
        ItemDTO expResult = searchedItem;
        ItemDTO result = inventory.searchItemByBarcode(searchedIdentifier);
        assertEquals(expResult, result, "Wrong item found.");
    }

    @Test
    void testSearchedItemNotFound(){
        ItemIdentifier searchedIdentifier = new ItemIdentifier(30);
        ItemDTO searchedItem = new ItemDTO("meatballs", new Amount(42), 25, new ItemIdentifier(30));
        ItemDTO result = inventory.searchItemByBarcode(searchedIdentifier);
        assertNull(result, "The fetched item is not null");
    }
}