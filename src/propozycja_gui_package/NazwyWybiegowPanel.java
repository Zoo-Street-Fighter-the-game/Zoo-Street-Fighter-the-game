package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;

import javax.swing.*;
import java.awt.*;

public class NazwyWybiegowPanel extends JPanel {
    private final JTabbedPane tabbedPane = new JTabbedPane();

    public NazwyWybiegowPanel(DzienneZoo zoo) {
        setLayout(new GridLayout(1, 1));

        java.util.List<Wybieg_podstawowy> listaWybiegow = zoo.getListaWybiegow();
        if (listaWybiegow != null) {
            for (int i = 0; i < listaWybiegow.size(); i++) {

                JPanel wybiegPanel = new JPanel();
                tabbedPane.addTab("Wybieg " + (i + 1), wybiegPanel);
            }
        }

        this.add(tabbedPane);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
