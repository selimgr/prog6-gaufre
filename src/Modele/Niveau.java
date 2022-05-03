package Modele;

import java.util.ArrayList;
import java.util.List;

public class Niveau {
    int lignes, colonnes;
    private List<Integer> contenu;
    final static int LIGNES_PAR_DEFAUT = 8;
    final static int COLONNES_PAR_DEFAUT = 6;
    final static int TAILLE_MAX = Case.TAILLE_MAX + 1;

    Niveau(int l, int c) {
        if (l < 1 || c < 1 || l > TAILLE_MAX || c > TAILLE_MAX) {
            throw new IllegalArgumentException("Impossible de créer le niveau : dimensions incorrectes");
        }
        lignes = l;
        colonnes = c;
        contenu = new ArrayList<>(lignes);

        for (int i = 0; i < lignes; i++) {
            contenu.add(colonnes);
        }
    }

    public Niveau (Niveau n){
        this.lignes = n.lignes();
        this.colonnes = n.colonnes();
        this.contenu = n.getContenu();
    }

    /**
     * @return Le nombre de lignes du niveau
     */
    public int lignes() {
        return lignes;
    }

    /**
     * @return Le nombre de colonnes du niveau
     */
    public int colonnes() {
        return colonnes;
    }

    void ajouterLigne() {
        contenu.add(colonnes);
        lignes++;
    }

    void supprimerLigne() {
        if (lignes == 1) {
            throw new IllegalStateException("Impossible de supprimer la dernière ligne");
        }
        contenu.remove(contenu.size() - 1);
        lignes--;
    }
    
    private void fixerColonnes(int c) {
        colonnes = c;

        for (int i = 0; i < lignes; i++) {
            contenu.set(i, colonnes);
        }
    }

    void ajouterColonne() {
        fixerColonnes(colonnes + 1);
    }

    void supprimerColonne() {
        if (colonnes == 1) {
            throw new IllegalStateException("Impossible de supprimer la dernière colonne");
        }
        fixerColonnes(colonnes - 1);
    }

    /**
     * Vérifie si une case contient un morceau de gaufre
     * @param l ligne de la case
     * @param c colonne de la case
     * @return true si la case (l, c) a un morceau et false si elle est vide
     * @throws IndexOutOfBoundsException si l ou c est en dehors des dimensions du niveau
     */
    public boolean aMorceau(int l, int c) {
        if (l < 0 || c < 0 || l >= lignes || c >= colonnes) {
            throw new IndexOutOfBoundsException("Case (" + l + ", " + c + ") invalide");
        }
        return l < contenu.size() && c < contenu.get(l);
    }

    public boolean jouerCoup(int l, int c) {
        if (!aMorceau(l, c)) {
            return false;
        }

        if (c == 0) {
            while (contenu.size() > l) {
                contenu.remove(contenu.size() - 1);
            }
        } else {
            while (l < contenu.size()) {
                if (c < contenu.get(l)) {
                    contenu.set(l, c);
                }
                l++;
            }
        }
        return true;
    }

    boolean estTermine() {
        return contenu.isEmpty();
    }

    public List<Integer> getContenu() {
        return contenu;
    }
    @Override
    public String toString() {
        String s = "";
        int t = contenu.size();
        for (int k=0; k<t;k++) {
            s += contenu.get(k) + " ";
        }
        return s;
    }
}

