package Wybieg_package;

import Klasy_Zwierzat.Zwierze;
import java.io.Serializable;
public class Wybieg_bezdomni extends Wybieg_abstract implements Serializable{


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
    public static Wybieg_bezdomni getInstance(){
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

    public void koniec_dnia(){
        usun_zwierzeta();
    }
    //=========================================================


    @Override
    public  void dodaj_zwierze(Zwierze obiekt) {
        getLista_zwierzat().add(obiekt);
    }
    @Override
    public  void usun_zwierze(Zwierze obiekt) {
        getLista_zwierzat().remove(obiekt);
        System.out.println("usunieto zwierze metoda w wybiegu bezdomni");
    }

    public String toString(){
        System.out.println(getLista_zwierzat());
        return null;
    }
}
