package gui_package;

import javax.swing.*;
import java.awt.*;
import DzienneZooPakiet.*;

public class PanelDzienZasoby extends JPanel {
    JLabel monety;
    JLabel jedzenie;
    JLabel exp;
    JLabel zasobymonety;
    JLabel jedzenieIkonka;
    JLabel expIkonka;
    private DzienneZoo zoo;



    public PanelDzienZasoby(DzienneZoo zoo)
    {
        this.zoo=zoo;
        monety = new JLabel(String.valueOf(zoo.getZmiennaZasoby().getMonety()));
        jedzenie = new JLabel(String.valueOf(zoo.getZmiennaZasoby().getJedzenie()));
        exp = new JLabel(String.valueOf(zoo.getZmiennaZasoby().getExp()));
        zasobymonety = new JLabel("Monety: ");
        jedzenieIkonka = new JLabel("Jedzenie: ");
        expIkonka = new JLabel("exp: ");

        this.add(zasobymonety);
        this.add(monety);
        this.add(jedzenieIkonka);
        this.add(jedzenie);
        this.add(expIkonka);
        this.add(exp);



        this.setBackground(Color.green);
        this.setPreferredSize(new Dimension(0, 75));

    }
    public void updatezasoby()
    {
        monety.setText(String.valueOf(zoo.getZmiennaZasoby().getMonety()));
        jedzenie.setText(String.valueOf(zoo.getZmiennaZasoby().getJedzenie()));
        exp.setText(String.valueOf(zoo.getZmiennaZasoby().getExp()));
        this.repaint();
        this.revalidate();

    }
}
