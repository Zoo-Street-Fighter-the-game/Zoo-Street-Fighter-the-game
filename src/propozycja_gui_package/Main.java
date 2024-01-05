package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import enumy.zwierzeta_enum;
import gui_package.MyFrame;
import pakiet_sklep.Sklep;

public class Main {
    public static void main (String [] args)

    {
        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.SREDNI));
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.WODNY, wielkosc_wybiegu_enum.SREDNI));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(zwierzeta_enum.LOS.stworzZwierze("bob"));
        zoo.getListaWybiegow().getLast().dodaj_zwierze(zwierzeta_enum.PINGWIN.stworzZwierze("bob"));

        MainFrame Frame = new MainFrame(DzienneZoo.getInstance());

    }
}
