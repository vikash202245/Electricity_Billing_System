
package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
public class Extra extends JFrame{
    Extra()
    {
        
        setSize(400,400);
        setLocation(700,200);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/SignUp.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        JButton cancel = new JButton("Cancel",new ImageIcon(i2));
        setVisible(true);
    }
    public static void main(String[] args)
    {
        new Extra();
    }
}
