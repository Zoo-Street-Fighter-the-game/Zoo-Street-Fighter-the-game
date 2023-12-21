package tests;

import DzienneZooPakiet.*;
//import Klasy_Zwierzat.ZwierzeLadowe;
import pakiet_zasoby.Zasoby;
import pakiet_sklep.*;

import java.util.NoSuchElementException;


public class test1 {
    public static void main(String[] args) {
        Zasoby bank = new Zasoby();
        Sklep sklepik = new Sklep();
        //ZwierzeLadowe Slon = new ZwierzeLadowe("Eustachy", 10, 10, 5, 5,5,5,0,10);

        DzienneZoo Zoo = new DzienneZoo("Zoo", bank);

        System.out.println(Zoo);

        Zoo.dodajPracownika(sklepik.kup_opiekuna("Hubert", "Cytarzynski", 1, 5, Zoo.getZmiennaZasoby()));
        System.out.println(Zoo);

        sklepik.sprzedaj_opiekuna(Zoo, Zoo.getZmiennaZasoby());
        System.out.println(Zoo);

        Zoo.dodajWybieg(sklepik.kup_wybieg("Ladowy", "Maly", Zoo.getZmiennaZasoby()));
        System.out.println(Zoo);

        sklepik.sprzedaj_wybieg(Zoo, Zoo.getZmiennaZasoby());
        System.out.println(Zoo);

        try {
            Zoo.getListaWybiegow().getFirst().dodaj_zwierze(sklepik.kup_zwierze("Ladowe", "Slon", 10, 10, 5, 5, 5, 5, 0, 10, Zoo.getZmiennaZasoby()));
            System.out.println(Zoo);
        } catch(NoSuchElementException e)
        {
            System.out.println("Cos poszlo nie tak");
        }
        System.out.println(Zoo);
    }
}