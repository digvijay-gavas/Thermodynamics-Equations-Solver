/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.input;

import he.error.HEException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author DIGI
 */
public class Inputs extends Vector<Input> {
    
    final boolean isSearchResult;
    private ResultSet searchResult;

    public Inputs() {
        this.isSearchResult = false;
    }

    public Inputs(ResultSet searchResult) {
        this.isSearchResult = true;
        this.searchResult=searchResult;
    }
    
    public Input next() throws SQLException
    {
        searchResult.next();
        return new Input(searchResult.getInt("id"));
    }
    
    public Input previous() throws SQLException
    {
        searchResult.previous();
        return new Input(searchResult.getInt("id"));
    }
    public boolean isEnd() throws SQLException
    {
        if(searchResult.next())
        {
            searchResult.previous();
            return false;
        }
        else
            return true;
    }
      
    
    public int getUnknownCount()
    {
        if(isSearchResult)
            return  -1;
                    
        int count=0;
        for (int i = 0; i < this.size(); i++) 
            if(this.get(i).isUnknown())
                count++;
        return count;
    }
    public int[] getUnknownIndexArray()
    {
        if(isSearchResult)
            return  null;
        
        int count=0;
        int[] unknown_index=new int[getUnknownCount()];
        for (int i = 0; i < this.size(); i++) 
            if(this.get(i).isUnknown())
            {
                unknown_index[count]=i;
                count++;
            }
        return unknown_index;
    }
    
    /*public int getUnknown()
    {
        int count=0;
        for (int i = 0; i < this.size(); i++) 
            if(this.get(i).isUnknown())
                count++;
        return count;
    }*/
    
    public double[] toDoubleArray()
    {
        double values[]=new double[this.size()];
        for (int i = 0; i < this.size(); i++) 
            values[i]=this.get(i).value;
        
        return values;
    }
    
    public Input getById(int id)
    {
        if(isSearchResult)
            return  null;
        
        for (int i = 0; i < this.size(); i++)
            if (this.get(i).id==id) 
                return this.get(i);
        return null;
    }
    
    @Deprecated
    public int indexOfName(String name)
    {
        if(isSearchResult)
            return  -1;
        
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).name.equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }
    
    @Deprecated
    public int indexOfSymbol(String symbol)
    {
        if(isSearchResult)
            return  -1;
        
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).name.equalsIgnoreCase(symbol))
                return i;
        }
        return -1;
    }
    
    public Input getByName(String name)
    {
        if(isSearchResult)
            return  getByName(name);
        
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).name.equalsIgnoreCase(name))
                return this.get(i);
        }
        return null;
    }
    
}
