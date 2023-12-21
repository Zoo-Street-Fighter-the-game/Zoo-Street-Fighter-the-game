package Wybieg_package;

import Klasy_Zwierzat.Zwierze;

import java.util.ArrayList;
import java.util.List;

public abstract class Wybieg_abstract {
    private List<Zwierze> lista_zwierzat =
            new ArrayList<>();

    public abstract void dodaj_zwierze(Zwierze obiekt);
    public abstract void usun_zwierze(Zwierze obiekt);

    public List<Zwierze> getLista_zwierzat() {
        return lista_zwierzat;
    }

    public void setLista_zwierzat(List<Zwierze> lista_zwierzat) {
        this.lista_zwierzat = lista_zwierzat;
    }
}
