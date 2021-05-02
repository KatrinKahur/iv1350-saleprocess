package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private ItemDTO testItem = new ItemDTO("yoghurt", new Amount(27), 20, new ItemIdentifier(3));
    private Item itemInstance;

    @BeforeEach
    void setUp() {
        itemInstance = new Item(testItem);
    }

    @AfterEach
    void tearDown() {
        itemInstance = null;
    }

    @Test
    void testPriceWithVATIsCalculatedCorrectly() {
        Amount itemPrice = itemInstance.getPrice();
        double itemVAT = itemInstance.getVAT();
        Amount itemVATconvertedIntoAmount = new Amount(itemVAT/100).multiply(itemPrice);
        Amount expectedResult = itemPrice.plus(itemVATconvertedIntoAmount);
        itemInstance.calculatePriceWithVAT();
        Amount result = itemInstance.getPriceWithVAT();
        assertEquals(expectedResult,result,"Calculated price with VAT is not correct.");

    }

    @Test
    void testQuantityIsIncreased() {
        int itemQuantity = itemInstance.getQuantity();
        int quantity1 = 1;
        int expectedResult = itemQuantity + quantity1;
        itemInstance.increaseQuantity();
        int result = itemInstance.getQuantity();
        assertEquals(expectedResult,result,"Item quantity is not increased correctly.");
    }

    @Test
    void testToString() {
        String itemName = itemInstance.getName();
        Amount itemPrice = itemInstance.getPrice();
        double itemVAT = itemInstance.getVAT();
        String expectedResult = "Item name: " + itemName + "\n" + "Item price: " + itemPrice + "\n" + "VAT: " + itemVAT + "\n";
        String result = itemInstance.toString();
        assertEquals(expectedResult, result,"The returned String is not correct.");
    }
}