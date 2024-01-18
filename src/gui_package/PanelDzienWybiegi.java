package gui_package;


import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;

public class PanelDzienWybiegi extends JPanel implements UpdateGUI {

    private PanelWybiegBezdomni wybiegBezdomni;

    public PanelDzienWybiegi(Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);
        sklep.setPanelDzienWybiegi(this);
        this.setBackground(new Color(0xffffff));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        wybiegBezdomni = new PanelWybiegBezdomni(sklep.getZoo(), sklep, sklep.getZoo().getWybiegDlaBezdomnych());
        this.add(wybiegBezdomni);

    }
    public void Wyczysc(Sklep sklep){
        this.removeAll();
        wybiegBezdomni = new PanelWybiegBezdomni(sklep.getZoo(), sklep, sklep.getZoo().getWybiegDlaBezdomnych());
        this.add(wybiegBezdomni);
        this.updateGUI();
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

    public PanelWybiegBezdomni getWybiegBezdomni() {
        return wybiegBezdomni;
    }
}
