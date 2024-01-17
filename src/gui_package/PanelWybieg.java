package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import gui_oknaPopUp.OknoNakarmZwierzeta;
import interfejsy.ObserwujacyPracownikGUI_interface;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelWybieg extends JPanel implements UpdateGUI, ObserwujacyPracownikGUI_interface {

    private final DzienneZoo zoo;
    private final Sklep sklep;
    private final Wybieg_podstawowy wybieg;
    private final JLabel wybiegLabel;
    private final WybiegButton kupZwierzeButton;
    private final WybiegButton sprzedajZwierzeButton;

    private final WybiegButton sprzedajWybiegButton;

    private final WybiegButton nakarmButton;

    private final WybiegButton wyczyscButton;

    private final WybiegButton pokazZwierzetaButton;

    public PanelWybieg(DzienneZoo zoo, Sklep sklep, Wybieg_podstawowy wybieg)
    {
        sklep.dodajObsewatoraGUI(this);

        this.zoo = zoo;
        this.sklep = sklep;
        this.wybieg = wybieg;

        wybiegLabel = new JLabel("Wybieg", SwingConstants.CENTER);
        kupZwierzeButton = new WybiegButton("Kup zwierze");
        sprzedajZwierzeButton = new WybiegButton("Sprzedaj zwierze");
        sprzedajWybiegButton = new WybiegButton("Sprzedaj wybieg");
        nakarmButton = new WybiegButton("Nakarm zwierzeta");
        wyczyscButton = new WybiegButton("Wyczysc wybieg");
        pokazZwierzetaButton = new WybiegButton("Pokaz zwierzeta");

        kupZwierzeButton.addActionListener(new ReakcjaKupZwierze());
        sprzedajZwierzeButton.addActionListener(new ReakcjaSprzedajZwierze());
        sprzedajWybiegButton.addActionListener(new ReakcjaSprzedajWybieg());
        nakarmButton.addActionListener(new ReakcjaNakarm());
        wyczyscButton.addActionListener(new ReakcjaWyczysc());
        pokazZwierzetaButton.addActionListener(new ReakcjaPokazZwierzeta());

        wyczyscButton.setEnabled(false);
        nakarmButton.setEnabled(false);

        wybiegLabel.setText((zoo.getListaWybiegow().indexOf(wybieg)+1) +". Wybieg pusty");

        if(wybieg.getRodzaj_srodowiska()== rodzaj_srodowiska_enum.LADOWY)
        {
            this.setBackground(new Color(0x0E9A07));
            wybiegLabel.setForeground(Color.white);
        }
        if(wybieg.getRodzaj_srodowiska()== rodzaj_srodowiska_enum.WODNY)
        {
            this.setBackground(new Color(0x024177));
            wybiegLabel.setForeground(Color.white);
        }
        if(wybieg.getRodzaj_srodowiska()== rodzaj_srodowiska_enum.POWIETRZNY)
        {
            this.setBackground(new Color(0xCAF8F4));
            wybiegLabel.setForeground(Color.black);
        }

        this.setPreferredSize(new Dimension(200, 200));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(2,2,20,2);
        gbc.gridwidth = 2;
        gbc.ipady=30;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(wybiegLabel, gbc);

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridwidth = 1;
        gbc.ipady=0;

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(kupZwierzeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(sprzedajZwierzeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(pokazZwierzetaButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(sprzedajWybiegButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(nakarmButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(wyczyscButton, gbc);




    }

    @Override
    public void updateGUI() {
        if(wybieg.getLista_zwierzat().isEmpty()) {
            wybiegLabel.setText((zoo.getListaWybiegow().indexOf(wybieg)+1) +". Wybieg pusty");
        }
        else
        {
            wybiegLabel.setText((zoo.getListaWybiegow().indexOf(wybieg)+1) +". Wybieg dla " + wybieg.getLista_zwierzat().getFirst().getNazwa());
        }
        this.repaint();
        this.revalidate();

    }

    @Override
    public void reakcjaZaznaczenie() {
        if(((PanelDzien)this.getParent().getParent()).getPanelPracownicy().getZaznaczonyPracownik().getIloscakcji()>0) {
            this.kupZwierzeButton.setEnabled(false);
            this.sprzedajWybiegButton.setEnabled(false);
            this.sprzedajZwierzeButton.setEnabled(false);
            this.pokazZwierzetaButton.setEnabled(false);
            this.nakarmButton.setEnabled(true);
            this.wyczyscButton.setEnabled(true);
        }
        else
        {
            this.kupZwierzeButton.setEnabled(false);
            this.sprzedajWybiegButton.setEnabled(false);
            this.sprzedajZwierzeButton.setEnabled(false);
            this.pokazZwierzetaButton.setEnabled(false);
            this.nakarmButton.setEnabled(false);
            this.wyczyscButton.setEnabled(false);
        }
    }

    @Override
    public void reakcjaOdznaczenie() {
        this.kupZwierzeButton.setEnabled(true);
        this.sprzedajWybiegButton.setEnabled(true);
        this.sprzedajZwierzeButton.setEnabled(true);
        this.pokazZwierzetaButton.setEnabled(true);
        this.nakarmButton.setEnabled(false);
        this.wyczyscButton.setEnabled(false);
    }

    class ReakcjaKupZwierze implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
                new gui_oknaPopUp.OknoKupZwierze(sklep, wybieg);
        }
    }

    class ReakcjaSprzedajZwierze implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoSprzedajZwierze(sklep, wybieg);
        }
    }

    class ReakcjaSprzedajWybieg implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.sprzedaj_wybieg(wybieg, PanelWybieg.this);
            ((PanelDzienWybiegi)PanelWybieg.this.getParent()).usunWybieg(PanelWybieg.this);
        }
    }

    class ReakcjaNakarm implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new OknoNakarmZwierzeta(sklep, ((PanelDzien)PanelWybieg.this.getParent().getParent()).getPanelPracownicy().getZaznaczonyPracownik(), wybieg);
        }
    }

    class ReakcjaWyczysc implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.wyczyscWybieg(((PanelDzien)PanelWybieg.this.getParent().getParent()).getPanelPracownicy().getZaznaczonyPracownik(),wybieg);

        }
    }

    class ReakcjaPokazZwierzeta implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoPokazZwierzeta(wybieg);
        }
    }

}
