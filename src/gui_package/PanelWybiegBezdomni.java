package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_bezdomni;
import Wybieg_package.Wybieg_podstawowy;
import gui_oknaPopUp.OknoNakarmZwierzeta;
import interfejsy.ObserwujacyPracownikGUI_interface;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelWybiegBezdomni extends JPanel implements UpdateGUI, ObserwujacyPracownikGUI_interface {

    private DzienneZoo zoo;
    private Sklep sklep;
    private Wybieg_bezdomni wybieg;
    private JLabel wybiegLabel;
    private WybiegButton sprzedajZwierzeButton;

    private WybiegButton nakarmButton;

    private WybiegButton pokazZwierzetaButton;

    public PanelWybiegBezdomni(DzienneZoo zoo, Sklep sklep, Wybieg_bezdomni wybieg)
    {
        sklep.dodajObsewatoraGUI(this);

        this.zoo = zoo;
        this.sklep = sklep;
        this.wybieg = wybieg;

        wybiegLabel = new JLabel("Wybieg", SwingConstants.CENTER);
        sprzedajZwierzeButton = new WybiegButton("Sprzedaj zwierze");
        nakarmButton = new WybiegButton("Nakarm zwierzeta");
        pokazZwierzetaButton = new WybiegButton("Pokaz zwierzeta");

        sprzedajZwierzeButton.addActionListener(new ReakcjaSprzedajZwierze());
        nakarmButton.addActionListener(new ReakcjaNakarm());
        pokazZwierzetaButton.addActionListener(new ReakcjaPokazZwierzeta());

        nakarmButton.setEnabled(false);

        wybiegLabel.setText("Wybieg dla bezdomnych");
        wybiegLabel.setForeground(Color.white);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(new Color(0xF19D9D));
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
        this.add(sprzedajZwierzeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(pokazZwierzetaButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(nakarmButton, gbc);
    }

    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }

    @Override
    public void reakcjaZaznaczenie() {
        if(((PanelDzien)this.getParent().getParent()).getPanelPracownicy().getZaznaczonyPracownik().getIloscakcji()>0) {
            this.sprzedajZwierzeButton.setEnabled(false);
            this.pokazZwierzetaButton.setEnabled(false);
            this.nakarmButton.setEnabled(true);
        }
        else
        {
            this.sprzedajZwierzeButton.setEnabled(false);
            this.pokazZwierzetaButton.setEnabled(false);
            this.nakarmButton.setEnabled(false);
        }
    }

    @Override
    public void reakcjaOdznaczenie() {
        this.sprzedajZwierzeButton.setEnabled(true);
        this.pokazZwierzetaButton.setEnabled(true);
        this.nakarmButton.setEnabled(false);
    }

    class ReakcjaSprzedajZwierze implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoSprzedajZwierze(sklep, wybieg);
        }
    }

    class ReakcjaNakarm implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new OknoNakarmZwierzeta(sklep, ((PanelDzien) PanelWybiegBezdomni.this.getParent().getParent()).getPanelPracownicy().getZaznaczonyPracownik(), wybieg);
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
