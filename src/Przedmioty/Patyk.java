package Przedmioty;

public class Patyk extends Przedmiot{
    public Patyk(){
        this.setNazwa("Patyk");
        this.setOpis("Uwaga: Przedmiot ten powinien byc uzywany jedynie w celach humorystycznych " +
                "i nie jest przeznaczony do powaznych starc. W przypadku konfrontacji z potezniejszymi przeciwnikami, " +
                "zdecydowanie zaleca sie ulepszenie do bardziej przyzwoitej broni.");
        this.setCena(5+((int)(Math.random()*10)));
        this.setSila(5+((int)(Math.random()*4)));
        this.setSzczescie(2+((int)(Math.random()*3)));
        this.setSzybkosc(3+((int)(Math.random()*7)));
        this.setZycie(0);
    }
}
