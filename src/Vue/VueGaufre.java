package Vue;

import javax.swing.*;
import java.awt.*;

import Modele.*;

public class VueGaufre extends JComponent {
    Niveau N;
    Jeu G;
    InterfaceJeu IJ;
    public int largeurCase, hauteurCase;
    
    public VueGaufre(Jeu J, InterfaceJeu JI){
        N = J.niveau();
        G = J;
        IJ = JI;
    }

    @Override
    public void paintComponent(Graphics g) {
        largeurCase = (this.getWidth())/this.N.colonnes();
        hauteurCase = (this.getHeight())/this.N.lignes();

		largeurCase = Math.min(largeurCase, hauteurCase);	

        for (int l = 0; l < N.getContenu().size(); l++) {
            for (int c = 0; c < N.getContenu().get(l); c++) {
                int x = c * largeurCase;
				int y = l * hauteurCase;

                g.setColor(Color.ORANGE);
                g.fillRect(x, y, largeurCase, hauteurCase);
                g.setColor(new Color(160,90,50));
                g.drawRect(x, y, largeurCase, hauteurCase);

                if (l == 0 && c == 0) {
                    g.setColor(new Color(10, 70, 10));
                    g.fillOval(x+5, y+5, largeurCase-10, hauteurCase-10);
                }
            }
        }
    }


}
