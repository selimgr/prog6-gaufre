package Vue;
import Modele.TypeJoueur;

import java.awt.event.*;

public class GaufreListener extends MouseListener {

    // TODO: Déplacer ça dans un Controller pr respecter la structure MVC

    @Override
    public void mousePressed(MouseEvent e) {
        VueGaufre t = (VueGaufre) e.getComponent();
        int y = ( e.getX() / t.largeurCase);
        int x = ( e.getY() / t.hauteurCase);

        // System.out.println(x + "," + y);
        if (t.G.jouerCoup(x, y)) {
            if (t.G.partieTerminee()) {
                t.IJ.currentPlayer.setText("Partie terminée! Gagnant: " + (t.G.nomJoueurSuivant()));
            } else {
                t.IJ.currentPlayer.setText("Le joueur " + (t.G.nomJoueurActuel()) + (t.G.typeJoueurActuel() != TypeJoueur.HUMAIN ? " (AI)" : "")  + " est entrain de jouer");
            }
        } else {
            t.IJ.currentPlayer.setText("Impossible de jouer le coup (?)");
        }
        t.repaint();
    }
}
