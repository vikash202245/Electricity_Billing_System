package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class ViewInformation extends JFrame implements ActionListener{
    JButton cancel;
    String smeter;
    ViewInformation(String smeter)
    {
        super("Customer Information");
        this.smeter = smeter;
        setBounds(350,100,850,650);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,20,350,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,25));
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70,80,100,20);
        lblname.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(200,80,300,20);
        name.setFont(new Font("Tahoma",Font.BOLD,16));
        add(name);
        
        JLabel lblmeter = new JLabel("Meter No.");
        lblmeter.setBounds(70,140,100,20);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblmeter);
        
        JLabel meter = new JLabel(smeter);
        meter.setBounds(200,140,300,20);
        meter.setFont(new Font("Tahoma",Font.BOLD,16));
        add(meter);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70,200,100,20);
        lbladdress.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lbladdress);
        
        JLabel address = new JLabel("");
        address.setBounds(200,200,300,20);
        address.setFont(new Font("Tahoma",Font.BOLD,16));
        add(address);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70,260,100,20);
        lblcity.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblcity);
        
        JLabel city = new JLabel("");
        city.setBounds(200,260,300,20);
        city.setFont(new Font("Tahoma",Font.BOLD,16));
        add(city);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500,80,100,20);
        lblstate.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblstate);
        
        JLabel state = new JLabel("");
        state.setBounds(600,80,300,20);
        state.setFont(new Font("Tahoma",Font.BOLD,16));
        add(state);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500,140,100,20);
        lblemail.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblemail);
        
        JLabel email = new JLabel("");
        email.setBounds(600,140,300,20);
        email.setFont(new Font("Tahoma",Font.BOLD,16));
        add(email);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500,200,100,20);
        lblphone.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblphone);
        
        JLabel phone = new JLabel("");
        phone.setBounds(600,200,300,20);
        phone.setFont(new Font("Tahoma",Font.BOLD,16));
        add(phone);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+smeter+"'");
            while(rs.next())
            {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(350,340,100,25);
        add(cancel);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);
        cancel.addActionListener(this);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==cancel)
        {
            setVisible(false);
        }
    }
    public static void main(String [] args)
    {
        new ViewInformation("");
    }
}
