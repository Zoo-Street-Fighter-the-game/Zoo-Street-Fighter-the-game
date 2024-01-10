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
       if(zwierze.getPrzedmiot()!=null){
           walczace_zwierze.setSila(walczace_zwierze.getSila()+zwierze.getPrzedmiot().getSila());
           walczace_zwierze.setSzybkosc(walczace_zwierze.getSzybkosc()+zwierze.getPrzedmiot().getSzybkosc());
           walczace_zwierze.setZycie(walczace_zwierze.getZycie()+zwierze.getPrzedmiot().getZycie());
           walczace_zwierze.setSzczescie(walczace_zwierze.getSzczescie()+zwierze.getPrzedmiot().getSzczescie());
       }

        double m_zal = 0;
        double m_wod = 0;
        double m_wia = 0;
        double m_nas = 0;
        double m_temp = 0;
        switch (walczace_zwierze.getNazwa()) {
            case "Pingwin":
                m_zal = -0.2* arena.getZalesienie();
                m_wod = arena.getWodyPowierzchniowe()*0.5;
                m_wia = -0.2*arena.getWiatr();
                m_nas = -0.2* arena.getNaslonecznienie();
                m_temp = -1*arena.getTemperatura();
                break;
            case "Żółw":
                m_zal = -0.1* arena.getZalesienie();
                m_wod = arena.getWodyPowierzchniowe()*0.7;
                m_wia = -0.1*arena.getWiatr();
                m_nas = 0.2* arena.getNaslonecznienie();
                m_temp = 0.3*arena.getTemperatura();
                break;

            case "Rekin":
                m_zal = -0.2* arena.getZalesienie();
                m_wod = arena.getWodyPowierzchniowe();
                m_wia = -0.1*arena.getWiatr();
                m_nas = 0.1* arena.getNaslonecznienie();
                m_temp = 0.4*arena.getTemperatura();
                break;
            case "Orka":
                m_zal = -0.3* arena.getZalesienie();
                m_wod = arena.getWodyPowierzchniowe();
                m_wia = -0.1*arena.getWiatr();
                m_nas = -0.1* arena.getNaslonecznienie();
                m_temp = -0.1*arena.getTemperatura();
                break;
            case "Niedźwiedź":
                m_zal = 0.75*arena.getZalesienie();
                m_wod = 0.1*arena.getWodyPowierzchniowe();
                m_wia = -0.3*arena.getWiatr();
                m_nas = 0.2* arena.getNaslonecznienie();
                m_temp = 0.5*arena.getTemperatura();
                break;
            case "Łoś":
                m_zal = arena.getZalesienie();
                m_wod = -0.1*arena.getWodyPowierzchniowe();
                m_wia = 0.1*arena.getWiatr();
                m_nas = 0.3* arena.getNaslonecznienie();
                m_temp = 0.4*arena.getTemperatura();
                break;
            case "Niedźwiedź polarny":
                m_zal = -0.1* arena.getZalesienie();
                m_wod = 0.3*arena.getWodyPowierzchniowe();
                m_wia = -0.1*arena.getWiatr();
                m_nas = -0.1* arena.getNaslonecznienie();
                m_temp = -1*arena.getTemperatura();
                break;
            case "Lew":
                m_zal = 0.2* arena.getZalesienie();
                m_wod = -0.3*arena.getWodyPowierzchniowe();
                m_wia = 0.1*arena.getWiatr();
                m_nas = 0.75* arena.getNaslonecznienie();
                m_temp = arena.getTemperatura();
                break;
                case "Orzeł":
                m_zal = 0.5* arena.getZalesienie();
                m_wod = 0.1*arena.getWodyPowierzchniowe();
                m_wia = arena.getWiatr();
                m_nas = 0.5* arena.getNaslonecznienie();
                m_temp = 0.3*arena.getTemperatura();
                break;

            case "Papuga":
                m_zal = 0.6* arena.getZalesienie();
                m_wod = -0.2*arena.getWodyPowierzchniowe();
                m_wia = 0.5*arena.getWiatr();
                m_nas = 0.5* arena.getNaslonecznienie();
                m_temp = 0.5*arena.getTemperatura();
                break;

            case "Paw":
                m_zal = 0.6* arena.getZalesienie();
                m_wod = -0.1*arena.getWodyPowierzchniowe();
                m_wia = 0.2*arena.getWiatr();
                m_nas = 0.3* arena.getNaslonecznienie();
                m_temp = 0.2*arena.getTemperatura();
                break;

            case "Nietoperz":
                m_zal = 0.3* arena.getZalesienie();
                m_wod = 0.1*arena.getWodyPowierzchniowe();
                m_wia = 0.7*arena.getWiatr();
                m_nas = 100*(1/ arena.getNaslonecznienie());
                m_temp = 0.1*arena.getTemperatura();
                break;

        }

        walczace_zwierze.setSila((int)( walczace_zwierze.getSila() + m_wod));
        walczace_zwierze.setZycie((int)(walczace_zwierze.getZycie() + m_temp));
        walczace_zwierze.setSzczescie((int)(walczace_zwierze.getSzczescie()+m_nas+m_zal));
        walczace_zwierze.setSzybkosc((int)(walczace_zwierze.getSzybkosc()+m_wia));

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
