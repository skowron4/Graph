package com.company;

public class Symulacja {
    private Graf graf = new Graf(5);
    public Symulacja(){
        graf.dodajKrawedz(0, 1, 10);
        graf.dodajKrawedz(0, 3, 30);
        graf.dodajKrawedz(0, 4, 100);
        graf.dodajKrawedz(1, 2, 50);
        graf.dodajKrawedz(2, 4, 10);
        graf.dodajKrawedz(3, 2, 20);
        graf.dodajKrawedz(3, 4, 60);

        graf.wyswietl();
        graf.dijkstra("Wroclaw");
        System.out.println();
        graf.dfs("Wroclaw");
    }
}
