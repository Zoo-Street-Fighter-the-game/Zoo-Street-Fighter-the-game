package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDzienWybiegi extends JPanel implements UpdateGUI {
    public PanelDzienWybiegi(DzienneZoo zoo, Sklep sklep, Wybieg_podstawowy wybieg)
    {
        sklep.dodajObsewatoraGUI(this);

        this.setBackground(Color.lightGray);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.add(new PanelWybieg(zoo, sklep, wybieg));

    }

    public void UsunWybieg(PanelWybieg W)
    {
        this.remove(W);
        this.UpdateGUI();
    }

    @Override
    public void UpdateGUI() {
        revalidate();
        repaint();
    }
}
