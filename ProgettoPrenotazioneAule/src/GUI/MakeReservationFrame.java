/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.GridLayout;
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
 *
 * @author Federico
 */
public class MakeReservationFrame extends JFrame {

    private JPanel main;
    private JLabel intestazione;
    private JLabel capacita;
    private JLabel data;
    private JLabel proiettore;
    private JLabel blackboard;
    private JLabel whiteboard;
    private JLabel laboratori;
    private JTextField capacitaField;
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
    JDatePanelImpl datePanel;
    DateModel model;
    JDatePickerImpl datePicker;
    String[] labList={"Aula regolare", "Aula computer", "Aula materiale elettrico","Aula biologia"};
    String[] hours={"9","10","11","12","13","14","15","16","17","18"};
    

    public MakeReservationFrame() {
        main = new JPanel(new GridLayout(11, 2));
        intestazione = new JLabel("compila i campi per fare una prenotazione");
        capacita = new JLabel("capacità");
        data = new JLabel("data");
        proiettore = new JLabel("proiettore");
        blackboard = new JLabel("lavagna");
        whiteboard = new JLabel("proiettore lucidi");
        laboratori = new JLabel("tipologia aula");
        capacitaField = new JTextField();
        laboratoriBox = new JComboBox(labList);
        startHourLabel=new JLabel("ora d'inizio");
        endHourLabel=new JLabel("ora fine");
        startHour=new JComboBox(hours);
        endHour=new JComboBox(hours);
        proiettoreCheck = new Checkbox();
        blackboardCheck = new Checkbox();
        whiteboardCheck = new Checkbox();
        accetta=new JButton("accetta");
        declina=new JButton("declina");
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        initComponents();

    }

    private void initComponents() {
        this.setTitle("prenotazione aula");
        this.add(main);
        this.setSize(600,400);
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
        main.add(accetta);
        main.add(declina);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }

}
