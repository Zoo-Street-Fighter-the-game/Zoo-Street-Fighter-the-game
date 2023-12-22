package pakiet_sklep;
import java.util.*;
import DzienneZooPakiet.*;
import Klasy_Zwierzat.Zwierze;
import Pracownik_package.Pracownik;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import pakiet_zasoby.Zasoby;
import enumy.zwierzeta_enum;

public class Sklep {
    private static int cena_sztuka_jedzenie = 47;
    final private static int cenaPracownika =234;

    private DzienneZoo zoo;

    final int mnoznikCenyPracownika=10;

    public Sklep(DzienneZoo zoo) {
        this.zoo = zoo;
    }

    public static int getCena_sztuka_jedzenie() {
        return cena_sztuka_jedzenie;
    }
    public void setCena_sztuka_jedzenie(int cena_sztuka_jedzenie) {
        Sklep.cena_sztuka_jedzenie = cena_sztuka_jedzenie;
    }


    public void sprzedaj_jedzenie(int ilosc, Zasoby zasoby) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Obecna cena za sztukę to: " + getCena_sztuka_jedzenie());

        try {
            System.out.print("Podaj ilość jedzenia do sprzedania: ");

            if (ilosc <= 0) {
                throw new IllegalArgumentException("Błędna wartość. Wprowadź liczbę większą niż 0.");
            }

            int przychod = ilosc * getCena_sztuka_jedzenie();

            if (ilosc > zasoby.getJedzenie()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo jedzenia. Wybierz mniejszą ilość.");
            }

            zasoby.zmienJedzenie(-ilosc);
            zasoby.setMonety(zasoby.getMonety() + przychod);
            System.out.println("Sprzedaż udana. Zarobiłeś: " + przychod + " monet");
        } catch (InputMismatchException e) {
            System.out.println("Błędny format danych. Wprowadź liczbę całkowitą.");
            scanner.next();
        } catch (IllegalArgumentException | BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }
    }




    public void sprzedaj_wybieg(Zasoby zasoby) {
        Scanner scanner = new Scanner(System.in);

        // Wyświetlanie dostępnych wybiegów
        System.out.println("Dostępne wybiegi:");
        List<Wybieg_podstawowy> listaWybiegow = zoo.getListaWybiegow();

        for (int i = 0; i < listaWybiegow.size(); i++) {
            System.out.println((i + 1) + ". " + listaWybiegow.get(i));
        }

        // Wybór numeru wybiegu do sprzedaży
        System.out.print("Wybierz numer wybiegu do sprzedaży: ");
        int numerWybiegu = scanner.nextInt();

        // Sprawdzenie poprawności wyboru
        if (numerWybiegu < 1 || numerWybiegu > listaWybiegow.size()) {
            System.out.println("Błędny numer wybiegu.");
            return;
        }

        // Wybranie konkretnego wybiegu
        Wybieg_podstawowy wybiegDoSprzedazy = listaWybiegow.get(numerWybiegu - 1);

        // Logika sprzedaży wybiegu
        zoo.usunWybieg(wybiegDoSprzedazy);

        // Wzrost stanu konta po sprzedaży
        int cenaWybiegu = (int) wybiegDoSprzedazy.getCena();
        zasoby.setMonety(zasoby.getMonety() + cenaWybiegu);

        System.out.println("Wybieg został pomyślnie sprzedany. Stan konta wzrósł o " + cenaWybiegu + " monet.");
    }


    public void sprzedaj_pracownika() {
        Scanner scanner = new Scanner(System.in);
        List<Pracownik> listaOpiekunow = zoo.getListaPracownikow();

        if(listaOpiekunow.isEmpty())
        {
            System.out.println("Nie masz zadnych pracownikow");
            return;
        }

        // Wyświetlanie dostępnych opiekunów
        System.out.println("Dostępni pracownicy:");

        for (int i = 0; i < listaOpiekunow.size(); i++) {
            System.out.println((i + 1) + ". " + listaOpiekunow.get(i));
        }


        System.out.print("Wybierz numer pracownika do sprzedaży: ");
        int numerPracownika = scanner.nextInt();


        if (numerPracownika < 1 || numerPracownika > listaOpiekunow.size()) {
            System.out.println("Błędny numer pracownika.");
            return;
        }


        Pracownik opiekunDoSprzedazy = listaOpiekunow.get(numerPracownika - 1);


        zoo.usunPracownika(opiekunDoSprzedazy);

        // Wzrost stanu konta po sprzedaży
        (zoo.getZmiennaZasoby()).setMonety(zoo.getZmiennaZasoby().getMonety() + cenaPracownika*mnoznikCenyPracownika);

        System.out.println("Opiekun został pomyślnie sprzedany. Stan konta wzrósł o " + cenaPracownika*mnoznikCenyPracownika + " monet.");
    }

    public void sprzedaj_zwierze() {
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

        // Logika sprzedaży zwierzęcia
        wybranyWybieg.usun_zwierze(zwierzeDoSprzedazy);

        // Wzrost stanu konta po sprzedaży
        int cenaZwierzecia = zwierzeDoSprzedazy.getCena();
        zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() + cenaZwierzecia);

        System.out.println("Zwierzę zostało pomyślnie sprzedane. Stan konta wzrósł o " + cenaZwierzecia + " monet.");
    }


    public  void kup_jedzenie(int ilosc){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Obecna cena za sztukę to: " + getCena_sztuka_jedzenie());

            try {
                System.out.print("Podaj ilość jedzenia: ");

                if (ilosc <= 0) {
                    throw new IllegalArgumentException("Błędna wartość. Wprowadź liczbę większą niż 0.");
                }

                int koszt = ilosc * getCena_sztuka_jedzenie();

                if (koszt > zoo.getZmiennaZasoby().getMonety()) {
                    throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz mniejszą ilość.");
                }

                zoo.getZmiennaZasoby().zmienJedzenie(ilosc);
                zoo.getZmiennaZasoby().setMonety(zoo.getZmiennaZasoby().getMonety() - koszt);
                System.out.println("zakup udany");

            } catch (InputMismatchException e) {
                System.out.println("Błędny format danych. Wprowadź liczbę całkowitą.");
                scanner.next();
            } catch (IllegalArgumentException | BrakSrodkowException e) {
                System.out.println(e.getMessage());
            }
    }
    public  Pracownik kup_pracownika(String imie, String nazwisko, int jakosc) {

        System.out.println("Obecna cena za pracownika to: " + cenaPracownika*mnoznikCenyPracownika);

        try {
            if (cenaPracownika*mnoznikCenyPracownika > zoo.getZmiennaZasoby().getMonety()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz tańszego pracownika lub zebrać więcej środków.");
            }

            zoo.getZmiennaZasoby().zmienMonety(-cenaPracownika*mnoznikCenyPracownika);
            System.out.println("zakup udany");

            return new Pracownik(imie, nazwisko, jakosc);

        } catch (BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Wybieg_podstawowy kup_wybieg(rodzaj_srodowiska_enum rodzajSrodowiska, wielkosc_wybiegu_enum wielkosc_wybiegu) {
        Wybieg_podstawowy wybieg = new Wybieg_podstawowy(rodzajSrodowiska, wielkosc_wybiegu);
        try {

            if (wybieg.getCena() > zoo.getZmiennaZasoby().getMonety()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz mniejszy wybieg lub zbierz więcej środków.");
            }

            zoo.getZmiennaZasoby().zmienMonety(-wybieg.getCena());
            System.out.println("Zakup udany");

            //zoo.dodajWybieg(wybieg);
           return wybieg;

        } catch (BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }



        return null;
    }


    public Zwierze kup_zwierze(zwierzeta_enum typ) {
        try{
            if(typ.podajCene() <= zoo.getZmiennaZasoby().getMonety())
            {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Kup inne zwierze lub zbierz więcej środków");
            }

            zoo.getZmiennaZasoby().zmienMonety(-typ.podajCene());
            System.out.println("Zakup udany");
            return typ.stworzZwierze();


        } catch (BrakSrodkowException e)
        {
            System.out.println(e.getMessage());

        }
        return null;
    }



    //public  void kup_bron(){} do zrobienia

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
    }
}
