package Klasy_Zwierzat;

//===========================================================================
//KLASA ROZSZERZAJĄCA ZWIERZE
//===========================================================================

import enumy.rodzaj_srodowiska_enum;

public class ZwierzeLadowe extends Zwierze{

    //===========================================================================
    //KONSTRUKTOR
    //===========================================================================
    public ZwierzeLadowe(String nazwa, int zycie, int sila, int wielkosc, int wskaznik_glodu, int zmeczenie, int zadowolenie, int cena) {
        super(nazwa, zycie, sila, wielkosc, wskaznik_glodu, zmeczenie, zadowolenie, cena, rodzaj_srodowiska_enum.LADOWY);
    }

}