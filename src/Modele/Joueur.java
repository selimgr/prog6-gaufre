package Modele;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Joueur {
    final String nom;
    final TypeJoueur type;
    LinkedList<Case> coups;
    int nbVictoires;

    Joueur(String nom, TypeJoueur t) {
        this.nom = nom;
        type = t;
    }

    String nom() {
        return nom;
    }

    TypeJoueur type() {
        return type;
    }

    void nouvellePartie() {
        coups = new LinkedList<>();
    }

    private void verifierPartieCommencee(String messageEchec) {
        if (coups == null) {
            throw new IllegalStateException(messageEchec + " : partie non commencée");
        }
    }

    void jouerCoup(int l, int c) {
        verifierPartieCommencee("Impossible de jouer le coup");
        coups.addFirst(new Case(l, c));
    }

    void annulerCoup() {
        verifierPartieCommencee("Impossible d'annuler un coup");
        if (coups.size() == 0) {
            throw new NoSuchElementException("Impossible d'annuler un coup : aucun coup joué");
        }
        coups.removeFirst();
    }

    int nombreCoups() {
        verifierPartieCommencee("Impossible d'obtenir le nombre de coups joués");
        return coups.size();
    }

    void ajouterVictoire() {
        nbVictoires++;
    }

    int nombreVictoires() {
        return nbVictoires;
    }
}
