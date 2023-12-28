package pakiet_arena;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import noc_walka.Atak;
import noc_walka.Leczenie;

import java.util.Random;
import java.util.Scanner;

import static pakiet_arena.Wybor_zwierzecia.*;

public class NocneZoo {
    public static void main(String[] args) {
        // to jest pod testy, ale reszta kodu pod gre już
        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.POWIETRZNY, wielkosc_wybiegu_enum.SREDNI));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(new Zwierze("1", 100, 100, 1, 1, 1, rodzaj_srodowiska_enum.POWIETRZNY));

        Poziom_trudnosci poziom_trudnosci = new Poziom_trudnosci();
        poziom_trudnosci.ustaw_poziom_trudnosci();
        Arena arena = new Arena();

        Zwierze twoje_zwierze = wybor_zwierzecia();
        Zwierze przeciwnik = wybor_przeciwnika(poziom_trudnosci.getTrudnosc());

        twoje_zwierze = stworzenie_zwierzecia_walczacego(twoje_zwierze, arena);
        przeciwnik = stworzenie_zwierzecia_walczacego(przeciwnik, arena);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Menu wyboru akcji
        int wybor;
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
                break;
            } else if (przeciwnik.getZycie() <= 0) {
                System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                break;
            }


            int losowaDecyzja = random.nextInt(2); // 0 - Atak, 1 - Leczenie
            if (losowaDecyzja == 0) {

                Atak atakPrzeciwnika = new Atak();
                atakPrzeciwnika.MenuAkcji(przeciwnik, twoje_zwierze);
                System.out.println("Przeciwnik zaatakował!");
            } else {
                Leczenie leczenieprzeciwnika = new Leczenie();
                leczenieprzeciwnika.MenuAkcji(przeciwnik, twoje_zwierze);
;
                System.out.println("Przeciwnik się leczy.");
            }

            if (twoje_zwierze.getZycie() <= 0) {
                System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                //usuwanie zwierzaka po przegranej
                break;
            } else if (przeciwnik.getZycie() <= 0) {
                System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                //mozna dodać gratyfikacja za wygrana twojego zwierzaka
                break;
            }

        } while (wybor != 3);
    }
}
