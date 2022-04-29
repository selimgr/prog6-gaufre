package Vue;

import javax.swing.*;

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

    public abstract void demarrer();
    public abstract void fermer();
}
