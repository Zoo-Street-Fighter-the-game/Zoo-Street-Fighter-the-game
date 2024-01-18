package pakiet_sklep;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.*;

import DzienneZooPakiet.*;
import Klasy_Zwierzat.Zwierze;
import Pracownik_package.Pracownik;
import Wybieg_package.Wybieg_abstract;
import Wybieg_package.Wybieg_podstawowy;
import enumy.przedmioty_enum;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import gui_oknaPopUp.*;
import enumy.zwierzeta_enum;
import gui_package.PanelDzienPracownicy;
import gui_package.PanelDzienWybiegi;
import gui_package.PanelWybieg;
import interfejsy.ObserwujacyPracownikGUI_interface;
import interfejsy.UpdateGUI;


public class Sklep {
    final private static int cena_sztuka_jedzenie = 1;
    final private static int cenaPracownika =10;
    private final ArrayList<UpdateGUI> listaGUI;

    private PanelDzienWybiegi panelDzienWybiegi;
    private PanelDzienPracownicy panelDzienPracownicy;
    private final DzienneZoo zoo;

    public static int getCena_sztuka_jedzenie() {
        return cena_sztuka_jedzenie;
    }
    public void setPanelDzienWybiegi(PanelDzienWybiegi panelDzienWybiegi) {this.panelDzienWybiegi = panelDzienWybiegi;}
    public void setPanelDzienPracownicy(PanelDzienPracownicy panelDzienPracownicy) {this.panelDzienPracownicy = panelDzienPracownicy;}
    public int getCenaPracownika() {return cenaPracownika;}
    //SETTER I GETTER DLA OBIEKTU DzienneZoo
    public DzienneZoo getZoo() {
        return zoo;
    }
    public Sklep(DzienneZoo zoo) {
        listaGUI = new ArrayList<>();
        this.zoo = zoo;
    }


    //METODY KLASY
    public void sprzedaj_jedzenie(int ilosc) {

        int przychod = ilosc * getCena_sztuka_jedzenie();

        if (ilosc > zoo.getZmiennaZasoby().getJedzenie()) {
            OknoSprzedajJedzenie.brakJedzenia();
        }
        else {
            zoo.getZmiennaZasoby().zmienJedzenie(-ilosc);
            zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() + przychod);
            System.out.println("Sprzedaż jedzenia udana. Zarobiłeś: " + przychod + " monet");
            updateGUI();
        }
    }




    public void sprzedaj_wybieg(Wybieg_podstawowy wybieg, ObserwujacyPracownikGUI_interface panelWybieg) {

        // Logika sprzedaży wybiegu
        panelDzienPracownicy.getListaObserwatorow().remove(panelWybieg);
        zoo.usunWybieg(wybieg);


        // Wzrost stanu konta po sprzedaży
        int cenaWybiegu =  wybieg.getCena();

        zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() + cenaWybiegu);

        System.out.println("Wybieg został pomyślnie sprzedany. Stan konta wzrósł o " + cenaWybiegu + " monet.");
        updateGUI();
    }


    public void sprzedaj_pracownika(int numerPracownika) {

        // Wzrost stanu konta po sprzedaży

        (zoo.getZmiennaZasoby()).setMonety(zoo.getZmiennaZasoby().getMonety() + cenaPracownika*zoo.getListaPracownikow().get(numerPracownika).getJakoscUslug());


        System.out.println("Opiekun został pomyślnie sprzedany. Stan konta wzrósł o " + cenaPracownika*zoo.getListaPracownikow().get(numerPracownika).getJakoscUslug() + " monet.");

        panelDzienPracownicy.usunPracownika(numerPracownika);
        zoo.usunPracownika(zoo.getListaPracownikow().get(numerPracownika));

        updateGUI();
    }

    public void sprzedaj_zwierze(Wybieg_abstract wybieg, Zwierze zwierze) {

        // Logika sprzedaży zwierzęcia
        wybieg.usun_zwierze(zwierze);

        // Wzrost stanu konta po sprzedaży
        zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() + zwierze.getCena());
        updateGUI();


        System.out.println("Zwierzę zostało pomyślnie sprzedane. Stan konta wzrósł o " + zwierze.getCena() + " monet.");
    }


    public  void kup_jedzenie(int ilosc) {


        int koszt = ilosc * getCena_sztuka_jedzenie();

        if (koszt > zoo.getZmiennaZasoby().getMonety()) {
            OknoKupJedzenie.brakSrodkow();
        }
        else {
        zoo.getZmiennaZasoby().zmienJedzenie(ilosc);
        zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() - koszt);
        updateGUI();
        System.out.println("zakup udany");

        }
    }
    public void kup_pracownika(String imie, String nazwisko, int jakosc) {

        System.out.println("Obecna cena za pracownika to: " + cenaPracownika*jakosc);


            if (cenaPracownika*jakosc > zoo.getZmiennaZasoby().getMonety()) {
                OknoKupPracownika.brakSrodkow();

            }
            else{
            zoo.getZmiennaZasoby().zmienMonety(-cenaPracownika*jakosc);
            System.out.println("zakup pracownika udany");
            zoo.dodajPracownika(new Pracownik(imie,nazwisko,jakosc, getZoo().getZmiennaZasoby()));
            panelDzienPracownicy.dodajPracownika(zoo.getListaPracownikow().getLast());
            updateGUI();

        }

    }

    public void kup_wybieg(rodzaj_srodowiska_enum rodzajSrodowiska, wielkosc_wybiegu_enum wielkosc_wybiegu) {


            if (wielkosc_wybiegu.getLiczbowa_Cena_Wybiegu() > zoo.getZmiennaZasoby().getMonety()) {
                OknoKupWybieg.brakSrodkow();
            }
            else {

                zoo.getZmiennaZasoby().zmienMonety(-wielkosc_wybiegu.getLiczbowa_Cena_Wybiegu());
                System.out.println("Zakup wybiegu udany");


                zoo.dodajWybieg(new Wybieg_podstawowy(rodzajSrodowiska, wielkosc_wybiegu));
                PanelWybieg panelWybieg = new PanelWybieg(zoo, this, zoo.getListaWybiegow().getLast());
                panelDzienWybiegi.dodajWybieg(panelWybieg);
                panelDzienPracownicy.getListaObserwatorow().add(panelWybieg);
                updateGUI();

            }

    }


    public void kup_zwierze(zwierzeta_enum typ, Wybieg_podstawowy wybieg, String imie) {
            if(typ.getCena() > zoo.getZmiennaZasoby().getMonety())
            {
                OknoKupZwierze.brakSrodkow();
            }
            else {
                zoo.getZmiennaZasoby().zmienMonety(-typ.getCena());
                System.out.println("Zakup zwierzecia udany");
                wybieg.dodaj_zwierze(typ.stworzZwierze(imie));
                updateGUI();
            }

    }

    public void nakarmZwierzeta(Pracownik pracownik, Wybieg_abstract wybieg, int ilosc)
    {
        boolean czyGlodne = false;
        for(Zwierze zwierze : wybieg.getLista_zwierzat())
        {
            if(zwierze.getWskaznik_glodu()<100) {
                czyGlodne = true;
                break;
            }
        }

        if(czyGlodne) {
            pracownik.nakarmZwierze(ilosc, wybieg);
            for (ObserwujacyPracownikGUI_interface o : panelDzienPracownicy.getListaObserwatorow()) {
                o.reakcjaZaznaczenie();
            }
            updateGUI();

        }
    }

    public void wyczyscWybieg(Pracownik pracownik, Wybieg_podstawowy wybieg)
    {
        if(wybieg.getCzystosc()<100) {
            pracownik.umyjWybieg(wybieg);
            for(ObserwujacyPracownikGUI_interface o : panelDzienPracownicy.getListaObserwatorow())
            {
                o.reakcjaZaznaczenie();
            }
            updateGUI();
        }
    }

    public void przeniesZwierzeBezdomni(Wybieg_podstawowy wybieg, Zwierze zwierze)
    {
        zoo.przeniesZwierze_bezdomni(wybieg, zwierze);
    }


    public void zakonczDzien(){
        zoo.zakonczDzien();
    }
    //obserwator GUI

    public void dodajObsewatoraGUI(UpdateGUI G)
    {
        listaGUI.add(G);
    }

    public void updateGUI()
    {
        for(UpdateGUI o : listaGUI)
        {
            o.updateGUI();
        }
    }


    public void kupBron(przedmioty_enum x, Zwierze y){

        if(x.stworzPrzedmiot().getCena()<=zoo.getZmiennaZasoby().getMonety()) {
            y.setPrzedmiot(x.stworzPrzedmiot());
            zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety()-x.stworzPrzedmiot().getCena());
            updateGUI();
            System.out.println("yeet");
        } else {
                SklepPrzedmioty.brakSrodkow();
            }
        }



    public void zapiszGre(){
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("Plik.ser"))) {
            so.writeObject(getZoo());

        } catch (IOException ignored) {
        }
        System.out.println("Zapis wykonany");
    }

    public void zapiszGre3(){
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("zapis.ser"))) {
            so.writeObject(getZoo());

        } catch (IOException ignored) {
        }
        System.out.println("Zapis wykonany");
    }

    public void wczytajGre2() {
        System.out.println(zoo.toString());
        System.out.println("Import");
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("Plik.ser"))) {
            DzienneZoo zoo2 = (DzienneZoo) is.readObject();
            zoo.getZmiennaZasoby().setMonety(zoo2.getZmiennaZasoby().getMonety());
            zoo.getZmiennaZasoby().setJedzenie(zoo2.getZmiennaZasoby().getJedzenie());
            zoo.getZmiennaZasoby().setExp(zoo2.getZmiennaZasoby().getExp());
            zoo.setDniCounter(zoo2.getDniCounter());

            for(int b = 0; b<((zoo.getListaPracownikow()).size()); b++){
                zoo.usunPracownika(zoo.getListaPracownikow().get(b));
                panelDzienPracownicy.usunPracownika(b);
                b--;

            }
                zoo.getWybiegDlaBezdomnych().getLista_zwierzat().clear();

                for (int a = 0; a < ((zoo.getListaWybiegow()).size()); a++) {
                    zoo.getListaWybiegow().get(a).getLista_zwierzat().clear();
                }

                panelDzienWybiegi.Wyczysc(this);
                zoo.getListaWybiegow().clear();

            for(int i = 0; i<(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().size()); i++){
                zoo.getWybiegDlaBezdomnych().dodaj_zwierze(new Zwierze(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getImie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getNazwa(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getZycie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSila(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzybkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzczescie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWielkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWskaznik_glodu(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getCena(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getRodzaj()));
            }

            for(int i = 0; i<((zoo2.getListaWybiegow()).size()); i++){
                zoo.dodajWybieg(new Wybieg_podstawowy(zoo2.getListaWybiegow().get(i).getRodzaj_srodowiska(), zoo2.getListaWybiegow().get(i).getWielkosc_wybiegu()));
                for(int j = 0; j<(zoo2.getListaWybiegow().get(i).getLista_zwierzat().size());j++){
                    zoo.getListaWybiegow().get(i).dodaj_zwierze(new Zwierze(zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getImie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getNazwa(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getZycie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSila(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzybkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzczescie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWielkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWskaznik_glodu(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getCena(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getRodzaj()));
                }

                PanelWybieg panelWybieg = new PanelWybieg(zoo, this, zoo.getListaWybiegow().getLast());
                panelDzienWybiegi.dodajWybieg(panelWybieg);
                panelDzienPracownicy.getListaObserwatorow().add(panelWybieg);


            }
            for(int i = 0; i<(zoo2.getListaPracownikow()).size(); i++){
                zoo.dodajPracownika(new Pracownik(zoo2.getListaPracownikow().get(i).getImie(),zoo2.getListaPracownikow().get(i).getNazwisko(),zoo2.getListaPracownikow().get(i).getJakoscUslug(), getZoo().getZmiennaZasoby()));
                panelDzienPracownicy.dodajPracownika(zoo.getListaPracownikow().getLast());
            }


        } catch (IOException | ClassNotFoundException ignored) {
        }
        updateGUI();
        System.out.println(zoo);

    }
    public void wczytajGre3() {
        System.out.println(zoo.toString());
        System.out.println("Import");
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("zapis.ser"))) {
            DzienneZoo zoo2 = (DzienneZoo) is.readObject();
            zoo.getZmiennaZasoby().setMonety(zoo2.getZmiennaZasoby().getMonety());
            zoo.getZmiennaZasoby().setJedzenie(zoo2.getZmiennaZasoby().getJedzenie());
            zoo.getZmiennaZasoby().setExp(zoo2.getZmiennaZasoby().getExp());
            zoo.setDniCounter(zoo2.getDniCounter());

            for(int b = 0; b<((zoo.getListaPracownikow()).size()); b++){
                zoo.usunPracownika(zoo.getListaPracownikow().get(b));
                panelDzienPracownicy.usunPracownika(b);
                b--;

            }
                zoo.getWybiegDlaBezdomnych().getLista_zwierzat().clear();

                for (int a = 0; a < ((zoo.getListaWybiegow()).size()); a++) {
                    zoo.getListaWybiegow().get(a).getLista_zwierzat().clear();
                }

                panelDzienWybiegi.Wyczysc(this);
                zoo.getListaWybiegow().clear();

            for(int i = 0; i<(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().size()); i++){
                zoo.getWybiegDlaBezdomnych().dodaj_zwierze(new Zwierze(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getImie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getNazwa(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getZycie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSila(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzybkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzczescie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWielkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWskaznik_glodu(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getCena(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getRodzaj()));
            }

            for(int i = 0; i<((zoo2.getListaWybiegow()).size()); i++){
                zoo.dodajWybieg(new Wybieg_podstawowy(zoo2.getListaWybiegow().get(i).getRodzaj_srodowiska(), zoo2.getListaWybiegow().get(i).getWielkosc_wybiegu()));
                for(int j = 0; j<(zoo2.getListaWybiegow().get(i).getLista_zwierzat().size());j++){
                    zoo.getListaWybiegow().get(i).dodaj_zwierze(new Zwierze(zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getImie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getNazwa(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getZycie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSila(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzybkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzczescie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWielkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWskaznik_glodu(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getCena(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getRodzaj()));
                }

                PanelWybieg panelWybieg = new PanelWybieg(zoo, this, zoo.getListaWybiegow().getLast());
                panelDzienWybiegi.dodajWybieg(panelWybieg);
                panelDzienPracownicy.getListaObserwatorow().add(panelWybieg);
            }
            for(int i = 0; i<(zoo2.getListaPracownikow()).size(); i++){
                zoo.dodajPracownika(new Pracownik(zoo2.getListaPracownikow().get(i).getImie(),zoo2.getListaPracownikow().get(i).getNazwisko(),zoo2.getListaPracownikow().get(i).getJakoscUslug(), getZoo().getZmiennaZasoby()));
                panelDzienPracownicy.dodajPracownika(zoo.getListaPracownikow().getLast());
            }


        } catch (IOException | ClassNotFoundException ignored) {
        }
        updateGUI();
        System.out.println(zoo);

    }


}
