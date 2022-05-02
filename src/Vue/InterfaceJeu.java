package Vue;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

import Modele.*;


public class InterfaceJeu extends InterfaceGraphique {

    String J1;
    JRadioButton[] b1;

    String J2;
    JRadioButton[] b2;

    public InterfaceJeu(Jeu J, String J1, String J2, JRadioButton[] b1, JRadioButton[] b2){
        this.J1=J1;
        this.J2=J2;
        this.b1=b1;
        this.b2=b2;
        runnable = new Runnable() {
            @Override
            public void run() { demarrer(J); }
        };
    }

    @Override
    public void demarrer(Jeu J) {
        this.frame = new JFrame("Gaufre empoisonnée (rip)");
        this.frame.setSize(700, 420);
        this.frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Taille minimum de la fenêtre (pour le redimensionnement)
        this.frame.setMinimumSize(new Dimension(700,420));

        this.frame.setVisible(true);

        // Vérification des noms et taille
        //System.out.println("Taille de la gauffre : (" + J.n.lignes() + "," + J.n.colonnes() + ")");
        System.out.println("Noms des joueurs : J1=" + J1 + " J2=" + J2);
        
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


        this.frame.setVisible(true);


        // Initilisation du Jeu
        VueGaufre VG = new VueGaufre(J);
        this.frame.getContentPane().add(VG);
    }

    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}