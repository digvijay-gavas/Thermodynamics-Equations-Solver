/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.database;

import he.error.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Digvijay
 */
public class IDB {

   
    public static Connection con;
    /**
     * @return  returns true if connection is made false else, 
     */
    public static boolean connectDB()
    {
        try {
            //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String passwardDB="12345";
            while(true)
            {
            try {
                //con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system",passwardDB);
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/janani","root",passwardDB);
                break;
                } catch (SQLException e) {
                
                    Logger.add("[INFO] IDB Asking Passward ");
                    JFrame frame = new JFrame("Internal DB Passward");
                    passwardDB = JOptionPane.showInputDialog(frame, "Enter DB passward:\n"+e.getMessage());
                }
            }
            
            Logger.add("[INFO] IDB connected ");
            Statement s=con.createStatement();
            //s.execute("alter session set NLS_COMP=ANSI");
            //s.execute("alter session set NLS_SORT=BINARY_CI;");
            return true;
        } catch (Exception ex) {
            return  false;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
