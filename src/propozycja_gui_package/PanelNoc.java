package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;

import javax.swing.*;
import java.awt.*;

public class PanelNoc extends JPanel {
    private NazwyWybiegowPanel panelWybiegi;
    private ListaZwierzatPanel panelNazwy;



    public PanelNoc(DzienneZoo instance) {
        panelWybiegi = new NazwyWybiegowPanel(instance);
        panelNazwy = new ListaZwierzatPanel(instance, panelWybiegi);


        this.setLayout(new BorderLayout());
        this.add(panelWybiegi, BorderLayout.NORTH);
        this.add(panelNazwy, BorderLayout.CENTER);


    }
}
