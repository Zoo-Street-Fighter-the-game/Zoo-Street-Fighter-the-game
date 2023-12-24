package pakiet_arena;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;

import static pakiet_arena.Wybor_zwierzecia.*;

public class NocneZoo {
    public static void main(String[] args) {
        //to jest pod testy,ale reszta kodu pod gre juz
        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.POWIETRZNY,wielkosc_wybiegu_enum.SREDNI));
        zoo.getListaWybiegow().get(0).dodaj_zwierze(new Zwierze("1",1,1,1,1,1, rodzaj_srodowiska_enum.POWIETRZNY));
        //
        Poziom_trudnosci poziom_trudnosci = new Poziom_trudnosci();
        poziom_trudnosci.ustaw_poziom_trudnosci();
        Arena arena = new Arena();

        Zwierze twoje_zwierze  = wybor_zwierzecia();
        Zwierze przeciwnik = wybor_przeciwnika(poziom_trudnosci.getTrudnosc());

        twoje_zwierze = stworzenie_zwierzecia_walczacego(twoje_zwierze,arena);
        przeciwnik = stworzenie_zwierzecia_walczacego(przeciwnik,arena);

    }
}
