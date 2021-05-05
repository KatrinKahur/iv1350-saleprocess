package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatChangeIsCalculatedCorrectly(){
        CashRegister cashRegister = new CashRegister();
        Sale saleInstance = new Sale();
        ItemDTO itemDTO = new ItemDTO("milk", new Amount(45), 20, new ItemIdentifier(28));
        saleInstance.registerItem(itemDTO);
        Amount totalPrice = saleInstance.endSale();




    }
}