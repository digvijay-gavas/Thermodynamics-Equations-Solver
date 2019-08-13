/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.test;

import he.error.HEException;
import he.resource.formula.Formula;
import he.resource.formula.FormulaDB;
import he.resource.formula.Formulae;
import he.resource.input.Input;
import he.resource.input.Inputs;
import he.resource.input.InputsDB;
import he.resource.procedure.Procedure;
import he.resource.procedure.ProcedureDB;
import java.sql.ResultSet;

/**
 *
 * @author digvijay
 */
public class Test_Formula {
    public static void main(String[] args) throws HEException, Exception {
        Inputs inputs=new Inputs();
        inputs.add(new Input(1,2.0));
        inputs.add(new Input(2,2.0));
        inputs.add(new Input(3));
        inputs.add(new Input(4));
        inputs.add(new Input(5,2.69));
        inputs.add(new Input(6));
        inputs.add(new Input(7));
        inputs.add(new Input(8,3.2));
        //System.out.println("he.test.Test_Formula.main() " + inputs.indexOfName("SS"));
        FormulaDB.add(new Formula("AdBD", inputs, "{1}*Math.sin({2})+{4}"));
        FormulaDB.add(new Formula("DEaF", inputs, "{6}+Math.pow({5},2)+{4}"));
        FormulaDB.add(new Formula("FGsH", inputs, "{7}+{8}+{6}"));
        FormulaDB.add(new Formula("ABCfD", inputs, "{1}*{4}+Math.pow({3},{2})"));
        //FormulaDB.runProcess("javac -classpath lib/HE.jar Formula/he/user/resource/formulae/Formula_1.java");
        /*Procedure.requiredAccuracy=0.0001;
        Formulae formulae=new Formulae();
        formulae.add(new Formula(7));
        formulae.add(new Formula(8));
        formulae.add(new Formula(9));
        formulae.add(new Formula(10));
        
        Procedure p=new Procedure(inputs, formulae, "P2");
        ProcedureDB.add(p);*/
        
        
        /*Inputs i=InputsDB.searchInputs("gA");
        //Formulae i=FormulaDB.searchFormulae("");
        while (!i.isEnd()) {
            Input temp=i.next();
            System.out.println(temp.name+"--"+ temp.description);
            
        }*/
        Procedure p=new Procedure(2);
        p.setInputs(inputs);
        p.run();
        
        new Input("AA");
        InputsDB.add(new Input("dS7Wf", "sw", "just to check add method", new float[]{0,0,0,0,0}));
        InputsDB.add(new Input("dS9W d", "sw", "just to check add method", new float[]{0,0,0,0,0}));
        InputsDB.add(new Input("aS6Ws sa", "sw", "just to check add method", new float[]{0,0,0,0,0}));
        InputsDB.add(new Input("rS7Was a", "sw", "just to check add method", new float[]{0,0,0,0,0}));
        
    }
    
}
