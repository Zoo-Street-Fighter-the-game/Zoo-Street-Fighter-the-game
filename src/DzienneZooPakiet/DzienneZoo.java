package DzienneZooPakiet;
import Klasy_Zwierzat.*;
import pakiet_zasoby.*;
import Wybieg_package.*;
import Pracownik_package.*;

import java.util.ArrayList;


public class DzienneZoo {


    //ZMIENNNE --------------------------------------------------------------
    private String nazwaZoo;
    private int dniCounter =0;
    private Zasoby zmiennaZasoby;
    final private Wybieg_bezdomni wybiegDlaBezdomnych = Wybieg_bezdomni.getIstnieje();
     private ArrayList <Wybieg_podstawowy> listaWybiegow = new ArrayList<>();
     private ArrayList <Pracownik> listaPracownikow = new ArrayList<>();


     //KONSTRUKTOR

    public DzienneZoo(String nazwaZoo, Zasoby zmiennaZasoby, ArrayList<Wybieg_podstawowy> listaWybiegow, ArrayList<Pracownik> listaPracownikow) {
        this.nazwaZoo = nazwaZoo;
        this.zmiennaZasoby = zmiennaZasoby;
        this.listaWybiegow = listaWybiegow;
        this.listaPracownikow = listaPracownikow;
    }
    public DzienneZoo(String nazwaZoo, Zasoby zmiennaZasoby)
    {
        this.nazwaZoo = nazwaZoo;
        this.zmiennaZasoby = zmiennaZasoby;
    }



    //SETTERY I GETTERY
    public String getNazwaZoo() {
        return nazwaZoo;
    }

    public void setNazwaZoo(String nazwaZoo) {
        this.nazwaZoo = nazwaZoo;
    }

    public Zasoby getZmiennaZasoby() {
        return zmiennaZasoby;
    }

    public void setZmiennaZasoby(Zasoby zmiennaZasoby) {
        this.zmiennaZasoby = zmiennaZasoby;
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
        System.out.println("Rozpoczynamy dzień "+ getDniCounter());
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
        System.out.println("Konczymy dzien numer "+ getDniCounter());
        for(Wybieg_podstawowy obiekt: listaWybiegow)
        {
            obiekt.zakonczenie_dnia();
        }
        System.out.println("Zasoby zgromadzone na koniec dnia: \n"+ zmiennaZasoby);
    }





    //DODAWANIE WYBIEGU I PRACOWNIKA
    public void dodajWybieg(Wybieg_podstawowy wybieg)
    {
        if(wybieg!=null)
        listaWybiegow.add(wybieg);
    }
    public void dodajPracownika(Pracownik x)
    {
        if(x!=null)
        listaPracownikow.add(x);
    }

    /*public void dodajZwierze(Zwierze z)
    {
        tutaj moze byc cos w rodzaju wyswietl wszystkie wybiegi i dodaj do wybranego zwierze 'z'
    }*/

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






    //TOSTRING
    public String toString()
 {
     StringBuilder status= new StringBuilder(getNazwaZoo() + " \n");

     if(listaWybiegow!=null)
            listaWybiegow.forEach(obiekt -> status.append(obiekt.toString()).append(" \n"));
    if(listaPracownikow!=null)
            listaPracownikow.forEach(obiekt -> status.append(obiekt.toString()).append(" \n"));

     status.append(zmiennaZasoby.toString()).append(" \n"); //daria musi dodac metode tostring!!!

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
