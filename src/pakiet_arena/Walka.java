package pakiet_arena;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import noc_walka.Atak;
import noc_walka.Leczenie;

import java.util.Scanner;

import static pakiet_arena.NocneZoo2.Q_TABLE_FILE;

public class Walka {
    private static final QLearningAgent agent = new QLearningAgent(2, 2);
    private static Zwierze twoje_zwierze;
    private static Zwierze przeciwnik;

    public static void walka(Zwierze twoje_wgrane_zwierze, Zwierze wgrany_przeciwnik) {
        twoje_zwierze = twoje_wgrane_zwierze;
        przeciwnik = wgrany_przeciwnik;

        //odczytanie stanu wuczonego algorytmu
        agent.loadQTableFromFile(Q_TABLE_FILE);

        while (warunki_zakonczenia_walki()){
            if(twoje_zwierze.getSzybkosc()> przeciwnik.getSzybkosc()){
                atak_twojego_zwierzecia();
                if (warunki_zakonczenia_walki()){
                    atak_wrogiego_zwierzecia();
                }
            }
            else{
                atak_wrogiego_zwierzecia();
                if (warunki_zakonczenia_walki()){
                    atak_twojego_zwierzecia();
                }
            }
        }

        // Zapisanie tabeli Q do pliku po zakończeniu gry
        agent.saveQTableToFile(Q_TABLE_FILE);

    }

    private static boolean warunki_zakonczenia_walki() {
        if (twoje_zwierze.getZycie() <= 0) {
        return false;
        } else if (przeciwnik.getZycie() <= 0) {
        return false;
        }
        return true;
    }

    private static void atak_twojego_zwierzecia() {
        boolean powtorzenie_petli = true;
        Scanner scanner = new Scanner(System.in);
        while(powtorzenie_petli){
            powtorzenie_petli = false;

            // Wybór akcji przez gracza
            System.out.println("Wybierz akcję:");
            System.out.println("1. Atak");
            System.out.println("2. Leczenie");

            int wybor = scanner.nextInt();


            switch (wybor) {
                case 1:
                    Atak atak = new Atak();
                    atak.menuAkcji(twoje_zwierze,przeciwnik);
                    break;
                case 2:

                    Leczenie leczenie = new Leczenie();
                    leczenie.menuAkcji(twoje_zwierze, przeciwnik);
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                    powtorzenie_petli = true;
            }
        }
        // Wyświetlenie aktualnych wartości zdrowia zwierząt
        System.out.println("Zycie twojego zwierzaka: " + twoje_zwierze.getZycie());
        System.out.println("zycie przeciwnika: " + przeciwnik.getZycie());
    }

    private static void atak_wrogiego_zwierzecia(){

        int actionPrzeciwnika = agent.chooseAction(1);
        // 0 akcja atak
        // 1 akcja leczenie
        switch (actionPrzeciwnika){
            case 0:
                Atak atakPrzeciwnika = new Atak();
                atakPrzeciwnika.menuAkcji(przeciwnik, twoje_zwierze);
                System.out.println("Przeciwnik zaatakował!");
                break;
            case 1:
                Leczenie leczenieprzeciwnika = new Leczenie();
                leczenieprzeciwnika.menuAkcji(przeciwnik, twoje_zwierze);
                System.out.println("Przeciwnik się leczy.");

                agent.learn(1, actionPrzeciwnika, 1, 5);

                break;
        }
        // Wyświetlenie aktualnych wartości zdrowia zwierząt
        System.out.println("Zycie twojego zwierzaka: " + twoje_zwierze.getZycie());
        System.out.println("zycie przeciwnika: " + przeciwnik.getZycie());
    }


    public static void podsumowanie(Poziom_trudnosci trudnosc,nr_wybiegu_Zwierze wybieg_zwierze){
        if (twoje_zwierze.getZycie() <= 0) {
            przegrana(wybieg_zwierze);
        } else if (przeciwnik.getZycie() <= 0) {
           wygrana(trudnosc);
        }
    }

    private static void przegrana(nr_wybiegu_Zwierze wybieg_zwierze){
        System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
        agent.learn(1, 0, 1, 10); // agent otrzymuje nagrode za to ze doproawdzil do przegrania przez nas gry
        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.getListaWybiegow().get(wybieg_zwierze.nr_wybiegu()).usun_zwierze(wybieg_zwierze.zwierze());
    }
    private  static void wygrana(Poziom_trudnosci trudnosc){
        System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
        agent.learn(1, 0, 1, -10);  // agent otrzymuje kare za to ze doproawdzil do przegrania przez nas gry
        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.getZmiennaZasoby().dodajExp((int) (50*trudnosc.getTrudnosc().getMnoznik()));
        zoo.getZmiennaZasoby().zmienMonety((int) (100*trudnosc.getTrudnosc().getMnoznik()));

    }


}
