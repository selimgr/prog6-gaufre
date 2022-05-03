package Modele;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestEtat {
    Etat q;
    final int n = 100;

    @Before
    public void nouvelEtat() {
        q = new Etat();
    }

    @Test
    public void testNouvelEtat() {
        assertTrue(q.indiceActuel >= 0);
        assertTrue(q.indiceActuel < Etat.NB_JOUEURS);
        assertEquals(0, q.nbJoueurs);
    }

    @Test
    public void testAjouterJoueur() {
        assertEquals(0, q.nbJoueurs);
        q.ajouterJoueur("abcd", TypeJoueur.HUMAIN);
        assertEquals(1, q.nbJoueurs);
        q.ajouterJoueur("efgh", TypeJoueur.HUMAIN);
        assertEquals(2, q.nbJoueurs);
    }

    void ajouterJoueurs() {
        q.ajouterJoueur("abcd", TypeJoueur.HUMAIN);
        q.ajouterJoueur("efgh", TypeJoueur.HUMAIN);
    }

    @Test
    public void testExceptionAjouterJoueur() {
        ajouterJoueurs();

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> q.ajouterJoueur("ijkl", TypeJoueur.HUMAIN)
        );
        assertTrue(e.getMessage().contains("Impossible d'ajouter un nouveau joueur"));
    }

    @Test
    public void testNouvellePartie() {
        ajouterJoueurs();
        q.nouvellePartie();
        assertEquals(0, q.joueurActuel().nombreCoups());
        assertEquals(0, q.joueurActuel().nombreVictoires());
        assertEquals(0, q.joueurSuivant().nombreCoups());
        assertEquals(0, q.joueurSuivant().nombreVictoires());
    }

    @Test
    public void testExceptionNouvellePartie() {
        for (int i = 0; i < Etat.NB_JOUEURS; i++) {
            IllegalStateException e = assertThrows(
                    IllegalStateException.class,
                    () -> q.nouvellePartie()
            );
            assertTrue(e.getMessage().contains("Impossible de créer une nouvelle partie : joueurs manquants"));

            q.ajouterJoueur("abcd", TypeJoueur.HUMAIN);
        }
    }

    @Test
    public void testJouerCoup() {
        ajouterJoueurs();
        q.nouvellePartie();
        Random r = new Random();
        int l, c;
        Joueur j1 = q.joueurActuel();
        Joueur j2 = q.joueurSuivant();
        int coupsJ1 = 0, coupsJ2 = 0;
        int victoiresJ1 = 0, victoiresJ2 = 0;

        assertEquals(0, j1.nombreCoups());
        assertEquals(0, j2.nombreCoups());
        assertEquals(0, j1.nombreVictoires());
        assertEquals(0, j2.nombreVictoires());

        for (int i = 0; i < n; i++) {
            l = r.nextInt(Niveau.TAILLE_MAX);
            c = r.nextInt(Niveau.TAILLE_MAX);
            q.jouerCoup(l, c);
            coupsJ1++;
            assertEquals(coupsJ1, j1.nombreCoups());
            assertEquals(coupsJ2, j2.nombreCoups());

            if (l == 0 && c == 0) {
                victoiresJ2++;
                q.nouvellePartie();
                coupsJ1 = 0;
                coupsJ2 = 0;
                continue;
            }

            l = r.nextInt(Niveau.TAILLE_MAX);
            c = r.nextInt(Niveau.TAILLE_MAX);
            q.jouerCoup(l, c);
            coupsJ2++;
            assertEquals(coupsJ1, j1.nombreCoups());
            assertEquals(coupsJ2, j2.nombreCoups());

            if (l == 0 && c == 0) {
                victoiresJ1++;
                q.nouvellePartie();
                j1 = q.joueurActuel();
                j2 = q.joueurSuivant();
                coupsJ1 = 0;
                coupsJ2 = 0;
                int tmp = victoiresJ1;
                victoiresJ1 = victoiresJ2;
                victoiresJ2 = tmp;
            }
        }
        assertEquals(coupsJ1, j1.nombreCoups());
        assertEquals(coupsJ2, j2.nombreCoups());
        assertEquals(victoiresJ1, j1.nombreVictoires());
        assertEquals(victoiresJ2, j2.nombreVictoires());
    }

    @Test
    public void testExceptionJouerCoup() {
        ajouterJoueurs();
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            int l = r.nextInt(Niveau.TAILLE_MAX);
            int c = r.nextInt(Niveau.TAILLE_MAX);

            IllegalStateException e = assertThrows(
                    IllegalStateException.class,
                    () -> q.jouerCoup(l, c)
            );
            assertTrue(e.getMessage().contains("Impossible de jouer le coup : partie non commencée"));
        }
    }

    @Test
    public void testSequenceCoup() {
        ajouterJoueurs();
        q.nouvellePartie();
        Random r = new Random();
        int l, c;

        assertEquals(0, q.joueurActuel().nombreCoups());
        assertEquals(0, q.joueurSuivant().nombreCoups());

        for (int i = 0; i < n; i++) {
            l = r.nextInt(Niveau.TAILLE_MAX);
            c = r.nextInt(Niveau.TAILLE_MAX);
            q.jouerCoup(l, c);

            if (l == 0 && c == 0) {
                q.nouvellePartie();
            } else {
                assertEquals(0, q.joueurActuel().nombreCoups());
                assertEquals(1, q.joueurSuivant().nombreCoups());
                assertTrue(q.annulerCoup());
                assertEquals(0, q.joueurActuel().nombreCoups());
                assertEquals(0, q.joueurSuivant().nombreCoups());
            }
        }
    }

    @Test
    public void testExceptionAnnulerCoupAucunePartie() {
        ajouterJoueurs();

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> q.annulerCoup()
        );
        assertTrue(e.getMessage().contains("Impossible d'obtenir le nombre de coups joués : partie non commencée"));
    }

    @Test
    public void testAnnulerCoupAucunCoup() {
        ajouterJoueurs();
        q.nouvellePartie();
        assertFalse(q.annulerCoup());
    }
}
