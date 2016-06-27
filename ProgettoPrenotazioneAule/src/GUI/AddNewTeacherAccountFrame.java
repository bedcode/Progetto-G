/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utenti.Account;
import Utenti.Teacher;
import Utenti.User;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aciredef
 */
public class AddNewTeacherAccountFrame extends JFrame implements ActionListener {
    private JLabel intro, email, esito;
    private JButton conferma;
    private JTextField emailT;
    private JPanel centre;
    private JPanel north;
    private JPanel south;
    private Account a;
    
    public AddNewTeacherAccountFrame() throws HeadlessException {
        this.setLayout(new BorderLayout());
        this.setSize(400,600);
        this.setResizable(false);
        a = Account.getInstance();
        intro = new JLabel("Aggiungi docente");
        email = new JLabel("Inserisci email del docente");
        conferma = new JButton ("conferma");
        emailT = new JTextField ();
        centre = new JPanel(new GridLayout(4,1));
        north = new JPanel(new GridLayout(2,1));
        south = new JPanel(new GridLayout(3,1));
        esito = new JLabel();
        initcomponents();
            }
    private void initcomponents(){
        this.add(north, BorderLayout.NORTH);
        this.add(centre, BorderLayout.CENTER);
        intro.setFont(new Font("Calibri", 30,30));
        email.setFont(new Font("Calibri", 20,20));
        north.add(intro);
        centre.add(email);
        centre.add(emailT);
        emailT.setMargin(new Insets(20,20,20,20));
        emailT.setSize(10,10);
        emailT.setEditable(true);
        emailT.setEnabled(true);
        this.add(south, BorderLayout.SOUTH);
        centre.add(new JPanel());
        centre.add(conferma);
        
        south.add(new JPanel());
        south.add(esito);
        south.add(new JPanel());
        conferma.addActionListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("conferma")) {
            String e = emailT.getText();
            User t = a.addNewTeacherAccount(new Teacher(e));
            if (t == null) {
                esito.setText("Non posso inserire il docente,\n controllare l'email");
            }
            else
                esito.setText("Docente inserito con successo");
        }
    }
    
    
}
