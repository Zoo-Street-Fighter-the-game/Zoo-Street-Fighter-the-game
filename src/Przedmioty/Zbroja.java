package Przedmioty;

public class Zbroja extends Przedmiot{
    Zbroja(){
        this.setNazwa("Zbroja");
        this.setOpis("Ktos moglby stwierdzic ze zbroja dla pingwina czy zyrafy to głupota. " +
                "Ktos prawdopodobnie ma racje, lecz czy jestes w stanie przejsc obojetnie wobec swietnych benefitow, " +
                "jakie oferuje? Kup nasza zbroje juz dzis, a twoj pasek zycia znaczaco sie powiekszy." +
                "Giermek sprzedawany oddzielnie.");
        this.setCena(50+((int)(Math.random()*20)));
        this.setSila(30+((int)(Math.random()*10)));
        this.setSzczescie(2);
        this.setSzybkosc(-20+((int)(Math.random()*4)));
        this.setZycie(50+((int)(Math.random()*10)));
    }
}
