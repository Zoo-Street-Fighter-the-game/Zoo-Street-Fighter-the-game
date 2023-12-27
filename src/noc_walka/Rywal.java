package noc_walka;
import Klasy_Zwierzat.*;

import java.util.Random;

public class Rywal {
    private String[] ladowe = {
            "Malpa",
            "Koza",
            "Lew",
            "Pies",
    };
    private String[] wodne = {
            "Karp",
            "Delfin",

    };
    private String[] powietrzne = {
            "Orzel",
            "Słowik"
    };
    public void losuj_rywala(){
        Random random = new Random();

        int x = random.nextInt(3);
        if(x == 1){
            int index = random.nextInt(ladowe.length);
            String message = ladowe[index];
            ZwierzeLadowe przeciwnik = new ZwierzeLadowe(message,100,30,20,100,0,100,0,3000);


        } else if (x==2) {
            int index = random.nextInt(powietrzne.length);
            String message = powietrzne[index];
            ZwierzePowietrzne przeciwnik = new ZwierzePowietrzne(message,100,30,20,100,0,100,0,3000);

        }else{
            int index = random.nextInt(wodne.length);
            String message = wodne[index];
            ZwierzeWodne przeciwnik = new ZwierzeWodne(message,100,30,20,100,0,100,0,3000);

        }

    }
}
