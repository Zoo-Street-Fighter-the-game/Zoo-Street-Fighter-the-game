package Przedmioty;

public class RekawiceBokserskie extends Przedmiot{
    RekawiceBokserskie(){
        this.setNazwa("Rekawice bokserskie");
        this.setOpis("Rekawice bokserskie zwiekszaja sile ciosow twojego zwierzecia, lecz takze umozliwiaja mu trzymanie gardy " +
                "(chociaz podobno najlepsza obrona jest atak)");
        this.setCena(150+((int)(Math.random()*10)));
        this.setSila(70+((int)(Math.random()*20)));
        this.setSzczescie(15);
        this.setSzybkosc(20+((int)(Math.random()*10)));
        this.setZycie(30+((int)(Math.random()*20)));
    }
}
