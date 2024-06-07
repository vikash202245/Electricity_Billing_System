package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener {
    JButton login,cancel,signup;
    JTextField username,password;
    Choice loggingin;
    Login()
    {
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        // userName
        
        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setBounds(300,20,100,20);
        add(lblUserName);
        
        username = new JTextField();
        username.setBounds(400,20,180,20);
        add(username);
        // password
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300,60,100,20);
        add(lblpassword);
        
        password = new JTextField();
        password.setBounds(400,60,180,20);
        add(password);
        // Logging in as
        
        JLabel logginginas = new JLabel("Logging in as");
        logginginas.setBounds(300,100,100,20);
        add(logginginas);
        // for dropbox we can use jComBox or Choice
         loggingin = new Choice();
         loggingin.add("Admin");
         loggingin.add("Customer");
         loggingin.setBounds(400,100,180,20);
         add(loggingin);
         //Button
         
         ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
         Image i4 = i3.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
         
         login = new JButton("Login", new ImageIcon(i4));
//         login = new JButton("Login");
//         login.setIcon(new ImageIcon(i4));         
         login.setBounds(330,160,100,30);
         login.addActionListener(this);
         add(login);
         
         ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/cross.png"));
         Image i6 = i5.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
         
         cancel = new JButton("Cancel", new ImageIcon(i6));
//         cancel = new JButton("Cancel");
//         cancel.setIcon(new ImageIcon(i6));
         cancel.setBounds(460,160,120,30);
         cancel.addActionListener(this);
         add(cancel);

         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/SignUp1.png"));
         Image i2 = i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);         
         signup = new JButton("Sign Up",new ImageIcon(i2));
//         signup = new JButton("Sign Up");
//         signup.setIcon(new ImageIcon(i4));
         signup.setBounds(390,210,140,30);
         signup.addActionListener(this);
         add(signup);
         
         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/loginPage.png"));
         Image i8 = i7.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);         
         ImageIcon i9 = new ImageIcon(i8);
         JLabel image = new JLabel(i9);
         image.setBounds(25,0,250,250);
         add(image);
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == login)
        {
            String susername = username.getText();
            String spassword = password.getText();
            String user = loggingin.getSelectedItem();
            try{
                Conn c = new Conn();
                String query = "select* from login where username = '"+susername+"' and password = '"+spassword+"' and user = '"+user+"'";
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next())
                {
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user,meter);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == signup)
        {
            setVisible(false);
            new SignUp();
        }
        else if(ae.getSource() == cancel)
        {
            setVisible(false);
        }
    }
    
    
    public static void main(String[]args)
    {
        new Login();
    }
}
