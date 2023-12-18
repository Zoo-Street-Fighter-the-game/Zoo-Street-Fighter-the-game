package Wybieg_package;

import Klasy_Zwierzat.Zwierze;

public class Wybieg_bezdomni extends Wybieg_abstract{


    //======================================================
    //                      zmienne
    //-------------------------------------------------------
    private static Wybieg_bezdomni istnieje;

    //========================================================


    //=========================================================
    //                  metody konstruktora
    //-----------------------------------------------------------
    private Wybieg_bezdomni() {
    }
    public static Wybieg_bezdomni getIstnieje(){
        if(istnieje == null)
            istnieje = new Wybieg_bezdomni();
        return istnieje;
    }
    //===========================================================

    //==========================================================
    //                  metody obiektu
    //----------------------------------------------------------
    public void usun_zwierzeta(){
        for (Zwierze obiekt : getLista_zwierzat()){
            this.usun_zwierze(obiekt);
        }
        getLista_zwierzat().clear();
        System.gc();

    }
    //=========================================================


    @Override
    public  void dodaj_zwierze(Zwierze obiekt) {
        getLista_zwierzat().add(obiekt);
    }
    @Override
    public  void usun_zwierze(Zwierze obiekt) {
        obiekt.release();
        obiekt = null;
        getLista_zwierzat().remove( null);
    }

    public String toString(){
        return " ";
    }
}
