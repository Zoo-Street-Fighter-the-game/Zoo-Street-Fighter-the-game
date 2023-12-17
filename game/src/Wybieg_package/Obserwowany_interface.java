package Wybieg_package;

public interface Obserwowany_interface {
    void dodaj_obserwatora( Obserwujacy_interface o);
    void usun_obserwatora(Obserwujacy_interface o);
    void powiadom_obserwatorow();
}
