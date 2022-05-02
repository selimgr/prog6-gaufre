package Modele;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestCase {
    Case c, c2;
    int ligne, colonne;
    int ligne2, colonne2;
    final int n = 100;

    void nouvelleCase(int lig, int col) {
        c = new Case(lig, col);
        ligne = lig;
        colonne = col;
    }

    void nouvelleCaseAleatoire() {
        Random r = new Random();
        ligne = r.nextInt(Case.TAILLE_MAX + 1);
        colonne = r.nextInt(Case.TAILLE_MAX + 1);
        c = new Case(ligne, colonne);
    }

    void deuxNouvellesCases(int lig1, int col1, int lig2, int col2) {
        nouvelleCase(lig2, col2);
        ligne2 = ligne;
        colonne2 = colonne;
        c2 = c;
        nouvelleCase(lig1, col1);
    }

    void deuxNouvellesCasesAleatoires() {
        nouvelleCaseAleatoire();
        ligne2 = ligne;
        colonne2 = colonne;
        c2 = c;
        nouvelleCaseAleatoire();
    }

    @Test
    public void testNouvelleCase() {
        for (int i = 0; i <= Case.TAILLE_MAX; i++) {
            for (int j = 0; j <= Case.TAILLE_MAX; j++) {
                c = new Case(i, j);
                assertEquals(i, c.ligne());
                assertEquals(j, c.colonne());
            }
        }
    }

    @Test
    public void testNouvelleCaseAleatoire() {
        for (int i = 0; i < n; i++) {
            nouvelleCaseAleatoire();
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
    public void testDescriptionArithmetiqueSimple() {
        c = new Case(3, 5);
        assertEquals(864, c.descriptionArithmetique());
        c = new Case(7, 12);
        assertEquals(8_957_952, c.descriptionArithmetique());
        c = new Case(2, 9);
        assertEquals(4_608, c.descriptionArithmetique());
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

    @Test
    public void testEstMultipleSimple() {
        deuxNouvellesCases(3, 5, 7, 12);
        assertFalse(c.estMultiple(c2));
        assertTrue(c2.estMultiple(c));

        deuxNouvellesCases(3, 5, 2, 9);
        assertFalse(c.estMultiple(c2));
        assertFalse(c2.estMultiple(c));

        deuxNouvellesCases(2, 9, 2, 9);
        assertTrue(c.estMultiple(c2));
        assertTrue(c2.estMultiple(c));
    }

    @Test
    public void testEstMultipleAleatoire() {
        for (int i = 0; i < n; i++) {
            deuxNouvellesCasesAleatoires();

            if (ligne >= ligne2 && colonne >= colonne2) {
                assertTrue(c.estMultiple(c2));
            } else {
                assertFalse(c.estMultiple(c2));
            }
        }
    }

    @Test
    public void testEstDiviseurSimple() {
        deuxNouvellesCases(3, 5, 7, 12);
        assertTrue(c.estDiviseur(c2));
        assertFalse(c2.estDiviseur(c));

        deuxNouvellesCases(3, 5, 2, 9);
        assertFalse(c.estDiviseur(c2));
        assertFalse(c2.estDiviseur(c));

        deuxNouvellesCases(2, 9, 2, 9);
        assertTrue(c.estDiviseur(c2));
        assertTrue(c2.estDiviseur(c));
    }

    @Test
    public void testEstDiviseurAleatoire() {
        for (int i = 0; i < n; i++) {
            deuxNouvellesCasesAleatoires();

            if (ligne <= ligne2 && colonne <= colonne2) {
                assertTrue(c.estDiviseur(c2));
            } else {
                assertFalse(c.estDiviseur(c2));
            }
        }
    }

    @Test
    public void testComparaisonSimple() {
        deuxNouvellesCases(3, 5, 7, 12);
        assertEquals(-1, c.compareTo(c2));
        assertEquals(1, c2.compareTo(c));

        deuxNouvellesCases(3, 5, 2, 9);
        assertEquals(1, c.compareTo(c2));
        assertEquals(1, c2.compareTo(c));

        deuxNouvellesCases(2, 9, 2, 9);
        assertEquals(0, c.compareTo(c2));
        assertEquals(0, c2.compareTo(c));
    }

    @Test
    public void testComparaisonAleatoire() {
        int branches = 0;

        for (int i = 0; i < n || branches != 7; i++) {
            deuxNouvellesCasesAleatoires();

            if (ligne == ligne2 && colonne == colonne2) {
                assertEquals(0, c.compareTo(c2));
                branches |= 1;
            } else if (ligne <= ligne2 && colonne <= colonne2) {
                assertEquals(-1, c.compareTo(c2));
                branches |= 2;
            } else {
                assertEquals(1, c.compareTo(c2));
                branches |= 4;
            }
        }
    }

    @Test
    public void testExceptionArgumentNull() {
        for (int i = 0; i < n; i++) {
            nouvelleCaseAleatoire();

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

    @Test
    public void testToString() {
        nouvelleCaseAleatoire();
        assertEquals("(" + ligne + ", " + colonne + ")", c.toString());
    }
}
