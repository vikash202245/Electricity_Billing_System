package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{
    String smeter;
    JButton back;
    Paytm(String smeter)
    {
        this.smeter = smeter;
        JEditorPane j = new JEditorPane();
        // gives text area
        j.setEditable(false);
        try{
            j.setPage("https://paytm.com/online-payments");
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<html> Could Not Load <html>");
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);
        
        setBounds(400,125,800,600);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new PayBill(smeter);
    }
    public static void main(String[]args)
    {
        new Paytm("");
    }
}
