package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class CalculateBill extends JFrame implements ActionListener{
    JTextField unit;
    JButton submit,cancel;
    Choice meter,month;
    JLabel name,address;
    CalculateBill()
    {
        setSize(700,500);
        setLocation(450,150);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(170,255,255));
        add(p);
        
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(160, 10, 300, 25);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        p.add(heading);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100,80,150,20);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblmeter);
        
        meter = new Choice();
        try
        {
            Conn c = new Conn();
            ResultSet rs= c.s.executeQuery("select * from meter_info");
            while(rs.next())
            {
                meter.add(rs.getString("meter_no"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        meter.setBounds(280, 80, 200, 20);
        p.add(meter);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,125,150,20);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblname);
           
        name = new JLabel("");
        name.setBounds(280, 125, 200, 20);
        p.add(name);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,170,150,20);
        lbladdress.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbladdress);
        
        address = new JLabel();
        address.setBounds(280, 170, 200, 20);
        p.add(address);
        
        meter.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie)
            {
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no='"+meter.getSelectedItem()+"'");
                    while(rs.next())
                    {
                        name.setText(rs.getString("name"));
                        address.setText(rs.getString("address"));
                    }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        JLabel lblunit = new JLabel("Units Consumed");
        lblunit.setBounds(100,215,150,20);
        lblunit.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblunit);
        
        unit = new JTextField();
        unit.setBounds(280,215,200,20);
        p.add(unit);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(100,260,150,20);
        lblmonth.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblmonth);
        
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
        
        month.setBounds(280, 260, 200, 20);
        p.add(month);
        
        submit = new JButton("Submit");
        submit.setBounds(130,340,150,30);
        submit.setBackground(Color.black);
        submit.setFont(new Font("Tahoma",Font.BOLD,14));
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        p.add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(310,340,150,30);
        cancel.setBackground(Color.black);
        cancel.setFont(new Font("Tahoma",Font.BOLD,14));
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        p.add(cancel);
        setLayout(new BorderLayout());
        add(p,"Center");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        add(image,"West");
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit)
        {
            String smeter = meter.getSelectedItem();
            String smonth = month.getSelectedItem();
            String sunit = unit.getText();
            int unit_consumed = Integer.parseInt(sunit);
            int totalBill=0;
                    
            String query ="select*from tax";
            try{
            Conn c = new Conn();            
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next())
            {
                totalBill+=unit_consumed*Integer.parseInt(rs.getString("cost_per_unit"));
                totalBill+=Integer.parseInt(rs.getString("meter_rent"));
                totalBill+=Integer.parseInt(rs.getString("service_charge"));
                totalBill+=Integer.parseInt(rs.getString("service_tax"));
                totalBill+=Integer.parseInt(rs.getString("swachchh_bharat_cess"));
                totalBill+=Integer.parseInt(rs.getString("fixed_tax"));
            }   
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            String query1 = "insert into bill values('"+smeter+"','"+smonth+"','"+sunit+"','"+totalBill+"','Not Paid')";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated SuccessFully");
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==cancel)
        {
            setVisible(false);
        }
    }
    public static void main(String[] args)
    {
        new CalculateBill();
    }
}
