package Klasy_Zwierzat;

//===========================================================================
//KLASA ROZSZERZAJĄCA ZWIERZE
//===========================================================================


import enumy.rodzaj_srodowiska_enum;

public class Wodne extends Zwierze{

    //===========================================================================
    //KONSTRUKTOR
    //===========================================================================

    public Wodne(String nazwa, int zycie, int sila, int wielkosc, int wskaznik_glodu, int zmeczenie, int zadowolenie, int przezyte_dni, int cena) {
        super(nazwa, zycie, sila, wielkosc, wskaznik_glodu, zmeczenie, zadowolenie, przezyte_dni, cena, rodzaj_srodowiska_enum.WODNY);
    }
}
