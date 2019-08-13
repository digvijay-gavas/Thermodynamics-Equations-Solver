/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.input;

import he.error.HEException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Digvijay Gavas
 */
public class Input {
    public final int id;
    public final float M,L,T,K,A;
    public double value;
    public final String name;
    public final String symbol;
    public String description;
    public boolean isUnknown=false;
    
    
    public Input(String name) throws HEException
    {
        String symbol="";
        float M = 0,L=0,T=0,K=0,A=0;
        int id=-1;
        ResultSet rs;
        try {
            rs = InputsDB.getInputResultSet(name);
            
                rs.next();
                M=rs.getFloat("M");
                L=rs.getFloat("L");
                T=rs.getFloat("T");
                K=rs.getFloat("K");
                A=rs.getFloat("A");
                id=rs.getInt("id");
                symbol=rs.getString("symbol");
                this.description=rs.getString("description");
            
            
        } catch (SQLException ex) {
            System.out.println("[ERROR] Input : "+ex.getMessage());
            throw new HEException(ex);
        }
        this.name=name;
            this.symbol=symbol;
            this.M=M;
            this.L=L;
            this.T=T;
            this.K=K;
            this.A=A;
            this.id=id;
        
    }
    
    @Deprecated
    public Input(int id)
    {
        this(id, 1);  
        this.isUnknown=true;
    }
    @Deprecated
    public Input(int id,double value)
    {
        this.value=value;
        
        ResultSet rs=InputsDB.getInputResultSet(id);
        String name="";
        String symbol="";
        float M = 0,L=0,T=0,K=0,A=0;
        try {
            rs.next();
            M=rs.getFloat("M");
            L=rs.getFloat("L");
            T=rs.getFloat("T");
            K=rs.getFloat("K");
            A=rs.getFloat("A");
            name=rs.getString("name");
            symbol=rs.getString("symbol");
            this.description=rs.getString("description");
        } catch (SQLException ex) {
            id=-1;
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.name=name;
        this.symbol=symbol;
        this.M=M;
        this.L=L;
        this.T=T;
        this.K=K;
        this.A=A;
        this.id=id;
        
    }
    @Deprecated
    public Input(int id,double value,boolean isUnknown)
    {
        this(id,value);
        this.isUnknown=isUnknown;        
    }
    public Input(String name,String symbol,String description,float[] unit)
    {
        this.name=name;
        this.symbol=symbol;
        this.description=description;
        this.M=unit[0];
        this.L=unit[1];
        this.T=unit[2];
        this.K=unit[3];
        this.A=unit[4];
        this.id=-1;
    }

    @Override
    public String toString() {
        if(!isUnknown)
        return super.toString()+"|"+name+"| value:"+value +"| Unit:"+M+","+L+","+T+","+K+","+A+"|"+description; //To change body of generated methods, choose Tools | Templates.
        else
        return super.toString()+"|"+name+"(Unknown)| value:"+value +"| Unit:"+M+","+L+","+T+","+K+","+A+"|"+description; //To change body of generated methods, choose Tools | Templates.    
    }

    /**
     * @return the isUnknown
     */
    public boolean isUnknown() {
        return isUnknown;
    }

    /**
     * @param isUnknown the isUnknown to set
     */
    public void setUnknown(boolean isUnknown) {
        this.isUnknown = isUnknown;
    }

    
}
