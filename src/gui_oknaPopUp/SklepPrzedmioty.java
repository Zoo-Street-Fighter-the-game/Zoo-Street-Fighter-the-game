package gui_oknaPopUp;

import Przedmioty.Przedmiot;
import enumy.przedmioty_enum;
import enumy.zwierzeta_enum;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SklepPrzedmioty extends JFrame {
    private Sklep sklep;
    private JButton kupPrzedmiotButton;
    private final PrzedmiotRadioButton miecz;
    private final PrzedmiotRadioButton wuwuzela;
    private final PrzedmiotRadioButton adidasy;
    private final PrzedmiotRadioButton topor;
    private final PrzedmiotRadioButton patyk;
    private final PrzedmiotRadioButton pnw;
    private final PrzedmiotRadioButton zbroja;
    private final PrzedmiotRadioButton rekawice;
    private JPanel panelMain;
    private JLabel text;
    private JPanel panelRadio;
    private ButtonGroup group;

    public SklepPrzedmioty(Sklep sklep){
        this.sklep=sklep;
        panelMain = new JPanel();
        panelRadio = new JPanel();
        text = new JLabel("Wybierz broń: ");


        kupPrzedmiotButton = new JButton("Kup przedmiot");
        kupPrzedmiotButton.setEnabled(false);
        kupPrzedmiotButton.addActionListener(new SklepPrzedmioty.ReakcjaKupZwierzeButton());

        miecz = new PrzedmiotRadioButton("miecz", przedmioty_enum.MIECZ);
        miecz.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

        wuwuzela = new PrzedmiotRadioButton("wuwuzela", przedmioty_enum.WUWUZELA);
        wuwuzela.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

        topor = new PrzedmiotRadioButton("topór", przedmioty_enum.TOPOR);
        topor.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

        adidasy = new PrzedmiotRadioButton("adidasy", przedmioty_enum.ADIDASY);
        adidasy.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

        pnw = new PrzedmiotRadioButton("pistolet na wode", przedmioty_enum.PISTOLET_NA_WODE);
        pnw.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

        patyk = new PrzedmiotRadioButton("patyk", przedmioty_enum.PATYK);
        patyk.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

        zbroja = new PrzedmiotRadioButton("zbroja", przedmioty_enum.ZBROJA);
        zbroja.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

        rekawice = new PrzedmiotRadioButton("rękawice bokserskie", przedmioty_enum.REKAWICE_BOKSERSKIE);
        rekawice.addActionListener(new SklepPrzedmioty.ReakcjaZwierzeRadioButton());

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

        this.add(panelMain);

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

        this.pack();
        this.setVisible(true);

    }

    class ReakcjaZwierzeRadioButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            kupPrzedmiotButton.setEnabled(true);

        }
    }

    class ReakcjaKupZwierzeButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            SklepPrzedmioty.this.dispose();
        }
    }






}

