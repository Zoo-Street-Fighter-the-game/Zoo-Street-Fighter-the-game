/*package tests;

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
        sklepik.kup_wybieg(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.DUZY);
        System.out.println(Zoo);
        sklepik.kup_pracownika("Hubert", "Cytar", 1);
        System.out.println(Zoo);
        sklepik.kup_zwierze(zwierzeta_enum.LOS);
        System.out.println(Zoo);
        Zoo.rozpocznijDzien();
        System.out.println(Zoo.getListaWybiegow().getFirst().getLista_zwierzat().getFirst().getMnoznik_pieniedzy());
        Zoo.getListaPracownikow().getFirst().umyjWybieg(Zoo.getListaWybiegow().getFirst());
        System.out.println(Zoo.getListaWybiegow().getFirst().getLista_zwierzat().getFirst().getMnoznik_pieniedzy());
        sklepik.sprzedaj_wybieg();
        sklepik.sprzedaj_zwierze();
        sklepik.sprzedaj_pracownika();
        System.out.println(Zoo);
        Zoo.getWybiegDlaBezdomnych().toString();

/*
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


*/
