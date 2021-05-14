package se.kth.iv1350.saleProcess.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.controller.OperationFailedException;
import se.kth.iv1350.saleProcess.integration.InvalidItemIdentifierException;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.integration.ServerNotRunningException;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionLoggerTest {
    private ExceptionLogger instance;
    private String logFileName = "excMsg-log.txt";

    @BeforeEach
    void setUp() {
        try{
            instance = new ExceptionLogger();
        }
        catch(IOException exception){
            fail("Creating ExceptionLogger failed.");
            exception.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        instance = null;
        File logFile = new File(logFileName);
        logFile.delete();
    }

    @Test
    void logException()throws IOException{
        ServerNotRunningException serverExc = new ServerNotRunningException();
        OperationFailedException opFailedExc = new OperationFailedException("Item registration failed. ", serverExc);
        instance.logException(opFailedExc);
        String expResultName = "Exception name: " + opFailedExc.getCause();
        String expResultMessage = "Exception message: " + opFailedExc.getMessage();
        assertTrue(fileContains(expResultName), "Wrong printout.");
        assertTrue(fileContains(expResultMessage), "Wrong exception message.");
    }

    private boolean fileContains(String searchedString) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(logFileName));
        String line = null;
        while( (line = file.readLine()) != null){
            if(line.contains(searchedString))
                return true;
        }
        return false;
    }
}