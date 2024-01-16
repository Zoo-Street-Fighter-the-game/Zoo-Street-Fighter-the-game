package gui_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DzienneZooPakiet.*;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;
import propozycja_gui_package.Animacja;

public class PanelDzienZasoby extends JPanel implements UpdateGUI, ActionListener {
    JLabel zasobymonety;
    JLabel jedzenieIkonka;
    JLabel expIkonka;
    JPanel naPrzycisk;

    JButton zmiana_na_noc_przycisk;
    private final DzienneZoo zoo;
    private Sklep sklep;


    public PanelDzienZasoby(Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);

        this.sklep = sklep;
        this.zoo=sklep.getZoo();
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 18, 10));

        zasobymonety = new JLabel("Monety: " + zoo.getZmiennaZasoby().getMonety());
        zasobymonety.setIcon(new ImageIcon("src/ikony/IkonaMonety.png"));
        zasobymonety.setFont(new Font(null, Font.ITALIC, 40));
        jedzenieIkonka = new JLabel("Jedzenie: " + zoo.getZmiennaZasoby().getJedzenie());
        jedzenieIkonka.setIcon(new ImageIcon("src/ikony/IkonaJedzenie.png"));
        jedzenieIkonka.setFont(new Font(null, Font.ITALIC, 40));
        expIkonka = new JLabel("Exp: " + zoo.getZmiennaZasoby().getExp());
        expIkonka.setIcon(new ImageIcon("src/ikony/IkonaExp.png"));
        expIkonka.setFont(new Font(null, Font.ITALIC, 40));

        naPrzycisk = new JPanel();
        naPrzycisk.setPreferredSize(new Dimension(420,70));
        naPrzycisk.setBackground(new Color(0,0,0,0));
        naPrzycisk.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


        zmiana_na_noc_przycisk = new JButton("ZAKONCZ DZIEN");
        zmiana_na_noc_przycisk.setFocusable(false);
        zmiana_na_noc_przycisk.addActionListener(new ReakcjaZakonczDzien());
        zmiana_na_noc_przycisk.setFont(new Font(null, Font.BOLD|Font.ITALIC, 20));
        zmiana_na_noc_przycisk.setPreferredSize(new Dimension(220, 60));
        zmiana_na_noc_przycisk.setBackground(new Color(0xfcc3a3));
        zmiana_na_noc_przycisk.setOpaque(true);

        naPrzycisk.add(zmiana_na_noc_przycisk);
        this.add(naPrzycisk);
        this.add(zasobymonety);
        this.add(jedzenieIkonka);
        this.add(expIkonka);



        this.setBackground(new Color(0xD3F3E3));
        this.setPreferredSize(new Dimension(0, 75));

    }


    public class ReakcjaZakonczDzien implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.zakonczDzien();
            //obiekt.przekazZoo();
            //go_to_night()
        }
    }
/*    public void go_to_night(){
        new Animacja();

    }*/
    @Override
    public void updateGUI() {
        zasobymonety.setText("Monety: " +zoo.getZmiennaZasoby().getMonety());
        jedzenieIkonka.setText("Jedzenie: " +zoo.getZmiennaZasoby().getJedzenie());
        expIkonka.setText("Exp: " + zoo.getZmiennaZasoby().getExp());
        repaint();
        revalidate();
    }


    public void actionPerformed(ActionEvent e) {
        //TO JEST PUSTE:)
    }
}
