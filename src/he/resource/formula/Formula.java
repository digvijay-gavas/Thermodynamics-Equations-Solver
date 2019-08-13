/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.formula;



import he.resource.input.Input;
import he.resource.input.Inputs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import he.error.Logger;
import he.resource.procedure.Procedure;

/**
 *
 * @author Prasad
 */
public class Formula {

    /**
     *
     * @param value
     * @return
     */
    public final int id;
    public final int[] inputids;
    private String classpath;
    public final String relation;
    public final String name;
    String description;
    Inputs inputs;
    
    @Deprecated
    public int unknownCount=0;
    public int[] unknownIndexes;
    //String inputids;

    /**
     * Retrive formula from IDB with given id and populate all properties of formula from database including id,name,inputids,relation, classpath
     * @param id unique id in IDB, must exist in IDB
     */
    
    @Deprecated
    public Formula(int id) {
        ResultSet rs=FormulaDB.getFormulaResultSet(id);
        String inputids="";
        String name="";
        String relation="";
        try {
            rs.next();
            this.classpath=rs.getString("classpath");
            relation=rs.getString("relation");
            name=rs.getString("name");
            inputids=rs.getString("inputids");
            this.description=rs.getString("description");
        } catch (SQLException ex) {
            id=-1;
            Logger.add("[ERROR] Formula : "+ex.getMessage());
        }
        this.id=id;
        this.name=name;
        this.relation=relation;
        this.inputids = Arrays.stream(inputids.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
    /**
     * use to create new formula object temporary, this can be saved in IDB by calling {@link he.resource.formula.FormulaDB#add(he.resource.formula.Formula, java.lang.String) FormulaDB.add(Formula,String)} method
     * @param name name for formula
     * @param inputs sequential {@link he.resource.input.Inputs Inputs} require for formula
     */
    public Formula(String name,Inputs inputs,String relation) {
        this.id=-1; 
        this.name=name;
        this.relation=relation;
        
        
        this.inputs=new Inputs();
        for (int i = 0; i < inputs.size(); i++)
        {
            if(relation.contains("{"+inputs.get(i).id+"}"))
            this.inputs.add(inputs.get(i));
            
        }
        
        this.inputids=new int[this.inputs.size()];
        for (int i = 0; i < this.inputids.length; i++) {
            inputids[i]=this.inputs.get(i).id;
        }
        //setInputs(inputs);
        //this.inputids = Arrays.stream(inputids.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
    public Formula(String name,Inputs inputs,String relation,String description) {
        this(name,inputs,relation);
        this.description=description;
    
    }
    
    
    /**
     * This method will set given input vector( {@link he.resource.input.Inputs Inputs Object}) including unknown input
     * @param inputs takes list of {@link he.resource.input.Input Input} Object
     */

    public void setInputs(Inputs inputs)
    {
        unknownCount=0;
        this.inputs=new Inputs();
        for (int i = 0; i < this.inputids.length; i++)
        {
            this.inputs.add(inputs.getById(this.inputids[i]));
            if(true==inputs.getById(this.inputids[i]).isUnknown)            
                 unknownCount++;
            
        }
        //Logger.add("setInput ----"+unknownCount);
    }
    public int getUnknownCount()
    {
        int unknownCount=0;
        //this.inputs=new Inputs();
        for (int i = 0; i < this.inputs.size(); i++)
        {
            //Logger.add("getUnknownCount ----"+inputs.get(i).isUnknown);
            if(true==inputs.get(i).isUnknown)            
                 unknownCount++;
        }
        return unknownCount;
    }
    
    /**
     * Numerically solves the formula (f(x)=0)to find unknown value
     * @return calculated value of unknown by bisection method
     */
    
    public double solve()
    {
        if (getUnknownCount()/*unknownCount*/==1) 
        {
            int unKnown=-1;
            for (int i = 0; i < inputs.size(); i++) 
                 if (inputs.get(i).isUnknown)
                     unKnown=i;
            
            double accuracy=Double.MAX_VALUE;
            double x1=inputs.get(unKnown).value
                  ,x2=++inputs.get(unKnown).value;
            double fzero1,fzero2;
            while (Math.abs(accuracy)>Procedure.requiredAccuracy) {
                
                inputs.get(unKnown).value=x1;
                fzero1=fzero(inputs.toDoubleArray());
                inputs.get(unKnown).value=x2;
                fzero2=fzero(inputs.toDoubleArray());
                
                double x3=(fzero2*x1-fzero1*x2)/(fzero2-fzero1);
                accuracy=x3-x2;
                x1=x2;
                x2=x3;
            }
            inputs.get(unKnown).isUnknown=false;
            Logger.add(""+inputs.get(unKnown).name+"=");
            return x2;
        }
        
        return Double.NaN;
    }
    /**
     * This method is used for <b>TESTING</b>
     * @param inputs h
     * @return j
     * @deprecated will be removed
     */
    @Deprecated
    public double solve1(Inputs inputs)
    {
        int unknown_index[]=inputs.getUnknownIndexArray();
        if(unknown_index.length==1)
        {
            double answer = fzero(inputs.toDoubleArray());
            if (fzero(inputs.toDoubleArray())<0)
            {
                double tukka1 = inputs.get(unknown_index[0]).value + 10;
                double Tm=inputs.get(unknown_index[0]).value;
                
                inputs.get(unknown_index[0]).value=tukka1;
                double answer1 = fzero(inputs.toDoubleArray());
                Logger.add(""+fzero(inputs.toDoubleArray()));
                double m = (answer1 - answer)/(tukka1 - Tm);
                double c = answer - (m * Tm);
                double tukka2 = -c/m;
                inputs.get(unknown_index[0]).value=tukka2;
                
                Logger.add(""+fzero(inputs.toDoubleArray()));
                Logger.add(""+tukka2);
                for (int i = 0; i < inputs.size(); i++)
                {
                    Logger.add(i+"  > "+inputs.get(i).value);
                }
                unknownCount--;
                return tukka2;
            }
            
        }
        
        else
        {
            Logger.add("Couldn't find unknown");
        }
        
        return Double.NaN;
        
    }
    
    
    
    private double fzero(double value[])
    {
        try {
            return ((FormulaSover)Class.forName(classpath).newInstance()).fzero(value);
        } catch (InstantiationException ex) {
            Logger.add("[ERROR] Formula#fzero() : "+ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.add("[ERROR] Formula#fzero() : "+ex.getMessage());
        } catch (ClassNotFoundException ex )
        {
            Logger.add("[ERROR] Formula#fzero() : "+ex.getMessage());
        }
        return Double.NaN;
    }
    
}
