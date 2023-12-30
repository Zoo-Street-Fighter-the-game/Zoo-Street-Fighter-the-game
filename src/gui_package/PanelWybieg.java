package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelWybieg extends JPanel implements UpdateGUI {

    private DzienneZoo zoo;
    private Sklep sklep;
    private Wybieg_podstawowy wybieg;
    private JLabel wybiegLabel;

    private WybiegButton kupZwierzeButton;

    private WybiegButton sprzedajZwierzeButton;

    private WybiegButton sprzedajWybiegButton;

    private WybiegButton nakarmButton;

    private WybiegButton wyczyscButton;

    private WybiegButton pokazZwierzetaButton;

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

        wybiegLabel.setText("Wybieg");
        wybiegLabel.setBackground(Color.red);
        wybiegLabel.setOpaque(true);

        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(200, 200));

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
        this.add(sprzedajWybiegButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(nakarmButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(wyczyscButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(pokazZwierzetaButton, gbc);

    }

    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
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
            sklep.sprzedaj_wybieg(wybieg);
            ((PanelDzienWybiegi)PanelWybieg.this.getParent()).UsunWybieg(PanelWybieg.this);
        }
    }

    class ReakcjaNakarm implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ReakcjaWyczysc implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Wyczyszczono zwierzeta");
        }
    }

    class ReakcjaPokazZwierzeta implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pokazano zwierzeta");
        }
    }

}
