package Vue;

import javax.swing.*;
import java.awt.event.*;

public class InterfaceMenu extends InterfaceGraphique {
    
    public void demarrer() {
        this.frame = new JFrame("Gaufre empoisonnée (rip)");
        this.frame.setSize(400, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        
        Box box = Box.createVerticalBox();
        JButton test = new JButton("jouer");

        // tests
        // todo: implémenter le controleur pour souris etc
        test.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                new InterfaceJeu();
                fermer();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
        box.add(test);
        this.frame.add(box);
    }

    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}