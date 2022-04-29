package Vue;

import javax.swing.*;
import java.awt.*;

public class InterfaceJeu extends InterfaceGraphique {

    
    public void demarrer() {
        this.frame = new JFrame("Gaufre empoisonnée (rip)");
        this.frame.setSize(700, 420);
        this.frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Taille minimum de la fenêtre (pour le redimensionnement)
        this.frame.setMinimumSize(new Dimension(700,420));

        this.frame.setVisible(true);


        
        JLabel joueur = new JLabel("Joueur 1 ou Joueur 2 ", SwingConstants.CENTER);
        joueur.setBackground(Color.GRAY);
        joueur.setForeground(Color.BLACK);
        joueur.setOpaque(true);
        this.frame.add(joueur, BorderLayout.NORTH);

        //this.frame.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("gaufre.jpg")), SwingConstants.CENTER), BorderLayout.WEST);


        // Création des boutons
        JButton start = createButton("Restart");
        JButton previous = createButton("Previous");
        JButton next = createButton("   Next    ");
        JButton save = createButton("Save");
        JButton exit = createButton("Exit");

        // Panel où il y a les boutons
        JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.GRAY);
        panelButton.setMinimumSize(new Dimension(200,400));
        panelButton.setMaximumSize(new Dimension(400,1000));
		this.frame.add(panelButton, BorderLayout.EAST);

        //On crée un conteneur avec gestion horizontale
        Box b1 = Box.createHorizontalBox();
        b1.add(start);
        //Idem
        Box b2 = Box.createHorizontalBox();
        b2.add(previous);
        b2.add(next);
        //Idem
        Box b3 = Box.createHorizontalBox();
        b3.add(save);
        //Idem
        Box b4 = Box.createHorizontalBox();
        b4.add(exit);
        //On crée un conteneur avec gestion verticale
        Box b5 = Box.createVerticalBox();
        b5.add(b1);
        b5.add(Box.createRigidArea(new Dimension(0,82)));
        b5.add(b2);
        b5.add(Box.createRigidArea(new Dimension(0,82)));
        b5.add(b3);
        b5.add(Box.createRigidArea(new Dimension(0,82)));
        b5.add(b4);

        panelButton.add(b5);


        this.frame.setVisible(true);
    }


    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}