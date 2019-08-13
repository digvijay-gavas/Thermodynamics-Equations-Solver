/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.formula;

import he.resource.input.Input;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author DIGI
 */
public class Formulae extends Vector<Formula>
{
    final boolean isSearchResult;
    private ResultSet searchResult;

    public Formulae() {
        this.isSearchResult = false;
    }

    public Formulae(ResultSet searchResult) {
        this.isSearchResult = true;
        this.searchResult = searchResult;
    }
    
    public Formula next() throws SQLException
    {
        searchResult.next();
        return new Formula(searchResult.getInt("id"));
    }
    
    public Formula previous() throws SQLException
    {
        searchResult.previous();
        return new Formula(searchResult.getInt("id"));
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
}
