package Modele;

public class Joueur {
    
    boolean isAI;
    String name;

    public Joueur(String N, boolean AI) {
        this.name = N;
        this.isAI = AI;
    }
    
    // Getters

    public String getPlayerName() {
        return this.name;
    }

    public boolean isAI() {
        return isAI;
    }
}
