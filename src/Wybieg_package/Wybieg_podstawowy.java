package Wybieg_package;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import interfejsy.Obserwowany_interface;
import interfejsy.Obserwujacy_ZwierzeWybieg_interface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wybieg_podstawowy extends Wybieg_abstract implements Obserwowany_interface, Serializable{



    //==========================================================================
    //                              zmienne
    //--------------------------------------------------------------------------
    private final List<Obserwujacy_ZwierzeWybieg_interface> obserwujacy = new ArrayList<>();
    private int wolne_miejsce_w_wybiegu;

    private final rodzaj_srodowiska_enum rodzaj_srodowiska;
    private final wielkosc_wybiegu_enum wielkosc_wybiegu;
    private float czystosc = 100;       //od 0 do 100
    private final int cena;
    //=================================================================================




    //===================================================================================
    //                          konstruktory
    //-----------------------------------------------------------------------------------
    public Wybieg_podstawowy(rodzaj_srodowiska_enum rodzaj_wybiegu, wielkosc_wybiegu_enum wielkosc_wybiegu) {
        this.rodzaj_srodowiska = rodzaj_wybiegu;
        this.wielkosc_wybiegu = wielkosc_wybiegu;
        this.wolne_miejsce_w_wybiegu = wielkosc_wybiegu.getLiczbowa_Wielkosc_Wybiegu();
        this.cena = wielkosc_wybiegu.getLiczbowa_Cena_Wybiegu();

    }

    //=====================================================================================


    //=============================================================================
    //                          dodawanie i usuwanie zwierzecia do wybieg
    //---------------------------------------------------------------------------------------


    public void dodaj_zwierze(Zwierze obiekt){
        if (czy_zwierze_spelnia_wymogi_dodania_do_wybiegu(obiekt)){
            getLista_zwierzat().add(obiekt);
            dodaj_obserwatora(obiekt);
            setWolne_miejsce_w_wybiegu( getWolne_miejsce_w_wybiegu() - obiekt.getWielkosc() );
            System.out.println("dodano zwierze do wybiegu");
        }
        else System.out.println(" nie udalo sie dodac zwierzecia");
    }

@Override
    public  void usun_zwierze(Zwierze obiekt){

        if (getLista_zwierzat().contains(obiekt)){
            setWolne_miejsce_w_wybiegu( getWolne_miejsce_w_wybiegu() + obiekt.getWielkosc() );
            usun_obserwatora(obiekt);

            getLista_zwierzat().remove( obiekt);

            System.gc();
            System.out.println("usunieto zwierze");
        }
        else
            System.out.println("nie udalo sie usunac zwierzecia");
    }



    //funkcje majace na celu sprawdzenie czy zwierze spelnia wymogi dodania
    //---------------------------------------------------------------------------------

    public  boolean czy_zwierze_spelnia_wymogi_dodania_do_wybiegu(Zwierze obiekt){
        return czy_wybieg_ma_ten_rodzaj_zwierzecia(obiekt) &&
                czy_zwierze_zmiesci_sie(obiekt) &&
                czy_zwierze_ma_dobry_typ(obiekt);
    }

    public  boolean czy_wybieg_ma_ten_rodzaj_zwierzecia(Zwierze obiekt){
        if (!getLista_zwierzat().isEmpty()){
            return obiekt.getNazwa().equals(getRodzaj_zwierzecia_w_wybiegu());
        }
        return true;
    }

    public boolean czy_zwierze_zmiesci_sie(Zwierze obiekt){
        return (getWolne_miejsce_w_wybiegu() - obiekt.getWielkosc())>=0;
    }
    public boolean czy_zwierze_ma_dobry_typ(Zwierze obiekt){
        return getRodzaj_srodowiska() == obiekt.getRodzaj();
    }
    //---------------------------------------------------------------------------------------
    //========================================================================================






    //========================================================================================
    //                      metody zwiazane z wlasnosciami zwierzat
    //-------------------------------------------------------------------------------------
    public void przychody_z_wybiegu(){
        int przychod = 0;
        DzienneZoo zoo = DzienneZoo.getInstance();
        for (Zwierze obiekt : getLista_zwierzat()){
            przychod+= (int)(obiekt.getMnoznik_pieniedzy()*10);
        }
        zoo.getZmiennaZasoby().dodajExp(przychod/10);
        zoo.getZmiennaZasoby().zmienJedzenie(przychod);
    }

    public void brudzenie_zwierzat(){
        for (Zwierze zwierze : getLista_zwierzat()){
           setCzystosc(getCzystosc() - zwierze.getWielkosc()*5);
           powiadom_obserwatorow();
        }
    }

    public void postarz(){
        for (Zwierze zwierze : getLista_zwierzat()){
            zwierze.setPrzezyte_dni(zwierze.getPrzezyte_dni()+1);
        }
    }
    public void nakarm(){
        for (int i=0;i<getLista_zwierzat().size();i++){
            getLista_zwierzat().get(i).rozpocznij_dzien();
            if (getLista_zwierzat().get(i).getWskaznik_glodu()<=0){
                usun_zwierze(getLista_zwierzat().get(i));
            }
        }
    }

    //=========================================================================================




    //========================================================================================
    //                                  release
    //-------------------------------------------------------------------------------------
    public void release(){
        obserwujacy.clear();
        Wybieg_bezdomni lista_bezdomnych = Wybieg_bezdomni.getInstance();
        for (Zwierze obiekt : getLista_zwierzat()){
            lista_bezdomnych.dodaj_zwierze(obiekt);
        }
        getLista_zwierzat().clear();
    }
    //=======================================================================================



    //=================================================================================
    //                      metody zwiazanie z obserwowaniem zwierzat
    //---------------------------------------------------------------------------------------
    @Override
    public void dodaj_obserwatora(Obserwujacy_ZwierzeWybieg_interface o) {
        obserwujacy.add(o);
    }

    @Override
    public void usun_obserwatora(Obserwujacy_ZwierzeWybieg_interface o) {
        obserwujacy.remove(o);
    }

    @Override
    public void powiadom_obserwatorow() {
        for(Obserwujacy_ZwierzeWybieg_interface o : obserwujacy){
            o.aktualizuj_oberwujacego(getCzystosc());
        }
    }


    //===============================================================================

    //===============================================================================
    //                          metody zwiazane z porami dnia
    //-------------------------------------------------------------------------------

    public void zakonczenie_dnia(){
        przychody_z_wybiegu();
        brudzenie_zwierzat();
        nakarm();
        powiadom_obserwatorow();
        postarz();
    }



    //======================================================================================
    //                                  gettery i settery oraz toString
    //-------------------------------------------------------------------------------------
    public String  toString(){
        StringBuilder status = new StringBuilder("Wybieg " + getRodzaj_srodowiska().toString() + " " +
                getWielkosc_wybiegu().toString() + " \n" +
                "dla : " + getRodzaj_zwierzecia_w_wybiegu() + " \n" +
                "czystosc: " + getCzystosc() + " \n" +
                "ilosc wolnego miejsca: " + getWolne_miejsce_w_wybiegu() + " \n" +
                "Zwierzeta w tym wybiegu : \n");
        for (Zwierze obiekt : getLista_zwierzat())
            status.append(obiekt.toString()).append(" \n");

        return status.toString();
    }


    public int getWolne_miejsce_w_wybiegu() {
        return wolne_miejsce_w_wybiegu;
    }

    public void setWolne_miejsce_w_wybiegu(int wolne_miejsce_w_wybiegu) {
        this.wolne_miejsce_w_wybiegu = wolne_miejsce_w_wybiegu;
    }


    public rodzaj_srodowiska_enum getRodzaj_srodowiska() {
        return rodzaj_srodowiska;
    }


    public wielkosc_wybiegu_enum getWielkosc_wybiegu() {
        return wielkosc_wybiegu;
    }


    public float getCzystosc() {
        return czystosc;
    }

    public void setCzystosc(float czystosc) {
        this.czystosc = czystosc;
    }


    public int getCena() {
        return cena;
    }


    //specjalny getter
    //--------------------------------------------------------------
    public String getRodzaj_zwierzecia_w_wybiegu(){
        if (!getLista_zwierzat().isEmpty())
            return getLista_zwierzat().getFirst().getNazwa();

        return null;
    }
    //===================================================================================
}