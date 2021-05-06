package se.kth.iv1350.saleProcess.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    ByteArrayOutputStream outputContent;
    PrintStream originalSystemOut;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        outputContent = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputContent));
    }

    @AfterEach
    void tearDown(){
        outputContent = null;
        System.setOut(originalSystemOut);
    }

    @Test
    void testMain(){
        String[] args = null;
        Main.main(args);
        String result = outputContent.toString();
        assertTrue(result.contains("The program ends the sale and returns the total price."),
                "The main method does not produce the correct output.");
    }
}