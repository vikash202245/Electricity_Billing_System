package electricity.billing.system;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateCustomerInformation extends JFrame implements ActionListener{
    String smeter;
    JTextField address,city,email,phone;
    Choice state;
    JButton update,cancel;
    UpdateCustomerInformation(String smeter)
    {
        super("Update Customer Information");
        this.smeter = smeter;
        setSize(800,500);
        setLocation(350,150);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading  = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        heading.setBounds(70, 40, 350, 30);
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        lblname.setBounds(20, 100, 150, 20);
        add(lblname);
        
        JLabel name = new JLabel();
        name.setFont(new Font("Tahoma",Font.BOLD,14));
        name.setBounds(200, 100, 200, 20);
        add(name);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        lblmeter.setBounds(20, 140, 150, 20);
        add(lblmeter);
        
        JLabel meter = new JLabel(smeter);
        meter.setFont(new Font("Tahoma",Font.BOLD,14));
        meter.setBounds(200, 140, 200, 20);
        add(meter);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setFont(new Font("Tahoma",Font.BOLD,14));
        lbladdress.setBounds(20, 180, 150, 20);
        add(lbladdress);
        
        address = new JTextField();
        address.setFont(new Font("Tahoma",Font.BOLD,14));
        address.setBounds(200, 180, 200, 20);
        add(address);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setFont(new Font("Tahoma",Font.BOLD,14));
        lblcity.setBounds(20, 220, 150, 20);
        add(lblcity);
        
        city = new JTextField();
        city.setFont(new Font("Tahoma",Font.BOLD,14));
        city.setBounds(200, 220, 200, 20);
        add(city);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setFont(new Font("Tahoma",Font.BOLD,14));
        lblstate.setBounds(20, 260, 150, 20);
        add(lblstate);
        
        state = new Choice();
        String[] states = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
                           "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka",
                           "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram",
                           "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
                           "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};

        for (String stat : states) {
            state.add(stat);
        }
        state.setBounds(200, 260, 200, 20);
        add(state);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setFont(new Font("Tahoma",Font.BOLD,14));
        lblemail.setBounds(20, 300, 150, 20);
        add(lblemail);
        
        email = new JTextField();
        email.setFont(new Font("Tahoma",Font.BOLD,14));
        email.setBounds(200, 300, 200, 20);
        add(email);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setFont(new Font("Tahoma",Font.BOLD,14));
        lblphone.setBounds(20, 340, 150, 20);
        add(lblphone);
        
        phone = new JTextField();
        phone.setFont(new Font("Tahoma",Font.BOLD,14));
        phone.setBounds(200, 340, 200, 20);
        add(phone);
        
        try{
            Conn c = new Conn();
            ResultSet rs  =c.s.executeQuery("select * from customer where meter_no='"+smeter+"'");
            while(rs.next())
            {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        update = new JButton("Update");
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setBounds(80, 400, 100, 20);
        update.addActionListener(this);
        add(update);
        
        cancel = new JButton("Back");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(240, 400, 100, 20);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(450, 80, 300, 300);
        add(image);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==update)
        {
            try{
                Conn c = new Conn();
                String query = "update customer set address='"+address.getText()+"',city='"+city.getText()+"',state='"+state.getSelectedItem()+"',email = '"+email.getText()+"',phone='"+phone.getText()+"' where meter_no='"+smeter+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Information Updated Successfully");
                setVisible(false);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==cancel)
             setVisible(false);
    }
    public static void main(String []args)
    {
        new UpdateCustomerInformation("");
    }
}
