package Wybieg_package;

import Klasy_Zwierzat.Zwierze;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Wybieg_abstract {
    private List<Zwierze> lista_zwierzat =
            new ArrayList<>();


    public abstract void dodaj_zwierze(Zwierze obiekt);
    public abstract void usun_zwierze(Zwierze obiekt);

    public List<Zwierze> getLista_zwierzat() {
        return lista_zwierzat;
    }

    public void setLista_zwierzat(List<Zwierze> lista_zwierzat) {
        this.lista_zwierzat = lista_zwierzat;
    }

    public int wybierzZwierze()
    {
        Scanner sc = new Scanner(System.in);
        int zwierze;
        System.out.println("Lista zwierzat w wybranym wybiegu: ");
        //listaWybiegow.size() TO OPCJA WYBIEGU DLA BEZDOMNYCH WYBIERANA PRZEZ UŻYTKOWNIKA
        for(int i=0; i<getLista_zwierzat().size(); i++)
        {
            System.out.println("Zwierze " + i + ": " + (getLista_zwierzat()).get(i));
        }

        //PODAWANIE ZWIERZECIA I WYBIEGU DO PRZENIESIENIA
        System.out.println("Podaj numer zwierzecia, ktore chcesz przeniesc!" );
        zwierze=sc.nextInt();
        return zwierze;
    }
}
