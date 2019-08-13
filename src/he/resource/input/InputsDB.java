/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.input;

import he.error.HEException;
import he.error.Logger;
import he.resource.database.IDB;
import he.resource.procedure.Procedure;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;


/**
 *
 * @author DIGI
 */
public class InputsDB {
    //static Connection con=null;
    /**
     * 
     * @param search
     * @return
     * @deprecated will be made private in further versions please do not use
     */
    @Deprecated
    static public ResultSet searchInput(String search)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from HE_INPUTS where name like ? or description like ?");
            ps.setString(1,"%"+search+"%");
            ps.setString(2,"%"+search+"%");
            return  ps.executeQuery();
        } catch (SQLException ex) {
            Logger.add(ex.getMessage());
        }
        
        return null;
    }
    
    static public Inputs searchInputs(String search)
    {
        return new Inputs(searchInput(search));
    }
    
    static ResultSet getInputResultSet(int id)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from HE_INPUTS where id=?");
            ps.setInt(1, id);
            return  ps.executeQuery();
        } catch (SQLException ex) {
            Logger.add(ex.getMessage());
        }
        
        return null;
    }
    
    static ResultSet getInputResultSet(String name) throws SQLException
    {
        if(null==IDB.con)
        IDB.connectDB();
        PreparedStatement ps=IDB.con.prepareStatement("select * from HE_INPUTS where name=?");
        ps.setString(1, name);
        return  ps.executeQuery();
    }
    
    /*static public int getInputId(String search)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from HE_INPUT where Name like ? or Description like ?");
            ps.setString(1,"%"+search+"%");
            ps.setString(2,"%"+search+"%");
            ResultSet rs=ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }*/
    
    /*static private boolean connectDB()
    {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","roomies");
            System.out.println("DB connected ");
            Statement s=con.createStatement();
            //s.execute("alter session set NLS_COMP=ANSI");
            //s.execute("alter session set NLS_SORT=BINARY_CI;");
            return true;
        } catch (SQLException ex) {
            return  false;
        }
    }*/
    
    Input getByName(String name) throws SQLException
    {
        return new Input(IDB.con.prepareStatement("select id from HE_INPUTS where name="+name).executeQuery().getInt("id"));
    }
    
    static public void add(Input input) throws HEException
    {
        
        //----------------------------------------------------------------------
        
        String query="INSERT INTO HE_INPUTS(M,L,T,K,A,symbol,name,description) VALUES("+input.M+","+input.L+","+input.T+","+input.K+","+input.A+",'"+input.symbol+"','"+input.name+"','"+input.description+"')";
        
        if(null==IDB.con)
            IDB.connectDB();

        try {
            PreparedStatement ps=IDB.con.prepareStatement(query);
            if(1==ps.executeUpdate())
                Logger.add("[SUCESS] Input : "+input.name+" sucessfully added to IDB");
            else
            {
                Logger.add("[ERROR] Input :"+input.name+" fail to add in IDB");
                throw new HEException("Input :"+input.name+" fail to add in IDB");
            }
        } catch (SQLException ex) {
            Logger.add("[ERROR] Inputs : "+ex.getMessage());
            throw new HEException(ex);
        }

    }

}
