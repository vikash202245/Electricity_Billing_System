
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
public class SignUp extends JFrame implements ActionListener{
    JButton create,back;
    Choice accountType;
    JTextField meter,username,name,password;
    SignUp()
    {
        super("Sign Up");
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 630, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(255,51,153),2),"Create A New Account", TitledBorder.LEADING,TitledBorder.TOP,null,new Color(255,51,153)));
        panel.setBackground(new Color(255,255,204));
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);
        
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.gray);
        heading.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(heading);
        
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(Color.gray);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        meter = new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);
        
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,170,140,20);
        lblname.setForeground(Color.gray);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblname);
        
        name = new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);
        
        meter.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent fe){}
            @Override
            public void focusLost(FocusEvent fe){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no='"+meter.getText()+"'");
                    while(rs.next())
                    {
                        name.setText(rs.getString("name"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        JLabel lblusername = new JLabel("UserName");
        lblusername.setBounds(100,130,140,20);
        lblusername.setForeground(Color.gray);
        lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblusername);
        
        username = new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100,210,140,20);
        lblpassword.setForeground(Color.gray);
        lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblpassword);
        
        password = new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);
        
        accountType.addItemListener(new ItemListener()
        {
           public void  itemStateChanged(ItemEvent ae)
           {
               String user = accountType.getSelectedItem();
               if(user.equals("Customer"))
               {
                   lblmeter.setVisible(true);
                   meter.setVisible(true);
                   name.setEditable(false);
               }
               else
               {
                   lblmeter.setVisible(false);
                   meter.setVisible(false);
                   name.setEditable(true);
               }
           }
        });
                
        
        create = new JButton("Create");
        create.setBounds(130,260,120,25);
        create.addActionListener(this);
        panel.add(create);
        
        
        back = new JButton("Back");
        back.setBounds(270,260,120,25);
        back.addActionListener(this);
        panel.add(back);
//        setSize(700,300);
//        setLocation(400,200); 
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/SignUp3.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 230,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,30,250,250);
        panel.add(image);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == create)
        {
            String atype = accountType.getSelectedItem();
            String smeter = meter.getText();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            try{
                Conn c = new Conn();
                String query =null;
                if(atype.equals("Admin"))
                        query= "insert into login values('"+ smeter + "', '" + susername + "', '" + sname + "', '" + spassword + "', '" + atype + "')";
                else
                        query = "update login set username='"+susername+"',password='"+spassword+"',user='"+atype+"' where meter_no='"+smeter+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == back)
        {
            setVisible(false);
            new Login();
        }
    }
    public static void main(String []args)
    {
        new SignUp();
    }
}
