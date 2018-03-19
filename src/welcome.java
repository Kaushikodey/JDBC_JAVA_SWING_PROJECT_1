
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.sql.ResultSetMetaData;

public class welcome {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    static int i = 100;
    JButton b1, b2, b3, b4, b5;
    JFrame frame, frame2;
    JTextField textField1, textField2, textField3, textField4;

    welcome() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Error   :" + ex);
        }
        b1 = new JButton("Press for Doctor availability");
        b2 = new JButton("Booking appointment");
        b3 = new JButton("For Cancel");
        b4 = new JButton("Reschedule");
        b5 = new JButton("Exit");
        init();
    }

    public void init() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1500, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        b3 = new JButton("Cancel Appointment");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = JOptionPane.showInputDialog(null, "Enter the booking id");
                    if (s.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter the booking id");
                    } else {
                        String q = "delete from bookapp where bid='" + s + "'";
                        st.executeUpdate(q);
                        JOptionPane.showMessageDialog(null, "Service charges for cancellation is Rs. 300/-");
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        });

        b4 = new JButton("Reschedule Appointment");
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s1 = JOptionPane.showInputDialog(null, "Enter the booking id");
                    String s2 = JOptionPane.showInputDialog(null, "Enter the date for rescheduling");
                    if (s1.equals("") || s2.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter all the information");
                    } else {
                        String s3 = "update bookapp set appdate='" + s2 + "'where bid='" + s1 + "'";
                        st.executeUpdate(s3);
                        JOptionPane.showMessageDialog(null, "Service charges for rescheduling is Rs. 1000/-");
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        b1 = new JButton("Check for Doctor availability");
        b1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        b1.setBounds(211, 266, 229, 41);
        frame.getContentPane().add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String q = "select * from docdb";
                    rs = st.executeQuery(q);
                    System.out.println("Records from database");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Doctor available");
                    } else {
                        JOptionPane.showMessageDialog(frame, "No Doctor is available ");

                    }
                } catch (Exception ex) {

                }
            }
        });

        JLabel lblNewLabel = new JLabel("Select Your Choice  To Perform the Following Action  :");
        lblNewLabel.setForeground(new Color(0, 0, 139));
        lblNewLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(176, 113, 564, 60);
        frame.getContentPane().add(lblNewLabel);
        b4.setFont(new Font("Times New Roman", Font.BOLD, 14));
        b4.setBounds(222, 376, 218, 41);
        frame.getContentPane().add(b4);

        b5 = new JButton("Exit");
        b5.setFont(new Font("Times New Roman", Font.BOLD, 14));
        b5.setBounds(417, 500, 108, 41);
        b5.setIcon(new ImageIcon(this.getClass().getResource("/ex3.png")));
        frame.getContentPane().add(b5);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        b3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        b3.setBounds(504, 376, 223, 41);
        frame.getContentPane().add(b3);

        b2 = new JButton("Booking appointment");
        b2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        b2.setBounds(504, 266, 223, 41);
        frame.getContentPane().add(b2);

        b2.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
                try {
                    String q="select * from bookapp";
                    rs=st.executeQuery(q);
                    ResultSetMetaData metaData=rs.getMetaData();
                    int rowcount=metaData.getColumnCount();
                   // System.out.println(rowcount);
                    i=i+rowcount;
                    new PatientAdministrator(++i);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                
            }
        });

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setFont(new Font("Palatino Linotype", Font.BOLD, 22));
        lblNewLabel_1.setBounds(-223, -29, 1616, 1228);
        lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/pp2.png")));
        frame.getContentPane().add(lblNewLabel_1);

        /*     ActionListener for Checking Button    */
 /*     ActionListener for EXIT Button    */
    }

}
