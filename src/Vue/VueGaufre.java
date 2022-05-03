package Vue;

import javax.swing.*;
import java.awt.Graphics;

import Modele.*;

public class VueGaufre extends JComponent {
    Niveau N;
    
    public VueGaufre(Jeu J){
        N = J.niveau();
    }


    @Override
    public void paintComponent(Graphics g) {
        int x, y;
        int w=10, h=10;

        for(int i=0; i<N.lignes(); i++){
            x=1;
            y=(i*w)+1;
            for(int j=0; j<N.colonnes(); j++){
                g.drawRect(x, y, w, h);
                x+=w;
            }
        }
    }

    //Faire une fonction pour modifier affichage de la gaufre



}
