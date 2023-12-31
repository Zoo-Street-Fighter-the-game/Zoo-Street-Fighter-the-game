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



    public PanelDzienZasoby(Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);

        this.zoo=sklep.getZoo();
        monety = new JLabel(String.valueOf(zoo.getZmiennaZasoby().getMonety()));
        monety.setFont(new Font(null, Font.PLAIN, 30));
        jedzenie = new JLabel(String.valueOf(zoo.getZmiennaZasoby().getJedzenie()));
        jedzenie.setFont(new Font(null, Font.PLAIN, 30));
        exp = new JLabel(String.valueOf(zoo.getZmiennaZasoby().getExp()));
        exp.setFont(new Font(null, Font.PLAIN, 30));
        zasobymonety = new JLabel("Monety: ");
        zasobymonety.setFont(new Font(null, Font.PLAIN, 30));
        jedzenieIkonka = new JLabel("Jedzenie: ");
        jedzenieIkonka.setFont(new Font(null, Font.PLAIN, 30));
        expIkonka = new JLabel("exp: ");
        expIkonka.setFont(new Font(null, Font.PLAIN, 30));

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
