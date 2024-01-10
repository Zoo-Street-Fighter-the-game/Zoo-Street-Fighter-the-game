package pakiet_arena;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Przedmioty.Przedmiot;

import Wybieg_package.Wybieg_podstawowy;
import enumy.przedmioty_enum;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import enumy.zwierzeta_enum;
import noc_walka.Atak;
import noc_walka.Leczenie;



import static pakiet_arena.Walka.podsumowanie;
import static pakiet_arena.Walka.walka;
import static pakiet_arena.Wybor_zwierzecia.*;
import static pakiet_arena.Wybor_zwierzecia.stworzenie_zwierzecia_walczacego;

public class NocneZoo2 {
    public static final String Q_TABLE_FILE = "q_table_new.ser";

    public static void main(String[] args) {
        DzienneZoo zoo = DzienneZoo.getInstance();

        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.LADOWY,wielkosc_wybiegu_enum.DUZY));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(zwierzeta_enum.NIEDZWIEDZ.stworzZwierze("bob"));
        //
        //
        Poziom_trudnosci poziom_trudnosci = new Poziom_trudnosci();
        poziom_trudnosci.ustaw_poziom_trudnosci();
        Arena arena = new Arena();
        System.out.println(arena);
        nr_wybiegu_Zwierze wybieg_zwierze = wybor_zwierzecia();
        Zwierze wybrane_zwierze = wybieg_zwierze.zwierze();
        Zwierze przeciwnik = wybor_przeciwnika(poziom_trudnosci.getTrudnosc());

        Zwierze twoje_zwierze = stworzenie_zwierzecia_walczacego(wybrane_zwierze, arena);
        System.out.println(twoje_zwierze);
        przeciwnik = stworzenie_zwierzecia_walczacego(przeciwnik, arena);


        walka(twoje_zwierze,przeciwnik);

        podsumowanie(poziom_trudnosci,wybieg_zwierze);
    }


}