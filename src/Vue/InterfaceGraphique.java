package Vue;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable {
    JFrame frame;

    public InterfaceGraphique() {
    }

    public static void demarre() {
        SwingUtilities.invokeLater(new InterfaceGraphique());
    }

    @Override
    public void run() {
        frame = new JFrame("Gaufre empoisonnée (rip)");
        frame.setSize(700, 420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Taille minimum de la fenêtre (pour le redimensionnement)
        frame.setMinimumSize(new Dimension(700,420));

        frame.setVisible(true);


        
        JLabel joueur = new JLabel("Joueur 1 ou Joueur 2 ", SwingConstants.CENTER);
        joueur.setBackground(Color.GRAY);
        joueur.setForeground(Color.BLACK);
        joueur.setOpaque(true);
        frame.add(joueur, BorderLayout.NORTH);

        frame.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("gaufre.jpg")), SwingConstants.CENTER), BorderLayout.WEST);


        // Création des boutons
        JButton start = createButton("Restart");
        JButton previous = createButton("Previous");
        JButton next = createButton("   Next    ");
        JButton exit = createButton("Exit");
        JButton load = createButton("Load game");
        JButton save = createButton("Save game");

        // Panel où il y a les boutons
        JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.GRAY);
        panelButton.setMinimumSize(new Dimension(200,400));
        panelButton.setMaximumSize(new Dimension(400,1000));
		frame.add(panelButton, BorderLayout.EAST);

        //On crée un conteneur avec gestion horizontale
        Box b1 = Box.createHorizontalBox();
        b1.add(start);
        //Idem
        Box b2 = Box.createHorizontalBox();
        b2.add(previous);
        b2.add(next);
        //Idem
        Box b3 = Box.createHorizontalBox();
        b3.add(load);
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


        frame.setVisible(true);
    }

    private JButton createButton(String s){
        JButton button = new JButton(s);
		button.setAlignmentX(Component.TOP_ALIGNMENT);
		button.setPreferredSize(new Dimension(120, 50));
		button.setFocusable(false);
        return button;
    }
    // Bizarre
    
}
