package Modele;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestCase {
    Case c, c2;
    int ligne, colonne;
    int ligne2, colonne2;
    final int n = 100;

    void nouvelleCase() {
        Random r = new Random();
        ligne = r.nextInt(Case.TAILLE_MAX + 1);
        colonne = r.nextInt(Case.TAILLE_MAX + 1);
        c = new Case(ligne, colonne);
    }

    @Test
    public void testNouvelleCase() {
        for (int i = 0; i < n; i++) {
            nouvelleCase();
            assertEquals(ligne, c.ligne());
            assertEquals(colonne, c.colonne());
        }
    }

    void exceptionCaseInvalide(int l, int c) {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Case(l, c)
        );
        assertTrue(e.getMessage().contains("Case (" + l + ", " + c + ") invalide"));
    }

    @Test
    public void testExceptionCaseInvalide() {
        for (int i = 1; i < n; i++) {
            exceptionCaseInvalide(-i, 0);
            exceptionCaseInvalide(0, -i);
            exceptionCaseInvalide(-i, -i);

            if (i > Case.TAILLE_MAX) {
                exceptionCaseInvalide(i, Case.TAILLE_MAX);
                exceptionCaseInvalide(Case.TAILLE_MAX, i);
                exceptionCaseInvalide(i, i);
            }
        }
    }

    @Test
    public void testDescriptionArithmetique() {
        long valeur1ereColonne = 1;

        for (int i = 0; i <= Case.TAILLE_MAX; i++) {
            long valeur = valeur1ereColonne;

            for (int j = 0; j <= Case.TAILLE_MAX; j++) {
                c = new Case(i, j);
                assertEquals(valeur, c.descriptionArithmetique());
                valeur += valeur;
            }
            valeur1ereColonne *= 3;
        }
    }

    void deuxNouvellesCases() {
        nouvelleCase();
        ligne2 = ligne;
        colonne2 = colonne;
        c2 = c;
        nouvelleCase();
    }

    @Test
    public void testEstMultiple() {
        for (int i = 0; i < n; i++) {
            deuxNouvellesCases();

            if (ligne >= ligne2 && colonne >= colonne2) {
                assertTrue(c.estMultiple(c2));
            } else {
                assertFalse(c.estMultiple(c2));
            }
        }
    }

    @Test
    public void testEstDiviseur() {
        for (int i = 0; i < n; i++) {
            deuxNouvellesCases();

            if (ligne <= ligne2 && colonne <= colonne2) {
                assertTrue(c.estDiviseur(c2));
            } else {
                assertFalse(c.estDiviseur(c2));
            }
        }
    }

    @Test
    public void testComparaison() {
        for (int i = 0; i < n; i++) {
            deuxNouvellesCases();

            if (ligne == ligne2 && colonne == colonne2) {
                assertEquals(0, c.compareTo(c2));
            } else if (ligne <= ligne2 && colonne <= colonne2) {
                assertEquals(-1, c.compareTo(c2));
            } else {
                assertEquals(1, c.compareTo(c2));
            }
        }
    }

    @Test
    public void testExceptionArgumentNull() {
        for (int i = 0; i < n; i++) {
            nouvelleCase();

            @SuppressWarnings("ResultOfMethodCallIgnored")
            IllegalArgumentException e = assertThrows(
                    IllegalArgumentException.class,
                    () -> c.compareTo(null)
            );
            assertTrue(e.getMessage().contains("Impossible de comparer avec null"));

            e = assertThrows(
                    IllegalArgumentException.class,
                    () -> c.estMultiple(null)
            );
            assertTrue(e.getMessage().contains("Impossible de comparer avec null"));

            e = assertThrows(
                    IllegalArgumentException.class,
                    () -> c.estDiviseur(null)
            );
            assertTrue(e.getMessage().contains("Impossible de comparer avec null"));
        }
    }
}
