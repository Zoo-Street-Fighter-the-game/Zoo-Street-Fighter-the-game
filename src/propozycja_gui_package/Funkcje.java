package propozycja_gui_package;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Klasy_Zwierzat.Zwierze;

import static propozycja_gui_package.WalkaPanel.*;

public class Funkcje implements UpdateGUI{
    private final ArrayList<UpdateGUI> listaGUI;

    public Funkcje() {
        listaGUI = new ArrayList<>();
    }

    public void zmienZwierze(Zwierze twoje_zwierze, Zwierze finalPrzeciwnik1, JPanel panelZwierzat) {
        JPanel playerAttackLabel = createAnimalPanel2(twoje_zwierze);
        JPanel opponentAttackLabel = createOpponentPanel2(finalPrzeciwnik1);
        panelZwierzat.add(playerAttackLabel, BorderLayout.WEST);
        panelZwierzat.add(opponentAttackLabel, BorderLayout.EAST);
        UpdateGUI();
    }
    public void dodajObsewatoraGUI(UpdateGUI G)
    {
        listaGUI.add(G);
    }
    public void UpdateGUI()
    {
        for(UpdateGUI o : listaGUI)
        {
            o.UpdateGUI();
        }
    }


}
