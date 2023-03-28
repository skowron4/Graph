package com.company;

public class Graf {
    private OneWayLinkedListWithHead<Wezel>[] listy;


    public Graf(int a){
        this.listy = new OneWayLinkedListWithHead[a];

        for (int i = 0; i < a; i++) {
            this.listy[i] = new OneWayLinkedListWithHead<>();
        }
    }

    public void dodajKrawedz(int a, int wierzcholek, int krawedz){
        this.listy[a].add(new Wezel(wierzcholek, krawedz));
    }

    public OneWayLinkedListWithHead<Wezel>[] getListy() {
        return listy;
    }

    private String miasto (int a){
        switch (a){
            case 0:
                return "Wroclaw";
            case 1:
                return "Olawa";
            case 2:
                return "Brzeg";
            case 3:
                return "Nysa";
            case 4:
                return "Opole";
            default:
                return "";
        }
    }

    private int miasto (String a){
        switch (a){
            case "Wroclaw":
                return 0;
            case "Olawa":
                return 1;
            case "Brzeg":
                return 2;
            case "Nysa":
                return 3;
            case "Opole":
                return 4;
            default:
                return -1;
        }
    }

    public void wyswietl(){
        System.out.println("Lista sasiedztwa: ");

        for (int i = 0; i < listy.length; i++) {
            System.out.print(miasto(i) + "(" + i + "): ");

            if (listy[i].isEmpty()){
                System.out.print("nie ma");
            } else {
                for (Wezel wezel: listy[i]){
                    System.out.print(wezel.toString() + ", ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private int minDroga(int[] dystans, boolean[] czyOdwieczone) {
        int min = Integer.MAX_VALUE;
        int najblizszy = -1;
        boolean czyTrzeba = true;
        for (int i = 0; i < dystans.length; i++){
            if (!czyOdwieczone[i] && dystans[i] < min) {
                min = dystans[i];
                najblizszy = i;
            } else {
                czyTrzeba = false;
            }
        }
        return najblizszy;
    }

    public void dijkstra(String m){
        int iloscWierzcholkow = listy.length;
        int[] dystanse = new int[iloscWierzcholkow];
        boolean[] czyOdwiedzone = new boolean[iloscWierzcholkow];
        int miasto = miasto(m);
        OneWayLinkedListWithHead<Wezel> listaWierzcholka;

        if (miasto != -1){
            listaWierzcholka = listy[miasto];

            for (int i = 0; i < iloscWierzcholkow; i++) {
                if (miasto == i){
                    dystanse[i] = 0;
                    czyOdwiedzone[i] = true;
                } else {
                    dystanse[i] = Integer.MAX_VALUE;

                    for (Wezel wezel: listaWierzcholka){
                        if (wezel.wierzcholek == i){
                            dystanse[i] = wezel.krawedz;
                            break;
                        }
                    }
                    czyOdwiedzone[i] = false;
                }
            }

            int nastepny = minDroga(dystanse, czyOdwiedzone);
            while (nastepny >= 0) {
                czyOdwiedzone[nastepny] = true;
                listaWierzcholka = listy[nastepny];

                for (Wezel wezel : listaWierzcholka) {
                    if (dystanse[wezel.wierzcholek] > dystanse[nastepny] + wezel.krawedz) {
                        dystanse[wezel.wierzcholek] = dystanse[nastepny] + wezel.krawedz;
                    }
                }

                nastepny = minDroga(dystanse, czyOdwiedzone);
            }
            System.out.println("Odleglosci od miasta wyjsciowego (najkrotsze drogi): \n");

            System.out.println(m + ": miasto wyjsciowe");
            for (int i = 0; i < dystanse.length; i++){
                if (dystanse[i] != Integer.MAX_VALUE && dystanse[i] != 0){
                    System.out.println(miasto(i) + ": " + dystanse[i]);
                }
            }

        } else {
            System.out.println("Nie ma takiego miasta");
        }
    }

    public void dfs(String m){
        boolean[] czyOdwiedzone = new boolean[listy.length];
        for (int i = 0; i < listy.length; i++) {
            czyOdwiedzone[i] = false;
        }

        if (miasto(m) != -1){
            System.out.println("Przejscie grafu w glab: ");
            dfsPomoc(czyOdwiedzone, miasto(m));
        } else {
            System.out.println("Nie znaleziono miasta o podanej nazwie: " + m);
        }
    }

    private void dfsPomoc(boolean[] czyOdwiedzone, int a){
        czyOdwiedzone[a] = true;
        System.out.println(miasto(a));

        for (Wezel wezel: listy[a]){
            if (!czyOdwiedzone[wezel.wierzcholek]){
                dfsPomoc(czyOdwiedzone, wezel.wierzcholek);
            }
        }
    }
}
