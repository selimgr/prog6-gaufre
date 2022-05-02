package Modele;

import java.util.Random;

public class Etat {
    final static int NB_JOUEURS = 2;
    int nbJoueurs;
    Joueur[] joueurs;
    int indiceActuel;

    Etat() {
        joueurs = new Joueur[NB_JOUEURS];
        Random r = new Random();
        indiceActuel = r.nextInt(NB_JOUEURS);
    }

    void ajouterJoueur(String nom, TypeJoueur type) {
        if (nbJoueurs >= NB_JOUEURS) {
            throw new IllegalStateException("Impossible d'ajouter un nouveau joueur");
        }
        joueurs[nbJoueurs++] = new Joueur(nom, type);
    }

    void nouvellePartie() {
        if (nbJoueurs != NB_JOUEURS) {
            throw new IllegalStateException("Impossible de créer une nouvelle partie : joueurs manquants");
        }
        for (int i = 0; i < nbJoueurs; i++) {
            joueurs[i].nouvellePartie();
        }
    }

    Joueur joueurActuel() {
        return joueurs[indiceActuel];
    }

    Joueur joueurSuivant() {
        return joueurs[(indiceActuel + 1) % NB_JOUEURS];
    }

    private void tourSuivant() {
        indiceActuel = (indiceActuel + 1) % NB_JOUEURS;
    }

    void jouerCoup(int l, int c) {
        joueurActuel().jouerCoup(l, c);

        if (l == 0 && c == 0) {
            joueurSuivant().ajouterVictoire();
        } else {
            tourSuivant();
        }
    }

    boolean annulerCoup() {
        if (joueurSuivant().nombreCoups() == 0) {
            return false;
        }
        tourSuivant();
        joueurActuel().annulerCoup();
        return true;
    }
}
