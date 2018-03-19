
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
import java.awt.Color;

public class PatientAdministrator {

    private Connection con;
    private Statement st;
    private ResultSet rs;
  
    JButton b6, b7;
    JFrame frame2;
    JTextField textField1, textField2, textField3, textField4, textField5;
    int j;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

    public PatientAdministrator(int i) {
        try {
            j = i;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Error   :" + ex);
        }
        initialize();
    }

    private void initialize() {
        frame2 = new JFrame();
        frame2.setBounds(0, 0, 1500, 1024);
        //  frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().setLayout(null);
        frame2.setVisible(true);

        lblNewLabel_1 = new JLabel("Please  Fill up the Form for Patient Details :");
        lblNewLabel_1.setForeground(new Color(135, 206, 235));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1.setBounds(643, 61, 365, 34);
        frame2.getContentPane().add(lblNewLabel_1);

        JLabel l1 = new JLabel("Name");
        l1.setForeground(new Color(135, 206, 250));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l1.setBounds(606, 159, 99, 34);
        frame2.getContentPane().add(l1);

        JLabel label2 = new JLabel("Gender");
        label2.setForeground(new Color(135, 206, 235));
        label2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label2.setBounds(606, 230, 90, 34);
        frame2.getContentPane().add(label2);

        JLabel label5 = new JLabel("Age");
        label5.setForeground(new Color(135, 206, 235));
        label5.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label5.setBounds(606, 290, 90, 34);
        frame2.getContentPane().add(label5);

        JLabel label3 = new JLabel("Address");
        label3.setForeground(new Color(135, 206, 250));
        label3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label3.setBounds(606, 350, 107, 34);
        frame2.getContentPane().add(label3);

        JLabel label4 = new JLabel("Contact number");
        label4.setForeground(new Color(135, 206, 250));
        label4.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label4.setBounds(563, 406, 150, 34);
        frame2.getContentPane().add(label4);

        textField1 = new JTextField();
        textField1.setBounds(793, 156, 265, 45);
        frame2.getContentPane().add(textField1);
        textField1.setColumns(10);

        textField2 = new JTextField();
        textField2.setBounds(793, 230, 86, 34);
        frame2.getContentPane().add(textField2);
        textField2.setColumns(10);

        textField5 = new JTextField();
        textField5.setBounds(793, 290, 86, 34);
        frame2.getContentPane().add(textField5);
        textField5.setColumns(10);

        textField3 = new JTextField();
        textField3.setBounds(793, 350, 289, 34);
        frame2.getContentPane().add(textField3);
        textField3.setColumns(10);

        textField4 = new JTextField();
        textField4.setBounds(793, 408, 289, 34);
        frame2.getContentPane().add(textField4);
        textField4.setColumns(10);

        b6 = new JButton("Save");
        b6.setFont(new Font("Tahoma", Font.BOLD, 14));
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String st = "insert into patient (name,sex,age,address,contact)values(?,?,?,?,?)";
                    PreparedStatement pat = con.prepareStatement(st);
                    pat.setString(1, textField1.getText());
                    pat.setString(2, textField2.getText());
                    pat.setString(3, textField5.getText());
                    pat.setString(4, textField3.getText());
                    pat.setString(5, textField4.getText());
                    pat.execute();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                if (textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("") || textField5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter all the information");
                } else {
                    JOptionPane.showMessageDialog(null, " Patient Details is Saved");
                    JOptionPane.showMessageDialog(null, "your booking id is: " + j);
                    SystemDB connect = new SystemDB();
                }
                //  connect.getdata();
            }
        });
        b6.setBounds(694, 549, 99, 45);
        frame2.getContentPane().add(b6);

        b7 = new JButton("Close");
        b7.setFont(new Font("Tahoma", Font.BOLD, 14));
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame2.dispose();
            }
        });
        b7.setBounds(903, 549, 114, 45);
        frame2.getContentPane().add(b7);

        lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(0, 0, 1500, 1024);
        lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/pt.png")));
        frame2.getContentPane().add(lblNewLabel);

    }
}
