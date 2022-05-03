package Modele;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestJeu {
    Jeu jeu;
    int lignes, colonnes;
    final int n = 150;

    @Before
    public void nouveauJeu() {
        jeu = new Jeu();
    }

    void nouveauxJoueurs() {
        jeu.nouveauJoueur("a", TypeJoueur.HUMAIN);
        jeu.nouveauJoueur("b", TypeJoueur.IA_FACILE);
    }

    @Test
    public void testNouveauxJoueurs() {
        nouveauxJoueurs();

        if (jeu.nomJoueurActuel().equals("a")) {
            assertEquals("a", jeu.nomJoueurActuel());
            assertEquals("b", jeu.nomJoueurSuivant());
            assertEquals(TypeJoueur.HUMAIN, jeu.typeJoueurActuel());
            assertEquals(TypeJoueur.IA_FACILE, jeu.typeJoueurSuivant());
        } else {
            assertEquals("b", jeu.nomJoueurActuel());
            assertEquals("a", jeu.nomJoueurSuivant());
            assertEquals(TypeJoueur.IA_FACILE, jeu.typeJoueurActuel());
            assertEquals(TypeJoueur.HUMAIN, jeu.typeJoueurSuivant());
        }
        jeu.nouvellePartie();
        assertEquals(0, jeu.nombreCoupsJoueurActuel());
        assertEquals(0, jeu.nombreCoupsJoueurSuivant());
        assertEquals(0, jeu.nombreVictoiresJoueurActuel());
        assertEquals(0, jeu.nombreVictoiresJoueurSuivant());
    }

    @Test
    public void testExceptionNombreMaxJoueurs() {
        nouveauxJoueurs();

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> jeu.nouveauJoueur("c", TypeJoueur.IA_MOYEN)
        );
        assertTrue(e.getMessage().contains("Impossible d'ajouter un nouveau joueur"));
    }

    void nouvellePartie(int i) {
        if (i == 0) {
            jeu.nouvellePartie();
            lignes = Niveau.LIGNES_PAR_DEFAUT;
            colonnes = Niveau.COLONNES_PAR_DEFAUT;
            return;
        }
        if (i < 100) {
            int unite = i % 10;
            if (unite == 1) {
                lignes++;
            }
            colonnes = unite + 1;
        } else {
            Random r = new Random();
            lignes = r.nextInt(Niveau.TAILLE_MAX - 1) + 1;
            colonnes = r.nextInt(Niveau.TAILLE_MAX - 1) + 1;
        }
        jeu.nouvellePartie(lignes, colonnes);
    }

    void verifierNiveauPlein() {
        for (int i = 0; i < jeu.niveau().lignes(); i++) {
            for (int j = 0; j < jeu.niveau().colonnes(); j++) {
                assertTrue(jeu.niveau().aMorceau(i, j));
            }
        }
    }

    @Test
    public void testNouvellePartie() {
        nouveauxJoueurs();

        for (int i = 0; i < n; i++) {
            nouvellePartie(i);
            assertEquals(lignes, jeu.niveau().lignes());
            assertEquals(colonnes, jeu.niveau().colonnes());
            verifierNiveauPlein();
        }
    }

    void exceptionNouvellePartieDimensions(int l, int c) {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> jeu.nouvellePartie(l, c)
        );
        assertTrue(e.getMessage().contains("Impossible de créer le niveau : dimensions incorrectes"));
    }

    @Test
    public void testExceptionNouvellePartieDimensions() {
        nouveauxJoueurs();

        for (int i = 0; i < n; i++) {
            exceptionNouvellePartieDimensions(-i, 1);
            exceptionNouvellePartieDimensions(1, -i);
            exceptionNouvellePartieDimensions(-i, -i);
        }
        exceptionNouvellePartieDimensions(Niveau.TAILLE_MAX + 1, Niveau.TAILLE_MAX);
        exceptionNouvellePartieDimensions(Niveau.TAILLE_MAX, Niveau.TAILLE_MAX + 1);
        exceptionNouvellePartieDimensions(Niveau.TAILLE_MAX + 1, Niveau.TAILLE_MAX + 1);
    }

    @Test
    public void testExceptionNouvellePartieJoueursManquants() {
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> jeu.nouvellePartie(0, 0)
        );
        assertTrue(e.getMessage().contains("Impossible de créer une nouvelle partie : joueurs manquants"));

        jeu.nouveauJoueur("a", TypeJoueur.HUMAIN);

        e = assertThrows(
                IllegalStateException.class,
                () -> jeu.nouvellePartie(0, 0)
        );
        assertTrue(e.getMessage().contains("Impossible de créer une nouvelle partie : joueurs manquants"));
    }

    void ajouterLigneEtColonne(int l, int c) {
        jeu.ajouterLigne();
        jeu.ajouterColonne();
        assertEquals(l, jeu.niveau().lignes());
        assertEquals(c, jeu.niveau().colonnes());
    }

    void supprimerLigneEtColonne(int l, int c) {
        jeu.supprimerLigne();
        jeu.supprimerColonne();
        assertEquals(l, jeu.niveau().lignes());
        assertEquals(c, jeu.niveau().colonnes());
    }

    @Test
    public void testAjouterSupprimer() {
        Random r = new Random();
        nouveauxJoueurs();

        for (int i = 0; i < n; i++) {
            nouvellePartie(i);

            for (int j = 0; j < n; j++) {
                ajouterLigneEtColonne(lignes + 1, colonnes + 1);
                supprimerLigneEtColonne(lignes, colonnes);

                if (lignes == 1) {
                    if (colonnes == 1) {
                        supprimerLigneEtColonne(lignes, colonnes);
                    } else {
                        supprimerLigneEtColonne(lignes, colonnes - 1);
                        jeu.ajouterColonne();
                        assertEquals(colonnes, jeu.niveau().colonnes());
                    }
                } else {
                    if (colonnes == 1) {
                        supprimerLigneEtColonne(lignes - 1, colonnes);
                        jeu.ajouterLigne();
                        assertEquals(lignes, jeu.niveau().lignes());
                    } else {
                        supprimerLigneEtColonne(lignes - 1, colonnes - 1);
                        ajouterLigneEtColonne(lignes, colonnes);
                    }
                }
            }
            verifierNiveauPlein();

            int l = r.nextInt(lignes);
            int c = r.nextInt(colonnes);
            jeu.jouerCoup(l, c);

            ajouterLigneEtColonne(lignes, colonnes);
            supprimerLigneEtColonne(lignes, colonnes);
        }
    }

    @Test
    public void testModificationsTaille() {
        int i, j;
        nouveauxJoueurs();

        for (i = 0; i < n; i++) {
            nouvellePartie(i);

            for (j = 0; j < n; j++) {
                ajouterLigneEtColonne(lignes + j + 1, colonnes + j + 1);
            }
            assertEquals(lignes + n, jeu.niveau().lignes());
            assertEquals(colonnes + n, jeu.niveau().colonnes());
            verifierNiveauPlein();

            for (j = n; j > 0; j--) {
                supprimerLigneEtColonne(lignes + j - 1, colonnes + j - 1);
            }
            assertEquals(lignes, jeu.niveau().lignes());
            assertEquals(colonnes, jeu.niveau().colonnes());
            verifierNiveauPlein();

            if (lignes < colonnes) {
                for (j = 1; j < lignes; j++) {
                    supprimerLigneEtColonne(lignes - j, colonnes - j);
                }
                for (j = 1; j < colonnes - lignes + 1; j++) {
                    jeu.supprimerColonne();
                    assertEquals(colonnes - lignes + 1 - j, jeu.niveau().colonnes());
                }
            } else {
                for (j = 1; j < colonnes; j++) {
                    supprimerLigneEtColonne(lignes - j, colonnes - j);
                }
                for (j = 1; j < lignes - colonnes + 1; j++) {
                    jeu.supprimerLigne();
                    assertEquals(lignes - colonnes + 1 - j, jeu.niveau().lignes());
                }
            }
            assertEquals(1, jeu.niveau().lignes());
            assertEquals(1, jeu.niveau().colonnes());
            verifierNiveauPlein();

            if (lignes < colonnes) {
                for (j = 1; j < lignes; j++) {
                    ajouterLigneEtColonne(j + 1, j + 1);
                }
                for (j = 1; j < colonnes - lignes + 1; j++) {
                    jeu.ajouterColonne();
                    assertEquals(lignes + j, jeu.niveau().colonnes());
                }
            } else {
                for (j = 1; j < colonnes; j++) {
                    ajouterLigneEtColonne(j + 1, j + 1);
                }
                for (j = 1; j < lignes - colonnes + 1; j++) {
                    jeu.ajouterLigne();
                    assertEquals(colonnes + j, jeu.niveau().lignes());
                }
            }
            assertEquals(lignes, jeu.niveau().lignes());
            assertEquals(colonnes, jeu.niveau().colonnes());
            verifierNiveauPlein();
        }
    }

    @Test
    public void testExceptionsAjouterSupprimer() {
        nouveauxJoueurs();

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> jeu.ajouterLigne()
        );
        assertTrue(e.getMessage().contains("Aucun niveau auquel ajouter une ligne"));

        e = assertThrows(
                IllegalStateException.class,
                () -> jeu.supprimerLigne()
        );
        assertTrue(e.getMessage().contains("Aucun niveau auquel supprimer une ligne"));

        e = assertThrows(
                IllegalStateException.class,
                () -> jeu.ajouterColonne()
        );
        assertTrue(e.getMessage().contains("Aucun niveau auquel ajouter une colonne"));

        e = assertThrows(
                IllegalStateException.class,
                () -> jeu.supprimerColonne()
        );
        assertTrue(e.getMessage().contains("Aucun niveau auquel supprimer une colonne"));
    }

    @Test
    public void testCoup() {
        Random r = new Random();
        nouveauxJoueurs();
        int victoiresJ1 = 0, victoiresJ2 = 0;

        for (int i = 0; i < n; i++) {
            nouvellePartie(i);

            int l = r.nextInt(lignes);
            int c = r.nextInt(colonnes);
            assertTrue(jeu.jouerCoup(l, c));

            if (l == 0 && c == 0) {
                assertTrue(jeu.partieTerminee());
                victoiresJ2++;
                assertEquals(1, jeu.nombreCoupsJoueurActuel());
                assertEquals(0, jeu.nombreCoupsJoueurSuivant());
            } else {
                assertFalse(jeu.partieTerminee());
                assertEquals(0, jeu.nombreCoupsJoueurActuel());
                assertEquals(1, jeu.nombreCoupsJoueurSuivant());
                int tmp = victoiresJ1;
                victoiresJ1 = victoiresJ2;
                victoiresJ2 = tmp;
            }
            assertEquals(victoiresJ1, jeu.nombreVictoiresJoueurActuel());
            assertEquals(victoiresJ2, jeu.nombreVictoiresJoueurSuivant());

            for (int j = 0; j < lignes; j++) {
                for (int k = 0; k < colonnes; k++) {
                    if (j < l || k < c) {
                        assertTrue(jeu.niveau().aMorceau(j, k));
                    } else {
                        assertFalse(jeu.niveau().aMorceau(j, k));
                    }
                }
            }
        }
    }

    void verifierCoup(int l, int c, int[] tab) {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                for (int k = l; k >= 0; k--) {
                    if (tab[l] <= c) {
                        assertFalse(jeu.niveau().aMorceau(l, c));
                        return;
                    }
                }
                assertTrue(jeu.niveau().aMorceau(l, c));
            }
        }
    }

    void sequenceCoup() {
        Random r = new Random();

        int[] tab = new int[lignes];

        for (int i = 0; i < lignes; i++) {
            tab[i] = Integer.MAX_VALUE;
        }

        while (!jeu.partieTerminee()) {
            int l = r.nextInt(lignes);
            int c = r.nextInt(colonnes);

            if (!jeu.niveau().aMorceau(l, c)) {
                continue;
            }
            assertTrue(jeu.jouerCoup(l, c));

            if (c < tab[l]) {
                tab[l] = c;
            }
            verifierCoup(l, c, tab);
        }
    }

    @Test
    public void testSequenceCoup() {
        nouveauxJoueurs();

        for (int i = 0; i < n; i++) {
            nouvellePartie(i);
            sequenceCoup();
        }
    }

    @Test
    public void testExceptionCoupAucunePartie() {
        Random r = new Random();
        nouveauxJoueurs();

        for (int i = 0; i < n; i++) {
            int l = r.nextInt(Niveau.TAILLE_MAX);
            int c = r.nextInt(Niveau.TAILLE_MAX);

            IllegalStateException e = assertThrows(
                    IllegalStateException.class,
                    () -> jeu.jouerCoup(l, c)
            );
            assertTrue(e.getMessage().contains("Aucun niveau auquel jouer"));
        }
    }

    void exceptionCoupCaseInvalide(int l, int c) {
        IndexOutOfBoundsException e = assertThrows(
                IndexOutOfBoundsException.class,
                () -> jeu.jouerCoup(l, c)
        );
        assertTrue(e.getMessage().contains("Case (" + l + ", " + c + ") invalide"));
    }

    @Test
    public void testExceptionCoupCaseInvalide() {
        nouveauxJoueurs();

        for (int i = 0; i < n; i++) {
            nouvellePartie(i);
            exceptionCoupCaseInvalide(-1, 0);
            exceptionCoupCaseInvalide(0, -1);
            exceptionCoupCaseInvalide(lignes, colonnes - 1);
            exceptionCoupCaseInvalide(lignes - 1, colonnes);
        }
    }

    @Test
    public void testCoupInvalide() {
        Random r = new Random();
        nouveauxJoueurs();

        for (int i = 0; i < n; i++) {
            nouvellePartie(i);

            int l = r.nextInt(lignes);
            int c = r.nextInt(colonnes);
            assertTrue(jeu.jouerCoup(l, c));

            l += r.nextInt(lignes - l);
            c += r.nextInt(colonnes - c);
            assertFalse(jeu.jouerCoup(l, c));
        }
    }

    @Test
    public void testPartieTerminee() {
        int l, c;
        Random r = new Random();
        nouveauxJoueurs();

        assertFalse(jeu.partieTerminee());

        for (int i = 0; i < n; i++) {
            nouvellePartie(i);

            do {
                assertFalse(jeu.partieTerminee());
                l = r.nextInt(lignes);
                c = r.nextInt(colonnes);
                jeu.jouerCoup(l, c);
            } while (l != 0 || c != 0);

            assertTrue(jeu.partieTerminee());
        }
    }
}
