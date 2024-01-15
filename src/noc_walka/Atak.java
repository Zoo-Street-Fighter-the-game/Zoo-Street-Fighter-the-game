package noc_walka;

import Klasy_Zwierzat.Zwierze;

import java.util.Random;

public class Atak implements MenuAkcji{
    @Override
    public void MenuAkcji(Zwierze zwierze, Zwierze przeciwnik) {
        Random random = new Random();

            int obrazenia = Math.abs((int) ((zwierze.getSila())*0.1+(zwierze.getSila())*Math.random()));
            przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
            System.out.println("Atakuje za: "+ obrazenia);




    }

}

