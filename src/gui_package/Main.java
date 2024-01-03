package gui_package;
import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;

public class Main {
    public static void main (String [] args)
    {
        MyFrame Frame = new MyFrame(new Sklep(DzienneZoo.getInstance()));
    }
    //trzeba zrobic polaczenie miedzy wskaznikiem glodu zwierzecia a jego smierci
}
