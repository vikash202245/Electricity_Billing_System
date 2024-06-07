package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class DepositeDetails extends JFrame implements ActionListener{
    Choice meter,month ;
    JTable table;
    JButton search,print;
    DepositeDetails() 
    {
        super("Deposite Details");
        setSize(700,700);
        setLocation(400,70);
        setLayout(null);
        getContentPane().setBackground(Color.white);
        
        JLabel lblmeter = new JLabel("Search by Meter Number");
        lblmeter.setBounds(20,20,150,20);
        add(lblmeter);
        
        meter = new Choice();
        meter.setBounds(180,20,150,20);
        add(meter);
        try
        {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next())
            {
                meter.add(rs.getString("meter_no"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        JLabel lblmonth = new JLabel("Search by Month");
        lblmonth.setBounds(400,20,100,20);
        add(lblmonth);
        
        month = new Choice();
        String[] months = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
        };
        for(String m:months)
        {
            month.add(m);
        }
        month.setBounds(520,20,100,20);
        add(month);
        
        table = new JTable();
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);
        
        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(140,70,80,20);
        print.addActionListener(this);
        add(print);
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==search)
        {
            String query = "select * from bill where meter_no='"+meter.getSelectedItem()+"' and month='"+month.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==print)
        {
            try
            {
                table.print();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args)
    {
        new DepositeDetails();
    }    
}

