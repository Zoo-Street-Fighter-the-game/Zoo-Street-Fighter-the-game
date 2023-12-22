package pakiet_arena;

import enumy.poziom_trudnosci_enum;

import java.util.Scanner;

public class Poziom_trudnosci {
    private  poziom_trudnosci_enum trudnosc;

    public void ustaw_poziom_trudnosci() {
        this.trudnosc = poziom_trudnosci();
    }

    private  poziom_trudnosci_enum poziom_trudnosci(){
        System.out.println("Wybierz poziom trudnosci: ");
        System.out.println("1.latwy    2.sredni      3.trudny");
        System.out.println("\npoziom trudnosci zwieksza sile przeciwnika, ale zwieksza rowniez mozliwa nagorde");

        poziom_trudnosci_enum trudnosc = null;

        Scanner scanner = new Scanner(System.in);
        boolean powtorz_petle = true;
        while (powtorz_petle) {
            powtorz_petle = false;

            switch (scanner.nextLine()) {
                case "1", "latwy":
                    trudnosc = poziom_trudnosci_enum.LATWY;
                    break;
                case "2", "sredni":
                    trudnosc = poziom_trudnosci_enum.SREDNI;
                    break;
                case "3", "trudny":
                    trudnosc = poziom_trudnosci_enum.TRUDNY;
                    break;
                default :
                    System.out.println("wybrales niepoprawy poziom");
                    powtorz_petle = true;
                    break;
            }
        }
        return trudnosc;
    }

    public poziom_trudnosci_enum getTrudnosc() {
        return trudnosc;
    }

    public void setTrudnosc(poziom_trudnosci_enum trudnosc) {
        this.trudnosc = trudnosc;
    }
}
