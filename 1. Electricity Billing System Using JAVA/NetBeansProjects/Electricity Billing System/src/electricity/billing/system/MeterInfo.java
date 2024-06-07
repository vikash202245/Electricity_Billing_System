package electricity.billing.system;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


public class MeterInfo extends JFrame implements ActionListener{
    
    JTextField name,address,city,state,email,phone;
    JButton submit;
    JLabel meter;
    Choice meterLocation,meterType,phaseCode,billType;
    String meterNo;
    MeterInfo(String meterNo)
    {
        this.meterNo = meterNo;
        setSize(720,500);
        setLocation(450,150);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(170,255,255));
        add(p);
        
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 240, 25);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        p.add(heading);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(60,80,150,20);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblmeter);
        
        meter = new JLabel(meterNo);
        meter.setBounds(220, 80, 200, 20);
        meter.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(meter);
        
        JLabel lblmeterlocation = new JLabel("Meter Location");
        lblmeterlocation.setBounds(60,125,150,20);
        lblmeterlocation.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblmeterlocation);
        
        meterLocation = new Choice();
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        meterLocation.setBounds(220, 125, 200, 20);
        p.add(meterLocation);
        
        JLabel lblmetertype = new JLabel("Meter Location");
        lblmetertype.setBounds(60,170,150,20);
        lblmetertype.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblmetertype);
        
        meterType = new Choice();
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");
        meterType.setBounds(220, 170, 200, 20);
        p.add(meterType);
        
        JLabel lblphasecode = new JLabel("Phase Code");
        lblphasecode.setBounds(60,215,150,20);
        lblphasecode.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblphasecode);
        
        phaseCode = new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        phaseCode.setBounds(220, 215, 200, 20);
        p.add(phaseCode);
        
        JLabel lblbilltype = new JLabel("Bill Type");
        lblbilltype.setBounds(60,260,150,20);
        lblbilltype.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblbilltype);
        
        billType = new Choice();
        billType.add("Normal");
        billType.add("Industrial");
        billType.setBounds(220, 260, 200, 20);
        p.add(billType);
        
        JLabel lbldays = new JLabel("Days");
        lbldays.setBounds(60,305,150,20);
        lbldays.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbldays);
        
        JLabel lblday = new JLabel("30 Days");
        lblday.setBounds(220,305,260,20);
        lblday.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblday);
        
        JLabel lblnote = new JLabel("Note");
        lblnote.setBounds(60,350,150,20);
        lblnote.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblnote);
        
        JLabel lblnotes = new JLabel("By Default Bill is Calculated for 30 days only");
        lblnotes.setBounds(220,350,500,20);
        lblnotes.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblnotes);
        
        submit = new JButton("Submit");
        submit.setBounds(130,410,150,30);
        submit.setBackground(Color.black);
        submit.setFont(new Font("Tahoma",Font.BOLD,14));
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        p.add(submit);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        add(image,"West");
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit)
        {
            String smeter = meterNo;
            String slocation = meterLocation.getSelectedItem();
            String stype = meterType.getSelectedItem();
            String scode = phaseCode.getSelectedItem();
            String sbill = billType.getSelectedItem();
            String sdays = "30";
            String query = "insert into meter_info values('"+smeter+"','"+slocation+"','"+stype+"','"+scode+"','"+sbill+"','"+sdays+"')";
            try{
            Conn c = new Conn();            
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
            setVisible(false);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
    public static void main(String[] args)
    {
        new MeterInfo("");
    }
}
