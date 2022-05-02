package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Niveau {
    int lignes, colonnes;
    List<Integer> contenu;
    final static int lignesParDefaut = 8;
    final static int colonnesParDefaut = 6;

    Niveau(int l, int c) {
        if (l < 0 || c < 0) {
            throw new IllegalArgumentException("La taille du niveau doit être positive");
        }
        lignes = l;
        colonnes = c;
        contenu = new ArrayList<>(lignes);

        for (int i = 0; i < lignes; i++) {
            contenu.add(colonnes);
        }
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
        if (lignes == 0) {
            throw new NoSuchElementException("Aucune ligne à supprimer");
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
        if (colonnes == 0) {
            throw new NoSuchElementException("Aucune colonne à supprimer");
        }
        fixerColonnes(colonnes - 1);
    }

    boolean aMorceau(int l, int c) {
        if (l < 0 || c < 0 || l >= lignes || c >= colonnes) {
            throw new IndexOutOfBoundsException("Case (" + l + ", " + c + ") invalide");
        }
        return l < contenu.size() && c < contenu.get(l);
    }

    boolean coup(int l, int c) {
        if (!aMorceau(l, c)) {
            return false;
        }

        while (lignes > l + 1) {
            supprimerLigne();
        }
        if (c == 0) {
            supprimerLigne();
        } else {
            contenu.set(l, c);
        }
        return true;
    }
}
