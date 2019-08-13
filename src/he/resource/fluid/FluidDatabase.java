/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.resource.fluid;

import he.resource.database.IDB;
import he.resource.input.InputsDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FluidDatabase {

        static Connection con;
        
        static public ResultSet getAllFluids()
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from HE_FLUIDs");
            return  ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
        
        
        static public ResultSet getAllFluidProperties(String Fluid)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from " + Fluid );
            return  ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
        
        //Following method not in use. Can be discarded.
    	private static boolean connect() throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","roomies");
                
		return true;
	}
        
        public static Fluid retriveFluid(String fluid) throws Exception{
            try {
                if(null==con)
                    connect();
                
                
                Fluid f=null;
                
                PreparedStatement psF = con.prepareStatement("SELECT * FROM tabs WHERE table_name = ?");
                psF.setString(1, fluid);
                ResultSet rF=psF.executeQuery();
                if(!rF.next())
                    throw  new Exception("Fluid not found");
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(FluidDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
        public static Connection getDatabaseConnection() throws SQLException{
            if(null==con)
              connect();
            
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","12345");
	 return c;
            
        }


        public  int saveFluid(String fluid) {
            
            return 0;
        }

}
