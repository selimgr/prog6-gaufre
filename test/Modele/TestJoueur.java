package Modele;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class TestJoueur {
    Joueur j;
    final int n = 100;

    void nouveauJoueur() {
        j = new Joueur("abcd", TypeJoueur.HUMAIN);
    }

    void verifierJoueur(String nom, TypeJoueur type) {
        j = new Joueur(nom, type);
        assertEquals(nom, j.nom());
        assertEquals(type, j.type());
    }

    @Test
    public void testJoueur() {
        verifierJoueur("abcd", TypeJoueur.HUMAIN);
        verifierJoueur("efgh", TypeJoueur.IA_FACILE);
        verifierJoueur("ijkl", TypeJoueur.IA_MOYEN);
        verifierJoueur("mnop", TypeJoueur.IA_DIFFICILE);
    }

    @Test
    public void testNouvellePartie() {
        nouveauJoueur();
        j.nouvellePartie();
        assertEquals(0, j.nombreCoups());
    }

    @Test
    public void testJouerAnnulerCoup() {
        nouveauJoueur();
        j.nouvellePartie();
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            int l = r.nextInt(Niveau.TAILLE_MAX);
            int c = r.nextInt(Niveau.TAILLE_MAX);
            j.jouerCoup(l, c);
            assertEquals(1, j.nombreCoups());
            j.annulerCoup();
            assertEquals(0, j.nombreCoups());
        }
    }

    @Test
    public void testSequenceCoups() {
        nouveauJoueur();
        j.nouvellePartie();
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            int l = r.nextInt(Niveau.TAILLE_MAX);
            int c = r.nextInt(Niveau.TAILLE_MAX);
            j.jouerCoup(l, c);
            assertEquals(i+1, j.nombreCoups());
        }
        assertEquals(n, j.nombreCoups());

        for (int i = n; i > 0; i--) {
            j.annulerCoup();
            assertEquals(i-1, j.nombreCoups());
        }
        assertEquals(0, j.nombreCoups());
    }

    @Test
    public void testExceptionJouerCoup() {
        nouveauJoueur();
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            int l = r.nextInt(Niveau.TAILLE_MAX);
            int c = r.nextInt(Niveau.TAILLE_MAX);

            IllegalStateException e = assertThrows(
                    IllegalStateException.class,
                    () -> j.jouerCoup(l, c)
            );
            assertTrue(e.getMessage().contains("Impossible de jouer le coup : partie non commencée"));
        }
    }

    @Test
    public void testExceptionAnnulerCoupAucunePartie() {
        nouveauJoueur();

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> j.annulerCoup()
        );
        assertTrue(e.getMessage().contains("Impossible d'annuler un coup : partie non commencée"));
    }

    @Test
    public void testExceptionAnnulerCoupAucunCoup() {
        nouveauJoueur();
        j.nouvellePartie();

        NoSuchElementException e = assertThrows(
                NoSuchElementException.class,
                () -> j.annulerCoup()
        );
        assertTrue(e.getMessage().contains("Impossible d'annuler un coup : aucun coup joué"));
    }

    @Test
    public void testExceptionNombreCoups() {
        nouveauJoueur();

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> j.nombreCoups()
        );
        assertTrue(e.getMessage().contains("Impossible d'obtenir le nombre de coups joués : partie non commencée"));
    }

    @Test
    public void testAjouterVictoire() {
        nouveauJoueur();
        j.nouvellePartie();

        assertEquals(0, j.nombreVictoires());

        for (int i = 0; i < n; i++) {
            j.ajouterVictoire();
            assertEquals(i+1, j.nombreVictoires());
        }
        assertEquals(n, j.nombreVictoires());
    }
}
