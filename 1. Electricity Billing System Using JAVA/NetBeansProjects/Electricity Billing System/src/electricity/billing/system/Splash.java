package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame implements Runnable{
    Splash()
    {
        Thread t;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/splash.png")); 
        Image i2 = i1.getImage().getScaledInstance(850, 561, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        setVisible(true);
        
        int x=1;
        for(int i=2;i<600;i+=4,x+=1)
        {
            setSize(i+x,i);
            setLocation(770-((i+x)/2),420-(i/2));
            try{
            Thread.sleep(9);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        t = new Thread(this);
        t.start();
        
        setVisible(true);
    }
    
    public void run()
    {
        try
        {
            Thread.sleep(7000);
            setVisible(false);
            // next Frame;
            new Login();
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }
    public static void main(String[]args)
    {
        new Splash();
    }
}
