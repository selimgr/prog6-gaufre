package Modele;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
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
        int victoiresJ1 = 0, victoiresJ2 = 0;

        assertEquals(0, q.joueurActuel().nombreCoups());
        assertEquals(0, q.joueurSuivant().nombreCoups());
        assertEquals(0, q.joueurActuel().nombreVictoires());
        assertEquals(0, q.joueurSuivant().nombreVictoires());

        for (int i = 0; i < n; i++) {
            l = r.nextInt(Niveau.TAILLE_MAX);
            c = r.nextInt(Niveau.TAILLE_MAX);
            q.jouerCoup(l, c);
            assertEquals(i, q.joueurActuel().nombreCoups());
            assertEquals(i+1, q.joueurSuivant().nombreCoups());
            if (l == 0 && c == 0) {
                victoiresJ2++;
            }

            l = r.nextInt(Niveau.TAILLE_MAX);
            c = r.nextInt(Niveau.TAILLE_MAX);
            q.jouerCoup(l, c);
            assertEquals(i+1, q.joueurActuel().nombreCoups());
            assertEquals(i+1, q.joueurSuivant().nombreCoups());
            if (l == 0 && c == 0) {
                victoiresJ1++;
            }
        }
        assertEquals(n, q.joueurActuel().nombreCoups());
        assertEquals(n, q.joueurSuivant().nombreCoups());
        assertEquals(victoiresJ1, q.joueurActuel().nombreVictoires());
        assertEquals(victoiresJ2, q.joueurSuivant().nombreVictoires());
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
            assertEquals(0, q.joueurActuel().nombreCoups());
            assertEquals(1, q.joueurSuivant().nombreCoups());
            assertTrue(q.annulerCoup());
            assertEquals(0, q.joueurActuel().nombreCoups());
            assertEquals(0, q.joueurSuivant().nombreCoups());
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
