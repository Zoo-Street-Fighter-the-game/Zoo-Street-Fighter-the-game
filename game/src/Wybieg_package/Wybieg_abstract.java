package Wybieg_package;

import pomocnicze.zwierzeta;

import java.util.ArrayList;
import java.util.List;

public abstract class Wybieg_abstract {
    private List<zwierzeta> lista_zwierzat =
            new ArrayList<>();

    public abstract void dodaj_zwierze(zwierzeta obiekt);
    public abstract void usun_zwierze(zwierzeta obiekt);

    public List<zwierzeta> getLista_zwierzat() {
        return lista_zwierzat;
    }

    public void setLista_zwierzat(List<zwierzeta> lista_zwierzat) {
        this.lista_zwierzat = lista_zwierzat;
    }
}
