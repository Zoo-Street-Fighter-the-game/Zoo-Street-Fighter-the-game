package Klasy_Zwierzat;

//===========================================================================
//KLASA ROZSZERZAJĄCA ZWIERZE
//===========================================================================

import Enum.Enum_rodzaj;
public class Ladowe extends Zwierze{

    //===========================================================================
    //KONSTRUKTOR
    //===========================================================================
    public Ladowe(String nazwa, int zycie, int sila, int wielkosc, int wskaznik_glodu, int zmeczenie, int zadowolenie, int przezyte_dni, int cena, Enum_rodzaj rodzaj) {
        super(nazwa, zycie, sila, wielkosc, wskaznik_glodu, zmeczenie, zadowolenie, przezyte_dni, cena, rodzaj);
    }

}