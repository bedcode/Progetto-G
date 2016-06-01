/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PrenotareAula.Campus;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

    public SupervisorFrame(String email) throws HeadlessException {
        this.setVisible(true);
        this.email = email;
        initcomponents();
        
    }
    
    public void initcomponents() {
        c = Campus.getInstance();
        name = new JLabel ( "Benvenuto Gestore: " +email);
        selection = new JLabel("Scegli un'opzione tra le seguenti:" );
    }
}
