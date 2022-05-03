package Vue;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

import Modele.*;


public class InterfaceJeu extends InterfaceGraphique {

    Jeu J;

    public InterfaceJeu(Jeu J){
        this.J=J;
    }
    
    public static void demarrer(Jeu J){
        SwingUtilities.invokeLater(new InterfaceJeu(J));
    }

        @Override
        public void run() {
        frame = new JFrame("Gaufre empoisonnée (rip)");
        frame.setSize(700, 420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Taille minimum de la fenêtre (pour le redimensionnement)
        frame.setMinimumSize(new Dimension(700,420));

        frame.setVisible(true);

        // Vérification des noms et taille
        System.out.println("Taille de la gauffre : (" + J.n.lignes() + "," + J.n.colonnes() + ")");
        //System.out.println("Noms des joueurs : J1=" + J1 + " J2=" + J2);
        
        JLabel joueur = new JLabel("Current player", SwingConstants.CENTER);
        joueur.setBackground(Color.GRAY);
        joueur.setForeground(Color.BLACK);
        joueur.setOpaque(true);
        frame.add(joueur, BorderLayout.NORTH);

        //this.frame.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("gaufre.jpg")), SwingConstants.CENTER), BorderLayout.WEST);


        // Création des boutons
        restart = createButton("Restart");
        previous = createButton("Previous");
        next = createButton("   Next    ");
        save = createButton("Save");
        exit = createButton("Exit");

        // Panel où il y a les boutons
        JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.GRAY);
        panelButton.setMinimumSize(new Dimension(200,400));
        panelButton.setMaximumSize(new Dimension(400,1000));
		frame.add(panelButton, BorderLayout.EAST);

        //On crée un conteneur avec gestion horizontale
        Box b1 = Box.createHorizontalBox();
        b1.add(restart);
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
        exit.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Ferme la fenêtre
                fermer();
            }
        });
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


        frame.setVisible(true);


        // Initilisation du Jeu
        VueGaufre VG = new VueGaufre(J);
        frame.getContentPane().add(VG);
    }

    //Faire une fonction qui recupère des coordonnées et modifie en conséquence niveau et VueGaufre apr rapport à niveau.
}