package Vue;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import Modele.*;

public class InterfaceJeu extends InterfaceGraphique {

    Jeu J;

    private JButton Exit;
    private JButton Next;
    private JButton Previous;
    private JButton Save;
    private JPanel controlsPanel;
    public JLabel currentPlayer;
    private JPanel gaufrePanel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JButton restartBtn;

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
        System.out.println("Taille de la gauffre : (" + J.niveau().lignes() + "," + J.niveau().colonnes() + ")");
        
        jPanel3 = new JPanel();
        gaufrePanel = new JPanel();
        controlsPanel = new JPanel();
        restartBtn = new JButton();
        jPanel1 = new JPanel();
        Previous = new JButton();
        Next = new JButton();
        Save = new JButton();
        Exit = new JButton();
        jPanel2 = new JPanel();
        currentPlayer = new JLabel();

        jPanel3.setLayout(new java.awt.BorderLayout());

        gaufrePanel.setLayout(new java.awt.BorderLayout());
        // Initilisation du Jeu
        VueGaufre VG = new VueGaufre(J, this);
        VG.addMouseListener(new GaufreListener());
        this.gaufrePanel.add(VG);

        jPanel3.add(gaufrePanel, java.awt.BorderLayout.CENTER);

        controlsPanel.setPreferredSize(new java.awt.Dimension(200, 316));
        controlsPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 20));

        restartBtn.setText("Restart");
        controlsPanel.add(restartBtn);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        Previous.setText("<");
        jPanel1.add(Previous);

        Next.setText(">");
        jPanel1.add(Next);

        controlsPanel.add(jPanel1);

        Save.setText("Save");
        controlsPanel.add(Save);

        Exit.setText("Exit");
        // TODO: Utiliser Clicklistener ?
        Exit.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Ferme la fenêtre
                fermer();
            }
        });
        controlsPanel.add(Exit);

        jPanel3.add(controlsPanel, java.awt.BorderLayout.EAST);

        this.frame.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(690, 30));
        jPanel2.setLayout(new java.awt.BorderLayout());

        currentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayer.setText("Le joueur " + (J.nomJoueurActuel() + (J.typeJoueurActuel() != TypeJoueur.HUMAIN ? " (AI)" : "")  + " est entrain de jouer"));
        jPanel2.add(currentPlayer, java.awt.BorderLayout.CENTER);

        this.frame.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        this.frame.setVisible(true);
        
    }
}
