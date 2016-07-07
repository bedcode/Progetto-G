/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PrenotareAula.Campus;
import PrenotareAula.Requirements;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Andrea
 */
public class MakeWeeklyReservationFrame extends JFrame {

    private JPanel main;
    private JLabel intestazione;
    private JLabel capacita;
    private JLabel dataInizio;
    private JLabel dataFine;
    private JLabel proiettore;
    private JLabel blackboard;
    private JLabel whiteboard;
    private JLabel laboratori;
    private JLabel descrizione;
    private JTextField capacitaField;
    private JTextField descrizioneField;
    private JLabel startHourLabel;
    private JLabel endHourLabel;
    private JComboBox startHour;
    private JComboBox endHour;
    private JComboBox laboratoriBox;
    private Checkbox proiettoreCheck;
    private Checkbox blackboardCheck;
    private Checkbox whiteboardCheck;
    private JButton accetta;
    private JButton declina;
    private DateModel model;
    private JDatePanelImpl datePanelInizio;
    private JDatePickerImpl datePickerInizio;
    private JDatePanelImpl datePanelFine;
    private JDatePickerImpl datePickerFine;
    private String[] labList = {"Aula regolare", "Aula computer", "Aula materiale elettrico", "Aula biologia"};
    private String[] hours = {"9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};

    public MakeWeeklyReservationFrame() {
        main = new JPanel(new GridLayout(13, 2));
        intestazione = new JLabel("compila i campi per fare una prenotazione con cadenza settimanale");
        capacita = new JLabel("capacità");
        dataInizio = new JLabel("data inizio prenotazione");
        dataFine = new JLabel("data fine prenotazione");
        proiettore = new JLabel("proiettore");
        blackboard = new JLabel("lavagna");
        whiteboard = new JLabel("proiettore lucidi");
        laboratori = new JLabel("tipologia aula");
        capacitaField = new JTextField();
        laboratoriBox = new JComboBox(labList);
        startHourLabel = new JLabel("ora d'inizio");
        endHourLabel = new JLabel("ora fine");
        startHour = new JComboBox(hours);
        endHour = new JComboBox(hours);
        proiettoreCheck = new Checkbox();
        blackboardCheck = new Checkbox();
        whiteboardCheck = new Checkbox();
        accetta = new JButton("accetta");
        declina = new JButton("esci");
        model = new UtilDateModel();
        descrizione = new JLabel("descrizione prenotazione");
        descrizioneField = new JTextField("aggiungere una descrizione");
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanelInizio = new JDatePanelImpl(model, p);
        datePickerInizio = new JDatePickerImpl(datePanelInizio, new DateLabelFormatter());
        datePanelFine = new JDatePanelImpl(model, p);
        datePickerFine = new JDatePickerImpl(datePanelFine, new DateLabelFormatter());
        initComponents();
    }

    private void initComponents() {
        this.setTitle("Prenotazione semestrale di un'aula");
        this.add(main);
        this.setSize(600, 400);
        this.setResizable(false);
        Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        main.setBorder(padding);
        main.add(intestazione);
        main.add(new JPanel());
        main.add(new JPanel());
        main.add(new JPanel());
        main.add(capacita);
        main.add(capacitaField);
        main.add(dataInizio);
        main.add(datePickerInizio);
        main.add(dataFine);
        main.add(datePickerFine);
        main.add(startHourLabel);
        main.add(startHour);
        main.add(endHourLabel);
        main.add(endHour);
        main.add(proiettore);
        main.add(proiettoreCheck);
        main.add(blackboard);
        main.add(blackboardCheck);
        main.add(whiteboard);
        main.add(whiteboardCheck);
        main.add(laboratori);
        main.add(laboratoriBox);
        main.add(descrizione);
        main.add(descrizioneField);
        main.add(accetta);
        main.add(declina);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] date = datePickerInizio.getJFormattedTextField().getText().split("-");
                Calendar ca = new GregorianCalendar(Integer.parseInt(date[0]), (Integer.parseInt(date[1]) - 1), Integer.parseInt(date[2]));
                Date inizio = ca.getTime();
                date = datePickerInizio.getJFormattedTextField().getText().split("-");
                ca = new GregorianCalendar(Integer.parseInt(date[0]), (Integer.parseInt(date[1]) - 1), Integer.parseInt(date[2]));
                Date fine = ca.getTime();
                Requirements re = new Requirements(Integer.parseInt(capacitaField.getText()), blackboardCheck.getState(), whiteboardCheck.getState(), proiettoreCheck.getState(), laboratoriBox.getToolTipText());
                int startTime = Integer.parseInt(startHour.getSelectedItem().toString());
                int endTime = Integer.parseInt(endHour.getSelectedItem().toString());
                String des = descrizioneField.getText();
                try {
                    List d = Campus.getInstance().askForReservationedited(re, inizio, startTime, endTime, des);
                    ClassroomDialog c = new ClassroomDialog(d, re, ca.getTime(), startTime, endTime, des);
                    c.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MakeReservationFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };
        accetta.addActionListener(al);
        this.setLocationRelativeTo(null);
    }
;

}
