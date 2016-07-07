/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PrenotareAula.Campus;
import PrenotareAula.Requirements;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author Aciredef
 */
public class ClassroomDialog extends JDialog implements ActionListener {

    private List<String> d = new ArrayList<String>();
    private Date data;
    private JLabel esito;
    private int startTime, endTime;
    private String des;
    Requirements r;
    Campus cp;

    public ClassroomDialog(List d, Requirements r, Date data, int startTime, int endTime, String des) {
        this.d = d;
        this.r = r;
        cp = Campus.getInstance();
        cp.updateReservation();
        this.setSize(400, 400);
        esito = new JLabel();
        this.setResizable(false);
        this.setLayout(new GridLayout(9, 6));
        this.setTitle("Scegli aula");
        this.data = data;
        this.des = des;
        this.endTime = endTime;
        this.startTime = startTime;
        initcomponents();
    }

    private void initcomponents() {
        this.add(new JLabel("Aule coi requisiti richiesti"));
        JRadioButton b;
        for (String d1 : d) {
            if (!d1.equalsIgnoreCase("Requisiti Simili")) {
                b = new JRadioButton(d1);
                this.add(b);
                b.addActionListener(this);
            } else {
                this.add(new JLabel(d1));
            }

        }
        this.add(esito);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean make = cp.makeReservation(ae.getActionCommand(), r, data, startTime, endTime, des);
        if(make) {
            esito.setText("Prenotazione effettuata");
        }
        else {
            esito.setText("Prenotazione fallita");
        }
    }
}
