package noc_walka;

import Klasy_Zwierzat.Zwierze;

public class Atak implements MenuAkcji{

    @Override
    public void menuAkcji(Zwierze zwierze, Zwierze przeciwnik) {

        if(zwierze.getPrzedmiot()!=null){
            int obrazenia = (int) ((zwierze.getSila())*0.1+(zwierze.getSila())*Math.random()+0.2*(zwierze.getPrzedmiot().getSila()));
            przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
            System.out.println("Atakuje za: "+ obrazenia);

        }else{
            int obrazenia = (int) ((zwierze.getSila())*0.1+(zwierze.getSila())*Math.random());
            przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
            System.out.println("Atakuje za: "+ obrazenia);


        }
        //int obrazenia = (int) ((zwierze.getSila())*0.1+(zwierze.getSila())*Math.random());
/*        przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
        System.out.println("Atakuje za: "+ obrazenia);*/
    }
}

