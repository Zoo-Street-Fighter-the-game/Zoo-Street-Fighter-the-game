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
    private JRadioButton zaznaczonyRadioButton;
    private ArrayList<ObserwujacyPracownikGUI_interface> listaObserwatorow;

    ImageIcon wybrany3 = new ImageIcon("src/ikony/IkonaPracownikWybrany3.png");
    ImageIcon wybrany2 = new ImageIcon("src/ikony/IkonaPracownikWybrany2.png");
    ImageIcon wybrany1 = new ImageIcon("src/ikony/IkonaPracownikWybrany1.png");
    ImageIcon wybrany0 = new ImageIcon("src/ikony/IkonaPracownikWybrany0.png");

    ImageIcon odznaczony3 = new ImageIcon("src/ikony/IkonaPracownik3.png");
    ImageIcon odznaczony2 = new ImageIcon("src/ikony/IkonaPracownik2.png");
    ImageIcon odznaczony1 = new ImageIcon("src/ikony/IkonaPracownik1.png");
    ImageIcon odznaczony0 = new ImageIcon("src/ikony/IkonaPracownik0.png");

    public PanelDzienPracownicy(Sklep sklep)
    {
        this.zoo = sklep.getZoo();
        sklep.dodajObsewatoraGUI(this);
        sklep.setPanelDzienPracownicy(this);

        JLabel logo = new JLabel();
        logo.setIcon( new ImageIcon("src/ikony/EmployessImage.png"));
        this.add(logo);
        zaznaczNie.addActionListener(new Odzaznaczanie());

        zaznaczNie.setFont(new Font("Comic Sans",Font.BOLD,15));
        zaznaczNie.setForeground(Color.BLACK);
        zaznaczNie.setBackground(new Color(0xe3e2de));
        this.add(zaznaczNie);
        listaObserwatorow =new ArrayList<>();


        this.setBackground(new Color(0xd3f3e3));
        this.setPreferredSize(new Dimension(250, 0));


    }
    class Zaznaczanie implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            zaznaczonyPracownik=zoo.getListaPracownikow().get(listaprzyciskow.indexOf((JRadioButton) e.getSource()));
            zaznaczonyRadioButton=(JRadioButton) e.getSource();
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
                zaznaczonyRadioButton=null;
                for(ObserwujacyPracownikGUI_interface o : listaObserwatorow)
                {
                o.reakcjaOdznaczenie();
                }

        }
    }
    public void dodajPracownika(Pracownik p)
    {
        listaprzyciskow.add(new JRadioButton(p.toString()));
        listaprzyciskow.getLast().setBackground(new Color(0xe3e2de));
        listaprzyciskow.getLast().setIcon(odznaczony3);
        listaprzyciskow.getLast().setSelectedIcon(wybrany3);
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
        if(zaznaczonyPracownik!=null) {
            switch (zaznaczonyPracownik.getIloscakcji()) {
                case 3: {
                    zaznaczonyRadioButton.setSelectedIcon(wybrany3);
                    zaznaczonyRadioButton.setIcon(odznaczony3);
                    break;
                }
                case 2: {
                    zaznaczonyRadioButton.setSelectedIcon(wybrany2);
                    zaznaczonyRadioButton.setIcon(odznaczony2);
                    break;
                }
                case 1: {
                    zaznaczonyRadioButton.setSelectedIcon(wybrany1);
                    zaznaczonyRadioButton.setIcon(odznaczony1);
                    break;
                }
                case 0: {
                    zaznaczonyRadioButton.setSelectedIcon(wybrany0);
                    zaznaczonyRadioButton.setIcon(odznaczony0);
                    break;
                }
                default: {
                    zaznaczonyRadioButton.setSelectedIcon(new ImageIcon("src/ikony/IkonaPracownikWybrany"));
                    zaznaczonyRadioButton.setIcon(new ImageIcon("src/ikony/IkonaPracownik"));
                    break;
                }
            }
        }
        this.repaint();
        this.revalidate();
    }
}
