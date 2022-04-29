package Vue;

import javax.swing.*;

public class InterfaceJeu extends InterfaceGraphique {
    
    public void demarrer() {
        this.frame = new JFrame("jeu");
        this.frame.setSize(400, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        
        Box box = Box.createVerticalBox();
        box.add(new JLabel("jeuuuuuuuuu"));

        this.frame.add(box);
    }

    public void fermer() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}
