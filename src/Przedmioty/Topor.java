package Przedmioty;

public class Topor extends Przedmiot{

    public Topor(){
        this.setNazwa("Topor");
        this.setOpis("Ten topor to dzielo ekscentrycznego kowala, ktory postanowil polaczyc styl z funkcjonalnoscia " +
                "w najbardziej absurdalny sposob. Obrazenia ktore zadaje sa potezne, jednak z wielka moca przychodzi wielka " +
                "odpowiedzialnosc i ciezki topor wyraznie spowalnia ruchy twojego zwierzecego wojownika");
        this.setCena(55+((int)(Math.random()*20)));
        this.setSila(140+((int)(Math.random()*15)));
        this.setSzczescie(5+((int)(Math.random()*5)));
        this.setSzybkosc(-30+((int)(Math.random()*5)));
        this.setZycie(5);
    }
}
