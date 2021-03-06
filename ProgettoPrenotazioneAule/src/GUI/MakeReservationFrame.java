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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
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
 * this class lets make a reservation, compiling some fields.
 * @author Federico/Federica
 */
public class MakeReservationFrame extends JFrame implements ActionListener {

    private JPanel main;
    private JLabel intestazione;
    private JLabel capacita;
    private JLabel data;
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
    private JDatePanelImpl datePanel;
    private DateModel model;
    private JDatePickerImpl datePicker;
    private String[] labList = {"Aula regolare", "Aula computer", "Aula materiale elettrico", "Aula biologia", "Aula Disegno"};
    private String[] hours = {"9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};

    public MakeReservationFrame() {
        main = new JPanel(new GridLayout(12, 2));
        intestazione = new JLabel("compila i campi per fare una prenotazione");
        capacita = new JLabel("capacità");
        data = new JLabel("data");
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
        descrizione=new JLabel("descrizione prenotazione");
        descrizioneField=new JTextField("");
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        initComponents();

    }

    private void initComponents() {
        this.setTitle("prenotazione aula");
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
        main.add(data);
        main.add(datePicker);
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
        declina.addActionListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] date = datePicker.getJFormattedTextField().getText().split("-");
                try {
                Calendar ca = new GregorianCalendar(Integer.parseInt(date[0]), (Integer.parseInt(date[1])-1), Integer.parseInt(date[2]));
                String lab = (String)laboratoriBox.getSelectedItem();
                if(lab.equalsIgnoreCase("Aula regolare")){
                    lab = null;
                }    
                else if(lab.equalsIgnoreCase("Aula computer")){
                    lab = "computer";
                }
                else if(lab.equalsIgnoreCase("Aula biologia")){
                    lab = "biologia";
                }
                else if(lab.equalsIgnoreCase("Aula disegno")){
                    lab = "disegno";
                }
                else if(lab.equalsIgnoreCase("Aula materiale elettrico")){
                    lab = "materiale elettrico";
                }
                
                Requirements re = new Requirements(Integer.parseInt(capacitaField.getText()), blackboardCheck.getState(), whiteboardCheck.getState(), proiettoreCheck.getState(), lab );
                int startTime = Integer.parseInt(startHour.getSelectedItem().toString());
                int endTime =  Integer.parseInt(endHour.getSelectedItem().toString());
                String des = descrizioneField.getText();
                
                    Campus cp = Campus.getInstance();
                    cp.updateReservation();
                    List d = cp.askForReservation(re, ca.getTime(), startTime ,endTime);
                    ClassroomDialog c = new ClassroomDialog(d, re, ca.getTime(), startTime ,endTime, des);
                    c.setVisible(true);
                    dispose();
                }
                catch(NumberFormatException ex) {
                    ex.getMessage();
                }
                catch(IndexOutOfBoundsException ex) {
                    ex.getMessage();
                }
            }

        };
        
        
        accetta.addActionListener(al);
        this.setLocationRelativeTo(null);
    }
;

    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getActionCommand().equalsIgnoreCase("esci")){
           this.dispose();
       }
    }


}

    
