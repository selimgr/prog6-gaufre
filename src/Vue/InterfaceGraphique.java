package Vue;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.*;

import java.awt.*;

import Modele.*;

abstract class InterfaceGraphique implements Runnable {
    Jeu J;

    JButton play;
    JButton load;

    JButton restart;
    JButton previous;
    JButton next;
    JButton save;
    JButton exit;

    JFrame frame;


    public JButton createButton(String s){
        JButton button = new JButton(s);
		button.setAlignmentX(Component.TOP_ALIGNMENT);
		button.setPreferredSize(new Dimension(120, 50));
		button.setFocusable(false);
        return button;
    }

    public JTextField createText(String s){
        JTextField T1 = new JTextField(s, 50);
        T1.getFont().deriveFont(Font.ITALIC);
        T1.setForeground(Color.gray);
        T1.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Si le player clique, alors le texte s'efface
                JTextField texte = ((JTextField)e.getSource());
                texte.setText("");
                texte.getFont().deriveFont(Font.PLAIN);
                texte.setForeground(Color.black);
                texte.removeMouseListener(this);
            }
        });
        return T1;
    }

    // Ferme la fenêtre
    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}