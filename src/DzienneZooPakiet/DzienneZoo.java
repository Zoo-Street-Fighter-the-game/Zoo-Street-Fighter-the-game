package DzienneZooPakiet;
import Klasy_Zwierzat.*;
import pakiet_zasoby.*;
import Wybieg_package.*;
import Pracownik_package.*;
import java.io.Serializable;
import java.util.ArrayList;


public class DzienneZoo implements Serializable{


    //ZMIENNNE --------------------------------------------------------------
    private int dniCounter =0;
    private final Zasoby zmiennaZasoby = new Zasoby(0,1000,100);
    private final Wybieg_bezdomni wybiegDlaBezdomnych = Wybieg_bezdomni.getInstance();
     private final ArrayList <Wybieg_podstawowy> listaWybiegow = new ArrayList<>();
     private final ArrayList <Pracownik> listaPracownikow = new ArrayList<>();
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



    public ArrayList<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }



    public int getDniCounter() {
        return dniCounter;
    }

    public void setDniCounter(int dniCounter) {
        this.dniCounter = dniCounter;
    }

    public Wybieg_bezdomni getWybiegDlaBezdomnych () {return wybiegDlaBezdomnych;}

    //KONIEC SETTEROW I GETTEROW


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



    //METODA PRZENOSZĄCA ZWIERZE Z WYBIEGU BEZDOMNI
    public void przeniesZwierze_bezdomni(Wybieg_podstawowy wybieg, Zwierze zwierze)
    {

        //DODAJEMY ZWIERZE DO WYBIEGU
        wybieg.dodaj_zwierze(zwierze);

        //USUWAMY ZWIERZE Z WYBIEGU
        wybiegDlaBezdomnych.usun_zwierze(zwierze);
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


}
