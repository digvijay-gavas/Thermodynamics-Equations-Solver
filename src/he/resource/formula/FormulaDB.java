/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.resource.formula;

import he.error.HEException;
import he.resource.database.IDB;
import he.resource.input.Inputs;
import he.resource.input.InputsDB;
import static he.resource.input.InputsDB.searchInput;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *==========================================================================================================
 *         Author                Date                                   Description
 *==========================================================================================================
 *
 */
public class FormulaDB {

    /**
     * Gives Resultset of formula from database
     * @param id unique id for formula
     * @return l
     */
    static ResultSet getFormulaResultSet(int id)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from HE_FORMULAE where id=?");
            ps.setInt(1, id);
            return  ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    /*static Formula getFormulaById(int id)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select * from HE_FORMULAE where id=?");
            ps.setInt(1, id);
            return  ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }*/
    
    
    static public Formulae searchFormulae(String search)
    {
        if(null==IDB.con)
            IDB.connectDB();
        try {
            PreparedStatement ps=IDB.con.prepareStatement("select id from HE_FORMULAE where name like ? or description like ?");
            ps.setString(1,"%"+search+"%");
            ps.setString(2,"%"+search+"%");
            return  new Formulae(ps.executeQuery());
        } catch (SQLException ex) {
            Logger.getLogger(InputsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    /**
     * add Formula to Internal Database
     * @param formula Formula object whose {@link he.resource.formula.Formula#setInputs(he.resource.input.Inputs) setInputs} method is called
     * use {@link he.resource.formula.Formula#Formula(int, java.lang.String, he.resource.input.Inputs, java.lang.String) } constructor
     * @throws HEException k
     * @throws IOException k
     */
    static public void add(Formula formula) throws HEException
    {
        
        //----------------------------------------------------------------------
        String relation=formula.relation;
        String query="INSERT INTO HE_FORMULAE(inputids,relation,classpath,name,description) VALUES('"+Arrays.toString(formula.inputids).replace("[", "").replace("]", "")+"','"+relation+"','he.user.resource.formulae.Formula_"+formula.name+"','"+formula.name+"','"+formula.description+"')";
        String formuladir="Formula";
        File dir=new File(formuladir+"/he/user/resource/formulae/");
        if(dir.mkdirs())
            System.out.println("[INFO] "+dir.getPath()+" DIR created");
        else
            System.out.println("[INFO] "+dir.getPath()+" DIR exist");

        File javafile=new File(dir.getPath()+"/Formula_"+formula.name+".java");
        try {
            if(javafile.createNewFile())
                System.out.println("[INFO] "+javafile.getName()+" java file created");
            else
                System.out.println("[INFO] "+javafile.getName()+" java file exist");
        } catch (IOException ex) {throw new HEException("cannot create "+javafile.getName()+" file at "+dir.getAbsolutePath()+" location",ex);}
        

        //----------------------------------------------------------------------
        for (int i = 0; i < formula.inputids.length; i++) {
            System.out.println("[----] "+relation);
            String search="{"+formula.inputids[i]+"}";
            while(relation.contains(search))
                relation=relation.replace(search, "value["+i+"]");

        }
        System.out.println("[----] "+relation);
        StringBuffer code=new StringBuffer("");
        code.append("package he.user.resource.formulae;import he.resource.formula.FormulaSover;\n");
        code.append("public class Formula_"+formula.name+" implements FormulaSover{\n");
        code.append("@Override\n");
        code.append("public double fzero(double value[]) {\n");
        code.append("return "+relation+";\n}}");
        
        try(  PrintWriter out = new PrintWriter(javafile))
        {
            out.println(code);
        } catch (FileNotFoundException ex) {throw new HEException("cannot write to file "+javafile.getName()+" file at "+dir.getAbsolutePath()+" location",ex);}


        File libdir=new File("lib");
        if(libdir.mkdir())
            System.out.println("[INFO] "+libdir.getName()+" created");
        else
            System.out.println("[INFO] "+libdir.getName()+" exist");

        //----------------------------------------------------------------------
        //File jar=new File("dist/HE1.jar");
        //File[] classfiles=new File[1];
        //classfiles[0]=new File("f/he/user/resource/formulae/Formula_"+for0mula.id+".class");

        //createJarArchive(jar, classfiles);
        if(null==IDB.con)
            IDB.connectDB();

        
        try {
            PreparedStatement ps=IDB.con.prepareStatement(query);
            if(1==ps.executeUpdate())
                System.out.println("[INFO] Formula :"+formula.name+" added to IDB");
            else
            {
                System.out.println("[INFO] Formula :"+formula.name+" fail add to IDB");
                throw new HEException("Formula :"+formula.name+" fail add to IDB");
            }
        } catch (SQLException ex) {
            System.out.println("[ERROR] "+ex.getMessage());
            throw new HEException("unable to fire query in IDB", ex);
        }

        try {
            
            runProcess("javac -classpath "+libdir.getPath()+"/HE.jar "+formuladir+"/he/user/resource/formulae/Formula_"+formula.name+".java");
            
            runProcess("jar uf "+libdir.getPath()+"/user.jar -C "+formuladir+" he/user/resource/formulae/Formula_"+formula.name+".class");
            System.out.println("[INFO] "+javafile.getName()+" class file added");
        } catch (Exception ex) {
            
            try{
                query="delete from HE_FORMULAE where name="+formula.name;
                PreparedStatement ps=IDB.con.prepareStatement(query);
                ps.execute();                
            }catch(SQLException ex1){throw new HEException("unable to remove formula with name="+formula.name, ex1);}
            System.out.println("[ERROR] "+javafile.getName()+" not able to compile and add to jar:\n"+ex.getMessage());
            throw new HEException("unable to compile "+javafile.getName()+" file at"+javafile.getAbsolutePath()+" location", ex);
        }
        

    }


    @Deprecated
    protected static void createJarArchive(File archiveFile, File[] tobeJared) {
    int BUFFER_SIZE = 10240;
    try {
      byte buffer[] = new byte[BUFFER_SIZE];
      // Open archive file
      FileOutputStream stream = new FileOutputStream(archiveFile);
      JarOutputStream out = new JarOutputStream(stream, new Manifest());

      for (int i = 0; i < tobeJared.length; i++) {
        if (tobeJared[i] == null || !tobeJared[i].exists()
            || tobeJared[i].isDirectory())
          continue; // Just in case...
        System.out.println("Adding " + tobeJared[i].getName());

        // Add archive entry
        JarEntry jarAdd = new JarEntry(tobeJared[i].getName());
        jarAdd.setTime(tobeJared[i].lastModified());
        out.putNextEntry(jarAdd);

        // Write file to archive
        FileInputStream in = new FileInputStream(tobeJared[i]);
        while (true) {
          int nRead = in.read(buffer, 0, buffer.length);
          if (nRead <= 0)
            break;
          out.write(buffer, 0, nRead);
        }
        in.close();
      }

      out.close();
      stream.close();
      System.out.println("Adding completed OK");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Error: " + ex.getMessage());
    }
  }


  private static void runProcess(String command) throws Exception {
    Process pro = Runtime.getRuntime().exec(command);
    //printLines(command + " stdout:", pro.getInputStream());
    //printLines(command + " stderr:", pro.getErrorStream());
    pro.waitFor();
    if(pro.exitValue()==1)
    {
        String line = null;
        String Errmsg="";
        BufferedReader in = new BufferedReader(
            new InputStreamReader(pro.getErrorStream()));
        while ((line = in.readLine()) != null) {
            Errmsg=Errmsg+"\n"+line;
        }
        System.out.println(Errmsg);
        throw new Exception();
    }
    //System.out.println(command + " exitValue() " + pro.exitValue());

  }

  public static void main(String[] args) {
    try {
      runProcess("javac Main.java");
      runProcess("java Main");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
