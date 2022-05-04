package Controller;

import Modele.Jeu;
import Modele.Pair;
import Vue.VueGaufre;
import Modele.Joueur;

public class Jouer {
    Jeu J;
    VueGaufre VG;

    public Jouer(Jeu J, VueGaufre VG){
        this.J = J;
        this.VG = VG;
    }

    public boolean jouerUnCoup(int x, int y) {
        System.out.println("V10 = "+x+"  V2 = "+y);

        if (J.getPlayer(J.getPlayer()).isAI() && J.estPremier()) {
            Pair<Integer,Integer> p = J.getIa1().joue(J.n, J.estPremier());
            return J.coup(p.getV1(),p.getV2());
        }
        else if (J.getPlayer(J.getPlayer()).isAI() && !J.estPremier()) {
            Pair<Integer,Integer> p2 = J.getIa1().joue(J.n, J.estPremier());
            System.out.println("V12 = "+p2.getV1()+"  V2 = "+p2.getV2());
            return J.coup(p2.getV1(),p2.getV2());
        }
        if (!J.getPlayer(J.getPlayer()).isAI()) {
            return J.coup(x,y);
        }
        return false;
    }
}
