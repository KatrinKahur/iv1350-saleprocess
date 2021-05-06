package se.kth.iv1350.saleProcess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.CashRegister;
import se.kth.iv1350.saleProcess.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    ByteArrayOutputStream outputContent;
    PrintStream originalSystemOut;
    private Printer printer;
    private Sale sale;
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        outputContent = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputContent));
        printer = new Printer();
        sale = new Sale();
        cashRegister = new CashRegister();
    }

    @AfterEach
    void tearDown() {
        outputContent = null;
        System.setOut(originalSystemOut);
        printer = null;
        sale = null;
        cashRegister = null;
    }

    @Test
    void testSystemOutputForPrintReceipt() {
        ItemDTO item = new ItemDTO("tomato juice", new Amount(38),12, new ItemIdentifier(35));
        sale.registerItem(item);
        sale.calculateTotalPrice();
        Amount cashPayment = new Amount(88);
        sale.registerPayment(cashRegister, cashPayment);
        sale.printReceipt(printer);
        String result = outputContent.toString();
        assertTrue(result.contains(item.getName()), "Item name is registered on the receipt.");
        assertTrue(result.contains(item.getPrice().toString()), "Item price is registered on the receipt.");
        assertTrue(result.contains(sale.getTotalPrice().toString()), "Total price is registered on the receipt.");
        assertTrue(result.contains(Integer.toString((int)item.getVAT())), "VAT is registered on the receipt.");
        assertTrue(result.contains("Thank you for your purchase!"), "Incorrect test on the receipt.");
    }
}