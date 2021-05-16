package se.kth.iv1350.saleProcess.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * This object logs exception messages to a file.
 */
public class ExceptionLogger {
    private PrintWriter excMsgLogFile;

    /**
     * Creates an instance on <code>ExceptionMessageLogger</code>
     */
    public ExceptionLogger()throws IOException {
        excMsgLogFile = new PrintWriter(new FileWriter("excMsg-log.txt"), true);
    }

    public void logException(Exception loggedExc){
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Exception name: ");
        logMessage.append(loggedExc.getCause());
        logMessage.append("\n");
        logMessage.append("Exception message: ");
        logMessage.append(loggedExc.getMessage());
        logMessage.append("\n");
        logMessage.append("Exception time: ");
        logMessage.append(LocalDateTime.now());
        excMsgLogFile.println(logMessage);
        loggedExc.printStackTrace(excMsgLogFile);
        excMsgLogFile.println();
    }
}
