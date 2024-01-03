package pakiet_sklep;
import java.util.*;

import DzienneZooPakiet.*;
import Klasy_Zwierzat.Zwierze;
import Pracownik_package.Pracownik;
import Wybieg_package.Wybieg_abstract;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import gui_oknaPopUp.OknoKupZwierze;
import enumy.zwierzeta_enum;
import gui_package.PanelDzienPracownicy;
import gui_package.PanelDzienWybiegi;
import gui_package.PanelWybieg;
import interfejsy.ObserwujacyPracownikGUI_interface;
import interfejsy.UpdateGUI;


public class Sklep {
    final private static int cena_sztuka_jedzenie = 1;
    final private static int cenaPracownika =10;
    private ArrayList<UpdateGUI> listaGUI;

    private PanelDzienWybiegi panelDzienWybiegi;
    private PanelDzienPracownicy panelDzienPracownicy;
    private DzienneZoo zoo;


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



        try {
            if (ilosc <= 0) {
                throw new IllegalArgumentException("Błędna wartość. Wprowadź liczbę większą niż 0.");
            }

            int przychod = ilosc * getCena_sztuka_jedzenie();

            if (ilosc > zoo.getZmiennaZasoby().getJedzenie()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo jedzenia. Wybierz mniejszą ilość.");
            }

            zoo.getZmiennaZasoby().zmienJedzenie(-ilosc);
            zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() + przychod);
            System.out.println("Sprzedaż jedzenia udana. Zarobiłeś: " + przychod + " monet");
            updateGUI();
        } catch (InputMismatchException e) {
            System.out.println("Błędny format danych. Wprowadź liczbę całkowitą.");

        } catch (IllegalArgumentException | BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }
    }




    public void sprzedaj_wybieg(Wybieg_podstawowy wybieg, ObserwujacyPracownikGUI_interface panelWybieg) {

        // Logika sprzedaży wybiegu
        panelDzienPracownicy.getListaObserwatorow().remove(panelWybieg);
        zoo.usunWybieg(wybieg);


        // Wzrost stanu konta po sprzedaży
        int cenaWybiegu = (int) wybieg.getCena();
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
        /*
        Scanner scanner = new Scanner(System.in);

        // Wyświetlanie dostępnych wybiegów
        System.out.println("Dostępne wybiegi:");
        ArrayList<Wybieg_podstawowy> listaWybiegow = zoo.getListaWybiegow();

        for (int i = 0; i < listaWybiegow.size(); i++) {
            System.out.println((i + 1) + ". " + listaWybiegow.get(i));
        }

        // Wybór numeru wybiegu
        System.out.print("Wybierz numer wybiegu: ");
        int numerWybiegu = scanner.nextInt();

        // Sprawdzenie poprawności wyboru
        if (numerWybiegu < 1 || numerWybiegu > listaWybiegow.size()) {
            System.out.println("Błędny numer wybiegu.");
            return;
        }

        // Wybranie konkretnego wybiegu
        Wybieg_podstawowy wybranyWybieg = listaWybiegow.get(numerWybiegu - 1);

        // Wyświetlanie zwierząt w wybranym wybiegu
        System.out.println("Zwierzęta w wybranym wybiegu:");
        List<Zwierze> zwierzetaWybiegu = wybranyWybieg.getLista_zwierzat();

        for (int i = 0; i < zwierzetaWybiegu.size(); i++) {
            System.out.println((i + 1) + ". " + zwierzetaWybiegu.get(i));
        }

        // Wybór numeru zwierzęcia do sprzedaży
        System.out.print("Wybierz numer zwierzęcia do sprzedaży: ");
        int numerZwierzecia = scanner.nextInt();

        // Sprawdzenie poprawności wyboru
        if (numerZwierzecia < 1 || numerZwierzecia > zwierzetaWybiegu.size()) {
            System.out.println("Błędny numer zwierzęcia.");
            return;
        }

        // Wybranie konkretnego zwierzęcia
        Zwierze zwierzeDoSprzedazy = zwierzetaWybiegu.get(numerZwierzecia - 1);
*/
        // Logika sprzedaży zwierzęcia
        wybieg.usun_zwierze(zwierze);

        // Wzrost stanu konta po sprzedaży
        zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() + zwierze.getCena());
        updateGUI();

        System.out.println("Zwierzę zostało pomyślnie sprzedane. Stan konta wzrósł o " + zwierze.getCena() + " monet.");
    }


    public  void kup_jedzenie(int ilosc){


            try {


                if (ilosc <= 0) {
                    throw new IllegalArgumentException("Błędna wartość. Wprowadź liczbę większą niż 0.");
                }

                int koszt = ilosc * getCena_sztuka_jedzenie();

                if (koszt > zoo.getZmiennaZasoby().getMonety()) {
                    throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz mniejszą ilość.");
                }

                zoo.getZmiennaZasoby().zmienJedzenie(ilosc);
                zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() - koszt);
                updateGUI();
                System.out.println("zakup udany");

            } catch (InputMismatchException e) {
                System.out.println("Błędny format danych. Wprowadź liczbę całkowitą.");

            } catch (IllegalArgumentException | BrakSrodkowException e) {
                System.out.println(e.getMessage());
            }
    }
    public void kup_pracownika(String imie, String nazwisko, int jakosc) {

        System.out.println("Obecna cena za pracownika to: " + cenaPracownika*jakosc);

        try {
            if (cenaPracownika*jakosc > zoo.getZmiennaZasoby().getMonety()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz tańszego pracownika lub zebrać więcej środków.");
            }

            zoo.getZmiennaZasoby().zmienMonety(-cenaPracownika*jakosc);
            System.out.println("zakup pracownika udany");
            zoo.dodajPracownika(new Pracownik(imie,nazwisko,jakosc, getZoo().getZmiennaZasoby()));
            panelDzienPracownicy.dodajPracownika(zoo.getListaPracownikow().getLast());
            updateGUI();


        } catch (BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }

    }

    public void kup_wybieg(rodzaj_srodowiska_enum rodzajSrodowiska, wielkosc_wybiegu_enum wielkosc_wybiegu) {
        try {

            if (wielkosc_wybiegu.getLiczbowa_Cena_Wybiegu() > zoo.getZmiennaZasoby().getMonety()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz mniejszy wybieg lub zbierz więcej środków.");
            }

            zoo.getZmiennaZasoby().zmienMonety(-wielkosc_wybiegu.getLiczbowa_Cena_Wybiegu());
            System.out.println("Zakup wybiegu udany");


            zoo.dodajWybieg(new Wybieg_podstawowy(rodzajSrodowiska, wielkosc_wybiegu));
            PanelWybieg panelWybieg = new PanelWybieg(zoo, this, zoo.getListaWybiegow().getLast());
            panelDzienWybiegi.dodajWybieg(panelWybieg);
            panelDzienPracownicy.getListaObserwatorow().add(panelWybieg);
            updateGUI();

        } catch (BrakSrodkowException e) {
            System.out.println(e.getMessage());
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



    //public  void kup_bron(){} do zrobienia
/*
    public static Thread startMessageSenderThread() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    sendMessage();
                    Thread.sleep(30000); // Czekaj 10 sekund
                }
            } catch (InterruptedException e) {
                // Przerwanie wątku
                System.out.println("Wątek przerwany.");
            }
        });

        thread.start();
        return thread;
    }

    private static void sendMessage() {
        String[] messages = {
                "To jest losowa wiadomość 1",
                "To jest losowa wiadomość 2",
                "To jest losowa wiadomość 3"
        };

        Random random = new Random();
        int index = random.nextInt(messages.length);
        String message = messages[index];
        System.out.println();
        System.out.println("   .------.");
        System.out.println("  | o  o |  " + message);
        System.out.println("  |  ^   | ");
        System.out.println("   '-----'");
        System.out.println();
    }*/
}
