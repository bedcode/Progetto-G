/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
/**
 *
 * @author Aciredef
 */
public class TeacherFrame extends JFrame {
    
    public TeacherFrame(){
        
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("Teacher's Options");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel label = new JLabel("Welcome. Please select an option.");
        // JTextField text1 = new JTextField(10);
        
        JButton show = new JButton("Read the calendar");
        JButton res = new JButton("Make a reservation");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));     
        
        panel.add(label);
        panel.add(show);        
        panel.add(res);
        
        this.add(panel);
        this.setVisible(true);
        
    }
    
    
}
