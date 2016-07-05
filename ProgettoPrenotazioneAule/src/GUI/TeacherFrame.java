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
import javax.swing.BorderFactory;
/**
 *
 * @author Aciredef
 */
public class TeacherFrame extends JFrame {
    
    public TeacherFrame(){
        
        //frame
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("Account Insegnante");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //label
        JLabel label = new JLabel("Benvenuto/a. Selezionare un'opzione.");
        JPanel panelLabel = new JPanel();
        panelLabel.add(label);
        
        //buttons
        JButton makeRes = new JButton("Prenotare un'aula");
        JButton viewRes = new JButton("Visualizza prenotazioni");
        JButton changePW = new JButton("Cambiare la password");
        JButton exit = new JButton("Uscire dall'applicazione");
        JPanel panelButtons = new JPanel(new GridLayout(4,1,100,10));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        panelButtons.add(makeRes);        
        panelButtons.add(viewRes);
        panelButtons.add(changePW);
        panelButtons.add(exit);
        
        //main
        JPanel panelMain = new JPanel(new GridLayout(2,1));
        panelMain.add(panelLabel);
        panelMain.add(panelButtons);  
        
        //add to frame
        this.add(panelMain);
        this.setVisible(true);
        
    }
    
    
}
