package Klasy_Zwierzat;

import Wybieg_package.rodzaj_srodowiska_enum;
import Wybieg_package.Obserwujacy_interface;

public abstract class Zwierze implements Obserwujacy_interface{

    //pola zwierząt
    private String nazwa;
    private int zycie;
    private int sila;
    private int wielkosc;
    private int wskaznik_glodu;
    private int zmeczenie;
    private int zadowolenie;
    private int przezyte_dni;
    private int cena;
    private float mnoznik_pieniedzy;
    private rodzaj_srodowiska_enum rodzaj_srodowiska;

    //konstruktor
    public Zwierze(String nazwa, int zycie, int sila, int wielkosc, int wskaznik_glodu, int zmeczenie, int zadowolenie, int przezyte_dni, int cena,rodzaj_srodowiska_enum rodzaj){
        this.nazwa = nazwa;
        this.zycie = zycie;
        this.sila = sila;
        this.wielkosc = wielkosc;
        this.wskaznik_glodu = wskaznik_glodu;
        this.zmeczenie = zmeczenie;
        this.zadowolenie = zadowolenie;
        this.przezyte_dni = przezyte_dni;
        this.cena = cena;
        this.rodzaj_srodowiska = rodzaj;
    }

    //Settery i gettery
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public int getZycie() {
        return zycie;
    }

    public void setZycie(int zycie) {
        this.zycie = zycie;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getWielkosc() {
        return wielkosc;
    }

    public void setWielkosc(int wielkosc) {
        this.wielkosc = wielkosc;
    }

    public int getWskaznik_glodu() {
        return wskaznik_glodu;
    }

    public void setWskaznik_glodu(int wskaznik_glodu) {
        this.wskaznik_glodu = wskaznik_glodu;
    }

    public int getZmeczenie() {
        return zmeczenie;
    }

    public void setZmeczenie(int zmeczenie) {
        this.zmeczenie = zmeczenie;
    }

    public int getZadowolenie() {
        return zadowolenie;
    }

    public void setZadowolenie(int zadowolenie) {
        this.zadowolenie = zadowolenie;
    }

    public int getPrzezyte_dni() {
        return przezyte_dni;
    }

    public void setPrzezyte_dni(int przezyte_dni) {
        this.przezyte_dni = przezyte_dni;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public float getMnoznik_pieniedzy() {
        return mnoznik_pieniedzy;
    }

    public void setMnoznik_pieniedzy(float mnoznik_pieniedzy) {
        this.mnoznik_pieniedzy = mnoznik_pieniedzy;
    }


    public rodzaj_srodowiska_enum getRodzaj_srodowiska() {
        return rodzaj_srodowiska;
    }

    public void setRodzaj_srodowiska(rodzaj_srodowiska_enum rodzaj_srodowiska) {
        this.rodzaj_srodowiska = rodzaj_srodowiska;
    }

    //Metoda obserwatora
    public void aktualizuj_oberwujacego(float czystosc, float jedzenie) {
        this.mnoznik_pieniedzy+= (czystosc-70+jedzenie-70)/10;
    }

    //Metoda na karmienie
    public void Karmienie(int jedzenie){
        this.wskaznik_glodu+=jedzenie/2;
    }

    //Jeszcze pusta metoda wypuszczania zwierzat
    public void release()
    {
            //tu bedzie funkcja release
    }

    //toString wyswietlajacy info o zwierzeciu
    @Override
    public String toString() {
        return "Zwierze{" +
                "nazwa=" + nazwa +
                ", zycie=" + zycie +
                ", sila=" + sila +
                ", wielkosc=" + wielkosc +
                ", wskaznik_glodu=" + wskaznik_glodu +
                ", zmeczenie=" + zmeczenie +
                ", zadowolenie=" + zadowolenie +
                ", przezyte_dni=" + przezyte_dni +
                ", cena=" + cena +
                ", rodzaj=" + getRodzaj_srodowiska() +
                '}';
    }
}
