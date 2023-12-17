package pomocnicze;

import Wybieg_package.rodzaj_wybiegu_enum;

public abstract class zwierzeta implements Obserwujacy_interface {
    private float mnoznik_pieniedzy;
    private int wielkosc;
    private rodzaj_wybiegu_enum rodzaj_zwierzecia;

    public zwierzeta(float mnoznik_pieniedzy, int wielkosc, rodzaj_wybiegu_enum rodzaj) {
        this.mnoznik_pieniedzy = mnoznik_pieniedzy;
        this.wielkosc = wielkosc;
        this.rodzaj_zwierzecia = rodzaj;
    }

    @Override
    public void aktualizuj_oberwujacego(float czystosc, float jedzenie) {
        this.mnoznik_pieniedzy+= (czystosc-70+jedzenie-70)/10;
    }

    public float getMnoznik_pieniedzy() {
        return mnoznik_pieniedzy;
    }

    public void setMnoznik_pieniedzy(float mnoznik_pieniedzy) {
        this.mnoznik_pieniedzy = mnoznik_pieniedzy;
    }

    public int getWielkosc() {
        return wielkosc;
    }

    public void setWielkosc(int wielkosc) {
        this.wielkosc = wielkosc;
    }

    public rodzaj_wybiegu_enum getRodzaj_zwierzecia() {
        return rodzaj_zwierzecia;
    }

    public void setRodzaj_zwierzecia(rodzaj_wybiegu_enum rodzaj_zwierzecia) {
        this.rodzaj_zwierzecia = rodzaj_zwierzecia;
    }

}
