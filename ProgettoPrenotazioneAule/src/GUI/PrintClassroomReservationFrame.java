/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PrenotareAula.Campus;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Aciredef
 */
public class PrintClassroomReservationFrame extends JFrame implements ActionListener{
    private JTextArea print;
    private JButton home, update;
    private JPanel north, east, west, south;
    private JLabel intro;
    private Campus cp;

    public PrintClassroomReservationFrame() throws HeadlessException {
        this.setTitle("Visualizza Prenotazioni");
        this.setLayout(new BorderLayout());
        this.setSize(400,300);
        this.setResizable(false);
        cp = Campus.getInstance();
        cp.updateReservation();
        print = new JTextArea();
        home = new JButton("Torna alla Home");
        update = new JButton("Aggiorna");
        north = new JPanel(new GridLayout(4,1));
        east = new JPanel(new GridLayout(4,1));
        west = new JPanel(new GridLayout(4,1));
        south = new JPanel(new GridLayout(2,1));
        intro = new JLabel ("Visualizza le prenotazioni presenti:");
        initcomponents();
    }
    private void initcomponents(){
        JScrollPane scrollPane = new JScrollPane(print);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        Icon i = new ImageIcon("./images/Icon.jpg");
        home.setIcon(i);
        north.add(home);
        north.add(new JPanel());
        intro.setFont(new Font("Calibri", 24, 24));
        north.add(intro);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        west.add(new JPanel());
        east.add(new JPanel());
        south.add(new JPanel());
        south.add(update);
        print.setText(cp.printAllClassroomReservation());
        print.setEditable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        home.addActionListener(this);
        update.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Torna alla Home")) {
            cp.closeConnection();
            this.dispose();

        }
        if( ae.getActionCommand().equalsIgnoreCase("Aggiorna")) {
            cp.updateReservation();
             print.setText(cp.printAllClassroomReservation());
        }
    }
    
    
    
}
