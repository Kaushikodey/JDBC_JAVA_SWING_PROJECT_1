
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private JFrame frmRegisterpage;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    /*  public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register window = new Register();
                    window.frmRegisterpage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    /**
     * Create the application.
     */
    public Register() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Error   :" + ex);
        }
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmRegisterpage = new JFrame();
        frmRegisterpage.setTitle("RegisterPage");
        frmRegisterpage.setBounds(0, 0, 1500, 1024);
        frmRegisterpage.dispose();
        frmRegisterpage.getContentPane().setLayout(null);
        frmRegisterpage.setVisible(true);
        JLabel lblNewLabel_2 = new JLabel("           User Registration  Form\r\n");
        lblNewLabel_2.setFont(new Font("Palatino Linotype", Font.BOLD, 22));
        lblNewLabel_2.setBounds(428, 155, 363, 33);
        frmRegisterpage.getContentPane().add(lblNewLabel_2);

        JButton btnNewButton_1 = new JButton("Close");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                JOptionPane.showMessageDialog(null, "  Thank you for Visiting Us !!!");
                frmRegisterpage.dispose();

            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_1.setBounds(648, 588, 132, 37);
        frmRegisterpage.getContentPane().add(btnNewButton_1);

        JButton btnNewButton = new JButton("Register Now");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try {
                    // st.executeUpdate("insert into data(uid,password) values("+myid+","+mypassword+")");
                    // String str = "insert into docdb (did,dname,special,unavailable)values('d02','k dey','cardio','2017-07-22')";
                    String s1 = "insert into register (name,username,password)values(?,?,?)";
                    PreparedStatement pat = con.prepareStatement(s1);
                    pat.setString(1, textField.getText());
                    pat.setString(2, textField_1.getText());
                    pat.setString(3, textField_2.getText());
                    pat.execute();
                } catch (Exception ex) {

                    System.out.println(ex);
                }
                if (textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter all the information");
                } else {

                    JOptionPane.showMessageDialog(null, "Your Registration Successful !");

                    //BookSystem connect = new BookSystem();
                    Login connect = new Login();
                }

            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBounds(409, 588, 143, 37);
        frmRegisterpage.getContentPane().add(btnNewButton);

        textField_2 = new JTextField();
        textField_2.setBounds(573, 471, 232, 33);
        frmRegisterpage.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(573, 394, 232, 33);
        frmRegisterpage.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel Name = new JLabel("Enter Name :");
        Name.setFont(new Font("Tahoma", Font.BOLD, 14));
        Name.setForeground(new Color(0, 0, 0));
        Name.setBounds(387, 325, 165, 37);
        frmRegisterpage.getContentPane().add(Name);

        textField = new JTextField();
        textField.setBounds(573, 329, 232, 33);
        frmRegisterpage.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Enter Your Email-ID as Username :");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(247, 390, 261, 37);
        frmRegisterpage.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter Password :");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(357, 473, 151, 25);
        frmRegisterpage.getContentPane().add(lblNewLabel_1);

        JLabel reglbl = new JLabel("");
        reglbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        reglbl.setBounds(10, -22, 1500, 1024);
        reglbl.setIcon(new ImageIcon(this.getClass().getResource("/bg2.png")));
        frmRegisterpage.getContentPane().add(reglbl);

    }
}
