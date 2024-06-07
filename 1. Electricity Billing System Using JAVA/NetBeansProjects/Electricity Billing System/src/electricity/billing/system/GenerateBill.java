package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class GenerateBill extends JFrame implements ActionListener{
    Choice month;
    String smeter;
    JButton bill,print;
    JTextArea area;
    GenerateBill(String smeter)
    {
        this.smeter = smeter;
        setBounds(550,20,500,790);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        
        JLabel heading = new JLabel("Generate Bill");
        
        JLabel meter = new JLabel(smeter);
        
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
        add(month);
        
        area = new JTextArea(50,15);
        //50rows, 15 columns
        area.setText("\n\n\t--------Click on the -----------\n\t Generate Bill Button to get \n\t the bill of the selected Month");
        area.setFont(new Font("Senserif",Font.ITALIC,18));
        JScrollPane pane = new JScrollPane(area);
        
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        
        print = new JButton("Print");
        print.addActionListener(this);
        
        panel.add(heading);
        panel.add(meter);
        panel.add(month);
        panel.add(print,"South");
        add(panel,"North");
        add(pane,"Center");
        add(bill,"South");
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==print)
        {
            try{
            area.print();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try{
                Conn c = new Conn();

                String smonth = month.getSelectedItem();

                area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH \n\tOF "+ smonth+",2023\n\n\n");

                ResultSet rs = c.s.executeQuery("select * from customer where meter_no='"+smeter+"'");
                if(rs.next())
                {
                    area.append("\n    Customer Name: "+rs.getString("name"));
                    area.append("\n    Meter Number :"+smeter);
                    area.append("\n    Address      : "+rs.getString("address"));
                    area.append("\n    City         : "+rs.getString("city"));
                    area.append("\n    State        : "+rs.getString("state"));
                    area.append("\n    Email        : "+rs.getString("email"));
                    area.append("\n    Phone        : "+rs.getString("phone"));
                    area.append("\n---------------------------------------------");
                    area.append("\n");

                }

                rs = c.s.executeQuery("select * from meter_info where meter_no='"+smeter+"'");

                if(rs.next())
                {
                    area.append("\n    Meter Location : "+rs.getString("meter_location"));
                    area.append("\n    Meter Type     : "+rs.getString("meter_type"));
                    area.append("\n    Phase Code     : "+rs.getString("phase_code"));
                    area.append("\n    Bill Type      : "+rs.getString("bill_type"));
                    area.append("\n    Days           : "+rs.getString("days"));
                    area.append("\n---------------------------------------------");
                    area.append("\n");
                }

                rs = c.s.executeQuery("select * from tax ");

                if(rs.next())
                {
                    area.append("\n    Cost per Unit         : "+rs.getString("cost_per_unit"));
                    area.append("\n    Meter Rent            : "+rs.getString("meter_rent"));
                    area.append("\n    Service Charge        : "+rs.getString("service_charge"));
                    area.append("\n    Service Tax           : "+rs.getString("service_tax"));
                    area.append("\n    Swachchha Bharat Cess : "+rs.getString("swachchh_bharat_cess"));
                    area.append("\n    Fixed Tax             : "+rs.getString("fixed_tax"));
                    area.append("\n---------------------------------------------");
                    area.append("\n");
                }

                rs = c.s.executeQuery("select * from bill where meter_no='"+smeter+"' and month='"+smonth+"'");

                if(rs.next())
                {
                    area.append("\n    Current Month         : "+smonth);
                    area.append("\n    Units Consumed            : "+rs.getString("units"));
                    area.append("\n    Total Charges        : "+rs.getString("totalamount"));
                    area.append("\n---------------------------------------------");
                    area.append("\n    Total Payable             : "+rs.getString("totalamount"));
                    area.append("\n---------------------------------------------");
                    area.append("\n");
                }


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String []args)
    {
        new GenerateBill("");
    }
}
