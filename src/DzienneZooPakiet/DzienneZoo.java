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
    private final Zasoby zmiennaZasoby = new Zasoby(0,1000,100);
    private final Wybieg_bezdomni wybiegDlaBezdomnych = Wybieg_bezdomni.getInstance();
     private ArrayList <Wybieg_podstawowy> listaWybiegow = new ArrayList<>();
     private ArrayList <Pracownik> listaPracownikow = new ArrayList<>();
    private static DzienneZoo istnieje;

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

    public Wybieg_bezdomni getWybiegDlaBezdomnych () {return wybiegDlaBezdomnych;}

    //KONIEC SETTEROW I GETTEROW

    public void rozpocznijDzien()
    {
        setDniCounter(getDniCounter()+1);
        System.out.println("Rozpoczynamy dzień "+ getDniCounter());
        for(Wybieg_podstawowy obiekt: listaWybiegow)
        {
            obiekt.rozpoczecie_dnia();
            zmiennaZasoby.zmienMonety(obiekt.przychody_z_wybiegu());
        }

    }
    public void zakonczDzien()
    {
        System.out.println("Konczymy dzien numer "+ getDniCounter());
        for(Wybieg_podstawowy obiekt: listaWybiegow)
        {
            obiekt.zakonczenie_dnia();
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


    //WYBIERANIE WYBIEGU SPOŚRÓD DOSTĘPNYCH
    public int wybierzWybiegi()
    {
        Scanner sc = new Scanner(System.in);
        int wybieg_zabieranie;
        //WYŚWIETLANIE WYBIEGU
        System.out.println("Wybiegi do wyboru: " );
        for(int i=0;i<listaWybiegow.size();i++)
        {
            System.out.println("Wybieg " + i + ": " + listaWybiegow.get(i));
        }
        System.out.println("Podaj wybieg, ktory chcesz wybrac!" );
        wybieg_zabieranie=sc.nextInt();

        return wybieg_zabieranie;
    }

    //PRZENOSZENIE ZWIERZĄT MIĘDZY WYBIEGAMI
    public void przeniesZwierze_Wybiegi()
    {
        int wybieg_zabieranie, zwierze;
        wybieg_zabieranie = wybierzWybiegi();
        zwierze = listaWybiegow.get(wybieg_zabieranie).wybierzZwierze();

        System.out.println("Podaj numer wybiegu, do ktorego chcesz je przeniesc!" );
        int wybieranie = wybierzWybiegi();

        //DODAJEMY ZWIERZE DO WYBIEGU
        listaWybiegow.get(wybieranie).dodaj_zwierze(((listaWybiegow.get(wybieg_zabieranie)).getLista_zwierzat()).get(zwierze));


        //USUWAMY ZWIERZE Z WYBIEGU
        listaWybiegow.get(wybieg_zabieranie).usun_zwierze(((listaWybiegow.get(wybieg_zabieranie)).getLista_zwierzat()).get(zwierze));

    }

    //METODA PRZENOSZĄCA ZWIERZE Z WYBIEGU BEZDOMNI
    public void przeniesZwierze_bezdomni()
    {
        int zwierze;
        zwierze = wybiegDlaBezdomnych.wybierzZwierze();

        System.out.println("Podaj numer wybiegu, do ktorego chcesz je przeniesc!");
        int wybieranie = wybierzWybiegi();

        //DODAJEMY ZWIERZE DO WYBIEGU
        listaWybiegow.get(wybieranie).dodaj_zwierze(((listaWybiegow.get(wybieranie)).getLista_zwierzat()).get(zwierze));

        //USUWAMY ZWIERZE Z WYBIEGU
        wybiegDlaBezdomnych.usun_zwierze(wybiegDlaBezdomnych.getLista_zwierzat().get(zwierze));
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
     status.append(zmiennaZasoby).append(" \n"); //daria musi dodac metode tostring!!!

     return status.toString();
 }


 //POKAZANIE PRACOWNIKOW I WYBIEGOW
 public void pokazpracownikow()
 {
     System.out.println("--------------- \n pracownicy:");
     for(Pracownik obiekt: listaPracownikow)
         System.out.println(obiekt);
 }
    public void pokazwybiegi()
    {
        System.out.println("--------------- \n wybiegi");
        for(Wybieg_podstawowy obiekt: listaWybiegow)
            System.out.println(obiekt);
    }


}
