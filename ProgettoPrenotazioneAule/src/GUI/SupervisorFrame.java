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
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Aciredef
 */
public class SupervisorFrame extends JFrame {
    private Campus c;
    private String email;
    private JLabel name;
    private JLabel selection;
    private JButton newReservation;
    private JButton weeklyReservation;
    private JButton deleteReservation;
    private JButton editReservation;
    private JButton printReservation;
    private JButton addTeacher;
    private JButton exit;
    private JPanel main;
    private JPanel upperArea;
    private JPanel lowerArea;
    private JPanel borderEast;
    private JPanel borderWest;
    private JPanel borderNorth;
    

    public SupervisorFrame(String email) throws HeadlessException {
        this.setVisible(true);
        this.email = email;
        c = Campus.getInstance();
        name = new JLabel ( "Benvenuto Gestore: " +email);
        selection = new JLabel("Scegli un'opzione tra le seguenti:" );
        newReservation = new JButton("Nuova Prenotazione");
        weeklyReservation = new JButton("Nuova prenotazione con cadenza settimanale");
        deleteReservation = new JButton("Elimina Prenotazione");
        editReservation = new JButton("Modifica Prenotazione");
        printReservation = new JButton("Visualizza le prenotazioni presenti");
        addTeacher = new JButton("Inserisci Account Docente");
        
        initcomponents();
        
    }
    
    public void initcomponents() {
        Dimension s=new Dimension(100, 100);
        Dimension d=new Dimension(600, 500);
        this.setSize(1300, 1000);        
        main = new JPanel(new BorderLayout());
        upperArea = new JPanel(new GridLayout(4,1));
        lowerArea = new JPanel();
        borderEast=new JPanel(new GridLayout(10,1));
        borderWest=new JPanel(new GridLayout(10,1));
        borderNorth=new JPanel();
        this.add(main);
        this.setLocationRelativeTo(null);
        main.setVisible(true);
        main.add(upperArea, BorderLayout.NORTH);
        main.add(lowerArea, BorderLayout.CENTER);
        main.add(borderEast, BorderLayout.EAST);
        main.add(borderWest, BorderLayout.WEST);
        borderEast.setPreferredSize(d);
        borderWest.setPreferredSize(d);
        lowerArea.setPreferredSize(d);
        upperArea.setPreferredSize(s);
        upperArea.add(new JPanel());
        name.setFont(new Font("Calibri", 24, 24));
        upperArea.add(name);        
        upperArea.add(new JPanel());
        selection.setFont(new Font("Calibri", 18, 18));
        upperArea.add(selection);
        
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        borderWest.add(newReservation);
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        borderWest.add(weeklyReservation);
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        borderWest.add(addTeacher);
        borderWest.add(Box.createRigidArea(new Dimension(1,0)));
        
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        borderEast.add(editReservation);
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        borderEast.add(deleteReservation);
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        borderEast.add(printReservation);
        borderEast.add(Box.createRigidArea(new Dimension(1,0)));
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
