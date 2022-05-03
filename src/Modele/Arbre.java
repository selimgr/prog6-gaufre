package Modele;
import java.util.ArrayList;
public class Arbre<E> {
    private Niveau niveau;
    private ArrayList<Arbre<E>> fils;
    private E contenu;

    public Arbre (E c ) {
            contenu = c;
            fils = new ArrayList<Arbre<E>>();
    }

    public void addFils(Arbre<E> a, E c){
        fils.add(a);
    }
    public void removeFils(int num){
        fils.remove(num);
    }


    //getter and setter
    public E getContenu(){
        return contenu;
    }

    public void setContenu(E contenu) {
        this.contenu = contenu;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public ArrayList<Arbre<E>> getFils() {
        return fils;
    }

    public void setFils(ArrayList<Arbre<E>> fils) {
        this.fils = fils;
    }

}
