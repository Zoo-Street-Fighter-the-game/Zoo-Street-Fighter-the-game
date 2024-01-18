package gui_package;

import javax.swing.*;
import java.awt.*;


import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;

public class ListaWybiegow2 extends JPanel {
    private final JTabbedPane tabbedPane = new JTabbedPane();

        public ListaWybiegow2(DzienneZoo zoo) {
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


