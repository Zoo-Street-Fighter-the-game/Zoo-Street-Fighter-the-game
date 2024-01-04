package Klasy_Zwierzat;

import Przedmioty.Przedmiot;
import enumy.rodzaj_srodowiska_enum;
import interfejsy.Obserwujacy_ZwierzeWybieg_interface;

public  class Zwierze implements Obserwujacy_ZwierzeWybieg_interface {


    //===========================================================================
    //POLA ZWIERZĄT
    //===========================================================================
    private String imie;
    private String nazwa;
    private int zycie;
    private int sila;
    private int szybkosc;
    private int szczescie;
    private int wielkosc;
    private int wskaznik_glodu;
    private int zmeczenie;
    private int zadowolenie;
    private int przezyte_dni = 0;
    private int cena;
    private rodzaj_srodowiska_enum rodzaj;
    private float mnoznik_pieniedzy=1;

    private Przedmiot przedmiot;

    //===========================================================================
    //KONSTRUKTOR
    //===========================================================================
    public Zwierze(String imie, String nazwa, int zycie, int sila,int szybkosc, int szczescie, int wielkosc, int wskaznik_glodu, int zmeczenie, int zadowolenie, int przezyte_dni, int cena,rodzaj_srodowiska_enum rodzaj){
        this.imie=imie;
        this.nazwa = nazwa;
        this.zycie = zycie;
        this.sila = sila;
        this.szybkosc=szybkosc;
        this.szczescie=szczescie;
        this.wielkosc = wielkosc;
        this.wskaznik_glodu = wskaznik_glodu;
        this.zmeczenie = zmeczenie;
        this.zadowolenie = zadowolenie;
        this.przezyte_dni = przezyte_dni;
        this.cena = cena;
        this.rodzaj=rodzaj;
    }


    //===========================================================================
    //METODY - ZMIANA MNOŻNIKA,KARMIENIE, RELEASE
    //===========================================================================

    //Metoda obserwatora
    public void aktualizuj_oberwujacego(float czystosc) {
        this.mnoznik_pieniedzy+= (czystosc-70)/10;
    }

    //Metoda na karmienie
    public void karmienie(int jedzenie){
        this.wskaznik_glodu+=jedzenie/2;
    }

    //Jeszcze pusta metoda wypuszczania zwierzat
    public void release()
    {
        //tu bedzie funkcja release
    }

    public Zwierze deep_clone() {
        return new Zwierze(getImie(),getNazwa(), getZycie(), getSila(),getSzybkosc(),getSzczescie(), getWielkosc(), getWskaznik_glodu(),getZmeczenie(), getZadowolenie(), getPrzezyte_dni(), getCena(), getRodzaj());
    }


    //===========================================================================
    //TOSTRING WYŚWIETLAJĄCY
    //===========================================================================

    //toString wyswietlajacy info o zwierzeciu
    @Override
    public String toString() {
        return "Zwierze{" +
                "imie="+this.getImie()+
                "nazwa=" + this.getNazwa() +
                ", zycie=" + this.getZycie() +
                ", sila=" + this.getSila() +
                ", szybkosc=" + this.getSzybkosc() +
                ", szczescie=" + this.getSzczescie() +
                ", wielkosc=" + this.getWielkosc() +
                ", wskaznik_glodu=" + this.getWskaznik_glodu() +
                ", zmeczenie=" + this.getZmeczenie() +
                ", zadowolenie=" + this.getZadowolenie() +
                ", przezyte_dni=" + this.getPrzezyte_dni() +
                ", cena=" + this.getCena() +
                ", rodzaj=" + this.getRodzaj() +
                '}';
    }

    //===========================================================================
    //SETTERY I GETTERY
    //===========================================================================

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getSzybkosc() {
        return szybkosc;
    }

    public void setSzybkosc(int szybkosc) {
        this.szybkosc = szybkosc;
    }

    public int getSzczescie() {
        return szczescie;
    }

    public void setSzczescie(int szczescie) {
        this.szczescie = szczescie;
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

    public rodzaj_srodowiska_enum getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(rodzaj_srodowiska_enum rodzaj) {
        this.rodzaj = rodzaj;
    }

    public float getMnoznik_pieniedzy() {
        return mnoznik_pieniedzy;
    }

    public void setMnoznik_pieniedzy(float mnoznik_pieniedzy) {
        this.mnoznik_pieniedzy = mnoznik_pieniedzy;
    }

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }
}