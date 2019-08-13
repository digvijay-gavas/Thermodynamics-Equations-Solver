/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.error;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Digvijay
 */
public class Logger {
    public static String error;
    public static boolean does_error_occur=false;
    final static File logFile=new File("HE.log");
    static FileWriter logFileWriter;
    static public void add(String log)
    {
        if(logFileWriter==null)
            try {
                logFileWriter=new FileWriter(logFile);
                
            } catch (IOException ex) {System.out.println("he.error.Logger.add() :" +ex.getMessage());}
        
        try {
                
                logFileWriter.append(log+"\n");
                logFileWriter.flush();
            } catch (IOException ex) {System.out.println("he.error.Logger.add() :" +ex.getMessage());}
        
        System.out.println(log);
        
    }
}
