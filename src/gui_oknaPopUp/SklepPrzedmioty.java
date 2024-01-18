package gui_oknaPopUp;

import Klasy_Zwierzat.Zwierze;
import enumy.przedmioty_enum;
import gui_package.ListaWybiegow2;
import pakiet_sklep.Sklep;
import DzienneZooPakiet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Wybieg_package.*;
public class SklepPrzedmioty extends JFrame {
    private final Sklep sklep;
    private final JTabbedPane tabbedPane;
    private final JButton kupPrzedmiotButton;
    private final DzienneZoo zoo;
    private PrzedmiotRadioButton wybranaBron;
    ///////////
    private final ArrayList<JRadioButton> listaRadioButton;
    private Zwierze wybraneZwierze;


    public SklepPrzedmioty(Sklep sklep){
        this.sklep=sklep;
        this.zoo=sklep.getZoo();
        JPanel panelMain = new JPanel();
        JPanel panelRadio = new JPanel();
        JLabel text = new JLabel("Wybierz broń: ");
        this.setLayout(new GridLayout(1, 1));


        kupPrzedmiotButton = new JButton("Kup przedmiot");
        kupPrzedmiotButton.setEnabled(false);
        kupPrzedmiotButton.addActionListener(new ReakcjaKupPrzedmiotButton());

        PrzedmiotRadioButton miecz = new PrzedmiotRadioButton("miecz", przedmioty_enum.MIECZ);
        miecz.addActionListener(new ReakcjaPrzedmiotRadioButton());

        PrzedmiotRadioButton wuwuzela = new PrzedmiotRadioButton("wuwuzela", przedmioty_enum.WUWUZELA);
        wuwuzela.addActionListener(new ReakcjaPrzedmiotRadioButton());

        PrzedmiotRadioButton topor = new PrzedmiotRadioButton("topór", przedmioty_enum.TOPOR);
        topor.addActionListener(new ReakcjaPrzedmiotRadioButton());

        PrzedmiotRadioButton adidasy = new PrzedmiotRadioButton("adidasy", przedmioty_enum.ADIDASY);
        adidasy.addActionListener(new ReakcjaPrzedmiotRadioButton());

        PrzedmiotRadioButton pnw = new PrzedmiotRadioButton("pistolet na wode", przedmioty_enum.PISTOLET_NA_WODE);
        pnw.addActionListener(new ReakcjaPrzedmiotRadioButton());

        PrzedmiotRadioButton patyk = new PrzedmiotRadioButton("patyk", przedmioty_enum.PATYK);
        patyk.addActionListener(new ReakcjaPrzedmiotRadioButton());

        PrzedmiotRadioButton zbroja = new PrzedmiotRadioButton("zbroja", przedmioty_enum.ZBROJA);
        zbroja.addActionListener(new ReakcjaPrzedmiotRadioButton());

        PrzedmiotRadioButton rekawice = new PrzedmiotRadioButton("rękawice bokserskie", przedmioty_enum.REKAWICE_BOKSERSKIE);
        rekawice.addActionListener(new ReakcjaPrzedmiotRadioButton());

        ButtonGroup group = new ButtonGroup();

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

        ListaWybiegow2 panelWybiegi = new ListaWybiegow2(zoo);

        JPanel panelNazwy = new JPanel();
       panelNazwy.setLayout(new BorderLayout());
        listaRadioButton=new ArrayList<>();
        tabbedPane = panelWybiegi.getTabbedPane();
        if (tabbedPane == null) {
            throw new IllegalStateException("JTabbedPane is null");
        }
        tabbedPane.removeAll();
        JPanel centralPanel = new JPanel(new GridLayout(1, 1));
        ButtonGroup grupka = new ButtonGroup();

        for (int i = 0; i < zoo.getListaWybiegow().size(); i++) {
           // wybieg = zoo.getListaWybiegow().get(i);
            JPanel wybiegPanel = new JPanel(new BorderLayout());
            tabbedPane.addTab("Wybieg " + (i + 1), wybiegPanel);

            dodajZwierzetaDoPanelu(wybiegPanel, zoo.getListaWybiegow().get(i), grupka);

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

    private void dodajZwierzetaDoPanelu(JPanel wybiegPanel, Wybieg_podstawowy wybieg, ButtonGroup group) {
        ArrayList<Zwierze> listaZwierzat = new ArrayList<>(wybieg.getLista_zwierzat());
        JPanel RadioPanel2 = new JPanel();

        for (Zwierze zwierze : listaZwierzat) {
            listaRadioButton.add( new JRadioButton(zwierze.getNazwa()+" "+zwierze.getImie()));
            (listaRadioButton.getLast()).addActionListener(new ReactionRadioButton());
            group.add(listaRadioButton.getLast());
            RadioPanel2.add(listaRadioButton.getLast());
        }

        wybiegPanel.add(RadioPanel2);
    }

    public class ReactionRadioButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int selectedTab = tabbedPane.getSelectedIndex();
           wybraneZwierze =(zoo.getListaWybiegow().get(selectedTab).getLista_zwierzat().get(listaRadioButton.indexOf(((JRadioButton)e.getSource()))));
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

    class ReakcjaKupPrzedmiotButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (wybraneZwierze == null || wybranaBron.getTyp() == null) {
                JOptionPane.showMessageDialog(SklepPrzedmioty.this,
                        "Wybierz proszę wszystkie opcje",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            } else if (wybranaBron.getTyp().stworzPrzedmiot().getCena() > zoo.getZmiennaZasoby().getMonety()) {
                JOptionPane.showMessageDialog(SklepPrzedmioty.this,
                        "Za mało środków",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                sklep.kupBron(wybranaBron.getTyp(), wybraneZwierze);
                JOptionPane.showMessageDialog(SklepPrzedmioty.this,
                        "Zakup Udany",
                        "Zakup",
                        JOptionPane.INFORMATION_MESSAGE);
            }

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

