package Vue;

import java.awt.event.*;
//import javax.swing.*;

public class ClickListener implements ActionListener{
    InterfaceGraphique Graphique;

    public ClickListener(InterfaceGraphique G){
        Graphique=G;
    }

    @Override
    public void actionPerformed(ActionEvent e) {   
        InterfaceMenu M = ((InterfaceMenu) Graphique);
        if(e.getSource() == Graphique.play){
            if(Integer.parseInt(M.getLigne())>30 || Integer.parseInt(M.getColonne())>50){
                M.Ligne.setText("");
                M.Colonne.setText("");
                // TODO : Ajouter une fenêtre d'erreur.
            }
            else{
                // Passe à l'interface Jeu
                int l=Integer.parseInt(M.getLigne());
                int c=Integer.parseInt(M.getColonne());
                M.J.nouvellePartie(l,c);
                M.fermer();
                InterfaceJeu.demarrer(M.J);
            }
        }else if(e.getSource() == Graphique.exit){
            M.fermer();
        }else if(e.getSource() == Graphique.load){
            // TODO
        }// TODO : Buttons Jeu
        else {
            //Demander à interface de jouer coup après avoir récupérer les coordonnées de la souris.
        }
    }
    
}
