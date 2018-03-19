
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;

public class SystemDB {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    JTextArea textArea;
    JFrame frame;
    JButton b1, b2, b3, b4, b5;
    private JLabel lblNewLabel;

    public SystemDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Error   :" + ex);
        }
        init();
    }

    public void init() {

        frame = new JFrame();
        frame.setBounds(0, 0, 1500, 1024);
        //    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        textArea = new JTextArea();
        textArea.setBounds(76, 101, 468, 272);
        frame.getContentPane().add(textArea);
        b1 = new JButton("Available Doctors");
        b1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getdata();
            }
        });
        b1.setBounds(645, 125, 252, 53);
        frame.getContentPane().add(b1);

        b2 = new JButton("Confirm appointment");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String s1 = JOptionPane.showInputDialog(null, "Enter the booking id");
                    String s2 = JOptionPane.showInputDialog(null, "Enter the doctor id");
                    String s3 = JOptionPane.showInputDialog(null, "Enter date for appointment(YYYY-MM-DD)");
                    String st = "insert into bookapp (bid,did,appdate) values (?,?,?)";
                    PreparedStatement pat = con.prepareStatement(st);
                    pat.setString(1, s1);
                    pat.setString(2, s2);
                    pat.setString(3, s3);
                    pat.execute();
                    JOptionPane.showMessageDialog(null, "Your Appointment is Successfully Booked");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                JOptionPane.showMessageDialog(null, "Service charges for booking appaointment is Rs. 800/-");
            }
        });
        b2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        b2.setBounds(90, 439, 200, 46);
        frame.getContentPane().add(b2);

        b3 = new JButton("Cancel");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = JOptionPane.showInputDialog(null, "Enter the booking id");
                    String q = "delete from bookapp where bid='" + s + "'";
                    st.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Service charges for cancellation is Rs. 300/- \n Refundable amount Rs. 500/-");
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });
        b3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        b3.setBounds(362, 441, 146, 43);
        frame.getContentPane().add(b3);

        b4 = new JButton("Reschedule");
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
        b4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        b4.setBounds(634, 439, 146, 46);
        frame.getContentPane().add(b4);

        b5 = new JButton("Exit");
        b5.setFont(new Font("Times New Roman", Font.BOLD, 16));
        b5.setIcon(new ImageIcon(this.getClass().getResource("/ex3.png")));
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        b5.setBounds(645, 260, 146, 53);
        frame.getContentPane().add(b5);

        lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 1500, 1024);
        lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/book.png")));
        frame.getContentPane().add(lblNewLabel);

    }

    public void getdata() {
        try {
            String q = "select * from docdb";
            rs = st.executeQuery(q);
            System.out.println("Records from database");
            while (rs.next()) {
                String name = rs.getString("dname");

                textArea.append("Did: " + rs.getString("did") + "   Name: "
                        + name + "   Specialist :" + rs.getString("specialist")
                        + "   unavailable from:" + rs.getString("unavailable_from") + "\n" 
                        + "unavailable to:" + rs.getString("unavailable_to") + "\n\n");
            }
        } catch (Exception e) {

        }
    }

}
