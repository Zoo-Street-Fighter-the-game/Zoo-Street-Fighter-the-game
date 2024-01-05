package pakiet_arena;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Przedmioty.Przedmiot;
import Wybieg_package.Wybieg_podstawowy;
import enumy.przedmioty_enum;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import enumy.zwierzeta_enum;
import noc_walka.Atak;
import noc_walka.Leczenie;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static pakiet_arena.Wybor_zwierzecia.*;
import static pakiet_arena.Wybor_zwierzecia.stworzenie_zwierzecia_walczacego;

public class NocneZoo2 {
    public static final String Q_TABLE_FILE = "q_table_new.ser";

    public static void main(String[] args) {
        QLearningAgent agent = new QLearningAgent(2, 2);

        //odczytanie stanu wuczonego algorytmu
        agent.loadQTableFromFile(Q_TABLE_FILE);

        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.POWIETRZNY, wielkosc_wybiegu_enum.SREDNI));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(zwierzeta_enum.LOS.stworzZwierze("bob"));
        Poziom_trudnosci poziom_trudnosci = new Poziom_trudnosci();
        poziom_trudnosci.ustaw_poziom_trudnosci();
        Arena arena = new Arena();

        Zwierze twoje_zwierze = wybor_zwierzecia();
        Zwierze przeciwnik = wybor_przeciwnika(poziom_trudnosci.getTrudnosc());

        twoje_zwierze = stworzenie_zwierzecia_walczacego(twoje_zwierze, arena);
        przeciwnik = stworzenie_zwierzecia_walczacego(przeciwnik, arena);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Przedmiot Miecz = przedmioty_enum.MIECZ.stworzPrzedmiot();

        int wybor;
        przeciwnik.setZycie(100);
        przeciwnik.setSila(20);
        twoje_zwierze.setPrzedmiot(Miecz);

        do {
            // Wybór akcji przez gracza
            System.out.println("Wybierz akcję:");
            System.out.println("1. Atak");
            System.out.println("2. Leczenie");
            System.out.println("3. Zakończ walkę");

            wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    Atak atak = new Atak();
                    atak.MenuAkcji(twoje_zwierze, przeciwnik);
                    break;
                case 2:

                    Leczenie leczenie = new Leczenie();
                    leczenie.MenuAkcji(twoje_zwierze, przeciwnik);
                    break;
                case 3:

                    System.out.println("Walka zakończona.");
                    break;
                default:

                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }

            // Sprawdzenie warunków zakończenia walki
            if (warunki_zakonczenia_walki(agent, twoje_zwierze, przeciwnik)) break;

            // Wybór akcji przez przeciwnika na podstawie agenta Q-learningu
            int actionPrzeciwnika = agent.chooseAction(1);
            // 0 akcja atak
            // 1 akcja leczenie

            if (actionPrzeciwnika == 0) {
                // Przeciwnik wykonuje atak
                Atak atakPrzeciwnika = new Atak();
                atakPrzeciwnika.MenuAkcji(przeciwnik, twoje_zwierze);
                System.out.println("Przeciwnik zaatakował!");

                // Sprawdzenie warunków zakończenia walki

            } else {
                // Przeciwnik wykonuje leczenie
                Leczenie leczenieprzeciwnika = new Leczenie();
                leczenieprzeciwnika.MenuAkcji(przeciwnik, twoje_zwierze);
                System.out.println("Przeciwnik się leczy.");


                agent.learn(1, actionPrzeciwnika, 1, 5);

                // Sprawdzenie warunków zakończenia walki

            }
            if (warunki_zakonczenia_walki(agent, twoje_zwierze, przeciwnik)) break;


            if (twoje_zwierze.getZycie() <= 0) {
                System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                break;
            } else if (przeciwnik.getZycie() <= 0) {
                System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                break;
            }

            // Wyświetlenie aktualnych wartości zdrowia zwierząt
            System.out.println("Moje:" + twoje_zwierze.getZycie());
            System.out.println(przeciwnik.getZycie());

        } while (wybor != 3);

        // Zapisanie tabeli Q do pliku po zakończeniu gry
        agent.saveQTableToFile(Q_TABLE_FILE);

    }

    private static boolean warunki_zakonczenia_walki(QLearningAgent agent, Zwierze twoje_zwierze, Zwierze przeciwnik) {
        if (twoje_zwierze.getZycie() <= 0) {
            System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
            agent.learn(1, 0, 1, 10); // agent otrzymuje nagrode za to ze doproawdzil do przegrania przez nas gry
            return true;
        } else if (przeciwnik.getZycie() <= 0) {
            System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
            agent.learn(1, 0, 1, -10);  // agent otrzymuje kare za to ze doproawdzil do przegrania przez nas gry

            return true;
        }
        return false;
    }
}

// Klasa agenta Q-learningu
