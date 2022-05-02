package Vue;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class InterfaceMenu extends InterfaceGraphique {
    
    public void demarrer() {
        this.frame = new JFrame("Gaufre empoisonnée (rip)");
        this.frame.setSize(800, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(700,400));
        this.frame.setVisible(true);
        
        Box boxFinal = Box.createVerticalBox();
        JButton play = createButton("Play");
        JButton load = createButton("Load game");
        JButton exit = createButton("Exit");

        // Ligne Joueur1
        JPanel pannelJ1 = new JPanel(new GridLayout(1,6));
        JLabel J1 = new JLabel(" Player 1");
        JTextField T1 = new JTextField(" Name of Player 1", 150);
        J1.setLabelFor(T1);

        ButtonGroup GroupJ1 = new ButtonGroup();
        JRadioButton b1 = new JRadioButton("Human", false);
        JRadioButton b2 = new JRadioButton("Easy AI", true);
        JRadioButton b3 = new JRadioButton("Medium AI", false);
        JRadioButton b4 = new JRadioButton("Difficult AI", false);
        GroupJ1.add(b1);GroupJ1.add(b2);
        GroupJ1.add(b3);GroupJ1.add(b4);

        pannelJ1.add(J1);
        pannelJ1.add(T1);
        pannelJ1.add(b1);
        pannelJ1.add(b2);
        pannelJ1.add(b3);
        pannelJ1.add(b4);

        boxFinal.add(pannelJ1);

        // Ligne Joueur2
        JPanel pannelJ2 = new JPanel(new GridLayout(1,6));
        JLabel J2 = new JLabel(" Player 2 ");
        JTextField T2 = new JTextField(" Name of Player 2", 150);
        J1.setLabelFor(T2);

        ButtonGroup GroupJ2 = new ButtonGroup();
        JRadioButton b21 = new JRadioButton("Human", false);
        JRadioButton b22 = new JRadioButton("Easy AI", true);
        JRadioButton b23 = new JRadioButton("Medium AI", false);
        JRadioButton b24 = new JRadioButton("Difficult AI", false);
        GroupJ2.add(b21);GroupJ2.add(b22);
        GroupJ2.add(b23);GroupJ2.add(b24);

        pannelJ2.add(J2);
        pannelJ2.add(T2);
        pannelJ2.add(b21);
        pannelJ2.add(b22);
        pannelJ2.add(b23);
        pannelJ2.add(b24);

        boxFinal.add(pannelJ2);


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