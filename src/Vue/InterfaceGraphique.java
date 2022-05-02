package Vue;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.*;

import java.awt.*;

abstract class InterfaceGraphique {
    JFrame frame;
    Runnable runnable;

    public InterfaceGraphique() {
        runnable = new Runnable() {
            @Override
            public void run() { demarrer(); }
        };
        SwingUtilities.invokeLater(runnable);
    }

    public JButton createButton(String s){
        JButton button = new JButton(s);
		button.setAlignmentX(Component.TOP_ALIGNMENT);
		button.setPreferredSize(new Dimension(120, 50));
		button.setFocusable(false);
        return button;
    }

    public JTextField createText(String s){
        JTextField T1 = new JTextField(s, 150);
        // A mettre dans une fonction
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

    public abstract void demarrer();
    public abstract void fermer();
}
