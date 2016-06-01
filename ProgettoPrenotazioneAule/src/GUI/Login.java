/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Aciredef
 */
public class Login extends JFrame {
    private JPanel main;
    private JPanel upperArea;
    private JPanel lowerArea;
    private JPanel borderEast;
    private JPanel borderWest;
    private JPanel borderNorth;
    private JLabel benvenuto;
    private JLabel email;
    private JLabel password;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton login;

    public Login() {
        main = new JPanel(new BorderLayout());
        upperArea = new JPanel(new BorderLayout());
        lowerArea = new JPanel(new GridLayout(12,1));
        borderEast=new JPanel();
        borderWest=new JPanel();
        borderNorth=new JPanel();
        benvenuto = new JLabel("Benvenuto in XClassLive, inserisci e-mail e password");
        email = new JLabel("e-mail");
        password = new JLabel("password");
        emailField = new JTextField();
        passwordField = new JTextField();
        login = new JButton("Login");
        initComponents();
    }
    
    
    private void initComponents() {
        this.add(main);
        this.setLocationRelativeTo(null);
        Dimension d=new Dimension(100, 500);
        this.setSize(500, 500);
        main.add(upperArea, BorderLayout.NORTH);
        main.add(lowerArea, BorderLayout.CENTER);
        main.add(borderEast, BorderLayout.EAST);
        main.add(borderWest, BorderLayout.WEST);
        borderEast.setPreferredSize(d);
        borderWest.setPreferredSize(d);
        upperArea.add(benvenuto);
        lowerArea.add(borderNorth);
        lowerArea.add(email);
        lowerArea.add(emailField);
        lowerArea.add(password);
        lowerArea.add(passwordField);
        lowerArea.add(login);
        benvenuto.setHorizontalAlignment(JLabel.CENTER);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }
    
    
    
    
    
    
    
}
