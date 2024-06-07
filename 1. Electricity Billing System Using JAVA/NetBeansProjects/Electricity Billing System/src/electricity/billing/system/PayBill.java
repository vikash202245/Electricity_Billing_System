package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class PayBill extends JFrame implements ActionListener {
    String smeter;
    Choice month;
    JButton pay,back;
    PayBill(String smeter)
    {
        this.smeter = smeter;
        setLayout(null);
        setBounds(300,150,900,600);
        
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,16));
        lblmeter.setBounds(35,80,200,20);
        add(lblmeter);
        
        JLabel meter = new JLabel(smeter);
        meter.setFont(new Font("Tahoma",Font.BOLD,16));
        meter.setBounds(300,80,200,20);
        add(meter);
        
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma",Font.BOLD,16));
        lblname.setBounds(35,140,200,20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setFont(new Font("Tahoma",Font.BOLD,16));
        name.setBounds(300,140,200,20);
        add(name);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setFont(new Font("Tahoma",Font.BOLD,16));
        lblmonth.setBounds(35,200,200,20);
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
        month.setBounds(300,200,200,20);
        add(month);
        
        JLabel lblunits = new JLabel("Units");
        lblunits.setFont(new Font("Tahoma",Font.BOLD,16));
        lblunits.setBounds(35,260,200,20);
        add(lblunits);
        
        JLabel unit = new JLabel("");
        unit.setFont(new Font("Tahoma",Font.BOLD,16));
        unit.setBounds(300,260,200,20);
        add(unit);
        
        JLabel lbltotal = new JLabel("Total Bill");
        lbltotal.setFont(new Font("Tahoma",Font.BOLD,16));
        lbltotal.setBounds(35,320,200,20);
        add(lbltotal);
        
        JLabel total = new JLabel("");
        total.setFont(new Font("Tahoma",Font.BOLD,16));
        total.setBounds(300,320,200,20);
        add(total);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setFont(new Font("Tahoma",Font.BOLD,16));
        lblstatus.setBounds(35,380,200,20);
        add(lblstatus);
        
        JLabel status = new JLabel("");
        status.setFont(new Font("Tahoma",Font.BOLD,16));
        status.setBounds(300,380,200,20);
        status.setForeground(Color.red);
        add(status);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='"+smeter+"'");
            while(rs.next())
            {
                name.setText(rs.getString("name"));
            }
            rs = c.s.executeQuery("select * from bill where meter_no='"+smeter+"' and month='"+month.getSelectedItem()+"'");
            
            while(rs.next())
            {
                unit.setText(rs.getString("units"));
                total.setText(rs.getString("totalamount"));
                status.setText(rs.getString("status"));                
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        month.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae)
            {
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no='"+smeter+"' and month='"+month.getSelectedItem()+"'");
                    while(rs.next())
                    {
                        unit.setText(rs.getString("units"));
                        total.setText(rs.getString("totalamount"));
                        status.setText(rs.getString("status"));                
                    }

                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        
        pay = new JButton("PAY");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(100,460,100,20);
        pay.addActionListener(this);
        add(pay);
        
        back = new JButton("BACK");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(300,460,100,20);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.white);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(400,120,600,300);
        add(image);
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == pay)
        {
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status='Paid' where meter_no='"+smeter+"' and month='"+month.getSelectedItem()+"'");
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(smeter);
        }
        else if(ae.getSource() == back)
        {
            setVisible(false);
        }
    }
    public static void main(String[] args)
    {
        new PayBill("");
    }
}
