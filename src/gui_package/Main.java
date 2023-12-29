package gui_package;
import DzienneZooPakiet.DzienneZoo;
import gui_package.MyFrame;
import pakiet_sklep.Sklep;

public class Main {
    public static void main (String [] args)
    {
        MyFrame Frame = new MyFrame(DzienneZoo.getInstance(), new Sklep(DzienneZoo.getInstance()));
    }
}
