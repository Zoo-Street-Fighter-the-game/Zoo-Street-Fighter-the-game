package pakiet_arena;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import enumy.poziom_trudnosci_enum;
import enumy.rodzaj_srodowiska_enum;
import enumy.zwierzeta_enum;

import java.util.Random;
import java.util.Scanner;


public class Wybor_zwierzecia {

    public static nr_wybiegu_Zwierze wybor_zwierzecia(){
        DzienneZoo zoo = DzienneZoo.getInstance();
        nr_wybiegu_Zwierze wynik = null;
        Scanner scanner = new Scanner(System.in);

        boolean powtorz_petle = true;
        while (powtorz_petle) {
            powtorz_petle = false;
            zoo.wypisz_zwierzeta();

            System.out.println("napisz numer wybiegu");
            while (!scanner.hasNextInt()) {
                System.out.println("To nie jest liczba. Wpisz numer wybiegu:");
                scanner.next();
            }
            int indeks_wybiegu = scanner.nextInt();

            System.out.println("napisz numer zwierzeta");
            while (!scanner.hasNextInt()) {
                System.out.println("To nie jest liczba. Wpisz numer zwierzecia:");
                scanner.next();
            }
            int indeks_zwierzecia = scanner.nextInt();

            try {
                Zwierze zwierze = zoo.getListaWybiegow().get(indeks_wybiegu).getLista_zwierzat().get(indeks_zwierzecia);
                wynik = new nr_wybiegu_Zwierze(indeks_wybiegu,zwierze);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wybrales niepoprawne zwierze lub wybieg. Spróbuj ponownie.");
                powtorz_petle = true;
            }

        }
        return wynik;
    }

    public static Zwierze stworzenie_zwierzecia_walczacego(Zwierze zwierze,Arena arena) {
        Zwierze walczace_zwierze = zwierze.deep_clone();
        double m_zal = 0;
        double m_wod = 0;
        double m_wia = 0;
        double m_nas = 0;
        double m_temp = 0;
        switch (walczace_zwierze.getRodzaj()) {
            case rodzaj_srodowiska_enum.LADOWY:
                m_zal = 0.5;
                m_wod = 0.1;
                m_wia = -0.2;
                m_nas = -0.2;
                break;
            case rodzaj_srodowiska_enum.POWIETRZNY:
                m_zal = -0.2;
                m_wod = -0.2;
                m_nas = -0.2;
                m_wia = 1;
                break;
            case rodzaj_srodowiska_enum.WODNY:
                m_zal = -0.2;
                m_wod = 0.8;
                m_nas = 0;
                m_wia = -0.2;
                break;
        }
        double ogolna_wartosc = arena.getTemperatura()*m_temp +arena.getZalesienie()*m_zal + arena.getNaslonecznienie()*m_nas + arena.getWiatr()*m_wia + arena.getWodyPowierzchniowe()*m_wod;
        walczace_zwierze.setSila( walczace_zwierze.getSila() + (int)ogolna_wartosc);
        walczace_zwierze.setZycie(walczace_zwierze.getZycie() + (int)ogolna_wartosc);


        return walczace_zwierze;
    }

    public static Zwierze wybor_przeciwnika(poziom_trudnosci_enum poziom_trudnosci){

        zwierzeta_enum[] mozliwe_zwierzeta = zwierzeta_enum.values();
        int indeks = new Random().nextInt(mozliwe_zwierzeta.length);
        Zwierze zwierze = mozliwe_zwierzeta[indeks].stworzZwierze("Bob");

        dostosuj_przeciwnika_pod_trudnosc(zwierze,poziom_trudnosci);
        return zwierze;
    }

    private static void dostosuj_przeciwnika_pod_trudnosc(Zwierze zwierze, poziom_trudnosci_enum poziom_trudnosci){
        zwierze.setSila((int) (zwierze.getSila() * poziom_trudnosci.getMnoznik()));
        zwierze.setZycie((int) (zwierze.getZycie()*poziom_trudnosci.getMnoznik()));
    }

}
