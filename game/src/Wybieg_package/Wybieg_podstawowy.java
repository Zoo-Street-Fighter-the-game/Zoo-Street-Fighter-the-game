package Wybieg_package;

import Klasy_Zwierzat.Zwierze;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import interfejsy.Obserwowany_interface;
import interfejsy.Obserwujacy_interface;

import java.util.ArrayList;
import java.util.List;

public abstract class Wybieg_podstawowy extends Wybieg_abstract implements Obserwowany_interface {



    //==========================================================================
    //                              zmienne
    //--------------------------------------------------------------------------
    private List<Obserwujacy_interface> obserwujacy = new ArrayList<>();
    private int wole_miejsce_w_wybiegu;

    private rodzaj_srodowiska_enum rodzaj_srodowiska;
    private wielkosc_wybiegu_enum wielkosc_wybiegu;
    private float czystosc = 100;       //od 0 do 100
    private float cena;
    private float czas_sprzatania;
    //=================================================================================




    //===================================================================================
    //                          konstruktory
    //-----------------------------------------------------------------------------------
    public Wybieg_podstawowy(rodzaj_srodowiska_enum rodzaj_wybiegu, wielkosc_wybiegu_enum wielkosc_wybiegu) {
        this.rodzaj_srodowiska = rodzaj_wybiegu;
        this.wielkosc_wybiegu = wielkosc_wybiegu;
        this.wole_miejsce_w_wybiegu = wielkosc_wybiegu.getLiczbowa_Wielkosc_Wybiegu();
        this.cena = wielkosc_wybiegu.getLiczbowa_Wielkosc_Wybiegu()*10;
        this.czas_sprzatania = wielkosc_wybiegu.getLiczbowa_Wielkosc_Wybiegu()*2;
    }

    //=====================================================================================


    //=============================================================================
    //                          dodawanie i usuwanie zwierzecia do wybieg
    //---------------------------------------------------------------------------------------


    public void dodaj_zwierze(Zwierze obiekt){
        if (czy_zwierze_spelnia_wymogi_dodania_do_wybiegu(obiekt)){
            getLista_zwierzat().add(obiekt);
            dodaj_obserwatora(obiekt);
            setWole_miejsce_w_wybiegu( getWole_miejsce_w_wybiegu() - obiekt.getWielkosc() );
            System.out.println("dodano zwierze do wybiegu");
        }
        else System.out.println(" nie udalo sie dodac zwierzecia");
    }

@Override
    public  void usun_zwierze(Zwierze obiekt){

        if (getLista_zwierzat().contains(obiekt)){
            setWole_miejsce_w_wybiegu( getWole_miejsce_w_wybiegu() + obiekt.getWielkosc() );
            usun_obserwatora(obiekt);
            obiekt.release();
            obiekt = null;
            getLista_zwierzat().remove( null);

            System.gc();
            System.out.println("usunieto zwierze");
        }
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
            return obiekt.getClass().getName().equals(getRodzaj_zwierzecia_w_wybiegu());
        }
        return true;
    }

    public boolean czy_zwierze_zmiesci_sie(Zwierze obiekt){
        return (getWole_miejsce_w_wybiegu() - obiekt.getWielkosc())>=0;
    }
    public boolean czy_zwierze_ma_dobry_typ(Zwierze obiekt){
        return getRodzaj_srodowiska() == obiekt.getRodzaj();
    }
    //---------------------------------------------------------------------------------------
    //========================================================================================






    //========================================================================================
    //                      metody zwiazane z wlasnosciami zwierzat
    //-------------------------------------------------------------------------------------
    public float przychody_z_wybiegu(){
        int przychod = 0;
        for (Zwierze obiekt : getLista_zwierzat()){
            przychod+= (int) (obiekt.getMnoznik_pieniedzy()*10);
        }
        return przychod;
    }

    public void brudzenie_zwierzat(){
        for (Zwierze zwierze : getLista_zwierzat()){
           setCzystosc(getCzystosc() - zwierze.getWielkosc()*5);
        }
    }
    //=========================================================================================




    //========================================================================================
    //                                  release
    //-------------------------------------------------------------------------------------
    public void release(){
        obserwujacy.clear();
        Wybieg_bezdomni lista_bezdomnych = Wybieg_bezdomni.getIstnieje();
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
    public void dodaj_obserwatora(Obserwujacy_interface o) {
        obserwujacy.add(o);
    }

    @Override
    public void usun_obserwatora(Obserwujacy_interface o) {
        obserwujacy.remove(o);
    }

    @Override
    public void powiadom_obserwatorow() {
        for(Obserwujacy_interface o : obserwujacy){
            o.aktualizuj_oberwujacego(getCzystosc());
        }
    }


    //===============================================================================

    //===============================================================================
    //                          metody zwiazane z porami dnia
    //-------------------------------------------------------------------------------
    public void rozpoczecie_dnia(){
        brudzenie_zwierzat();
        powiadom_obserwatorow();
    }
    public void zakonczenie_dnia(){

    }



    //======================================================================================
    //                                  gettery i settery oraz toString
    //-------------------------------------------------------------------------------------
    public String  toString(){
        StringBuilder status = new StringBuilder("Wybieg " + getRodzaj_srodowiska().toString() + " \n" +
                getWielkosc_wybiegu().toString() + " \n" +
                "dla : " + getRodzaj_zwierzecia_w_wybiegu() + " \n" +
                "czystosc: " + getCzystosc() + " \n" +
                "ilosc wolnego miejsca: " + getWole_miejsce_w_wybiegu() + " \n" +
                "Zwierzeta w tym wybiegu : \n");
        for (Zwierze obiekt : getLista_zwierzat())
            status.append(obiekt.toString()).append(" \n");

        return status.toString();
    }

    public List<Obserwujacy_interface> getobserwujacy() {
        return obserwujacy;
    }

    public void setobserwujacy(List<Obserwujacy_interface> obserwujacy) {
        obserwujacy = obserwujacy;
    }

    public int getWole_miejsce_w_wybiegu() {
        return wole_miejsce_w_wybiegu;
    }

    public void setWole_miejsce_w_wybiegu(int wole_miejsce_w_wybiegu) {
        this.wole_miejsce_w_wybiegu = wole_miejsce_w_wybiegu;
    }


    public rodzaj_srodowiska_enum getRodzaj_srodowiska() {
        return rodzaj_srodowiska;
    }

    public void setRodzaj_srodowiska(rodzaj_srodowiska_enum rodzaj_srodowiska) {
        this.rodzaj_srodowiska = rodzaj_srodowiska;
    }

    public wielkosc_wybiegu_enum getWielkosc_wybiegu() {
        return wielkosc_wybiegu;
    }

    public void setWielkosc_wybiegu(wielkosc_wybiegu_enum wielkosc_wybiegu) {
        this.wielkosc_wybiegu = wielkosc_wybiegu;
    }

    public float getCzystosc() {
        return czystosc;
    }

    public void setCzystosc(float czystosc) {
        this.czystosc = czystosc;
    }



    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public float getCzas_sprzatania() {
        return czas_sprzatania;
    }

    public void setCzas_sprzatania(float czas_sprzatania) {
        this.czas_sprzatania = czas_sprzatania;
    }

    //specjalny getter
    //--------------------------------------------------------------
    public String getRodzaj_zwierzecia_w_wybiegu(){
        if (!getLista_zwierzat().isEmpty())
            return getLista_zwierzat().get(0).getClass().getName();
        return null;
    }
    //===================================================================================
}