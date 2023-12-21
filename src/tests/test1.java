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

        DzienneZoo Zoo = new DzienneZoo("Zoo", bank);

        System.out.println(Zoo);

       Zoo.dodajWybieg(sklepik.kup_wybieg(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.DUZY, Zoo.getZmiennaZasoby()));
       System.out.println(Zoo);




    }
}