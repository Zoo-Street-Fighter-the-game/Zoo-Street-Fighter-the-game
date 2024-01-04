package Przedmioty;

public class Przedmiot {
private String nazwa;
private String opis;
private int zycie;
private int sila;
private int szybkosc;
private int szczescie;
private int cena;

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

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Przedmiot:" +
                "nazwa='" + getNazwa()+ '\'' +
                ", opis='" + getOpis() + '\'' +
                ", zycie=" + getZycie()+
                ", sila=" + getSila() +
                ", szybkosc=" + getSzybkosc() +
                ", szczescie=" + getSzczescie() +
                ", cena=" + getCena();
    }
}
