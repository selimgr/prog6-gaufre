package Vue;

import javax.swing.*;
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

    public abstract void demarrer();
    public abstract void fermer();
}