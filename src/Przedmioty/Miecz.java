package Przedmioty;

public class Miecz extends Przedmiot{

    public Miecz(){
        this.setNazwa("Miecz");
        this.setOpis("Moze nie jest to ekskalibur, ale z twoich zwierzat tez zaden krol Artur. " +
                "Miecz zapewnia hojny bonus do obrazen w walkach zwierzat. Wnoszenie broni bialej na arene walk" +
                " nie jest prawdopodobnie najuczciwszym zagraniem, ale takie juz prawa miejskiej dzungli.");
        this.setCena(55+((int)(Math.random()*20)));
        this.setSila(85+((int)(Math.random()*15)));
        this.setSzczescie(10+((int)(Math.random()*10)));
        this.setSzybkosc(30+((int)(Math.random()*5)));
        this.setZycie(4);
    }
}
