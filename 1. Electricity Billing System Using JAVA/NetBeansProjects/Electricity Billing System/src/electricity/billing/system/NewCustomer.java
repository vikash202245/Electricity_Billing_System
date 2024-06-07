package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

class NewCustomer extends JFrame implements ActionListener{
    JTextField name,address,city,email,phone;
    JButton next,cancel;
    JLabel meter;
    Choice state;
    NewCustomer()
    {
        setSize(700,500);
        setLocation(450,150);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(170,255,255));
        add(p);
        
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100,80,150,20);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblname);
        
        name = new JTextField();
        name.setBounds(280, 80, 200, 20);
        p.add(name);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100,125,150,20);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblmeter);
        
        
        meter = new JLabel();
        meter.setBounds(280, 125, 200, 20);
        meter.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(meter);
        
        Random ran = new Random();
        long number = ran.nextLong()%1000000;
        meter.setText(Long.toString(Math.abs(number)));
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,170,150,20);
        lbladdress.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbladdress);
        
        address = new JTextField();
        address.setBounds(280, 170, 200, 20);
        p.add(address);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100,215,150,20);
        lblcity.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblcity);
        
        city = new JTextField();
        city.setBounds(280, 215, 200, 20);
        p.add(city);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100,260,150,20);
        lblstate.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblstate);
        
        state = new Choice();
        String[] states = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
                           "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka",
                           "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram",
                           "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
                           "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};

        for (String stat : states) {
            state.add(stat);
        }
        state.setBounds(280, 260, 200, 20);
        p.add(state);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100,305,150,20);
        lblemail.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblemail);
        
        email = new JTextField();
        email.setBounds(280, 305, 200, 20);
        p.add(email);
        
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(100,350,150,20);
        lblphone.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblphone);
        
        phone = new JTextField();
        phone.setBounds(280, 350, 200, 20);
        p.add(phone);
        
        next = new JButton("Next");
        next.setBounds(130,410,150,30);
        next.setBackground(Color.black);
        next.setFont(new Font("Tahoma",Font.BOLD,14));
        next.setForeground(Color.white);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(310,410,150,30);
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
        if(ae.getSource()==next)
        {
            String sname = name.getText();
            String smeter = meter.getText();
            String saddress = address.getText();
            String scity = city.getText();
            String sstate = state.getSelectedItem();
            String semail = email.getText();
            String sphone = phone.getText();
            String query1 = "insert into customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+semail+"','"+sphone+"')";
            String query2 = "insert into login values('"+smeter+"','','"+sname+"','','')";
            try{
            Conn c = new Conn();            
            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
            // new Frame
            new MeterInfo(smeter);
            }
            catch(Exception e)
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
        new NewCustomer();
    }
}
