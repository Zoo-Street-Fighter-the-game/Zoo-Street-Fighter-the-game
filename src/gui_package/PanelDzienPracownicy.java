package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Pracownik_package.Pracownik;
import interfejsy.ObserwujacyPracownikGUI_interface;
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

    private ArrayList<ObserwujacyPracownikGUI_interface> listaObserwatorow;

    public PanelDzienPracownicy(Sklep sklep)
    {
        this.zoo = sklep.getZoo();
        sklep.dodajObsewatoraGUI(this);
        sklep.setPanelDzienPracownicy(this);
        zaznaczNie.addActionListener(new Odzaznaczanie());
        this.add(zaznaczNie);
        listaObserwatorow =new ArrayList<>();


        this.setBackground(Color.red);
        this.setPreferredSize(new Dimension(250, 0));


    }
    class Zaznaczanie implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            zaznaczonyPracownik=zoo.getListaPracownikow().get(listaprzyciskow.indexOf((JRadioButton) e.getSource()));
            for(ObserwujacyPracownikGUI_interface o : listaObserwatorow)
            {
                o.reakcjaZaznaczenie();
            }

        }
    }
    class Odzaznaczanie implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
                grupapracownikow.clearSelection();
                zaznaczonyPracownik=null;
                for(ObserwujacyPracownikGUI_interface o : listaObserwatorow)
                {
                o.reakcjaOdznaczenie();
                }

        }
    }
    public void dodajPracownika(Pracownik p)
    {
        listaprzyciskow.add(new JRadioButton(p.toString()));
        this.add(listaprzyciskow.getLast());
        grupapracownikow.add(listaprzyciskow.getLast());
        listaprzyciskow.getLast().addActionListener(new Zaznaczanie() );
    }
    public void usunPracownika(int numer)
    {

        this.remove(listaprzyciskow.get(numer));
        grupapracownikow.remove(listaprzyciskow.get(numer));
        listaprzyciskow.remove(numer);


    }

    public Pracownik getZaznaczonyPracownik() {
        return zaznaczonyPracownik;
    }

    public ArrayList<ObserwujacyPracownikGUI_interface> getListaObserwatorow() {
        return listaObserwatorow;
    }

    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }
}
