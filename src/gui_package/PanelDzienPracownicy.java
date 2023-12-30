package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Pracownik_package.Pracownik;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelDzienPracownicy extends JPanel implements UpdateGUI {

    private ArrayList<JRadioButton> listaprzyciskow=new ArrayList<>();
    private DzienneZoo zoo;
    private ButtonGroup grupapracownikow=new ButtonGroup();
    private JButton zaznaczNie = new JButton("Usun zaznaczenie");
    private Pracownik zaznaczonyPracownik;
    public PanelDzienPracownicy(DzienneZoo zoo, Sklep sklep)
    {
        this.zoo = zoo;
        sklep.dodajObsewatoraGUI(this);
        sklep.setPanelDzienPracownicy(this);
        zaznaczNie.addActionListener(new Odzaznaczanie() );
        this.add(zaznaczNie);

        this.setBackground(Color.red);
        this.setPreferredSize(new Dimension(250, 0));


    }
    class zaznaczanie implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
                    zaznaczonyPracownik=zoo.getListaPracownikow().get(listaprzyciskow.indexOf((JRadioButton) e.getSource()));

        }
    }
    class Odzaznaczanie implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==zaznaczNie)
            {
                grupapracownikow.clearSelection();
                zaznaczonyPracownik=null;
            }
        }
    }
    public void dodajpracownika(Pracownik p)
    {
        listaprzyciskow.add(new JRadioButton(p.toString()));
        this.add(listaprzyciskow.getLast());
        grupapracownikow.add(listaprzyciskow.getLast());
        listaprzyciskow.getLast().addActionListener(new zaznaczanie() );
    }
    public void usunpracownika (int numer)
    {

        this.remove(listaprzyciskow.get(numer));
        grupapracownikow.remove(listaprzyciskow.get(numer));
        listaprzyciskow.remove(numer);


    }


    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }
}
