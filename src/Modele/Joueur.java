package Modele;

import Controller.IA;

public class Joueur {
    
    boolean isAI;
    String name;

    int difficulteIA; // 0 = facile, 1 = moyen, 2 difficile

    public Joueur(String N, boolean AI, int diff) {
        this.name = N;
        this.isAI = AI;
        difficulteIA=diff;
    }
    
    // Getters

    public String getPlayerName() {
        return this.name;
    }

    public boolean isAI() {
        return isAI;
    }

    public int getDifficulteIA(){
        return difficulteIA;
    }
}
