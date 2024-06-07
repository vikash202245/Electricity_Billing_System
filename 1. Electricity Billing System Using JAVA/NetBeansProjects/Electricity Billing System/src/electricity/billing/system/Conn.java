
package electricity.billing.system;

import java.sql.*;
        
public class Conn {
    Connection c;
    Statement s;
    Conn()
    {
        try
        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ebs1","root","Yadav@123");
            s = c.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
