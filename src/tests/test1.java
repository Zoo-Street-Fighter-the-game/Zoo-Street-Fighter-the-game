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

        DzienneZoo Zoo = DzienneZoo.getInstance();

        Sklep sklepik = new Sklep(Zoo);
        System.out.println(Zoo);

        Zoo.rozpocznijDzien();
        sklepik.kup_wybieg(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.DUZY);
        sklepik.kup_zwierze(zwierzeta_enum.LOS);
        sklepik.getZoo().zakonczDzien();

        sklepik.getZoo().rozpocznijDzien();
        sklepik.kup_wybieg(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.MALY);
        sklepik.getZoo().zakonczDzien();


    }
}