package Vue;
import java.awt.event.*;

public class GaufreListener extends MouseListener {

    // TODO: Déplacer ça dans un Controller pr respecter la structure MVC

    @Override
    public void mousePressed(MouseEvent e) {
        VueGaufre t = (VueGaufre) e.getComponent();
        int y = ( e.getX() / t.largeurCase);
        int x = ( e.getY() / t.hauteurCase);

        System.out.println(x + "," + y);

        if (t.Controller.jouerUnCoup(x,y)) {
            if (t.G.partieTerminee()) {
                t.IJ.currentPlayer.setText("Partie terminée! Gagnant: " + (t.G.getPlayer(t.G.getPlayer()+1).getPlayerName()));
            } else {
                t.IJ.currentPlayer.setText("Le joueur " + (t.G.getPlayer(t.G.getPlayer()).getPlayerName()) + (t.G.getPlayer(t.G.getPlayer()).isAI() == true ? " (AI)" : "")  + " est entrain de jouer");
            }
        } else {
            t.IJ.currentPlayer.setText("Impossible de jouer le coup (?)");
        }
        t.repaint();

    }
}
