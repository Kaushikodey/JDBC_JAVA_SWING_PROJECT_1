
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Action;
import javax.swing.JPasswordField;

public class Login {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private JFrame frmLogin, frame;
    private JTextField usernameText;
    private JPasswordField psswrdText;
   // private final Action action = new SwingAction();
    String user, pass;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frmLogin.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {
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
        frmLogin = new JFrame();
        frmLogin.setBounds(0, 0, 1500, 1024);
        frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLogin.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("  Your health is our Responsibility.........");
        lblNewLabel.setForeground(new Color(153, 0, 0));
        lblNewLabel.setFont(new Font("Palatino Linotype", Font.ITALIC, 20));
        lblNewLabel.setBounds(764, 280, 338, 33);
        frmLogin.getContentPane().add(lblNewLabel);

        JLabel caption1 = new JLabel(" Welcome To");
        caption1.setForeground(new Color(0, 102, 0));
        caption1.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 23));
        caption1.setBounds(851, 67, 165, 33);
        frmLogin.getContentPane().add(caption1);

        JLabel caption2 = new JLabel("   Bhogallo Multi-Speciality  Hospital");
        caption2.setForeground(new Color(102, 0, 102));
        caption2.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 34));
        caption2.setBounds(669, 135, 574, 64);
        frmLogin.getContentPane().add(caption2);

        JButton exitBTN = new JButton("Exit");
        exitBTN.setFont(new Font("Tahoma", Font.BOLD, 12));
        exitBTN.setIcon(new ImageIcon(this.getClass().getResource("/ex3.png")));
        exitBTN.setBounds(1089, 608, 102, 40);
        frmLogin.getContentPane().add(exitBTN);

        JButton registerBTN = new JButton("Register");
        registerBTN.setFont(new Font("Tahoma", Font.BOLD, 12));
        registerBTN.setIcon(new ImageIcon(this.getClass().getResource("/reg1.png")));
        registerBTN.setBounds(1159, 538, 118, 40);
        frmLogin.getContentPane().add(registerBTN);

        JButton loginBTN = new JButton(" Login");
     //   loginBTN.setAction(action);
        loginBTN.setFont(new Font("Tahoma", Font.BOLD, 12));
        loginBTN.setIcon(new ImageIcon(this.getClass().getResource("/lg2.png")));
        loginBTN.setBounds(1007, 538, 115, 40);
        frmLogin.getContentPane().add(loginBTN);

        psswrdText = new JPasswordField();
        psswrdText.setFont(new Font("Tahoma", Font.PLAIN, 11));
        psswrdText.setToolTipText("");
        psswrdText.setBounds(1112, 441, 165, 20);
        frmLogin.getContentPane().add(psswrdText);
        psswrdText.setColumns(10);

        JLabel psswrd = new JLabel(" Password");
        psswrd.setForeground(new Color(0, 0, 102));
        psswrd.setFont(new Font("Tahoma", Font.BOLD, 16));
        psswrd.setBounds(997, 433, 102, 33);
        frmLogin.getContentPane().add(psswrd);

        usernameText = new JTextField();
        usernameText.setBounds(1112, 386, 165, 20);
        frmLogin.getContentPane().add(usernameText);
        usernameText.setColumns(10);

        JLabel usernameLabel = new JLabel("      Username");
        usernameLabel.setForeground(new Color(0, 0, 102));
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        usernameLabel.setBounds(972, 378, 131, 33);
        frmLogin.getContentPane().add(usernameLabel);

        JLabel bklbl = new JLabel("");
        bklbl.setBounds(10, 0, 1500, 1024);
        frmLogin.getContentPane().add(bklbl);
        bklbl.setIcon(new ImageIcon(this.getClass().getResource("/bg3.png")));

        loginBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                boolean flag = false;

                if (usernameText.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
                } else if (psswrdText.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
                } else {
                    user = usernameText.getText();              // Collecting the input
                    pass = psswrdText.getText();               // Collecting the input

                    try {

                     //   Class.forName("com.mysql.jdbc.Driver");
                     //   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                        // PreparedStatement pst = con.prepareStatement("Select * from login where username=? and password=?");
                        String q = "select * from register";
                        rs = st.executeQuery(q);
                        while (rs.next()) {
                             String uname = rs.getString("username");
                            if(uname.equals(user))
                            {
                                String pword=rs.getString("password");
                                if(pass.equals(pword))
                                {
                                   flag = true;
                                   break; 
                                }
                            }
                        } 
                    } catch (Exception e) {
                        System.out.println(e);

                    }
                    //  String pwd = String.copyValueOf(pass);           // converting from array to string
                    if (flag) {
                        JOptionPane.showMessageDialog(null, " Login Successful !!!");
                        welcome w = new welcome();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username/Password ! please try again..");
                    }

                }

            }
        });

        exitBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "  Thank you for Visiting Us !!!");
                System.exit(1);

            }
        });

        registerBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                	Register reg = new Register();
            }
        });

    }
/*
    private class SwingAction extends AbstractAction {

        public SwingAction() {
            putValue(NAME, "Login");
            putValue(SHORT_DESCRIPTION, "Some short description");
        }

        public void actionPerformed(ActionEvent e) {
        }
    }*/
}
