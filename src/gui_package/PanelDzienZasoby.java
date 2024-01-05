package gui_package;

import javax.swing.*;
import java.awt.*;
import DzienneZooPakiet.*;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

public class PanelDzienZasoby extends JPanel implements UpdateGUI {
    JLabel zasobymonety;
    JLabel jedzenieIkonka;
    JLabel expIkonka;
    private DzienneZoo zoo;



    public PanelDzienZasoby(Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);

        this.zoo=sklep.getZoo();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        zasobymonety = new JLabel("Monety: " + zoo.getZmiennaZasoby().getMonety());
        zasobymonety.setIcon(new ImageIcon("src/ikony/IkonaMonety.png"));
        zasobymonety.setFont(new Font(null, Font.ITALIC, 40));
        jedzenieIkonka = new JLabel("Jedzenie: " + zoo.getZmiennaZasoby().getJedzenie());
        jedzenieIkonka.setIcon(new ImageIcon("src/ikony/IkonaJedzenie.png"));
        jedzenieIkonka.setFont(new Font(null, Font.ITALIC, 40));
        expIkonka = new JLabel("Exp: " + zoo.getZmiennaZasoby().getExp());
        expIkonka.setIcon(new ImageIcon("src/ikony/IkonaExp.png"));
        expIkonka.setFont(new Font(null, Font.ITALIC, 40));

        this.add(zasobymonety);
        this.add(jedzenieIkonka);
        this.add(expIkonka);



        this.setBackground(new Color(0xD3F3E3));
        this.setPreferredSize(new Dimension(0, 75));

    }

    @Override
    public void updateGUI() {
        zasobymonety.setText("Monety: " +zoo.getZmiennaZasoby().getMonety());
        jedzenieIkonka.setText("Jedzenie: " +zoo.getZmiennaZasoby().getJedzenie());
        expIkonka.setText("Exp: " + zoo.getZmiennaZasoby().getExp());
        repaint();
        revalidate();
        System.out.println("apdejt zasobow");
    }
}
