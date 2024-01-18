package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;


import javax.swing.*;
import java.awt.*;

public class PanelNoc extends JPanel {


    public PanelNoc(DzienneZoo instance) {
        NazwyWybiegowPanel panelWybiegi = new NazwyWybiegowPanel(instance);
        ListaZwierzatPanel panelNazwy = new ListaZwierzatPanel(instance, panelWybiegi);

        this.setLayout(new BorderLayout());
        this.add(panelWybiegi, BorderLayout.NORTH);
        this.add(panelNazwy, BorderLayout.CENTER);


    }
}
