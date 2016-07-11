/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

/**
 * This class shows the option that a Teacher can do
 * @author Fabio
 */
public class TeacherFrame extends JFrame implements ActionListener{
    
    private final Dimension d = new Dimension(500, 500);
    private String email;
    private JLabel welcome;
    private JLabel choice;
    private JButton makeRes;
    private JButton viewRes;
    private JButton changePW;
    private JButton exit;
    private JPanel panelLabel;
    private JPanel panelButtons;
    private JPanel panelMain;
    
    /**
     * 
     * @param email The teacher's email used to display a welcome message
     *              and to change the password
     */
    public TeacherFrame(String email){
        
        this.email = email;
        initFrame(d);
        makeComponents();
        addListeners();
        
    }
    
    
    private void initFrame(Dimension d){
        
        setTitle("Account Insegnante");
        setSize(d);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    /*
     * Makes all the components: labels, buttons, panels
     */
    private void makeComponents(){
        
        //labels
        welcome = new JLabel("Benvenuto utente " + email);
        choice = new JLabel("Selezionare un'opzione.");
        welcome.setFont(new Font("Calibri", 24, 24));
        choice.setFont(new Font("Calibri", 18, 18));
        panelLabel = new JPanel(new GridLayout(0, 1, 0, 0));
        panelLabel.add(welcome);
        panelLabel.add(choice);
        panelLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        //buttons
        makeRes = new JButton("Prenotare aula");
        viewRes = new JButton("Visualizzare prenotazioni");
        changePW = new JButton("Cambiare password");
        exit = new JButton("Uscire");
        panelButtons = new JPanel(new GridLayout(0, 1, 10, 20));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelButtons.add(makeRes);        
        panelButtons.add(viewRes);
        panelButtons.add(changePW);
        panelButtons.add(exit);
        
        //main panel
        panelMain = new JPanel(new BorderLayout());
        panelMain.add(panelLabel, BorderLayout.NORTH);
        panelMain.add(panelButtons, BorderLayout.CENTER);
        panelMain.setBorder(BorderFactory.createEmptyBorder(25, 35, 20, 35));
        add(panelMain);
        
    }
    
    /**
     * Add action listeners to components
     */
    private void addListeners(){
            
        makeRes.addActionListener(this);
        viewRes.addActionListener(this);
        changePW.addActionListener(this);
        exit.addActionListener(this);
        
    }
        
    /**
     * 
     * @param ae The event caused by pressing the buttons
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equalsIgnoreCase("Prenotare Aula")) {
            this.setVisible(true);
            MakeReservationFrame f = new MakeReservationFrame();
            f.setVisible(true);
        }
        
        if (ae.getActionCommand().equalsIgnoreCase("Visualizzare prenotazioni")) {
            this.setVisible(true);
            PrintClassroomReservationFrame f = new PrintClassroomReservationFrame();
            f.setVisible(true);
        }
        
        if (ae.getActionCommand().equalsIgnoreCase("Cambiare Password")) {
            this.setVisible(true);
            ChangePasswordFrame f = new ChangePasswordFrame(email);
            f.setVisible(true);
        }
        
        if (ae.getActionCommand().equalsIgnoreCase("Uscire")) {
            this.dispose();
            Login l = new Login();
            l.setVisible(true);
        }
        
    }
    
    
}
