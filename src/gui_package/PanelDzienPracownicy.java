package gui_package;

import DzienneZooPakiet.DzienneZoo;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class PanelDzienPracownicy extends JPanel implements UpdateGUI {

    public PanelDzienPracownicy(DzienneZoo zoo, Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);

        this.setBackground(Color.red);
        this.setPreferredSize(new Dimension(250, 0));

    }

    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }
}
