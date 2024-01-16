package noc_walka;

import Klasy_Zwierzat.Zwierze;

public class Atak implements MenuAkcji{

    @Override
    public void menuAkcji(Zwierze zwierze, Zwierze przeciwnik) {


        int obrazenia = (int) ((zwierze.getSila())*0.1+(zwierze.getSila())*Math.random());
        przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
        System.out.println("Atakuje za: "+ obrazenia);
    }
}

