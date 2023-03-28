package com.company;

public class Wezel {
    int wierzcholek;
    int krawedz;

    public Wezel (int wierzcholek, int brzeg){
        this.wierzcholek = wierzcholek;
        this.krawedz = brzeg;
    }

    private String miasto (int a){
        switch (a){
            case 0:
                return "Wroclaw";
            case 1:
                return "Olawa";
            case 2:
                return "Brzewg";
            case 3:
                return "Nysa";
            case 4:
                return "Opole";
            default:
                return "";
        }
    }

    public String toString(){
        return miasto(this.wierzcholek) + " (" + this.krawedz +")";
    }

    public int getWierzcholek() {
        return wierzcholek;
    }

    public int getKrawedz() {
        return krawedz;
    }
}
