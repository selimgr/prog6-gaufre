package Modele;

public class Case implements Comparable<Case> {
    int ligne, colonne;
    long descriptionArithmetique;
    final static int TAILLE_MAX = 24;

    /**
     * Crée une nouvelle case
     * @param l indice de la ligne de la case
     * @param c indice de la colonne de la case
     * @throws IllegalArgumentException si l ou c est négatif ou supérieur à Case.TAILLE_MAX
     */
    public Case(int l, int c) {
        if (l < 0 || c < 0 || l > Case.TAILLE_MAX || c > Case.TAILLE_MAX) {
            throw new IllegalArgumentException("Case (" + l + ", " + c + ") invalide");
        }
        ligne = l;
        colonne = c;
    }

    /**
     * @return la ligne de la case
     */
    public int ligne() {
        return ligne;
    }

    /**
     * @return la colonne de la case
     */
    public int colonne() {
        return colonne;
    }

    /**
     * Calcule la description arithmétique de la case
     * @return un entier long correspondant à 2^(la colonne de la case) * 3^(la ligne de la case)
     */
    public long descriptionArithmetique() {
        if (descriptionArithmetique == 0) {
            descriptionArithmetique = (1L << colonne) * (long) Math.pow(3, ligne);
        }
        return descriptionArithmetique;
    }

    /**
     * Vérifie si cette case est un mutliple d'une autre case
     * @param c la case avec laquelle comparer
     * @return true si cette case est un multiple de la case c et false sinon
     * @throws IllegalArgumentException si c est null
     */
    public boolean estMultiple(Case c) {
        if (c == null) {
            throw new IllegalArgumentException("Impossible de comparer avec null");
        }
        return c.compareTo(this) <= 0;
    }

    /**
     * Vérifie si cette case est un diviseur d'une autre case
     * @param c la case avec laquelle comparer
     * @return true si cette case est un diviseur de la case c et false sinon
     * @throws IllegalArgumentException si c est null
     */
    public boolean estDiviseur(Case c) {
        return compareTo(c) <= 0;
    }

    /**
     * Compare 2 deux cases
     * @param c la case à comparer.
     * @return 0 si les cases sont identiques, -1 si cette case est un diviseur de la case c et 1 sinon
     * @throws IllegalArgumentException si c est null
     */
    @Override
    public int compareTo(Case c) {
        if (c == null) {
            throw new IllegalArgumentException("Impossible de comparer avec null");
        }
        if (descriptionArithmetique() == c.descriptionArithmetique()) {
            return 0;
        }
        if (c.descriptionArithmetique() % descriptionArithmetique() == 0) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "(" + ligne + ", " + colonne + ")";
    }
}
