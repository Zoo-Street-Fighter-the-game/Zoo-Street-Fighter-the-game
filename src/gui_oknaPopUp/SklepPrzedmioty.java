package gui_oknaPopUp;

import Klasy_Zwierzat.Zwierze;
import enumy.przedmioty_enum;
import gui_package.ListaWybiegow2;
import pakiet_sklep.Sklep;
import DzienneZooPakiet.*;
import gui_package.ListaZwierzat2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SklepPrzedmioty extends JFrame {
    private Sklep sklep;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JButton kupPrzedmiotButton;
    private final PrzedmiotRadioButton miecz;
    private final PrzedmiotRadioButton wuwuzela;
    private final PrzedmiotRadioButton adidasy;
    private final PrzedmiotRadioButton topor;
    private final PrzedmiotRadioButton patyk;
    private final PrzedmiotRadioButton pnw;
    private final PrzedmiotRadioButton zbroja;
    private final PrzedmiotRadioButton rekawice;
    private ListaWybiegow2 panelWybiegi;
    private ListaZwierzat2 panelNazwy;
    private final DzienneZoo zoo;
    private JPanel panelMain;
    private JLabel text;
    private JPanel panelRadio;
    private ButtonGroup group;
    private PrzedmiotRadioButton wybranaBron;


    public SklepPrzedmioty(Sklep sklep){
        this.sklep=sklep;
        this.zoo=sklep.getZoo();
        panelMain = new JPanel();
        panelRadio = new JPanel();
        text = new JLabel("Wybierz broń: ");
        this.setLayout(new GridLayout(1, 1));


        kupPrzedmiotButton = new JButton("Kup przedmiot");
        kupPrzedmiotButton.setEnabled(false);
        kupPrzedmiotButton.addActionListener(new ReakcjaKupPrzedmiotButton());

        miecz = new PrzedmiotRadioButton("miecz", przedmioty_enum.MIECZ);
        miecz.addActionListener(new ReakcjaPrzedmiotRadioButton());

        wuwuzela = new PrzedmiotRadioButton("wuwuzela", przedmioty_enum.WUWUZELA);
        wuwuzela.addActionListener(new ReakcjaPrzedmiotRadioButton());

        topor = new PrzedmiotRadioButton("topór", przedmioty_enum.TOPOR);
        topor.addActionListener(new ReakcjaPrzedmiotRadioButton());

        adidasy = new PrzedmiotRadioButton("adidasy", przedmioty_enum.ADIDASY);
        adidasy.addActionListener(new ReakcjaPrzedmiotRadioButton());

        pnw = new PrzedmiotRadioButton("pistolet na wode", przedmioty_enum.PISTOLET_NA_WODE);
        pnw.addActionListener(new ReakcjaPrzedmiotRadioButton());

        patyk = new PrzedmiotRadioButton("patyk", przedmioty_enum.PATYK);
        patyk.addActionListener(new ReakcjaPrzedmiotRadioButton());

        zbroja = new PrzedmiotRadioButton("zbroja", przedmioty_enum.ZBROJA);
        zbroja.addActionListener(new ReakcjaPrzedmiotRadioButton());

        rekawice = new PrzedmiotRadioButton("rękawice bokserskie", przedmioty_enum.REKAWICE_BOKSERSKIE);
        rekawice.addActionListener(new ReakcjaPrzedmiotRadioButton());

        group = new ButtonGroup();

        group.add(zbroja);
        group.add(patyk);
        group.add(rekawice);
        group.add(topor);
        group.add(wuwuzela);
        group.add(miecz);
        group.add(pnw);
        group.add(adidasy);

        panelRadio.add(zbroja);
        panelRadio.add(patyk);
        panelRadio.add(rekawice);
        panelRadio.add(topor);
        panelRadio.add(wuwuzela);
        panelRadio.add(miecz);
        panelRadio.add(pnw);
        panelRadio.add(adidasy);

        panelRadio.setLayout(new GridLayout(2,4));
        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets=new Insets(10,10,10,10);

        gbc.gridwidth=3;
        gbc.gridx=0;
        gbc.gridy=0;

        panelMain.add(text, gbc);

        gbc.gridwidth=3;
        gbc.gridx=0;
        gbc.gridy=1;

        panelMain.add(panelRadio, gbc);

        gbc.gridwidth=3;
        gbc.gridx=0;
        gbc.gridy=2;


        panelMain.add(kupPrzedmiotButton, gbc);


        this.setTitle("Kup Przedmiot");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        kupPrzedmiotButton.setFocusable(false);

        panelWybiegi = new ListaWybiegow2(zoo);
        panelNazwy = new ListaZwierzat2(zoo, panelWybiegi);

        this.setLayout(new BorderLayout());
        this.add(panelWybiegi, BorderLayout.NORTH);
        this.add(panelNazwy, BorderLayout.CENTER);
        this.add(panelMain, BorderLayout.SOUTH);



        this.pack();
        this.setVisible(true);

    }

    class ReakcjaPrzedmiotRadioButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            kupPrzedmiotButton.setEnabled(true);
            wybranaBron = (PrzedmiotRadioButton)e.getSource();
        }
    }

    class ReakcjaKupPrzedmiotButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
          //  sklep.kupBron();

        }
    }






}

