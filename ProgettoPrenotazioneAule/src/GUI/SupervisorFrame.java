/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PrenotareAula.Campus;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Aciredef
 */
public class SupervisorFrame extends JFrame implements ActionListener {
    private Campus c;
    private String email;
    private JLabel name;
    private JLabel selection;
    private JButton newReservation;
    private JButton weeklyReservation;
    private JButton deleteReservation;
    private JButton changePassword;
    private JButton printReservation;
    private JButton addTeacher;
    private JButton deleteTeacher;
    private JButton exit;
    private JPanel main;
    private JPanel upperArea;
    private JPanel lowerArea;
    private JPanel borderEast;
    private JPanel borderWest;
    private JPanel borderNorth;
    private Toolkit t;
    public SupervisorFrame(String email) throws HeadlessException {
        this.setVisible(true);
        this.email = email;
        c = Campus.getInstance();
        name = new JLabel ( "Benvenuto Gestore: " +email);
        selection = new JLabel("Scegli un'opzione tra le seguenti:" );
        newReservation = new JButton("Nuova Prenotazione");
        weeklyReservation = new JButton("Nuova prenotazione con cadenza settimanale");
        deleteReservation = new JButton("Elimina Prenotazione");
        changePassword = new JButton("Cambia Password");
        printReservation = new JButton("Visualizza le prenotazioni presenti");
        addTeacher = new JButton("Inserisci Account Docente");
        deleteTeacher = new JButton("Elimina Account Docente");
        exit = new JButton("Esci");
        
        initcomponents();
        
    }
    
    public void initcomponents() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screen = t.getScreenSize();
        Dimension s=new Dimension(50, 50);
        Dimension c=new Dimension(100, 100);
        Dimension d=new Dimension(600, 500);
        this.setSize(screen);        
        Border b = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        main = new JPanel(new BorderLayout());
        main.setBorder(b);
        this.add(main);
        
        upperArea = new JPanel(new GridLayout(4,1));
        lowerArea = new JPanel();
        borderEast=new JPanel(new GridLayout(10,1));
        borderWest=new JPanel(new GridLayout(10,1));
        borderNorth=new JPanel();
        this.setLocationRelativeTo(null);
        main.setVisible(true);
        main.add(upperArea, BorderLayout.NORTH);
        main.add(lowerArea, BorderLayout.CENTER);
        main.add(borderEast, BorderLayout.EAST);
        main.add(borderWest, BorderLayout.WEST);
        borderEast.setPreferredSize(d);
        borderWest.setPreferredSize(d);
        lowerArea.setPreferredSize(c);
        upperArea.setPreferredSize(c);
        upperArea.add(new JPanel());
        name.setFont(new Font("Calibri", 24, 24));
        upperArea.add(name);        
        upperArea.add(new JPanel());
        selection.setFont(new Font("Calibri", 18, 18));
        upperArea.add(selection);
        
        
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        borderWest.add(newReservation);
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        borderWest.add(weeklyReservation);
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        borderWest.add(addTeacher);
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        
        
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        borderEast.add(printReservation);
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        borderEast.add(deleteReservation);
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        
        borderEast.add(deleteTeacher);
        borderWest.add(changePassword);
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        borderEast.add(exit);
        newReservation.addActionListener(this);
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         if(ae.getActionCommand().equals("Nuova Prenotazione"))
             this.setVisible(false);
         MakeReservationFrame f = new MakeReservationFrame();
         f.setVisible(true);
    }
}
