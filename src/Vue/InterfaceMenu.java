package Vue;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class InterfaceMenu extends InterfaceGraphique {
    
    public void demarrer() {
        this.frame = new JFrame("Gaufre empoisonnée (rip)");
        this.frame.setSize(400, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(400,400));
        this.frame.setVisible(true);
        
        Box boxFinal = Box.createVerticalBox();
        JButton play = createButton("Play");
        JButton load = createButton("Load game");
        JButton exit = createButton("Exit");


        play.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Passe à l'interface Jeu
                new InterfaceJeu();
                fermer();
            }
        });
        Box box1 = Box.createHorizontalBox();
        box1.add(play);
        boxFinal.add(box1);
        

        load.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                // TODO
            }
        });
        Box box2 = Box.createHorizontalBox();
        box2.add(load);
        boxFinal.add(box2);
        

        exit.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Ferme la fenêtre
                fermer();
            }
        });
        Box box3 = Box.createHorizontalBox();
        box3.add(exit);
        boxFinal.add(box3);
        
        
        

        this.frame.add(boxFinal);


    }

    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}