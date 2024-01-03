package Przedmioty;

public class PistoletNaWode extends Przedmiot{
    PistoletNaWode(){
        this.setNazwa("Pistolet na wode");
        this.setOpis("Pistolet na wode to swietny wybor dla wszystkich Wondych zwierzat, ktore z reguly maja trudniej " +
                "w walkach zwierzat. #LifeIsBrutal Juz dzis odzyskaj przewage nad przeciwnikami i zamien arene w " +
                "kolejna Atlantyde juz dzis, tylko za " + this.getCena());
        this.setCena(120+((int)(Math.random()*20)));
        this.setSila(55+((int)(Math.random()*20)));
        this.setSzczescie(20+((int)(Math.random()*10)));
        this.setSzybkosc(55+((int)(Math.random()*20)));
        this.setZycie(60+((int)(Math.random()*30)));
    }
}
