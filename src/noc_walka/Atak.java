package noc_walka;

import Klasy_Zwierzat.Zwierze;

public class Atak implements MenuAkcji{
    @Override
    public void menuAkcji(Zwierze zwierze, Zwierze przeciwnik) {
        //tutaj docelowo ma znajdowac sie mechanizm obrazen polaczony z wartosciami broni i zwierzat
        int obrazenia;
        if(zwierze.getPrzedmiot()!=null){
            obrazenia = (int) ((zwierze.getSila() + zwierze.getPrzedmiot().getSila()) * zwierze.getPrzedmiot().getSzczescie() * 0.02);
        }
        else{
            obrazenia = (int) ((zwierze.getSila()) * 0.4);
        }
        przeciwnik.setZycie(przeciwnik.getZycie() - obrazenia);
        System.out.println("Atakuje za: "+ obrazenia);


    }

}

