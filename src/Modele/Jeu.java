package Modele;

public class Jeu {
    Niveau n;
    boolean partieCommencee;
    boolean partieTerminee;

    /**
     * Crée une nouvelle partie de taille par défaut
     */
    public void nouvellePartie() {
        nouvellePartie(Niveau.lignesParDefaut, Niveau.colonnesParDefaut);
    }

    /**
     * Crée une nouvelle partie de taille choisie
     * @param l nombre de lignes
     * @param c nombre de colonnes
     * @throws IllegalArgumentException si l < 1 ou c < 1
     */
    public void nouvellePartie(int l, int c) {
        n = new Niveau(l, c);
        partieCommencee = false;
        partieTerminee = false;
    }

    /**
     * @return Le niveau actuel
     */
    public Niveau niveau() {
        return n;
    }

    /**
     * Ajoute une nouvelle ligne
     * @throws IllegalStateException si aucun appel à nouvellePartie n'a été effectué
     */
    public void ajouterLigne() {
        if (n == null) {
            throw new IllegalStateException("Aucun niveau auquel ajouter une ligne");
        }
        if (!partieCommencee) {
            n.ajouterLigne();
        }
    }

    /**
     * Supprime une ligne
     * @throws IllegalStateException si aucun appel à nouvellePartie n'a été effectué
     */
    public void supprimerLigne() {
        if (n == null) {
            throw new IllegalStateException("Aucun niveau auquel supprimer une ligne");
        }
        if (!partieCommencee && n.lignes() > 1) {
            n.supprimerLigne();
        }
    }

    /**
     * Ajoute une nouvelle colonne
     * @throws IllegalStateException si aucun appel à nouvellePartie n'a été effectué
     */
    public void ajouterColonne() {
        if (n == null) {
            throw new IllegalStateException("Aucun niveau auquel ajouter une colonne");
        }
        if (!partieCommencee) {
            n.ajouterColonne();
        }
    }

    /**
     * Supprime une colonne
     * @throws IllegalStateException si aucun appel à nouvellePartie n'a été effectué
     */
    public void supprimerColonne() {
        if (n == null) {
            throw new IllegalStateException("Aucun niveau auquel supprimer une colonne");
        }
        if (!partieCommencee && n.colonnes() > 1) {
            n.supprimerColonne();
        }
    }

    /**
     * Joue un coup
     * @param l indice de la ligne du coup à jouer
     * @param c indice de la colonne du coup à jouer
     * @return true si un coup a été joué et false sinon
     * @throws IllegalStateException si aucun appel à nouvellePartie n'a été effectué
     * @throws IndexOutOfBoundsException si l ou c est en dehors des dimensions du niveau
     */
    public boolean coup(int l, int c) {
        if (n == null) {
            throw new IllegalStateException("Aucun niveau auquel jouer");
        }
        partieCommencee = true;

        if (!n.coup(l, c)) {
            return false;
        }

        if (n.estTermine()) {
            partieTerminee = true;
        } else {
            joueurSuivant();
        }
        return true;
    }

    /**
     * @return true si la partie est terminée et false sinon
     */
    public boolean partieTerminee() {
        return partieTerminee;
    }
    
    void joueurSuivant() {

    }
}
