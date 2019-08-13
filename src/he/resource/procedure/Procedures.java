/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.procedure;

import he.resource.input.Input;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Digvijay
 */
public class Procedures extends Vector<Input>{
    ResultSet searchResult;
    final boolean isSearchResult;

    public Procedures() {
        this.isSearchResult = false;
    }
    
    
    public Procedures(ResultSet searchResult) {
        this.isSearchResult = true;
        this.searchResult=searchResult;
    }
    
    public Procedure next() throws SQLException
    {
        searchResult.next();
        return new Procedure(searchResult.getInt("id"));
    }
    
    public Procedure previous() throws SQLException
    {
        searchResult.previous();
        return new Procedure(searchResult.getInt("id"));
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

    

    
    
}
