package gui_package;

import javax.swing.*;
import java.awt.*;
import DzienneZooPakiet.*;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

public class PanelDzienZasoby extends JPanel implements UpdateGUI {
    JLabel monety;
    JLabel jedzenie;
    JLabel exp;
    JLabel zasobymonety;
    JLabel jedzenieIkonka;
    JLabel expIkonka;
    private DzienneZoo zoo;



    public PanelDzienZasoby(DzienneZoo zoo, Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);

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

    @Override
    public void updateGUI() {
        monety.setText(String.valueOf(zoo.getZmiennaZasoby().getMonety()));
        jedzenie.setText(String.valueOf(zoo.getZmiennaZasoby().getJedzenie()));
        exp.setText(String.valueOf(zoo.getZmiennaZasoby().getExp()));
        repaint();
        revalidate();
    }
}
