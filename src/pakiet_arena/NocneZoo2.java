package pakiet_arena;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import noc_walka.Atak;
import noc_walka.Leczenie;
import pakiet_arena.Arena;
import pakiet_arena.Poziom_trudnosci;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static pakiet_arena.Wybor_zwierzecia.*;
import static pakiet_arena.Wybor_zwierzecia.stworzenie_zwierzecia_walczacego;

public class NocneZoo2 {
    private static final String Q_TABLE_FILE = "q_table.ser";

    public static void main(String[] args) {
        QLearningAgent agent = new QLearningAgent(2, 2);
        agent.loadQTableFromFile(Q_TABLE_FILE);

        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.POWIETRZNY, wielkosc_wybiegu_enum.SREDNI));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(new Zwierze("1", 100, 20, 1, 1, 1, rodzaj_srodowiska_enum.POWIETRZNY));

        Poziom_trudnosci poziom_trudnosci = new Poziom_trudnosci();
        poziom_trudnosci.ustaw_poziom_trudnosci();
        Arena arena = new Arena();

        Zwierze twoje_zwierze = wybor_zwierzecia();
        Zwierze przeciwnik = wybor_przeciwnika(poziom_trudnosci.getTrudnosc());

        twoje_zwierze = stworzenie_zwierzecia_walczacego(twoje_zwierze, arena);
        przeciwnik = stworzenie_zwierzecia_walczacego(przeciwnik, arena);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int wybor;
        przeciwnik.setZycie(100);
        przeciwnik.setSila(20);

        do {
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

            if (twoje_zwierze.getZycie() <= 0) {
                System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                agent.learn(1, 0, 1, 10); // Nagroda za przegraną walkę przeciwnika
                break;
            } else if (przeciwnik.getZycie() <= 0) {
                System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                agent.learn(1, 0, 1, -10); // Kara za wygraną walkę przeciwnika
                break;
            }


            int actionPrzeciwnika = agent.chooseAction(1);

            if (actionPrzeciwnika == 0) {
                Atak atakPrzeciwnika = new Atak();
                atakPrzeciwnika.MenuAkcji(przeciwnik, twoje_zwierze);
                System.out.println("Przeciwnik zaatakował!");
                if (twoje_zwierze.getZycie() <= 0) {
                    System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, 10);
                    break;
                } else if (przeciwnik.getZycie() <= 0) {
                    System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, -10);
                    break;
                }

            } else {
                Leczenie leczenieprzeciwnika = new Leczenie();
                leczenieprzeciwnika.MenuAkcji(przeciwnik, twoje_zwierze);
                System.out.println("Przeciwnik się leczy.");

                agent.learn(1, actionPrzeciwnika, 1, 5); // Nagroda za leczenie przeciwnika

                if (twoje_zwierze.getZycie() <= 0) {
                    System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, 10); // Nagroda za przegraną walkę przeciwnika
                    break;
                } else if (przeciwnik.getZycie() <= 0) {
                    System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, -10); // Kara za wygraną walkę przeciwnika
                    break;
                }

            }

            if (twoje_zwierze.getZycie() <= 0) {
                System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                break;
            } else if (przeciwnik.getZycie() <= 0) {
                System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                break;
            }
            System.out.println("Moje:"+twoje_zwierze.getZycie());
            System.out.println(przeciwnik.getZycie());

        } while (wybor != 3);
        agent.saveQTableToFile(Q_TABLE_FILE);
    }
}

class QLearningAgent {
    private double[][] qTable;
    private double learningRate;
    private double discountFactor;
    private double epsilon;

    public QLearningAgent(int stateSize, int actionSize) {
        qTable = new double[stateSize][actionSize];
        learningRate = 0.1;
        discountFactor = 0.9;
        epsilon = 0.1;
    }

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

            // Zmniejszenie prawdopodobieństwa wyboru akcji leczenia
            if (bestAction == 1 && Math.random() < 0.7) {
                return 0;  // Wybierz atak zamiast leczenia w 70% przypadków
            } else {
                return bestAction;
            }
        }
    }


    public void learn(int state, int action, int nextState, int reward) {
        double predict = qTable[state][action];
        double target = discountFactor * maxQValue(nextState);
        qTable[state][action] += learningRate * (reward + target - predict);
    }

    private double maxQValue(int state) {
        double maxQ = qTable[state][0];
        for (int i = 1; i < qTable[state].length; i++) {
            if (qTable[state][i] > maxQ) {
                maxQ = qTable[state][i];
            }
        }
        return maxQ;
    }

    public void saveQTableToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(qTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadQTableFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            qTable = (double[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
