package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;
import enumy.poziom_trudnosci_enum;
import gui_oknaPopUp.RadioButton;
import gui_package.ListaWybiegow2;
import noc_walka.Atak;
import noc_walka.Leczenie;
import pakiet_arena.Poziom_trudnosci;
import pakiet_arena.QLearningAgent;
import pakiet_arena.Walka;
import pakiet_arena.nr_wybiegu_Zwierze;
import propozycja_gui_package.NazwyWybiegowPanel;
import propozycja_gui_package.PopUpPanelZwierzeInfo;
import propozycja_gui_package.PoziomTrudnosciPanel;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static pakiet_arena.NocneZoo2.Q_TABLE_FILE;


public class ListaZwierzat2 extends JPanel {
    private ListaWybiegow2 nazwyWybiegowPanel;
    private static int wybiegzmienna;
    public ListaZwierzat2(DzienneZoo zoo, ListaWybiegow2 nazwyWybiegowPanel) {
        this.nazwyWybiegowPanel = nazwyWybiegowPanel;
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = nazwyWybiegowPanel.getTabbedPane();
        if (tabbedPane == null) {
            throw new IllegalStateException("JTabbedPane is null");
        }

        // Usuń istniejące zakładki przed dodaniem nowych
        tabbedPane.removeAll();

        JPanel centralPanel = new JPanel(new GridLayout(1, 1));

        for (int i = 0; i < zoo.getListaWybiegow().size(); i++) {
            Wybieg_podstawowy wybieg = zoo.getListaWybiegow().get(i);
            JPanel wybiegPanel = new JPanel(new BorderLayout());
            tabbedPane.addTab("Wybieg " + (i + 1), wybiegPanel);

            dodajZwierzetaDoPanelu(wybiegPanel, wybieg, i);

            centralPanel.add(tabbedPane);
        }
        this.add(centralPanel, BorderLayout.CENTER);

    }

    private void dodajZwierzetaDoPanelu(JPanel wybiegPanel, Wybieg_podstawowy wybieg, int numerWybiegu) {
        ArrayList<Zwierze> listaZwierzat = new ArrayList<>(wybieg.getLista_zwierzat());
        JPanel RadioPanel2 = new JPanel();
          for (Zwierze zwierze : listaZwierzat) {
              RadioButton xd = new RadioButton(zwierze.getNazwa(), zwierze);
              RadioPanel2.add(xd);
          }
        wybiegPanel.add(RadioPanel2);
    }
}