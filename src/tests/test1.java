package tests;

import DzienneZooPakiet.*;
//import Klasy_Zwierzat.ZwierzeLadowe;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import enumy.zwierzeta_enum;
import pakiet_zasoby.Zasoby;
import pakiet_sklep.*;

import java.util.NoSuchElementException;


public class test1 {
    public static void main(String[] args) {
        Zasoby bank = new Zasoby(0,200,100);
        Sklep sklepik = new Sklep();

        DzienneZoo Zoo = DzienneZoo.getInstance();

        System.out.println(Zoo);

       Zoo.dodajWybieg(sklepik.kup_wybieg(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.DUZY, Zoo.getZmiennaZasoby()));
       System.out.println(Zoo);



<<<<<<< HEAD
=======
        sklepik.sprzedaj_wybieg(Zoo, Zoo.getZmiennaZasoby());
        System.out.println(Zoo);

        try {
            //Zoo.getListaWybiegow().getFirst().dodaj_zwierze(sklepik.kup_zwierze("Ladowe", "Slon", 10, 10, 5, 5, 5, 5, 0, 10, Zoo.getZmiennaZasoby()));
            System.out.println(Zoo);
        } catch(NoSuchElementException e)
        {
            System.out.println("Cos poszlo nie tak");
        }
        System.out.println(Zoo);
        Zoo.getListaWybiegow().getFirst().dodaj_zwierze(zwierzeta_enum.LOS.stworzZwierze());
        zwierzeta_enum cos = zwierzeta_enum.PINGWIN;
>>>>>>> Dominik_branch



    }
}