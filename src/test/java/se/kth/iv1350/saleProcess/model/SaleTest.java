package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

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
    void testRunningTotalZero() {
        Amount expRunningTotal = new Amount();
        Amount runningTotal = saleInstance.getRunningTotal();
        assertEquals(expRunningTotal, runningTotal, "Running total is not 0 at the sale start.");
    }

    @Test
    void testGettingRecentlyScannedItem() {
        saleInstance.registerItem(itemDTOYoghurt);
        Item expResult = new Item(itemDTOYoghurt);
        Item result = saleInstance.getRecentlyScannedItem();
        assertEquals(expResult, result, "The recently registered item is not correct.");
    }

    @Test
    void runningTotalUpdatedAfterRegisteringANewItem() {
        saleInstance.registerItem(itemDTOYoghurt);
        Item itemInstance = new Item(itemDTOYoghurt);
        itemInstance.calculatePriceWithVAT();
        Amount expRunningTotal = itemInstance.getPriceWithVAT();
        Amount runningTotal = saleInstance.getRunningTotal();
        assertEquals(expRunningTotal, runningTotal, "Running total is not correct.");
    }

    @Test
    void testUpdatingQuantityOfItemThatAlreadyExistsInTheSale() {
        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.registerItem(itemDTOYoghurt);
        int expItemQuantityAfterRegisteringTheItem = 2;
        int itemQuantityAfterRegisteringTheItem = saleInstance.getRecentlyScannedItem().getQuantity();
        assertEquals(expItemQuantityAfterRegisteringTheItem, itemQuantityAfterRegisteringTheItem, "Item quantity is not updated correctly.");
    }

    @Test
    void testCalculatingTotalPrice(){
        Item itemInstance = new Item(itemDTOYoghurt);
        Item anotherItemInstance = new Item(itemDTOIceCream);
        itemInstance.calculatePriceWithVAT();
        anotherItemInstance.calculatePriceWithVAT();

        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.registerItem(itemDTOIceCream);
        Amount expResult = itemInstance.getPriceWithVAT().plus(anotherItemInstance.getPriceWithVAT());
        Amount result = saleInstance.endSale();
        assertEquals(expResult, result, "The total price returned by the sale is not correct.");
    }

    @Test
    void testPaymentRegisteredInTheSale(){
        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.endSale();
        CashRegister cashRegister = new CashRegister();
        Amount cashPayment = new Amount(125);
        saleInstance.registerPayment(cashRegister, cashPayment);
        Amount expResult = cashPayment;
        Amount result = saleInstance.getCashPayment();
        assertEquals(expResult, result, "Payment is not registered correctly.");
    }

    @Test
    void testThatChangeIsCalculatedCorrectly(){
        CashRegister cashRegister = new CashRegister();
        Sale saleInstance = new Sale();
        saleInstance.registerItem(itemDTOYoghurt);
        Amount totalPrice = saleInstance.endSale();
        Amount cashPayment = new Amount(100);
        saleInstance.registerPayment(cashRegister, cashPayment);
        Amount expectedChange = cashPayment.minus(totalPrice);
        Amount change = saleInstance.getChange();
        assertEquals(expectedChange, change, "Change is not calculated correctly.");



    }

}