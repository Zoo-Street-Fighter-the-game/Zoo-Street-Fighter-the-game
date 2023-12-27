package tests;

import DzienneZooPakiet.*;
//import Klasy_Zwierzat.ZwierzeLadowe;
import Klasy_Zwierzat.ZwierzeLadowe;
import Wybieg_package.Wybieg_Ladowy;
import Wybieg_package.Wybieg_podstawowy;
import enumy.wielkosc_wybiegu_enum;
import pakiet_zasoby.Zasoby;
import pakiet_sklep.*;

import java.util.NoSuchElementException;


public class test1 {
    ///
    public static void main(String[] args) {
        Zasoby bank = new Zasoby();
        Sklep sklepik = new Sklep();
        ZwierzeLadowe Slon = new ZwierzeLadowe("Eustachy", 10, 10, 5, 5,5,5,0,10);
        ZwierzeLadowe Slon2 = new ZwierzeLadowe("Eustachy2", 10, 10, 5, 5,5,5,0,10);
        ZwierzeLadowe Slon3 = new ZwierzeLadowe("Eustachy3", 10, 10, 5, 5,5,5,0,10);

        DzienneZoo Zoo = new DzienneZoo("Zoo", bank);



        Zoo.dodajWybieg(sklepik.kup_wybieg("Ladowy", "Maly", Zoo.getZmiennaZasoby()));
        Wybieg_Ladowy wybieg1 = new Wybieg_Ladowy(wielkosc_wybiegu_enum.MALY);
        wybieg1.dodaj_zwierze(Slon);
        wybieg1.dodaj_zwierze(Slon2);
        System.out.println(wybieg1);


        System.out.println(Zoo);
    }
}