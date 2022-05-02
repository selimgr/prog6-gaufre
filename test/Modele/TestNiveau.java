package Modele;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestNiveau {
    Niveau niveau;
    int lignes, colonnes;
    final int n = 200;
    final int tailleMax = 1000;

    void nouveauNiveau(int i) {
        if (i < 100) {
            int unite = i % 10;
            if (unite == 0) {
                lignes++;
            }
            colonnes = unite + 1;
        } else {
            Random r = new Random();
            lignes = r.nextInt(tailleMax) + 1;
            colonnes = r.nextInt(tailleMax) + 1;
        }
        niveau = new Niveau(lignes, colonnes);
    }

    void verifierNiveauPlein() {
        for (int i = 0; i < niveau.lignes(); i++) {
            for (int j = 0; j < niveau.colonnes(); j++) {
                assertTrue(niveau.aMorceau(i, j));
            }
        }
    }

    @Test
    public void testNouveauNiveau() {
        for (int i = 0; i < n; i++) {
            nouveauNiveau(i);
            assertEquals(lignes, niveau.lignes());
            assertEquals(colonnes, niveau.colonnes());
            verifierNiveauPlein();
        }
    }

    void exceptionNouveauNiveau(int l, int c) {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Niveau(l, c)
        );
        assertTrue(e.getMessage().contains("La taille du niveau doit être positive"));
    }

    @Test
    public void testExceptionNouveauNiveau() {
        for (int i = 0; i < n; i++) {
            exceptionNouveauNiveau(-i, 1);
            exceptionNouveauNiveau(1, -i);
            exceptionNouveauNiveau(-i, -i);
        }
    }

    void ajouterLigneEtColonne(int l, int c) {
        niveau.ajouterLigne();
        niveau.ajouterColonne();
        assertEquals(l, niveau.lignes());
        assertEquals(c, niveau.colonnes());
    }

    void supprimerLigneEtColonne(int l, int c) {
        niveau.supprimerLigne();
        niveau.supprimerColonne();
        assertEquals(l, niveau.lignes());
        assertEquals(c, niveau.colonnes());
    }

    @Test
    public void testAjouterSupprimer() {
        for (int i = 0; i < n; i++) {
            nouveauNiveau(i);

            for (int j = 0; j < n; j++) {
                ajouterLigneEtColonne(lignes + 1, colonnes + 1);
                supprimerLigneEtColonne(lignes, colonnes);

                if (lignes == 1) {
                    if (colonnes != 1) {
                        niveau.supprimerColonne();
                        assertEquals(colonnes - 1, niveau.colonnes());
                        niveau.ajouterColonne();
                        assertEquals(colonnes, niveau.colonnes());
                    }
                } else if (colonnes == 1) {
                    niveau.supprimerLigne();
                    assertEquals(lignes - 1, niveau.lignes());
                    niveau.ajouterLigne();
                    assertEquals(lignes, niveau.lignes());
                } else {
                    supprimerLigneEtColonne(lignes - 1, colonnes - 1);
                    ajouterLigneEtColonne(lignes, colonnes);
                }
            }
            verifierNiveauPlein();
        }
    }

    @Test
    public void testModificationsTaille() {
        int i, j;

        for (i = 0; i < n; i++) {
            nouveauNiveau(i);

            for (j = 0; j < n; j++) {
                ajouterLigneEtColonne(lignes + j + 1, colonnes + j + 1);
            }
            assertEquals(lignes + n, niveau.lignes());
            assertEquals(colonnes + n, niveau.colonnes());
            verifierNiveauPlein();

            for (j = n; j > 0; j--) {
                supprimerLigneEtColonne(lignes + j - 1, colonnes + j - 1);
            }
            assertEquals(lignes, niveau.lignes());
            assertEquals(colonnes, niveau.colonnes());
            verifierNiveauPlein();

            if (lignes < colonnes) {
                for (j = 1; j < lignes; j++) {
                    supprimerLigneEtColonne(lignes - j, colonnes - j);
                }
                for (j = 1; j < colonnes - lignes + 1; j++) {
                    niveau.supprimerColonne();
                    assertEquals(colonnes - lignes + 1 - j, niveau.colonnes());
                }
            } else {
                for (j = 1; j < colonnes; j++) {
                    supprimerLigneEtColonne(lignes - j, colonnes - j);
                }
                for (j = 1; j < lignes - colonnes + 1; j++) {
                    niveau.supprimerLigne();
                    assertEquals(lignes - colonnes + 1 - j, niveau.lignes());
                }
            }
            assertEquals(1, niveau.lignes());
            assertEquals(1, niveau.colonnes());
            verifierNiveauPlein();

            if (lignes < colonnes) {
                for (j = 1; j < lignes; j++) {
                    ajouterLigneEtColonne(j + 1, j + 1);
                }
                for (j = 1; j < colonnes - lignes + 1; j++) {
                    niveau.ajouterColonne();
                    assertEquals(lignes + j, niveau.colonnes());
                }
            } else {
                for (j = 1; j < colonnes; j++) {
                    ajouterLigneEtColonne(j + 1, j + 1);
                }
                for (j = 1; j < lignes - colonnes + 1; j++) {
                    niveau.ajouterLigne();
                    assertEquals(colonnes + j, niveau.lignes());
                }
            }
            assertEquals(lignes, niveau.lignes());
            assertEquals(colonnes, niveau.colonnes());
            verifierNiveauPlein();
        }
    }

    void exceptionSupprimerLigne() {
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> niveau.supprimerLigne()
        );
        assertTrue(e.getMessage().contains("Impossible de supprimer la dernière ligne"));
    }

    void exceptionSupprimerColonne() {
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> niveau.supprimerColonne()
        );
        assertTrue(e.getMessage().contains("Impossible de supprimer la dernière colonne"));
    }

    @Test
    public void testExceptionsSupprimer() {
        for (int i = 1; i < n; i++) {
            niveau = new Niveau(1, i);
            exceptionSupprimerLigne();
            niveau = new Niveau(i, 1);
            exceptionSupprimerColonne();
        }
    }

    void exceptionCaseInvalide(int l, int c) {
        IndexOutOfBoundsException e = assertThrows(
                IndexOutOfBoundsException.class,
                () -> niveau.aMorceau(l, c)
        );
        assertTrue(e.getMessage().contains("Case (" + l + ", " + c + ") invalide"));
    }

    @Test
    public void testExceptionCaseInvalide() {
        for (int i = 0; i < n; i++) {
            nouveauNiveau(i);
            exceptionCaseInvalide(-1, 0);
            exceptionCaseInvalide(0, -1);
            exceptionCaseInvalide(lignes, colonnes - 1);
            exceptionCaseInvalide(lignes - 1, colonnes);
        }
    }

    @Test
    public void testCoup() {
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            nouveauNiveau(i);

            int l = r.nextInt(lignes);
            int c = r.nextInt(colonnes);

            assertTrue(niveau.coup(l, c));

            for (int j = 0; j < lignes; j++) {
                for (int k = 0; k < colonnes; k++) {
                    if (j < l || k < c) {
                        assertTrue(niveau.aMorceau(j, k));
                    } else {
                        assertFalse(niveau.aMorceau(j, k));
                    }
                }
            }
        }
    }

    @Test
    public void testCoupPerdant() {
        for (int i = 0; i < n; i++) {
            nouveauNiveau(i);
            assertFalse(niveau.estTermine());
            assertTrue(niveau.coup(0, 0));
            assertTrue(niveau.estTermine());
        }
    }

    @Test
    public void testCoupInvalide() {
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            nouveauNiveau(i);

            int l = r.nextInt(lignes);
            int c = r.nextInt(colonnes);
            assertTrue(niveau.coup(l, c));

            l += r.nextInt(lignes - l);
            c += r.nextInt(colonnes - c);
            assertFalse(niveau.coup(l, c));
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
            assertTrue(niveau.coup(l, c));

            if (c < tab[l]) {
                tab[l] = c;
            }
            verifierCoup(l, c, tab);
        }
    }

    @Test
    public void testSequenceCoup() {
        for (int i = 0; i < n; i++) {
            nouveauNiveau(i);
            sequenceCoup();
        }
    }
}
