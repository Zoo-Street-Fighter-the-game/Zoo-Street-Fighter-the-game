package noc_walka;

import Klasy_Zwierzat.Zwierze;
import pakiet_arena.Arena;

import java.util.Random;

public class Atak implements MenuAkcji{
    @Override
    public void MenuAkcji(Zwierze zwierze, Zwierze przeciwnik) {
        Random random = new Random();
        double x = random.nextDouble(1);
        int obrazenia = (int) (zwierze.getSila()*x);
        przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
        System.out.println("Atakuje za: "+ obrazenia);
    }

}

