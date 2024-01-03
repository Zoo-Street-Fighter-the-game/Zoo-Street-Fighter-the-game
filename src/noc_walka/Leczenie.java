package noc_walka;

import Klasy_Zwierzat.Zwierze;
import pakiet_arena.Arena;

public class Leczenie implements MenuAkcji{
    @Override
    public void MenuAkcji(Zwierze zwierze, Zwierze przeciwnik) {
        //tutaj tez wartosc do ustalenia
        int leczenie = 10;
        zwierze.setZycie(zwierze.getZycie()+leczenie);
        System.out.println("Lecze  za: "+leczenie);
    }
}
