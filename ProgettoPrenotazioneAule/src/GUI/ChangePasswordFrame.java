/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PrenotareAula.Campus;
import Utenti.Account;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Aciredef
 */
public class ChangePasswordFrame extends JFrame implements ActionListener {

    private JPanel main;
    private JPanel upperArea;
    private JPanel lowerArea;
    private JPanel borderEast;
    private JPanel borderWest;
    private JPanel borderNorth;
    private JLabel email;
    private JLabel oldpassword;
    private JLabel newpassword;
    private JLabel errpassword;
    private JTextField emailField;
    private JPasswordField oldpasswordField, newpasswordField;
    private JButton conferma, home;
    private String emailLog;
    Account a;

    public ChangePasswordFrame(String emailLog) {
        this.emailLog = emailLog;
        a = Account.getInstance();
        this.setTitle("Cambia Password");
        this.setResizable(false);
        main = new JPanel(new BorderLayout());
        upperArea = new JPanel(new BorderLayout());
        lowerArea = new JPanel(new GridLayout(12, 1));
        borderEast = new JPanel();
        borderWest = new JPanel();
        borderNorth = new JPanel();
        email = new JLabel("e-mail");
        oldpassword = new JLabel("password attuale");
        newpassword = new JLabel("Password nuova");
        errpassword = new JLabel();
        emailField = new JTextField();
        newpasswordField = new JPasswordField();
        oldpasswordField = new JPasswordField();
        conferma = new JButton("Conferma");
        home = new JButton("Torna alla Home");
        Icon i = new ImageIcon("./images/Icon.jpg");
        home.setIcon(i);
        initComponents();
    }

    private void initComponents() {
        this.add(main);

        Dimension d = new Dimension(100, 500);
        this.setSize(500, 500);
        main.add(upperArea, BorderLayout.NORTH);
        main.add(lowerArea, BorderLayout.CENTER);
        main.add(borderEast, BorderLayout.EAST);
        main.add(borderWest, BorderLayout.WEST);
        borderEast.setPreferredSize(d);
        borderWest.setPreferredSize(d);
        upperArea.add(home);
        lowerArea.add(borderNorth);
        
        lowerArea.add(email);
        lowerArea.add(emailField);
        lowerArea.add(oldpassword);
        lowerArea.add(oldpasswordField);
        lowerArea.add(newpassword);
        lowerArea.add(newpasswordField);
        lowerArea.add(new JPanel());
        lowerArea.add(conferma);
        
        lowerArea.add(errpassword);
        
        
        errpassword.setHorizontalAlignment(JLabel.CENTER);
        home.addActionListener(this);
        conferma.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equalsIgnoreCase("Conferma")) {
            String e = emailField.getText();
            String op = String.valueOf(oldpasswordField.getPassword());
            String np = String.valueOf(newpasswordField.getPassword());
            boolean fun = a.setNewPassword(emailLog, e, op, np);
            if(fun == false) {
                errpassword.setForeground(Color.red);
                errpassword.setText("combinazione nome utente password errata!");
                
            }
            if(fun == true){
                errpassword.setForeground(Color.black);
                errpassword.setText("Password Cambiata");
            }
                
        }
         if (ae.getActionCommand().equals("Torna alla Home")) {
            this.setVisible(false);
            
        }
    }
}
