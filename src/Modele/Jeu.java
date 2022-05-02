package Modele;

public class Jeu {
    Niveau n;
    boolean partieCommencee;

    /**
     * Crée une nouvelle partie de taille par défaut
     */
    public void nouvellePartie() {
        nouvellePartie(Niveau.lignesParDefaut, Niveau.colonnesParDefaut);
    }

    /**
     * Crée une nouvelle partie de taille choisie
     * @param l : nombre de lignes
     * @param c : nombre de colonnes
     */
    public void nouvellePartie(int l, int c) {
        n = new Niveau(l, c);
        partieCommencee = false;
    }

    /**
     * @return Le niveau actuel
     */
    public Niveau niveau() {
        return n;
    }

    /**
     * Ajoute une nouvelle ligne
     */
    public void ajouterLigne() {
        if (!partieCommencee) {
            n.ajouterLigne();
        }
    }

    /**
     * Supprime une ligne
     */
    public void supprimerLigne() {
        if (!partieCommencee) {
            n.supprimerLigne();
        }
    }

    /**
     * Ajoute une nouvelle colonne
     */
    public void ajouterColonne() {
        if (!partieCommencee) {
            n.ajouterColonne();
        }
    }

    /**
     * Supprime une colonne
     */
    public void supprimerColonne() {
        if (!partieCommencee) {
            n.supprimerColonne();
        }
    }

    /**
     * Joue un coup
     * @param l : indice de la ligne du coup à jouer
     * @param c : indice de la colonne du coup à jouer
     */
    public void coup(int l, int c) {
        partieCommencee = true;

        if (!n.coup(l, c)) {
            return;
        }

        if (l == 0 && c == 0) {
            partieTermine();
        } else {
            joueurSuivant();
        }
    }

    void partieTermine() {

    }

    void joueurSuivant() {

    }
}
