package Przedmioty;

public class Wuwuzela extends Przedmiot{
    Wuwuzela(){
        this.setNazwa("Wuwuzela");
        this.setOpis("Pieniadze szczescia nie daja, ale nasza wuwuzela jak najbardziej. Naprawde. " +
                "Jesli czujesz ze los ci sprzyja wuwuzela moze byc idealna bronia dla ciebie, poniewaz " +
                "znacznie zwieksza szanse na zadanie krytycznych obrazen");
        this.setCena(140+((int)(Math.random()*20)));
        this.setSila(55+((int)(Math.random()*5)));
        this.setSzczescie(70+((int)(Math.random()*20)));
        this.setSzybkosc(35+((int)(Math.random()*7)));
        this.setZycie(10+((int)(Math.random()*15)));
    }
}
