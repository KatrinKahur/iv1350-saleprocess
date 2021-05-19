package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.integration.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private final ItemDTO itemDTOYoghurt = new ItemDTO("yoghurt", new Amount(27), 20, new ItemIdentifier(3));
    private final ItemDTO itemDTOIceCream = new ItemDTO("Strawberry ice cream", new Amount(39), 20, new ItemIdentifier(14));
    private Sale saleInstance;

    @BeforeEach
    void setUp() {
        saleInstance = new Sale();

    }

    @AfterEach
    void tearDown() {
        saleInstance = null;
    }

    @Test
    void testRunningTotalZeroBeforeRegisteringFirstItem() {
        Amount expResult = new Amount();
        Amount result = saleInstance.getPaymentInformation().getRunningTotal();
        assertEquals(expResult, result, "Running total is not 0 at the sale start.");
    }

    @Test
    void testGettingRecentlyScannedItem() {
        saleInstance.registerItem(itemDTOYoghurt);
        Item expResult = new Item(itemDTOYoghurt);
        Item result = saleInstance.createSaleDTO().getRecentlyScannedItem();
        assertEquals(expResult, result, "The recently registered item is not correct.");
    }

    @Test
    void runningTotalUpdatedAfterRegisteringANewItem() {
        saleInstance.registerItem(itemDTOYoghurt);
        Item itemInstance = new Item(itemDTOYoghurt);
        Amount expRunningTotal = itemInstance.getPriceWithVAT();
        Amount runningTotal = saleInstance.getPaymentInformation().getRunningTotal();
        assertEquals(expRunningTotal, runningTotal, "Running total is not correct.");
    }

    @Test
    void testUpdatingQuantityOfItemThatAlreadyExistsInTheSale() {
        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.registerItem(itemDTOYoghurt);
        int expItemQuantityAfterRegisteringTheItem = 2;
        int itemQuantityAfterRegisteringTheItem = saleInstance.createSaleDTO().getRecentlyScannedItem().getQuantity();
        assertEquals(expItemQuantityAfterRegisteringTheItem, itemQuantityAfterRegisteringTheItem,
                "Item quantity is not updated correctly.");
    }

    @Test
    void testCalculatingTotalPrice(){
        Item itemInstance = new Item(itemDTOYoghurt);
        Item anotherItemInstance = new Item(itemDTOIceCream);
        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.registerItem(itemDTOIceCream);
        Amount expResult = itemInstance.getPriceWithVAT().plus(anotherItemInstance.getPriceWithVAT());
        Amount result = saleInstance.getPaymentInformation().getRunningTotal();
        assertEquals(expResult, result, "The total price returned by the sale is not correct.");
    }

    @Test
    void testPaymentRegisteredInTheSale(){
        saleInstance.registerItem(itemDTOYoghurt);
        CashRegister cashRegister = new CashRegister();
        Amount cashPayment = new Amount(125);
        saleInstance.registerPayment(cashRegister, cashPayment);
        Amount expResult = cashPayment;
        Amount result = saleInstance.getPaymentInformation().getPaidAmount();
        assertEquals(expResult, result, "PaymentInformation is not registered correctly.");
    }

    @Test
    void testCalculateVAT(){
        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.registerItem(itemDTOIceCream);
        Item instance = new Item(itemDTOYoghurt);
        Item anotherInstance = new Item(itemDTOIceCream);
        Amount expResult = instance.getVATConvertedIntoAmount().plus(anotherInstance.getVATConvertedIntoAmount());
        saleInstance.calculateVAT();
        Amount result = saleInstance.createSaleDTO().getTotalVAT();
        assertEquals(expResult, result, "VAT for the entire sale is incorrect.");
    }

    @Test
    void testThatNewItemIsAddedToTheSaleIfASimilarItemDoesNotExistInTheCurrentSale(){
        saleInstance.registerItem(itemDTOYoghurt);
        Item expResult = new Item(itemDTOYoghurt);
        Item result = saleInstance.createSaleDTO().getItemList().get(0);
        assertEquals(expResult, result, "Item is not added to the sale.");
    }

    @Test
    void testMultipleDifferentItemsRegisteredInTheSale(){
        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.registerItem(itemDTOIceCream);
        int expResult = 2;
        int result = saleInstance.createSaleDTO().getItemList().size();
        assertEquals(expResult, result, "Not all items are registered in the sale");
    }
    @Test
    void testRegisterItemReturnsSaleDTOWithCorrectData(){
       SaleDTO saleInfo = saleInstance.registerItem(itemDTOYoghurt);
       String result = saleInfo.toString();
       assertTrue(result.contains(
               Integer.toString((int)saleInstance.getPaymentInformation().getRunningTotal().getAmount())),
               "The running total included in the sale info is incorrect.");
       assertTrue(result.contains(itemDTOYoghurt.getName()),
               "The item name included in the sale info is incorrect.");
       assertTrue(result.contains(Integer.toString((int)itemDTOYoghurt.getPrice().getAmount())),
               "The item price included in the sale info is incorrect,");
    }
}