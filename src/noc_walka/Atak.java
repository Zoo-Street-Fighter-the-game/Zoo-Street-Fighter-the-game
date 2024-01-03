package noc_walka;

import Klasy_Zwierzat.Zwierze;
import pakiet_arena.Arena;

import java.util.Random;

public class Atak implements MenuAkcji{
    @Override
    public void MenuAkcji(Zwierze zwierze, Zwierze przeciwnik) {
        Random random = new Random();
        //tutaj docelowo ma znajdowac sie mechanizm obrazen polaczony z wartosciami broni i zwierzat
        if(zwierze.getPrzedmiot()!=null){
            int obrazenia = (int) ((zwierze.getSila()+zwierze.getPrzedmiot().getSila())*zwierze.getPrzedmiot().getSzczescie()*0.02);
            przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
            System.out.println("Atakuje za: "+ obrazenia);
        }
        else{
            int obrazenia = (int) ((zwierze.getSila())*0.4);
            przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
            System.out.println("Atakuje za: "+ obrazenia);
        }



    }

}

