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

    public JButton createButton(String s) {
        JButton button = new JButton(s);
		button.setAlignmentX(Component.TOP_ALIGNMENT);
		button.setPreferredSize(new Dimension(120, 50));
		button.setFocusable(false);
        return button;
    }

    public JTextField createText(String s) {
        JTextField T1 = new JTextField(s, 50);
        T1.getFont().deriveFont(Font.ITALIC);
        T1.setForeground(Color.gray);
        T1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (T1.getText().equals(s)) {
                    T1.setText("");
                    T1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (T1.getText().isEmpty()) {
                    T1.setForeground(Color.GRAY);
                    T1.setText(s);
                }
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