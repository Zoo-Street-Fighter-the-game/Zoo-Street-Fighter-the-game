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
import Przedmioty.Przedmiot;


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


    public void kup_bron(Przedmiot nazwa_przedmiotu) {
        try {
            if (nazwa_przedmiotu.getCena() > zoo.getZmiennaZasoby().getMonety()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Kup inne zwierzę lub zbierz więcej środków");
            }

            zoo.getZmiennaZasoby().zmienMonety(-nazwa_przedmiotu.getCena());
            System.out.println("Zakup udany");

            // Przypisanie broni do zwierzęcia
            System.out.println("Przypisz do jakiego zwierzecia ma należeć broń:");

            // Wyświetlanie dostępnych wybiegów
            System.out.println("Dostępne wybiegi:");
            ArrayList<Wybieg_podstawowy> listaWybiegow = zoo.getListaWybiegow();

            while (true) {
                // Wybór numeru wybiegu
                System.out.print("Wybierz numer wybiegu: ");
                Scanner scanner = new Scanner(System.in);
                int numerWybiegu = scanner.nextInt();

                // Sprawdzenie poprawności wyboru
                if (numerWybiegu < 1 || numerWybiegu > listaWybiegow.size()) {
                    System.out.println("Błędny numer wybiegu. Spróbuj ponownie.");
                } else {
                    // Wybranie konkretnego wybiegu
                    Wybieg_podstawowy wybranyWybieg = listaWybiegow.get(numerWybiegu - 1);

                    // Wyświetlanie zwierząt w wybranym wybiegu
                    System.out.println("Zwierzęta w wybranym wybiegu:");
                    List<Zwierze> zwierzetaWybiegu = wybranyWybieg.getLista_zwierzat();

                    for (int i = 0; i < zwierzetaWybiegu.size(); i++) {
                        System.out.println((i + 1) + ". " + zwierzetaWybiegu.get(i));
                    }

                    // Wybór numeru zwierzęcia
                    System.out.print("Wybierz numer zwierzęcia: ");
                    int numerZwierzecia = scanner.nextInt();

                    // Sprawdzenie poprawności wyboru
                    if (numerZwierzecia < 1 || numerZwierzecia > zwierzetaWybiegu.size()) {
                        System.out.println("Błędny numer zwierzęcia. Spróbuj ponownie.");
                    } else {
                        // Wybranie konkretnego zwierzęcia
                        Zwierze zwierzeDoPrzypisaniaBroni = zwierzetaWybiegu.get(numerZwierzecia - 1);

                        // Sprawdzenie, czy zwierzę już posiada broń
                        if (zwierzeDoPrzypisaniaBroni.getPrzedmiot() != null) {
                            System.out.println("To zwierzę już posiada broń. Wybierz inne zwierzę.");
                        } else {
                            // Przypisanie broni do zwierzęcia
                            zwierzeDoPrzypisaniaBroni.setPrzedmiot(nazwa_przedmiotu);
                            System.out.println("Broń przypisana do zwierzęcia: " + zwierzeDoPrzypisaniaBroni);
                            zwierzeDoPrzypisaniaBroni.setZycie(zwierzeDoPrzypisaniaBroni.getZycie()+nazwa_przedmiotu.getZycie());
                            break; // Wyjście z pętli, gdy przypisanie broni powiodło się
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Błędny format danych. Wprowadź poprawny numer.");
        } catch (IllegalArgumentException | BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }
    }

    public void zapiszGre(){
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("Plik.ser"))) {
            so.writeObject(getZoo());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Zapis wykonany");
    }
    public void zapiszGre3(){
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("zapis.ser"))) {
            so.writeObject(getZoo());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Zapis wykonany");
    }
/*
*//*    public void wczytajGre() {
        System.out.println(zoo.toString());
        System.out.println("Import");
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("blank.ser"))) {
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


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        updateGUI();
        System.out.println(zoo.toString());

    }*/
    public void wczytajGre2() {
        System.out.println(zoo.toString());
        System.out.println("Import");
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("Plik.ser"))) {
            DzienneZoo zoo2 = (DzienneZoo) is.readObject();
            zoo.getZmiennaZasoby().setMonety(zoo2.getZmiennaZasoby().getMonety());
            zoo.getZmiennaZasoby().setJedzenie(zoo2.getZmiennaZasoby().getJedzenie());
            zoo.getZmiennaZasoby().setExp(zoo2.getZmiennaZasoby().getExp());
            zoo.setDniCounter(zoo2.getDniCounter());

            int dlugosc = ((zoo.getListaPracownikow()).size());
            for(int b = 0; b<dlugosc; b++){
                panelDzienPracownicy.usunPracownika(0);
                zoo.usunPracownika(zoo.getListaPracownikow().get(0));
            }
                zoo.getWybiegDlaBezdomnych().getLista_zwierzat().clear();

            for (int a = 0; a < ((zoo.getListaWybiegow()).size()); a++) {
                for(int j = 0; j<zoo.getListaWybiegow().get(a).getLista_zwierzat().size();j++)
                zoo.getListaWybiegow().get(a).getLista_zwierzat().get(j).setPrzedmiot(null);
            }


                for (int a = 0; a < ((zoo.getListaWybiegow()).size()); a++) {
                    zoo.getListaWybiegow().get(a).getLista_zwierzat().clear();
                }

                panelDzienWybiegi.Wyczysc(this);
                zoo.getListaWybiegow().clear();

            for(int i = 0; i<(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().size()); i++){
                Zwierze x = new Zwierze(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getImie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getNazwa(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getZycie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSila(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzybkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzczescie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWielkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWskaznik_glodu(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getCena(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getRodzaj());
                zoo.getWybiegDlaBezdomnych().dodaj_zwierze(x);
                if (zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getPrzedmiot()!=null){
                    x.setPrzedmiot(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getPrzedmiot());
                }
            }

            for(int i = 0; i<((zoo2.getListaWybiegow()).size()); i++){
                zoo.dodajWybieg(new Wybieg_podstawowy(zoo2.getListaWybiegow().get(i).getRodzaj_srodowiska(), zoo2.getListaWybiegow().get(i).getWielkosc_wybiegu()));
                for(int j = 0; j<(zoo2.getListaWybiegow().get(i).getLista_zwierzat().size());j++){
                   Zwierze z = new Zwierze(zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getImie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getNazwa(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getZycie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSila(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzybkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzczescie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWielkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWskaznik_glodu(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getCena(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getRodzaj());
                    zoo.getListaWybiegow().get(i).dodaj_zwierze(z);
                    if (zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getPrzedmiot()!=null){
                        z.setPrzedmiot(zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getPrzedmiot());
                    }
                }

                PanelWybieg panelWybieg = new PanelWybieg(zoo, this, zoo.getListaWybiegow().getLast());
                panelDzienWybiegi.dodajWybieg(panelWybieg);
                panelDzienPracownicy.getListaObserwatorow().add(panelWybieg);

            }
            for(int i = 0; i<(zoo2.getListaPracownikow()).size(); i++){
                zoo.dodajPracownika(new Pracownik(zoo2.getListaPracownikow().get(i).getImie(),zoo2.getListaPracownikow().get(i).getNazwisko(),zoo2.getListaPracownikow().get(i).getJakoscUslug(), getZoo().getZmiennaZasoby()));
                panelDzienPracownicy.dodajPracownika(zoo.getListaPracownikow().getLast());
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        updateGUI();
        System.out.println(zoo.toString());

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

            int dlugosc = ((zoo.getListaPracownikow()).size());
            for(int b = 0; b<dlugosc; b++){

                panelDzienPracownicy.usunPracownika(0);
                zoo.usunPracownika(zoo.getListaPracownikow().get(0));



            }
                zoo.getWybiegDlaBezdomnych().getLista_zwierzat().clear();

            for (int a = 0; a < ((zoo.getListaWybiegow()).size()); a++) {
                for(int j = 0; j<zoo.getListaWybiegow().get(a).getLista_zwierzat().size();j++)
                    zoo.getListaWybiegow().get(a).getLista_zwierzat().get(j).setPrzedmiot(null);
            }


            for (int a = 0; a < ((zoo.getListaWybiegow()).size()); a++) {
                zoo.getListaWybiegow().get(a).getLista_zwierzat().clear();
            }

            panelDzienWybiegi.Wyczysc(this);
            zoo.getListaWybiegow().clear();

            for(int i = 0; i<(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().size()); i++){
                Zwierze x = new Zwierze(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getImie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getNazwa(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getZycie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSila(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzybkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getSzczescie(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWielkosc(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getWskaznik_glodu(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getCena(),zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getRodzaj());
                zoo.getWybiegDlaBezdomnych().dodaj_zwierze(x);
                if (zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getPrzedmiot()!=null){
                    x.setPrzedmiot(zoo2.getWybiegDlaBezdomnych().getLista_zwierzat().get(i).getPrzedmiot());
                }
            }

            for(int i = 0; i<((zoo2.getListaWybiegow()).size()); i++){
                zoo.dodajWybieg(new Wybieg_podstawowy(zoo2.getListaWybiegow().get(i).getRodzaj_srodowiska(), zoo2.getListaWybiegow().get(i).getWielkosc_wybiegu()));
                for(int j = 0; j<(zoo2.getListaWybiegow().get(i).getLista_zwierzat().size());j++){
                    Zwierze z = new Zwierze(zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getImie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getNazwa(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getZycie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSila(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzybkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getSzczescie(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWielkosc(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getWskaznik_glodu(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getCena(),zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getRodzaj());
                    zoo.getListaWybiegow().get(i).dodaj_zwierze(z);
                    if (zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getPrzedmiot()!=null){
                        z.setPrzedmiot(zoo2.getListaWybiegow().get(i).getLista_zwierzat().get(j).getPrzedmiot());
                    }
                }

                PanelWybieg panelWybieg = new PanelWybieg(zoo, this, zoo.getListaWybiegow().getLast());
                panelDzienWybiegi.dodajWybieg(panelWybieg);
                panelDzienPracownicy.getListaObserwatorow().add(panelWybieg);
            }
            for(int i = 0; i<(zoo2.getListaPracownikow()).size(); i++){
                zoo.dodajPracownika(new Pracownik(zoo2.getListaPracownikow().get(i).getImie(),zoo2.getListaPracownikow().get(i).getNazwisko(),zoo2.getListaPracownikow().get(i).getJakoscUslug(), getZoo().getZmiennaZasoby()));
                panelDzienPracownicy.dodajPracownika(zoo.getListaPracownikow().getLast());
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        updateGUI();
        System.out.println(zoo.toString());

    }
    public void sprzedaj_bron() {
        try {
            // Wyświetlanie dostępnych wybiegów
            System.out.println("Dostępne wybiegi:");
            ArrayList<Wybieg_podstawowy> listaWybiegow = zoo.getListaWybiegow();

            // Wybór numeru wybiegu
            while (true) {
                try {
                    System.out.print("Wybierz numer wybiegu: ");
                    Scanner scanner = new Scanner(System.in);
                    int numerWybiegu = scanner.nextInt();


                    if (numerWybiegu < 1 || numerWybiegu > listaWybiegow.size()) {
                        System.out.println("Błędny numer wybiegu. Spróbuj ponownie.");
                    } else {
                        // Wybranie konkretnego wybiegu
                        Wybieg_podstawowy wybranyWybieg = listaWybiegow.get(numerWybiegu - 1);

                        // Wyświetlanie zwierząt w wybranym wybiegu
                        System.out.println("Zwierzęta w wybranym wybiegu:");
                        List<Zwierze> zwierzetaWybiegu = wybranyWybieg.getLista_zwierzat();

                        for (int i = 0; i < zwierzetaWybiegu.size(); i++) {
                            System.out.println((i + 1) + ". " + zwierzetaWybiegu.get(i));
                        }


                        System.out.print("Wybierz numer zwierzęcia: ");
                        int numerZwierzecia = scanner.nextInt();


                        if (numerZwierzecia < 1 || numerZwierzecia > zwierzetaWybiegu.size()) {
                            System.out.println("Błędny numer zwierzęcia. Spróbuj ponownie.");
                        } else {
                            // Wybranie konkretnego zwierzęcia
                            Zwierze zwierzeDoSprzedazyPrzedmiotu = zwierzetaWybiegu.get(numerZwierzecia - 1);

                            // Sprawdzenie, czy zwierzę posiada broń
                            Przedmiot bronZwierzecia = zwierzeDoSprzedazyPrzedmiotu.getPrzedmiot();
                            if (bronZwierzecia == null) {
                                throw new IllegalArgumentException("To zwierzę nie posiada broni do sprzedania.");
                            }


                            int cenaBroni = bronZwierzecia.getCena();
                            zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() + cenaBroni);
                            zwierzeDoSprzedazyPrzedmiotu.setPrzedmiot(null); // Usunięcie broni ze zwierzęcia

                            System.out.println("Broń została pomyślnie sprzedana.");

                            zwierzeDoSprzedazyPrzedmiotu.setZycie(zwierzeDoSprzedazyPrzedmiotu.getZycie()-bronZwierzecia.getZycie());
                            break;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Błędny format danych. Wprowadź poprawny numer.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
