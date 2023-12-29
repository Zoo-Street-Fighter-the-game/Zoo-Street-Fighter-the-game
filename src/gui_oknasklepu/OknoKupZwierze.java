package gui_oknasklepu;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class OknoKupZwierze extends JFrame {
    public OknoKupZwierze (DzienneZoo zoo, Sklep sklepik)
    {
        JLabel text = new JLabel("Kup Zwierzaka");

        this.setTitle("Kup Zwierzaka");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(text);
        this.pack();
        this.setVisible(true);


    }

}
