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
    private static final String Q_TABLE_FILE = "q_table_new.ser";

    public static void main(String[] args) {
        QLearningAgent agent = new QLearningAgent(2, 2);

        //odczytanie stanu wuczonego algorytmu
        agent.loadQTableFromFile(Q_TABLE_FILE);

        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.POWIETRZNY, wielkosc_wybiegu_enum.SREDNI));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(zwierzeta_enum.LOS.stworzZwierze());
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
class QLearningAgent {
    // Tabela Q przechowująca wartości dla stanów i akcji
    private double[][] qTable;
    private double learningRate;
    private double discountFactor;
    // Parametr epsilon używany do eksploracji losowej
    private double epsilon;


    public QLearningAgent(int stateSize, int actionSize) {
        // Inicjalizacja tablicy Q
        qTable = new double[stateSize][actionSize];

        learningRate = 0.15;
        discountFactor = 0.9999;
        epsilon = 0.2;
    }

    // Metoda do wyboru akcji przez agenta z uwzględnieniem eksploracji losowej
    public int chooseAction(int state) {
        if (Math.random() < epsilon) {
            // Wybór losowej akcji z pewnym prawdopodobieństwem epsilon
            return new Random().nextInt(qTable[state].length);
        } else {
            // Wybór akcji na podstawie wartości Q
            int bestAction = 0;
            for (int i = 1; i < qTable[state].length; i++) {
                if (qTable[state][i] > qTable[state][bestAction]) {
                    bestAction = i;
                }
            }

            // Dodatkowa eksploracja - zamiana akcji 1 na 0 z pewnym prawdopodobieństwem (1 -leczenie 0 - atak)
            // oslabienie leczenia, poniewaz najkorzystniejszym wyborem zawsze bedzie leczenie
            if (bestAction == 1 && Math.random() < 0.5) {
                return 0;
            } else {
                return bestAction;
            }
        }
    }

    // Metoda do nauki agenta na podstawie otrzymanych nagród
    public void learn(int state, int action, int nextState, int reward) {
        double predict = qTable[state][action];
        double target = discountFactor * maxQValue(nextState);
        qTable[state][action] += learningRate * (reward + target - predict);
    }

    // Metoda zwracająca maksymalną wartość Q dla danego stanu
    private double maxQValue(int state) {
        double maxQ = qTable[state][0];
        for (int i = 1; i < qTable[state].length; i++) {
            if (qTable[state][i] > maxQ) {
                maxQ = qTable[state][i];
            }
        }
        return maxQ;
    }

    // Metoda do zapisu tabeli Q do pliku
    public void saveQTableToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(qTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda do wczytania tabeli Q z pliku
    public void loadQTableFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            qTable = (double[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Metoda do wyświetlenia zawartości tabeli Q
    public void displayQTable() {
        for (int i = 0; i < qTable.length; i++) {
            for (int j = 0; j < qTable[i].length; j++) {
                System.out.print(qTable[i][j] + "\t");
            }
            System.out.println();
        }
    }
}