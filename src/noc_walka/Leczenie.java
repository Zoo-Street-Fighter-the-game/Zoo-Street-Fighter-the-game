package noc_walka;

import Klasy_Zwierzat.Zwierze;

public class Leczenie implements MenuAkcji{
    @Override
    public void menuAkcji(Zwierze zwierze, Zwierze przeciwnik) {
        //tutaj tez wartosc do ustalenia
        int leczenie = (int)(5+Math.random()*50);
        zwierze.setZycie(zwierze.getZycie()+leczenie);
        System.out.println("Lecze  za: "+leczenie);
    }
}
