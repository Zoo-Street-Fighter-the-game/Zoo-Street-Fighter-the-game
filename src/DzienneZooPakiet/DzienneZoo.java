package DzienneZooPakiet;
import Klasy_Zwierzat.*;
import pakiet_zasoby.*;
import Wybieg_package.*;
import Pracownik_package.*;

import java.util.ArrayList;
import java.util.Scanner;


public class DzienneZoo {


    //ZMIENNNE --------------------------------------------------------------
    private int dniCounter =0;
    final private Zasoby zmiennaZasoby = new Zasoby(0,200,100);
    final private Wybieg_bezdomni wybiegDlaBezdomnych = Wybieg_bezdomni.getInstance();
     private ArrayList <Wybieg_podstawowy> listaWybiegow = new ArrayList<>();
     private ArrayList <Pracownik> listaPracownikow = new ArrayList<>();
    private static DzienneZoo istnieje;
    Scanner sc = new Scanner(System.in);

     //KONSTRUKTOR

    private DzienneZoo() {
    }
    public static DzienneZoo getInstance(){
        if (istnieje == null){
            istnieje = new DzienneZoo();
        }
        return istnieje;
    }



    //SETTERY I GETTERY

    public Zasoby getZmiennaZasoby() {
        return zmiennaZasoby;
    }

    public ArrayList<Wybieg_podstawowy> getListaWybiegow() {
        return listaWybiegow;
    }

    public void setListaWybiegow(ArrayList<Wybieg_podstawowy> listaWybiegow) {
        this.listaWybiegow = listaWybiegow;
    }

    public ArrayList<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

    public void setListaPracownikow(ArrayList<Pracownik> listaPracownikow) {
        this.listaPracownikow = listaPracownikow;
    }

    public int getDniCounter() {
        return dniCounter;
    }

    public void setDniCounter(int dniCounter) {
        this.dniCounter = dniCounter;
    }

    //KONIEC SETTEROW I GETTEROW

    public void rozpocznijDzien()
    {
        ArrayList <Zwierze> pom ;
        setDniCounter(getDniCounter()+1);
        System.out.println("\nRozpoczynamy dzień "+ getDniCounter());
        for(Wybieg_podstawowy obiekt: listaWybiegow)
        {
            pom = (ArrayList<Zwierze>) obiekt.getLista_zwierzat();
            for (Zwierze zwierze : pom) {
                zwierze.setPrzezyte_dni(zwierze.getPrzezyte_dni() + 1);
            }
        }

    }
    public void zakonczDzien()
    {
        System.out.println("\nKonczymy dzien numer "+ getDniCounter() + "\n");
        System.out.println("stan wybiegów wyglada następująco:\n");
        for(Wybieg_podstawowy obiekt: listaWybiegow)
        {
            obiekt.brudzenie_zwierzat();
            System.out.println(obiekt);
            obiekt.zakonczenie_dnia(); // pusta metoda
        }
        System.out.println("Zasoby zgromadzone na koniec dnia: \n"+ zmiennaZasoby);
    }



    //DODAWANIE WYBIEGU I PRACOWNIKA
    public void dodajWybieg(Wybieg_podstawowy wybieg) {
        listaWybiegow.add(wybieg);
    }
    public void dodajPracownika(Pracownik x) {
        listaPracownikow.add(x);
    }



    //DODAWANIE ZWIERZĘCIA DO WYBRANEGO WYBIEGU
    public void dodajZwierzeWZoo(Zwierze z){
        pokazWybiegi(); //metoda pokazujaca wybiegi, potrzebna w innych metodach tez, wiec takie rozwiazanie nie powiela kodu

        //podawanie wybiegu z wcześniej wyświetlonych
        System.out.println("Podaj wybieg, ktory chcesz wybrac:" );

        int indeks = sc.nextInt();
        sc.nextLine();

        listaWybiegow.get(indeks).dodaj_zwierze(z);

        System.out.println("pomyslnie dodano zwierze do wybiegu");


    }

    //PRZENOSZENIE ZWIERZĄT MIĘDZY WYBIEGAMI
    public void przeniesZwierze()
    {
        pokazWybiegi();

        //PODAWANIE WYBIEGU
        System.out.println("Podaj wybieg, ktory chcesz wybrac:" );
        int wybieg_zabieranie=sc.nextInt();

        //WYŚWIETLANIE ZWIERZAT DLA DANEGO WYBIEGU
        System.out.println("Lista zwierzat w wybranym wybiegu: ");
        //listaWybiegow.size() TO OPCJA WYBIEGU DLA BEZDOMNYCH WYBIERANA PRZEZ UŻYTKOWNIKA
        if(wybieg_zabieranie==listaWybiegow.size())//IF DLA SPRAWDZENIA KTÓRY RODZAJ WYBIEGU CHCEMY
        {
            for(int i=0; i<wybiegDlaBezdomnych.getLista_zwierzat().size(); i++)
            {
                System.out.println("Zwierze " + i + ": " + (wybiegDlaBezdomnych.getLista_zwierzat()).get(i));
            }
        }else {
            for(int i=0; i<((listaWybiegow.get(wybieg_zabieranie)).getLista_zwierzat()).size(); i++)
            {
                System.out.println("Zwierze " + i + ": " + ((listaWybiegow.get(wybieg_zabieranie)).getLista_zwierzat()).get(i));
            }
        }

        //PODAWANIE ZWIERZECIA I WYBIEGU DO PRZENIESIENIA
        System.out.println("Podaj numer zwierzecia, ktore chcesz przeniesc:" );
        int zwierze=sc.nextInt();
        System.out.println("Podaj numer wybiegu, do ktorego chcesz je przeniesc:" );
        int wybieg_dawanie=sc.nextInt();


        if(wybieg_dawanie==listaWybiegow.size())//IF DLA SPRAWDZENIA, NA KTORY WYBIEG DODAJEMY ZWIERZE
        {
            //DODAJEMY ZWIERZE DO WYBIEGU
            wybiegDlaBezdomnych.dodaj_zwierze(wybiegDlaBezdomnych.getLista_zwierzat().get(zwierze));
        }else {
            //DODAJEMY ZWIERZE DO WYBIEGU
            listaWybiegow.get(wybieg_dawanie).dodaj_zwierze(((listaWybiegow.get(wybieg_zabieranie)).getLista_zwierzat()).get(zwierze));
        }

        if(wybieg_zabieranie==listaWybiegow.size())//IF DLA SPRAWDZENIA, Z KTÓREGO WYBIEGU USUWAMY ZWIERZE
        {
            //USUWAMY ZWIERZE Z WYBIEGU
            wybiegDlaBezdomnych.usun_zwierze(wybiegDlaBezdomnych.getLista_zwierzat().get(zwierze));
        }else {
            //USUWAMY ZWIERZE Z WYBIEGU
            listaWybiegow.get(wybieg_zabieranie).usun_zwierze(((listaWybiegow.get(wybieg_zabieranie)).getLista_zwierzat()).get(zwierze));
        }
    }

    public void usunWybieg(Wybieg_podstawowy x)
    {
        if (getListaWybiegow().contains(x)){
            x.release();
            getListaWybiegow().remove(x);
            System.out.println("usunieto wybieg: " + x );
        }
        else System.out.println("nie udalo sie usunac wybiegu");
    }

    public void usunPracownika(Pracownik x)
    {
        if (getListaPracownikow().contains(x)){
            getListaPracownikow().remove(x);
            System.out.println("usunieto pracownika: " + x );
        }
        else System.out.println("nie udalo sie usunac pracownika");
    }

    public void wypisz_zwierzeta(){
        for (int i=0;i<getListaWybiegow().size();i++){
            System.out.println("Wybieg nr" + i+ ": \n" );
            getListaWybiegow().get(i).wypisz_zwierzeta();
        }

    }


    //TOSTRING
    public String toString()
 {
     StringBuilder status= new StringBuilder();

     if(!listaWybiegow.isEmpty())
            listaWybiegow.forEach(obiekt -> status.append(obiekt.toString()).append(" \n"));
     else
         status.append("nie masz żadnych wybiegów\n");

    if(!listaPracownikow.isEmpty())
            listaPracownikow.forEach(obiekt -> status.append(obiekt.toString()).append(" \n"));
    else
        status.append("nie masz żadnych pracowników\n");
     status.append(zmiennaZasoby.toString()).append(" \n"); //daria musi dodac metode tostring!!!

     return status.toString();
 }


    //POKAZANIE PRACOWNIKOW I WYBIEGOW
    public void pokazPracownikow() {
        System.out.println("--------------- \npracownicy:");
        System.out.println("Pracownicy do wyboru: ");
        for (int i = 0; i < listaWybiegow.size(); i++) {
            System.out.println("Pracownik " + i + ": " + listaWybiegow.get(i));
        }
    }

    public void pokazWybiegi(){
        System.out.println("--------------- \nwybiegi");
        System.out.println("Wybiegi do wyboru: " );
        for(int i=0;i<listaWybiegow.size();i++)
        {
            System.out.println("Wybieg " + i + ": " + listaWybiegow.get(i));
        }
        System.out.println("Wybieg " + listaWybiegow.size() + ": " + wybiegDlaBezdomnych);//OSTATNI WYBIEG ZAWSZE DO WYBORU TO WYBIEG BEZDOMNYCH
        System.out.println("--------------- \n");
    }


}
