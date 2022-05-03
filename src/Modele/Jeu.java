package Modele;

public class Jeu {
    Niveau n;
    Etat q;
    boolean partieCommencee;
    boolean partieTerminee;

    Jeu() {
        q = new Etat();
    }

    public void nouveauJoueur(String nom, TypeJoueur type) {
        q.ajouterJoueur(nom, type);
    }

    /**
     * Crée une nouvelle partie de taille par défaut
     */
    public void nouvellePartie() {
        nouvellePartie(Niveau.LIGNES_PAR_DEFAUT, Niveau.COLONNES_PAR_DEFAUT);
    }

    /**
     * Crée une nouvelle partie de taille choisie
     * @param l nombre de lignes
     * @param c nombre de colonnes
     * @throws IllegalArgumentException si l ou c inférieur à 1 ou supérieur à Niveau.TAILLE_MAX
     * @throws IllegalStateException si le nombre de joueurs est inférieur au nombre de joueurs attendu
     */
    public void nouvellePartie(int l, int c) {
        q.nouvellePartie();
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
    public boolean jouerCoup(int l, int c) {
        if (n == null) {
            throw new IllegalStateException("Aucun niveau auquel jouer");
        }
        partieCommencee = true;

        if (!n.jouerCoup(l, c)) {
            return false;
        }
        q.jouerCoup(l, c);

        if (n.estTermine()) {
            partieTerminee = true;
        }
        return true;
    }

    /**
     * @return true si la partie est terminée et false sinon
     */
    public boolean partieTerminee() {
        return partieTerminee;
    }

    public String nomJoueurActuel() {
        return q.joueurActuel().nom();
    }

    public String nomJoueurSuivant() {
        return q.joueurSuivant().nom();
    }

    public TypeJoueur typeJoueurActuel() {
        return q.joueurActuel().type();
    }

    public TypeJoueur typeJoueurSuivant() {
        return q.joueurSuivant().type();
    }

    public int nombreCoupsJoueurActuel() {
        return q.joueurActuel().nombreCoups();
    }

    public int nombreCoupsJoueurSuivant() {
        return q.joueurSuivant().nombreCoups();
    }

    public int nombreVictoiresJoueurActuel() {
        return q.joueurActuel().nombreVictoires();
    }

    public int nombreVictoiresJoueurSuivant() {
        return q.joueurSuivant().nombreVictoires();
    }
}
