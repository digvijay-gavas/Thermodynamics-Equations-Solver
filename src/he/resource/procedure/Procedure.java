/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.procedure;

import he.resource.formula.Formula;
import he.resource.formula.FormulaDB;
import he.resource.formula.Formulae;
import he.resource.input.Input;

import he.resource.input.Inputs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Digvijay
 */
public class Procedure {
    Inputs inputs;
    Formulae formulae;
    public final int id;
    public final int[] inputids;
    public final int[] formulaids;
    public final String name;
    public String description;
    
    public static double requiredAccuracy=0.1;

    public Procedure(int id) {
        ResultSet rs=ProcedureDB.getProcedureResultSet(id);
        String inputids="";
        String formulaids="";
        String name="";
        
        try {
            rs.next();
            name=rs.getString("name");
            formulaids=rs.getString("formulaids");            
            inputids=rs.getString("inputids");
            this.description=rs.getString("description");
        } catch (SQLException ex) {
            id=-1;
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id=id;
        this.name=name;
        this.formulaids=Arrays.stream(formulaids.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        this.inputids = Arrays.stream(inputids.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        formulae=new Formulae();
        for (int i = 0; i < this.formulaids.length; i++) {
            formulae.add(new Formula(this.formulaids[i]));
        }
    
    }

    public Procedure(Inputs inputs, Formulae formulae,String name) {
        this.inputs = inputs;
        this.formulae = formulae;
        this.id =-1;
        
        Set<Integer> inputids=new HashSet<Integer>();
        formulaids=new int[formulae.size()];
        for (int i = 0; i < formulae.size(); i++) {
            formulaids[i]=formulae.get(i).id; 
            for (int j = 0; j < formulae.get(i).inputids.length; j++) {
                inputids.add(formulae.get(i).inputids[j]);
            }
        }
        
        Object[] temp=inputids.toArray();
        this.inputids=new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            this.inputids[i]=(int)temp[i];
        }
        
        this.name = name;
    }
    
    
    
    
    
    
    public void setInputs(Inputs inputs)
    {
        this.inputs=inputs;
    }
    
   
   
    public void run()
    {
        //updateUnknownCount();
        for (int i = 0; i < formulae.size(); i++) {
            formulae.get(i).setInputs(inputs);
            System.out.println("Unkwon ---"+formulae.get(i).getUnknownCount());
            if(formulae.get(i).getUnknownCount()/*unknownCount*/==1)
            {
                System.out.println("run() "+formulae.get(i).name+": "+formulae.get(i).relation);
                System.out.println("ans "+formulae.get(i).solve());
                //updateUnknownCount();
            }
        }
    }
    
    /*private void updateUnknownCount()
    {
        for (int i = 0; i < formulae.size(); i++) {
            for (int j = 0; j < inputs.size(); j++) {
                //int fitNo=0;
                for (int k = 0; k < formulae.get(i).inputids.length; k++) {
                    if (formulae.get(i).inputids[k]==inputs.get(j).id && inputs.get(j).isUnknown) 
                    {
                        formulae.get(i).unknownCount++;
                        System.out.println(formulae.get(i).name+"  "+inputs.get(j).name +"  "+formulae.get(i).unknownCount);
                    }
                }
            }
        }
    }*/
    public void display()
    {
        
    }

    @Deprecated
    public void setFormulae(Formulae formulae) {
        this.formulae=formulae;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
