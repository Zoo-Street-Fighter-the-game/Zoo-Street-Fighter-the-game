package Przedmioty;

public class Adidasy extends Przedmiot{

    public Adidasy() {
        this.setNazwa("Adidasy");
        this.setOpis("Chcesz zeby twoje zwierze pedzilo jak wiatr? Adidasy zapewniaja duzy bonus do szybkosci. " +
                "Byc moze myslisz sobie; ale moje zwierze ma pletwy :( Nic nie szkodzi! Nasz produkt nie dyskryminuje " +
                "i umozliwia osiagniecie zawrotnej predkosci wszystkim istotom duzym i malym.");
        this.setCena(60+((int)(Math.random()*15)));
        this.setSila(5+((int)(Math.random()*5)));
        this.setSzczescie(20+((int)(Math.random()*5)));
        this.setSzybkosc(80+((int)(Math.random()*20)));
        this.setZycie(10+((int)(Math.random()*20)));


    }
}
