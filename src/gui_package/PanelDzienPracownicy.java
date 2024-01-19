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

import java.util.*;


public class PanelDzienPracownicy extends JPanel implements UpdateGUI {

    private final ArrayList<JRadioButton> listaprzyciskow=new ArrayList<>();
    private final DzienneZoo zoo;
    private final ButtonGroup grupapracownikow=new ButtonGroup();
    private Pracownik zaznaczonyPracownik;
    private JRadioButton zaznaczonyRadioButton;
    private final ArrayList<ObserwujacyPracownikGUI_interface> listaObserwatorow;
    private final HashMap<Pracownik,JRadioButton> HS= new HashMap<>();

    ImageIcon wybrany3 = new ImageIcon("src/ikony/IkonaPracownikWybrany3.png");
    ImageIcon wybrany2 = new ImageIcon("src/ikony/IkonaPracownikWybrany2.png");
    ImageIcon wybrany1 = new ImageIcon("src/ikony/IkonaPracownikWybrany1.png");
    ImageIcon wybrany0 = new ImageIcon("src/ikony/IkonaPracownikWybrany0.png");

    ImageIcon odznaczony3 = new ImageIcon("src/ikony/IkonaPracownik3.png");
    ImageIcon odznaczony2 = new ImageIcon("src/ikony/IkonaPracownik2.png");
    ImageIcon odznaczony1 = new ImageIcon("src/ikony/IkonaPracownik1.png");
    ImageIcon odznaczony0 = new ImageIcon("src/ikony/IkonaPracownik0.png");

    public PanelDzienPracownicy(Sklep sklep) {
        this.zoo = sklep.getZoo();
        sklep.dodajObsewatoraGUI(this);
        sklep.setPanelDzienPracownicy(this);

        JLabel logo = new JLabel();
        logo.setIcon( new ImageIcon("src/ikony/EmployessImage.png"));
        this.add(logo);
        JButton zaznaczNie = new JButton("Usun zaznaczenie");
        zaznaczNie.addActionListener(new Odzaznaczanie());

        zaznaczNie.setFont(new Font("Comic Sans",Font.BOLD,15));
        zaznaczNie.setForeground(Color.BLACK);
        zaznaczNie.setBackground(new Color(0xe3e2de));
        this.add(zaznaczNie);
        listaObserwatorow =new ArrayList<>();


        this.setBackground(new Color(0xd3f3e3));
        this.setPreferredSize(new Dimension(250, 0));


    }

    public void sortujprzyciski()
    {
        for (JRadioButton jRadioButton : listaprzyciskow) {
            this.remove(jRadioButton);
        }
        Collections.sort(zoo.getListaPracownikow());
        for (int i=0; i<listaprzyciskow.size();i++)
        {
            this.add(HS.get(zoo.getListaPracownikow().get(i)));
        }

    }
    public <K, V> K getKeyByValue(HashMap<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    class Zaznaczanie implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            sortujprzyciski();
            zaznaczonyPracownik= getKeyByValue(HS, (JRadioButton) e.getSource());
            System.out.println(zaznaczonyRadioButton);
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
        HS.put(p, listaprzyciskow.getLast());
        sortujprzyciski();

    }

    public void usunPracownika(int numer)
    {
        if (numer >= 0 && numer < listaprzyciskow.size()) {
            this.remove(HS.get(zoo.getListaPracownikow().get(numer)));
            grupapracownikow.remove(listaprzyciskow.get(numer));
            listaprzyciskow.remove(numer);
            HS.remove(zoo.getListaPracownikow().get(numer));
        }
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
