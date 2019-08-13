package he.ui;

import he.resource.input.InputsDB;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class HEFrame extends Frame{

	public HEFrame() {
		setBounds(0,0,100,100);
		setVisible(true);
	}
	public static void main(String[] args) throws InterruptedException {
		//Frame f=new Frame();
		//f.setVisible(true);
                JFrame f=new JFrame();
                
                JInternalFrame search=new JInternalFrame("Search");
                search.setBounds(0, 0, 200, 500);
                GridLayout gl=new GridLayout(2, 1);
                search.setLayout(gl);
                search.setVisible(true);
                
                
                JTextField searchText=new JTextField("Temp");
                ResultSet rs=InputsDB.searchInput("%%");
                JList searchList= new JList();;
            try {
                Vector<String> s=new Vector<String>();
                while(rs.next())
                {
                    s.add(rs.getString("Name"));
                }
                searchList.setListData(s);
            } catch (SQLException ex) {
                Logger.getLogger(HEFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                searchText.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent e) {
                        System.out.println("searching");
                        ResultSet rs=InputsDB.searchInput(((JTextField)e.getComponent()).getText());
                        try {
                            while (rs.next())
                            {
                                
                                System.out.print(rs.getString("Name")+"\t\t\t\t");
                                System.out.println(rs.getString("Description"));
                                
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(HEFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    public void keyPressed(KeyEvent e) {
                        
                    }

                    public void keyReleased(KeyEvent e) {
                        
                    }
                });
                
                
                Component add = f.add(search);
                search.add(searchText);
                search.add(searchList);
                f.setBounds(300,0,200,500);
		f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("szfsdf");
		//Thread.sleep(1000);
		//System.exit(0);
	}
}
