package pakiet_sklep;
import java.util.*;
import DzienneZooPakiet.*;
import Klasy_Zwierzat.ZwierzeLadowe;
import Klasy_Zwierzat.ZwierzePowietrzne;
import Klasy_Zwierzat.ZwierzeWodne;
import Klasy_Zwierzat.Zwierze;
import Pracownik_package.Pracownik;
import Wybieg_package.Wybieg_Ladowy;
import Wybieg_package.Wybieg_Powietrzny;
import Wybieg_package.Wybieg_Wodny;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import pakiet_zasoby.Zasoby;

public class Sklep {
    private static int cena_sztuka_jedzenie = 47;
    final private static int cenaopiekun=234;



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




    public void sprzedaj_wybieg(DzienneZoo dzienneZoo, Zasoby zasoby) {
        Scanner scanner = new Scanner(System.in);

        // Wyświetlanie dostępnych wybiegów
        System.out.println("Dostępne wybiegi:");
        List<Wybieg_podstawowy> listaWybiegow = dzienneZoo.getListaWybiegow();

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
        dzienneZoo.usunWybieg(wybiegDoSprzedazy);

        // Wzrost stanu konta po sprzedaży
        int cenaWybiegu = (int) wybiegDoSprzedazy.getCena();
        zasoby.setMonety(zasoby.getMonety() + cenaWybiegu);

        System.out.println("Wybieg został pomyślnie sprzedany. Stan konta wzrósł o " + cenaWybiegu + " monet.");
    }


    public void sprzedaj_opiekuna(DzienneZoo dzienneZoo, Zasoby zasoby) {
        Scanner scanner = new Scanner(System.in);

        // Wyświetlanie dostępnych opiekunów
        System.out.println("Dostępni opiekunowie:");
        List<Pracownik> listaOpiekunow = dzienneZoo.getListaPracownikow();

        for (int i = 0; i < listaOpiekunow.size(); i++) {
            System.out.println((i + 1) + ". " + listaOpiekunow.get(i));
        }


        System.out.print("Wybierz numer opiekuna do sprzedaży: ");
        int numerOpiekuna = scanner.nextInt();


        if (numerOpiekuna < 1 || numerOpiekuna > listaOpiekunow.size()) {
            System.out.println("Błędny numer opiekuna.");
            return;
        }


        Pracownik opiekunDoSprzedazy = listaOpiekunow.get(numerOpiekuna - 1);


        dzienneZoo.usunPracownika(opiekunDoSprzedazy);

        // Wzrost stanu konta po sprzedaży
        int cenaOpiekuna = cenaopiekun;
        zasoby.setMonety(zasoby.getMonety() + cenaOpiekuna);

        System.out.println("Opiekun został pomyślnie sprzedany. Stan konta wzrósł o " + cenaOpiekuna + " monet.");
    }

    public void sprzedaj_zwierze(DzienneZoo dzienneZoo, Zasoby zasoby) {
        Scanner scanner = new Scanner(System.in);

        // Wyświetlanie dostępnych wybiegów
        System.out.println("Dostępne wybiegi:");
        ArrayList<Wybieg_podstawowy> listaWybiegow = dzienneZoo.getListaWybiegow();

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
        zasoby.setMonety(zasoby.getMonety() + cenaZwierzecia);

        System.out.println("Zwierzę zostało pomyślnie sprzedane. Stan konta wzrósł o " + cenaZwierzecia + " monet.");
    }




    public  void kup_jedzenie(int ilosc,Zasoby zasoby){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Obecna cena za sztukę to: " + getCena_sztuka_jedzenie());

            try {
                System.out.print("Podaj ilość jedzenia: ");

                if (ilosc <= 0) {
                    throw new IllegalArgumentException("Błędna wartość. Wprowadź liczbę większą niż 0.");
                }

                int koszt = ilosc * getCena_sztuka_jedzenie();

                if (koszt > zasoby.getMonety()) {
                    throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz mniejszą ilość.");
                }

                zasoby.zmienJedzenie(ilosc);
                zasoby.setMonety(zasoby.getMonety() - koszt);
                System.out.println("zakup udany");
            } catch (InputMismatchException e) {
                System.out.println("Błędny format danych. Wprowadź liczbę całkowitą.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (BrakSrodkowException e) {
                System.out.println(e.getMessage());
            }




    }
    public  Pracownik kup_opiekuna(String imie, String nazwisko, int id, int jakosc, Zasoby zasoby) {
        System.out.println("Obecna cena za opiekuna to: " + cenaopiekun);

        try {
            int koszt = cenaopiekun;
            koszt+= jakosc*10;
            if (koszt > zasoby.getMonety()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz tańszego opiekuna lub zebrać więcej środków.");
            }

            zasoby.zmienMonety(-koszt);
            System.out.println("zakup udany");
            return new Pracownik(imie, nazwisko, id, jakosc);
        } catch (BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Wybieg_podstawowy kup_wybieg(rodzaj_srodowiska_enum rodzajSrodowiska, wielkosc_wybiegu_enum wielkosc_wybiegu, Zasoby zasoby) {
        Wybieg_podstawowy wybieg = null;
        switch (rodzajSrodowiska) {
            case rodzaj_srodowiska_enum.LADOWY ->   wybieg = new Wybieg_Ladowy(null);
            case rodzaj_srodowiska_enum.WODNY ->    wybieg = new Wybieg_Wodny(null);
            case rodzaj_srodowiska_enum.POWIETRZNY ->   wybieg = new Wybieg_Powietrzny(null);

        }
        switch(wielkosc_wybiegu){
            case wielkosc_wybiegu_enum.MALY ->   wybieg.setWielkosc_wybiegu(wielkosc_wybiegu_enum.MALY);
            case wielkosc_wybiegu_enum.SREDNI ->    wybieg.setWielkosc_wybiegu(wielkosc_wybiegu_enum.SREDNI);
            case wielkosc_wybiegu_enum.DUZY ->   wybieg.setWielkosc_wybiegu(wielkosc_wybiegu_enum.DUZY);
        }
        try {
            int koszt =(int) wybieg.getCena();
            if (koszt > zasoby.getMonety()) {
                throw new BrakSrodkowException("Nie masz wystarczająco dużo pieniędzy. Wybierz mniejszy wybieg lub zebrać więcej środków.");
            }

            zasoby.zmienMonety(-koszt);
            System.out.println("Zakup udany");

           return wybieg;

        } catch (BrakSrodkowException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    public Zwierze kup_zwierze(String typ, String nazwa, int zycie, int sila, int wielkosc, int glod, int zmeczenie, int zadowolenie, int cena, Zasoby zasoby) {
        if (cena <= zasoby.getMonety()) {
            return switch (typ) {
                case "Ladowe" -> {
                    zasoby.zmienMonety(-cena);
                    yield new ZwierzeLadowe(nazwa, zycie, sila, wielkosc, glod, zmeczenie, zadowolenie, cena);
                }
                case "Wodne" -> {
                    zasoby.zmienMonety(-cena);
                    yield new ZwierzeWodne(nazwa, zycie, sila, wielkosc, glod, zmeczenie, zadowolenie, cena);
                }
                case "Powietrzne" -> {
                    zasoby.zmienMonety(-cena);
                    yield new ZwierzePowietrzne(nazwa, zycie, sila, wielkosc, glod, zmeczenie, zadowolenie, cena);
                }
                default -> {
                    System.out.println("Nieprawidłowy typ zwierzęcia.");
                    yield null;
                }
            };
        } else {
            System.out.println("Brak wystarczających środków finansowych.");
            return null;
        }
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
