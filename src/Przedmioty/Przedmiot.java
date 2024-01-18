package Przedmioty;

import java.io.Serializable;

public class Przedmiot implements Serializable {
private final String nazwa;
private final String opis;
private final int zycie;
private final int sila;
private final int szybkosc;
private final int szczescie;
private final int cena;

    public Przedmiot(String nazwa, String opis, int cena, int sila, int szczescie, int szybkosc,int zycie) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.zycie = zycie;
        this.sila = sila;
        this.szybkosc = szybkosc;
        this.szczescie = szczescie;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getZycie() {
        return zycie;
    }

    public int getSila() {
        return sila;
    }

    public int getSzybkosc() {
        return szybkosc;
    }

    public int getSzczescie() {
        return szczescie;
    }

    public int getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return "Przedmiot:" +
                "nazwa='" + getNazwa()+ '\'' +
                ", zycie=" + getZycie()+
                ", sila=" + getSila() +
                ", szybkosc=" + getSzybkosc() +
                ", szczescie=" + getSzczescie() +
                ", cena=" + getCena();
    }
}
