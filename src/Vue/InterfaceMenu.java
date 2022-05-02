package Vue;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class InterfaceMenu extends InterfaceGraphique {

    private JTextField T1;
    private JTextField T2;

    private JTextField Ligne;
    private JTextField Colonne;

    private JRadioButton[] b1;
	private JRadioButton[] b2;
    
    public void demarrer() {
        this.frame = new JFrame("Menu");
        this.frame.setSize(800, 280);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(790,275));
        this.frame.setVisible(true);
        
        Box boxFinal = Box.createVerticalBox();
        JButton play = createButton("Play");
        JButton load = createButton("Load game");
        JButton exit = createButton("Exit");

        // Ligne Joueur1
        JPanel pannelJ1 = new JPanel(new GridLayout(1,6));
        JLabel J1 = new JLabel(" Player 1");
        T1 = createText(" Name of Player 1");
        J1.setLabelFor(T1);

        ButtonGroup GroupJ1 = new ButtonGroup();
        b1 = new JRadioButton[4];
        b1[0] = new JRadioButton("Human", false);
        b1[1] = new JRadioButton("Easy AI", true);
        b1[2] = new JRadioButton("Medium AI", false);
        b1[3] = new JRadioButton("Difficult AI", false);
        GroupJ1.add(b1[0]);GroupJ1.add(b1[1]);
        GroupJ1.add(b1[2]);GroupJ1.add(b1[3]);

        pannelJ1.add(J1);
        pannelJ1.add(T1);
        pannelJ1.add(b1[0]);
        pannelJ1.add(b1[1]);
        pannelJ1.add(b1[2]);
        pannelJ1.add(b1[3]);

        pannelJ1.setMaximumSize(new Dimension(1500,100));

        boxFinal.add(pannelJ1);

        // Ligne Joueur2
        JPanel pannelJ2 = new JPanel(new GridLayout(1,6));
        JLabel J2 = new JLabel(" Player 2 ");
        T2 = createText(" Name of Player 2");
        J1.setLabelFor(T2);

        ButtonGroup GroupJ2 = new ButtonGroup();
        b2 = new JRadioButton[4];
        b2[0] = new JRadioButton("Human", false);
        b2[1] = new JRadioButton("Easy AI", true);
        b2[2] = new JRadioButton("Medium AI", false);
        b2[3] = new JRadioButton("Difficult AI", false);
        GroupJ2.add(b2[0]);GroupJ2.add(b2[1]);
        GroupJ2.add(b2[2]);GroupJ2.add(b2[3]);

        pannelJ2.add(J2);
        pannelJ2.add(T2);
        pannelJ2.add(b2[0]);
        pannelJ2.add(b2[1]);
        pannelJ2.add(b2[2]);
        pannelJ2.add(b2[3]);

        pannelJ2.setMaximumSize(new Dimension(1500,100));

        boxFinal.add(pannelJ2);

        // Ligne pour la taille de la gauffre
        Box SizePanel = Box.createVerticalBox();

        // Texte
        Box name = Box.createHorizontalBox();
        JLabel J = new JLabel("Waffle size");
        name.add(J);
        SizePanel.add(name);

        // Box contenant les deux zones de texte (ligne(s) et colonne(s))
        Box Size = Box.createHorizontalBox();
        Ligne = createText(" Line(s)");
        Ligne.setSize(new Dimension(100, 60));
        Ligne.setMaximumSize(new Dimension(100, 60));
        Colonne = createText(" Colonne(s)");
        Colonne.setSize(new Dimension(100, 60));
        Colonne.setMaximumSize(new Dimension(100, 60));

        Size.add(Box.createRigidArea(new Dimension(310, 0)));
        Size.add(Ligne);
        Size.add(Box.createRigidArea(new Dimension(10, 0)));
        Size.add(Colonne);
        Size.add(Box.createRigidArea(new Dimension(310, 0)));

        SizePanel.add(Size);

        boxFinal.add(SizePanel);


        play.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(Integer.parseInt(getLigne())>30 || Integer.parseInt(getColonne())>50){
                    fermer();
                    // Ajouter une fenêtre d'erreur.
                    new InterfaceMenu();
                }
                else{
                    // Passe à l'interface Jeu
                    new InterfaceJeu(getLigne(), getColonne(), getJ1(), getJ2(), getButtonJ1(), getButtonJ2());
                    fermer();
                }
            }
        });
        Box box1 = Box.createHorizontalBox();
        box1.add(play);
        boxFinal.add(box1);
        boxFinal.add(Box.createRigidArea(new Dimension(1,20)));
        

        load.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                // TODO
            }
        });
        Box box2 = Box.createHorizontalBox();
        box2.add(load);
        boxFinal.add(box2);
        boxFinal.add(Box.createRigidArea(new Dimension(1,20)));
        

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

    // Permet de récupérer la taille de la gauffre
    public String getLigne(){
        return Ligne.getText();
    }

    public String getColonne(){
        return Colonne.getText();
    }

    // Permet de récupérer le nom des joueurs/IA
    public String getJ1(){
        return T1.getText();
    }

    public String getJ2(){
        return T2.getText();
    }

    // Permet de savoir si Humain, IA facile, etc
    public JRadioButton[] getButtonJ1(){
        return b1;
    }

    public JRadioButton[] getButtonJ2(){
        return b2;
    }

    // Ferme la fenêtre
    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}