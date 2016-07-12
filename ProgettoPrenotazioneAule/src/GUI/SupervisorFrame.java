/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
 * This class shows options that the Supervisor can do 
 * @author Federica
 */
public class SupervisorFrame extends JFrame implements ActionListener {

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

    /**
     *
     * @param email The supervisor's email used to display a welcome message and
     * to change the password
     *
     */
    public SupervisorFrame(String email) {
        this.setVisible(true);
        this.email = email;
        name = new JLabel("Benvenuto Gestore: " + email);
        selection = new JLabel("Scegli un'opzione tra le seguenti:");
        newReservation = new JButton("Nuova Prenotazione");
        weeklyReservation = new JButton("Nuova prenotazione con cadenza settimanale");
        deleteReservation = new JButton("Elimina Prenotazione");
        changePassword = new JButton("Cambia Password");
        printReservation = new JButton("Visualizza le prenotazioni presenti");
        addTeacher = new JButton("Inserisci Account Docente");
        deleteTeacher = new JButton("Elimina Account Docente");
        exit = new JButton("Esci");
        initComponents();
    }

    public void initComponents() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screen = t.getScreenSize();
        Dimension s = new Dimension(50, 50);
        Dimension c = new Dimension(100, 100);
        Dimension d = new Dimension(600, 500);
        this.setSize(screen);
        Border b = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        main = new JPanel(new BorderLayout());
        main.setBorder(b);
        this.add(main);
        upperArea = new JPanel(new GridLayout(4, 1));
        lowerArea = new JPanel();
        borderEast = new JPanel(new GridLayout(10, 1));
        borderWest = new JPanel(new GridLayout(10, 1));
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
        borderWest.add(Box.createRigidArea(new Dimension(1, 0)));
        borderWest.add(newReservation);
        borderWest.add(Box.createRigidArea(new Dimension(1, 0)));
        borderWest.add(weeklyReservation);
        borderWest.add(Box.createRigidArea(new Dimension(1, 0)));
        borderWest.add(addTeacher);
        borderWest.add(Box.createRigidArea(new Dimension(1, 0)));
        borderEast.add(Box.createRigidArea(new Dimension(1, 0)));
        borderEast.add(printReservation);
        borderEast.add(Box.createRigidArea(new Dimension(1, 0)));
        borderEast.add(deleteReservation);
        borderEast.add(Box.createRigidArea(new Dimension(1, 0)));
        borderEast.add(deleteTeacher);
        borderWest.add(changePassword);
        borderEast.add(Box.createRigidArea(new Dimension(1, 0)));
        borderEast.add(exit);
        newReservation.addActionListener(this);
        deleteTeacher.addActionListener(this);
        addTeacher.addActionListener(this);
        changePassword.addActionListener(this);
        exit.addActionListener(this);
        printReservation.addActionListener(this);
        deleteReservation.addActionListener(this);
        weeklyReservation.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Nuova Prenotazione")) {
            this.setVisible(true);
            MakeReservationFrame f = new MakeReservationFrame();
            f.setVisible(true);
        }
        if (ae.getActionCommand().equalsIgnoreCase("Inserisci Account Docente")) {
            this.setVisible(true);
            AddNewTeacherAccountFrame f = new AddNewTeacherAccountFrame();
            f.setVisible(true);
        }
        if (ae.getActionCommand().equalsIgnoreCase("Elimina Account Docente")) {
            this.setVisible(true);
            DeleteTeacherAccountFrame f = new DeleteTeacherAccountFrame();
            f.setVisible(true);
        }
        if (ae.getActionCommand().equalsIgnoreCase("Cambia Password")) {
            this.setVisible(true);
            ChangePasswordFrame f = new ChangePasswordFrame(email);
            f.setVisible(true);
        }
        if (ae.getActionCommand().equalsIgnoreCase("Esci")) {
            this.dispose();
            Login l = new Login();
            l.setVisible(true);
        }
        if(ae.getActionCommand().equalsIgnoreCase("Visualizza le prenotazioni presenti")){
            PrintClassroomReservationFrame f = new PrintClassroomReservationFrame();
            f.setVisible(true);
        }
        if(ae.getActionCommand().equalsIgnoreCase("Elimina Prenotazione")){
            DeleteReservationFrame f = new DeleteReservationFrame();
            f.setVisible(true);
        }
        if(ae.getActionCommand().equalsIgnoreCase("Nuova prenotazione con cadenza settimanale")){
            MakeWeeklyReservationFrame f = new MakeWeeklyReservationFrame();
            f.setVisible(true);
        }
    }
}
