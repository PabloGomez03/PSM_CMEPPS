/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psm;

import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel {
    private int errors = 0;

    public void setErrors(int errors) {
        this.errors = errors;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        
        g2.setColor(Color.BLACK);
        g2.drawLine(20, h - 20, 120, h - 20); 
        g2.drawLine(70, h - 20, 70, 20);      
        g2.drawLine(70, 20, w / 2, 20);      
        g2.drawLine(w / 2, 20, w / 2, 50);    

       
        g2.setColor(Color.RED);
        if (errors >= 1) g2.drawOval(w/2 - 15, 50, 30, 30); 
        if (errors >= 2) g2.drawLine(w/2, 80, w/2, 140);    
        if (errors >= 3) g2.drawLine(w/2, 90, w/2 - 20, 120); 
        if (errors >= 4) g2.drawLine(w/2, 90, w/2 + 20, 120); 
        if (errors >= 5) g2.drawLine(w/2, 140, w/2 - 20, 180); 
        if (errors >= 6) g2.drawLine(w/2, 140, w/2 + 20, 180); 
    }
}