package Modele;

public class Pair<K,V> {
    K v1;
    V v2;

    public Pair(K a, V b){
        v1 = a;
        v2 = b;
    }

    public K getV1() {
        return v1;
    }

    public V getV2() {
        return v2;
    }
}
