package Pracownik_package;
import Wybieg_package.*;
import Klasy_Zwierzat.Zwierze;
import pakiet_zasoby.Zasoby;
import java.io.Serializable;



public class Pracownik implements Serializable, Comparable<Pracownik>{

    //INICJOWANIE SKLADOWYCH
    private final String imie;
    private final String nazwisko;
    private final int jakoscUslug; //w skali do 10,
    //wplywa na metody umyjWybieg i nakarmZwierze
    private int iloscakcji = 3;
    private final Zasoby zasoby;


    //KONSTRUKTOR
    public Pracownik(String imie, String nazwisko, int jakoscUslug, Zasoby zasoby) {

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.jakoscUslug = jakoscUslug;
        this.zasoby = zasoby;
    }


    //METODY KLASY

    public void umyjWybieg(Wybieg_podstawowy wybieg) {

        int poziomCzystosci = (int) wybieg.getCzystosc();

        poziomCzystosci += getJakoscUslug() * poziomCzystosci;
        iloscakcji--;

        if (poziomCzystosci >= 100) { //nie moze przekraczac 100 z zalozenia
            poziomCzystosci = 100;
        }

        wybieg.setCzystosc(poziomCzystosci);
        wybieg.powiadom_obserwatorow();

    }

    public void nakarmZwierze(int ilosc, Wybieg_abstract wybieg) {
        for (Zwierze zwierze : wybieg.getLista_zwierzat()) {
            zasoby.zmienJedzenie(-ilosc);
            zwierze.karmienie(jakoscUslug * ilosc); //tutaj mozna dac zmienną ze sliderem w gui
            System.out.println(zwierze.getWskaznik_glodu());
        }
        iloscakcji--;
    }

    @Override
    public String toString() {
        return
                imie +
                        " " + nazwisko +
                        " " + jakoscUslug + " jakosci";

    }

    //GETTERY I SETTERY


    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getJakoscUslug() {
        return jakoscUslug;
    }

    public int getIloscakcji() {
        return iloscakcji;
    }


    @Override
    public int compareTo(Pracownik o) {
        return this.getImie().compareTo( o.getImie());

    }
}
