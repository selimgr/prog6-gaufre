package Controller;
import Modele.Niveau;
import Modele.Pair;

import java.util.HashMap; // import the HashMap class
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class IA {
    private HashMap<String, Integer> memo;
    private HashMap<String, Pair<Integer,Integer>> configGagnants;
    private int difficulte;
    //Arbre a;
    public IA(int dif) {
        //a = new Arbre<Niveau>(n);
        memo = new HashMap<>();
        configGagnants = new HashMap<>();
        difficulte = dif;
    }

    /**
     * Retourne si le coup est gagnant pour L'IA
     */
    public boolean Evaluation(boolean joueur){
        return joueur;
    }

    public boolean Calcul_joueur_1(Niveau n,boolean humain){
        List<Integer> c = n.getContenu();
        if (c.size() == 1) {
            return Evaluation(humain);
        }
        if (c.size() == 0){
            return false;
        }

        boolean valeur = false;
        // regarder si on peux répondre en taux de 1;
        if (memo.get(n.toString()) != null){
            return configGagnants.get(n.toString()) != null;
        }
        memo.put(n.toString(),0);
        ListIterator<Integer> li = c.listIterator();
        int i = 0;
        while (li.hasNext()) {
            int it = c.get(i);
            for (int k = 0; k < it; k++){
                Niveau niv = new Niveau(n);
                niv.coup(i,k);
                valeur = valeur || Calcul_joueur_2(niv,!humain);
                if (valeur){
                    Pair<Integer,Integer> coupG = new Pair<>(i,k);
                    if (humain) configGagnants.putIfAbsent(niv.toString(),coupG);
                }
            }
            i++;
        }
        return valeur;
    }

    public boolean Calcul_joueur_2(Niveau n,boolean humain){
        List<Integer> c = n.getContenu();
        if (c.size() == 1) {
            return Evaluation(humain);
        }
        if (c.size() == 0){
            return false;
        }

        boolean valeur = true;
        // regarder si on peux répondre en taux de 1;
        if (memo.get(n.toString()) != null){
            return configGagnants.get(n.toString()) != null;
        }
        memo.put(n.toString(),1);
        ListIterator<Integer> li = c.listIterator();
        int i = 0;
        while (li.hasNext()) {
            int it = c.get(i);
            for (int k = 0; k < it; k++){
                Niveau niv = new Niveau(n);
                niv.coup(i,k);
                valeur = valeur && Calcul_joueur_1(niv,!humain);
                if (valeur){
                    Pair<Integer,Integer> coupG = new Pair<>(i,k);
                    if (humain) configGagnants.putIfAbsent(niv.toString(),coupG);
                }
            }
            i++;
        }
        return valeur;
    }

    public Pair<Integer,Integer> joue(Niveau n,boolean premier){
        if (memo.size() == 0){
            // On pourra rajouter les heuristiques vu dans le documents IA içi
            //Calcul_joueur_1(n,!premier);
        }
        Random r = new Random();
        int x = r.nextInt(n.lignes());
        System.out.println("x = "+x);
        int y = r.nextInt(n.getContenu().get(x));
        return new Pair<Integer, Integer>(x,y);
        //return configGagnants.get(n.toString());
        //n.coup(Coup.getV1(),Coup.getV2());
        //return n;
    }
}
