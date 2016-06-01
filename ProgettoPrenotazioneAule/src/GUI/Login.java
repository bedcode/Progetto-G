/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Aciredef
 */
public class Login extends JFrame {
    private JPanel main;
    private JPanel upperArea;
    private JPanel lowerArea;
    private JLabel benvenuto;
    private JLabel email;
    private JLabel password;
    private JTextArea emailField;
    private JTextArea passwordField;
    private JButton login;

    public Login() {
        main = new JPanel(new BorderLayout());
        upperArea = new JPanel(new BorderLayout());
        lowerArea = new JPanel(new GridLayout(5,1));
        benvenuto = new JLabel("Benvenuto in XClassLive, inserisci e-mail e password");
        email = new JLabel("e-mail");
        password = new JLabel("password");
        emailField = new JTextArea();
        passwordField = new JTextArea();
        login = new JButton("Login");
        initComponents();
    }
    
    
    private void initComponents() {
        this.add(main);
        main.add(upperArea, BorderLayout.NORTH);
        main.add(lowerArea, BorderLayout.SOUTH);
        upperArea.add(benvenuto);
        lowerArea.add(email);
        lowerArea.add(emailField);
        lowerArea.add(password);
        lowerArea.add(passwordField);
        lowerArea.add(login);
    }
    
    
    
    
    
    
    
}
