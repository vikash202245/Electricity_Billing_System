package electricity.billing.system;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
public class BillDetails extends JFrame{
    String smeter;
    BillDetails(String smeter)
    {
        super("BillDetails");
        this.smeter = smeter;
        setBounds(400,100,700,650);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JTable table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill where meter_no='"+smeter+"'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 650);
        add(sp);
        setVisible(true);
    }

    public static void main(String []args)
    {
        new BillDetails("");
    }
}
