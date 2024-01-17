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
import java.util.ArrayList;
import Wybieg_package.*;
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
    private JPanel panelNazwy;
    private final DzienneZoo zoo;
    private JPanel panelMain;
    private JLabel text;
    private JPanel panelRadio;
    private ButtonGroup group;
    private PrzedmiotRadioButton wybranaBron;
    ///////////
    private ArrayList<JRadioButton> listaRadioButton;
    private ButtonGroup grupka;
    private Wybieg_podstawowy wybieg;
    private Zwierze wybraneZwierze;


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
       // panelNazwy = new ListaZwierzat2(zoo, panelWybiegi);
////////////////////////
       panelNazwy = new JPanel();
       panelNazwy.setLayout(new BorderLayout());
        listaRadioButton=new ArrayList<>();
        JTabbedPane tabbedPane = panelWybiegi.getTabbedPane();
        if (tabbedPane == null) {
            throw new IllegalStateException("JTabbedPane is null");
        }
        tabbedPane.removeAll();
        JPanel centralPanel = new JPanel(new GridLayout(1, 1));
        grupka = new ButtonGroup();
        for (int i = 0; i < zoo.getListaWybiegow().size(); i++) {
            wybieg = zoo.getListaWybiegow().get(i);
            JPanel wybiegPanel = new JPanel(new BorderLayout());
            tabbedPane.addTab("Wybieg " + (i + 1), wybiegPanel);

            dodajZwierzetaDoPanelu(wybiegPanel, wybieg, i, grupka);

            centralPanel.add(tabbedPane);
        }
        panelNazwy.add(centralPanel, BorderLayout.CENTER);





        this.setLayout(new BorderLayout());
        this.add(panelWybiegi, BorderLayout.NORTH);
        this.add(panelNazwy, BorderLayout.CENTER);
        this.add(panelMain, BorderLayout.SOUTH);



        this.pack();
        this.setVisible(true);

    }

    private void dodajZwierzetaDoPanelu(JPanel wybiegPanel, Wybieg_podstawowy wybieg, int numerWybiegu, ButtonGroup group) {
        ArrayList<Zwierze> listaZwierzat = new ArrayList<>(wybieg.getLista_zwierzat());
        JPanel RadioPanel2 = new JPanel();

        for (Zwierze zwierze : listaZwierzat) {
            listaRadioButton.add( new JRadioButton(zwierze.getNazwa()));
            (listaRadioButton.getLast()).addActionListener(new ReactionRadioButton());
            group.add(listaRadioButton.getLast());
            RadioPanel2.add(listaRadioButton.getLast());
        }

        wybiegPanel.add(RadioPanel2);
    }

    public class ReactionRadioButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            wybraneZwierze =(wybieg.getLista_zwierzat().get(listaRadioButton.indexOf(((JRadioButton)e.getSource()))));
        }
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
         if(wybraneZwierze!=null&&wybranaBron.getTyp()!=null) {
             sklep.kupBron(wybranaBron.getTyp(), wybraneZwierze);
         }else {JOptionPane.showMessageDialog(SklepPrzedmioty.this,
                 "Wybierz prosze wszystkie opcje",
                 "Błąd",
                 JOptionPane.ERROR_MESSAGE);}

        }
    }

    public static void brakSrodkow()
    {
        JOptionPane.showMessageDialog(null,
                "Nie masz wystarczajaco duzo monet!",
                "Brak Srodkow",
                JOptionPane.ERROR_MESSAGE);
    }




}

