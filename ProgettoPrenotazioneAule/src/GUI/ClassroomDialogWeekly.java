/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PrenotareAula.Campus;
import PrenotareAula.Requirements;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 *This class shows what you can reserve and after your choice make the reservation
 * evry week from the start date to the day before the end date
 * @author Federica
 */
public class ClassroomDialogWeekly extends JDialog implements ActionListener{
    private Date fine;
    private Campus cp;
    private JLabel esito;
    private List<String> d = new ArrayList<String>();
    private Date inizio;
    private int startTime, endTime;
    protected String des;
    protected Requirements r;
    private JPanel north, east, west, south;
    /**
     * 
     * @param d list of things free
     * @param r requirements for the reservation
     * @param inizio date of start
     * @param fine date of end
     * @param startTime 
     * @param endTime
     * @param des description
     * note: if start and end date are the same no date is reserved
     */
    public ClassroomDialogWeekly (List d, Requirements r, Date inizio, Date fine, int startTime, int endTime, String des) {
        
        this.fine = fine;
        this.d = d;
        this.r = r;
        cp = Campus.getInstance();
        cp.updateReservation();
        this.setSize(500, 300);
        esito = new JLabel();
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setTitle("Scegli aula");
        this.inizio = inizio;
        this.des = des;
        this.endTime = endTime;
        this.startTime = startTime;
        east = new JPanel(new GridLayout(9, 6));
        west = new JPanel(new GridLayout(9, 6));
        east.setVisible(false);
        north = new JPanel(new GridLayout(1, 2));
        south = new JPanel(new GridLayout(3, 3));
        initcomponents();
    }
    
    private void initcomponents() {
        this.add(north, BorderLayout.NORTH);
        this.add(east, BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);
        this.add(south, BorderLayout.SOUTH);
        north.add(new JLabel("Aule coi requisiti richiesti"));
        JLabel l = new JLabel("Aule coi requisiti Simili");
        north.add(l);
        l.setVisible(false);
        JRadioButton b;
        int i = 0;
        while (!d.get(i).equalsIgnoreCase("Requisiti Simili")) {
            b = new JRadioButton(d.get(i));
            west.add(b);
            b.addActionListener(this);
            i++;
        }
        i++;
        for (int j = i; j < d.size(); j++) {
            b = new JRadioButton(d.get(j));
            east.setVisible(true);
            l.setVisible(true);
            east.add(b);
            b.addActionListener(this);
        }

        this.add(esito);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean make = cp.makeWeeklyReservation(ae.getActionCommand(), r, inizio, fine, startTime, endTime, des);
        if (make) {
            esito.setText("Prenotazione settimanale effettuata");
            JDialog dia = new JDialog();
            dia.setVisible(true);
            dia.add(new JLabel("Prenotazione settimanale effettuata"));
            this.dispose();
            dia.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dia.setSize(300,100);
            dia.setLocationRelativeTo(null);
            dia.setResizable(false);
            
        } else {
            esito.setText("Prenotazione fallita");
        }
    }
}
