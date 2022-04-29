package Modele;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestNiveau {
    Niveau niveau;
    final int lignes = 8;
    final int colonnes = 6;
    final int n = 100;

    @Before
    public void init() {
        niveau = new Niveau(lignes, colonnes);
    }

    @Test
    public void testInit() {
        assertEquals(niveau.lignes(), lignes);
        assertEquals(niveau.colonnes(), colonnes);

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                assertTrue(niveau.aMorceau(i, j));
            }
        }
    }

    @Test
    public void testExceptionInit() {
        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> new Niveau(0, 1)
        );

        assertTrue(e.getMessage().contains("La taille du niveau doit être positive"));

        e = assertThrows(
                RuntimeException.class,
                () -> new Niveau(1, 0)
        );

        assertTrue(e.getMessage().contains("La taille du niveau doit être positive"));
    }

    @Test
    public void testModificationTaille() {
        int i;

        for (i = 0; i < n; i++) {
            niveau.ajouterLigne();
            assertEquals(niveau.lignes(), lignes+1);
            niveau.ajouterColonne();
            assertEquals(niveau.colonnes(), colonnes+1);
            niveau.supprimerLigne();
            assertEquals(niveau.lignes(), lignes);
            niveau.supprimerColonne();
            assertEquals(niveau.colonnes(), colonnes);
        }

        for (i = 0; i < n; i++) {
            niveau.ajouterLigne();
            assertEquals(niveau.lignes(), lignes+i+1);
            niveau.ajouterColonne();
            assertEquals(niveau.colonnes(), colonnes+i+1);
        }

        for (i = 0; i < niveau.lignes(); i++) {
            for (int j = 0; j < niveau.colonnes(); j++) {
                assertTrue(niveau.aMorceau(i, j));
            }
        }

        for (i = n; i > 0; i--) {
            niveau.supprimerLigne();
            assertEquals(niveau.lignes(), lignes+i-1);
            niveau.supprimerColonne();
            assertEquals(niveau.colonnes(), colonnes+i-1);
        }
    }

    @Test
    public void testExceptionsSupprimer() {
        niveau = new Niveau(1, 1);

        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> niveau.supprimerLigne()
        );

        assertTrue(e.getMessage().contains("Impossible de supprimer la dernière ligne"));

        e = assertThrows(
                RuntimeException.class,
                () -> niveau.supprimerColonne()
        );

        assertTrue(e.getMessage().contains("Impossible de supprimer la dernière colonne"));
    }

    @Test
    public void testExceptionCaseInvalide() {
        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> niveau.aMorceau(-1, 0)
        );

        assertTrue(e.getMessage().contains("Case (" + -1 + ", " + 0 + ") invalide"));

        e = assertThrows(
                RuntimeException.class,
                () -> niveau.aMorceau(0, -1)
        );

        assertTrue(e.getMessage().contains("Case (" + 0 + ", " + -1 + ") invalide"));

        e = assertThrows(
                RuntimeException.class,
                () -> niveau.aMorceau(lignes, colonnes-1)
        );

        assertTrue(e.getMessage().contains("Case (" + lignes + ", " + (colonnes-1) + ") invalide"));

        e = assertThrows(
                RuntimeException.class,
                () -> niveau.aMorceau(lignes-1, colonnes)
        );

        assertTrue(e.getMessage().contains("Case (" + (lignes-1) + ", " + colonnes + ") invalide"));
    }

    @Test
    public void testCoup() {
        int l, c;
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            l = r.nextInt(lignes);
            c = r.nextInt(colonnes);

            niveau.coup(l, c);

            for (int j = 0; j < lignes; j++) {
                for (int k = 0; k < colonnes; k++) {
                    if (j < l || k < c) {
                        assertTrue(niveau.aMorceau(j, k));
                    } else {
                        assertFalse(niveau.aMorceau(j, k));
                    }
                }
            }

            niveau = new Niveau(lignes, colonnes);
        }
    }

    @Test
    public void testCoupPerdant() {
        assertFalse(niveau.estTermine());
        niveau.coup(0, 0);
        assertTrue(niveau.estTermine());
    }

    @Test
    public void testSequenceCoup() {
        for (int i = 0; i < n; i++) {
            sequenceCoup();
        }
    }

    void sequenceCoup() {
        Random r = new Random();

        int[] tab = new int[lignes];

        for (int i = 0; i < lignes; i++) {
            tab[i] = Integer.MAX_VALUE;
        }

        while (!niveau.estTermine()) {
            int l = r.nextInt(lignes);
            int c = r.nextInt(colonnes);

            if (!niveau.aMorceau(l, c)) {
                continue;
            }
            niveau.coup(l, c);

            if (c < tab[l]) {
                tab[l] = c;
            }
            verifierCoup(l, c, tab);
        }
    }

    void verifierCoup(int l, int c, int[] tab) {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                for (int k = l; k >= 0; k--) {
                    if (tab[l] <= c) {
                        assertFalse(niveau.aMorceau(l, c));
                        return;
                    }
                }
                assertTrue(niveau.aMorceau(l, c));
            }
        }
    }
}
