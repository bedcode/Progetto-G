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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class, writing the id of the reservation, deletes it 
 * @author Federica
 */
public class DeleteReservationFrame extends JFrame implements ActionListener{
    private JButton delete, home;
    private JLabel id, esito;
    private JTextField idt;
    private JPanel centre;
    private JPanel north;
    private JPanel south, east, west;
    private Campus cp;

    public DeleteReservationFrame() {
       cp = Campus.getInstance();
       this.setLayout(new BorderLayout());
       this.setSize(400, 300);
       this.setResizable(false);
       this.setTitle("Elimina Prenotazione");
       id = new JLabel("Inserisci id della prenotazione da eliminare");
       delete = new JButton("Elimina");
       home = new JButton("Torna alla Home");
       idt = new JTextField();
       centre = new JPanel(new GridLayout(7, 1));
       north = new JPanel(new GridLayout(3, 1));
       south = new JPanel(new GridLayout(3, 1));
       east = new JPanel(new GridLayout(3, 1));
       west = new JPanel(new GridLayout(3, 1));
       esito = new JLabel();
       initComponents();
    }
    
    private void initComponents() {
        this.add(north, BorderLayout.NORTH);
        this.add(centre, BorderLayout.CENTER);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        west.add(new JPanel());
        east.add(new JPanel());
        id.setFont(new Font("Calibri", 20, 20));
        centre.add(home);
        Icon i = new ImageIcon("./images/Icon.jpg");
        home.setIcon(i);
        centre.add(new JPanel());
        centre.add(new JPanel());
        centre.add(id);
        centre.add(idt);
        idt.setEditable(true);
        idt.setEnabled(true);
        this.add(south, BorderLayout.SOUTH);
        centre.add(new JPanel());
        centre.add(delete);
        south.add(new JPanel());
        south.add(esito);
        south.add(new JPanel());
        delete.addActionListener(this);
        home.addActionListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Elimina")) {
            String e = idt.getText();
            cp.updateReservation();
            boolean es = cp.deleteReservation(Integer.parseInt(e));
            
            if (es == false) {
                esito.setText("Controlla l'id della prenotazione");
            } else {
                esito.setText("Prenotazione eliminata con successo");
            }
            this.idt.setText("");
        }
        if (ae.getActionCommand().equals("Torna alla Home")) {
            //cp.closeConnection();
            this.dispose();

        }
    }
    
}
