package gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class PanelDzienWybiegi extends JPanel implements UpdateGUI {
    private DzienneZoo zoo;
    private Sklep sklep;
    public PanelDzienWybiegi(DzienneZoo zoo, Sklep sklep, Wybieg_podstawowy wybieg)
    {
        sklep.dodajObsewatoraGUI(this);
        sklep.setPanelDzienWybiegi(this);
        this.zoo=zoo;
        this.sklep=sklep;

        this.setBackground(Color.lightGray);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.add(new PanelWybieg(zoo, sklep, wybieg));


    }

    public void usunWybieg(PanelWybieg W)
    {
        this.remove(W);
        this.updateGUI();
    }
    public void dodajWybieg(PanelWybieg W)
    {
        this.add(W);

    }


    @Override
    public void updateGUI() {


        revalidate();
        repaint();
    }
}
