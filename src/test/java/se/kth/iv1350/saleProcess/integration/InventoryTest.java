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
        ItemDTO expResult = new ItemDTO("meatballs", new Amount(42), 25, searchedIdentifier);

        try {
            ItemDTO result = inventory.searchItemByBarcode(searchedIdentifier);
            assertEquals(expResult, result, "Wrong item found.");
        } catch (InvalidItemIdentifierException | ServerNotRunningException exc){
            fail("The process of fetching items from the inventory failed. Exception was thrown.");
            exc.printStackTrace();
        }
    }

    @Test
    void testInvalidItemIdentifierExceptionThrown(){
        ItemIdentifier nonExistingSearchedIdentifier = new ItemIdentifier(30);

        try{
            inventory.searchItemByBarcode(nonExistingSearchedIdentifier);
            fail("Item search with non-existing item was successful.");
        }
        catch(InvalidItemIdentifierException exc){
            assertTrue(exc.getMessage().contains("Item identifier " + nonExistingSearchedIdentifier.getBarcode() +
                    " does not exist."), "Wrong exception message.");
        }
        catch (ServerNotRunningException exc){
            fail("Wrong exception thrown.");
        }
    }

    @Test
    void testServerNotRunningExceptionThrown(){
        ItemIdentifier identifierCausingServerException = new ItemIdentifier(20);

        try{
            inventory.searchItemByBarcode(identifierCausingServerException);
            fail("Item with the identifier that causes the server exception was successfully fetched from the Inventory.");
        }
        catch (ServerNotRunningException exc){
            assertTrue(exc.getMessage().contains("Cannot reach the server."), "Wrong exception message.");
        }
        catch (InvalidItemIdentifierException exc){
            fail("Wrong exception thrown.");
        }
    }

}