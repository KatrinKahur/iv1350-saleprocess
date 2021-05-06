package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testIfPriceWithVATIsCalculatedCorrectly() {
        ItemDTO testItemDTO = new ItemDTO("yoghurt", new Amount(27), 20, new ItemIdentifier(3));
        Item itemInstance = new Item(testItemDTO);
        Amount itemPrice = testItemDTO.getPrice();
        double itemVAT = testItemDTO.getVAT();
        double itemVATDividedByHundred = itemVAT/100;
        Amount itemVATConvertedIntoAmount = new Amount(itemVATDividedByHundred).multiply(itemPrice);
        Amount expectedResult = itemPrice.plus(itemVATConvertedIntoAmount);
        Amount result = itemInstance.getPriceWithVAT();
        assertEquals(expectedResult,result,"Calculated price is not correct.");
    }

    @Test
    void testItemQuantityIsIncreasedAfterAddingASimilarItemToTheSale() {
        ItemDTO itemDTO = new ItemDTO("yoghurt", new Amount(27), 20, new ItemIdentifier(3));
        Item itemInstance = new Item(itemDTO);
        itemInstance.increaseQuantity();
        int expectedResult = 2;
        int result = itemInstance.getQuantity();
        assertEquals(expectedResult,result,"Item quantity is not increased correctly.");
    }

    @Test
    void testToString() {
        ItemDTO itemDTO = new ItemDTO("yoghurt", new Amount(27), 20, new ItemIdentifier(3));
        Item itemInstance = new Item(itemDTO);
        String expectedResult = "Item name: " + itemDTO.getName() + "\n" + "Item price: " + itemDTO.getPrice() + " SEK\n"
                + "VAT: " + itemDTO.getVAT() + " %\n";
        String result = itemInstance.toString();
        assertEquals(expectedResult, result,"The returned strings do not have the same content.");
    }

    @Test
    void testItemsEqualBasedOnTheirBarcode(){
        ItemDTO itemDTO = new ItemDTO("cornflakes", new Amount(50), 20, new ItemIdentifier(25));
        ItemDTO anotherItemDTO = new ItemDTO("milk", new Amount(23), 20, new ItemIdentifier(25));
        Item itemInstance = new Item(itemDTO);
        Item anotherItemInstance = new Item(anotherItemDTO);
        boolean condition = itemInstance.equals(anotherItemInstance);
        assertTrue(condition, "Items are not equal.");
    }

    @Test
    void testItemsNotEqualBasedOnTheirBarcode(){
        ItemDTO itemDTO = new ItemDTO("cornflakes", new Amount(50), 20, new ItemIdentifier(25));
        ItemDTO anotherItemDTO = new ItemDTO("milk", new Amount(23), 20, new ItemIdentifier(27));
        Item itemInstance = new Item(itemDTO);
        Item anotherItemInstance = new Item(anotherItemDTO);
        boolean condition = itemInstance.equals(anotherItemInstance);
        assertFalse(condition, "Items are equal.");
    }

    @Test
    void testItemToCompareNull(){
        ItemDTO itemDTO = new ItemDTO("cornflakes", new Amount(50), 20, new ItemIdentifier(25));
        Item itemInstance = new Item(itemDTO);
        Item anotherItemInstance = null;
        boolean condition = itemInstance.equals(anotherItemInstance);
        assertFalse(condition, "Items are equal.");
    }

    @Test
    void testItemToCompareNotInstanceOfTheClass(){
        ItemDTO itemDTO = new ItemDTO("cornflakes", new Amount(50), 20, new ItemIdentifier(25));
        Item itemInstance = new Item(itemDTO);
        ItemIdentifier anotherInstance = new ItemIdentifier(9);
        boolean condition = itemInstance.equals(anotherInstance);
        assertFalse(condition, "Items are equal.");
    }

    @Test
    void testItemQuantitySetToDefaultValueOfOne(){
        ItemDTO itemDTO = new ItemDTO("cornflakes", new Amount(50), 20, new ItemIdentifier(25));
        Item itemInstance = new Item(itemDTO);
        int expResult = 1;
        int result = itemInstance.getQuantity();
        assertEquals(expResult,result,"The default value of item quantity is not set to 1.");
    }
}