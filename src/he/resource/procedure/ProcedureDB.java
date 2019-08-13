/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.procedure;

import he.error.HEException;
import he.resource.database.IDB;
import he.resource.input.InputsDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Digvijay
 */
public class ProcedureDB {
    static ResultSet getProcedureResultSet(int id)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from HE_PROCETURES where id=?");
            ps.setInt(1, id);
            return  ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    static public Procedures searchProcedures(String search)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select id from HE_PROCETURES where name like ? or description like ?");
            ps.setString(1,"%"+search+"%");
            ps.setString(2,"%"+search+"%");
            return  new Procedures(ps.executeQuery());
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    /**
     * add Formula to Internal Database
     * @param procedure Procedure object 
     * @throws HEException for not adding Procedure in database
     */
    
    static public void add(Procedure procedure) throws HEException
    {
        
        //----------------------------------------------------------------------
        
        String query="INSERT INTO HE_PROCETURES(inputids,formulaids,name,description) VALUES('"+Arrays.toString(procedure.inputids).replace("[", "").replace("]", "")+"','"+Arrays.toString(procedure.formulaids).replace("[", "").replace("]", "")+"','"+procedure.name+"','"+procedure.description+"')";
        
        if(null==IDB.con)
            IDB.connectDB();

        
        try {
            PreparedStatement ps=IDB.con.prepareStatement(query);
            if(1==ps.executeUpdate())
                System.out.println("[INFO] Procedure :"+procedure.name+" added to IDB");
            else
            {
                System.out.println("[INFO] Procedure :"+procedure.name+" fail add to IDB");
                throw new HEException("Procedure :"+procedure.name+" fail add to IDB");
            }
        } catch (SQLException ex) {
            System.out.println("[ERROR] "+ex.getMessage());
            throw new HEException("unable to fire query in IDB", ex);
        }

    }
    
}
