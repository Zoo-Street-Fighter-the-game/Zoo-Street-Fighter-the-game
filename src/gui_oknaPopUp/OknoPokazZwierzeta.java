package gui_oknaPopUp;

import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_abstract;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class OknoPokazZwierzeta extends JFrame {

    private final Wybieg_abstract wybieg;

    private final ArrayList<JButton> listaZwierzeButton;
    public OknoPokazZwierzeta(Wybieg_abstract wybieg)
    {
        this.wybieg = wybieg;
        listaZwierzeButton = new ArrayList<>();
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));

        this.setTitle("Kup Zwierze");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.stworzPrzyciski();
        this.pack();
        this.setVisible(true);

    }

    public void stworzPrzyciski()
    {
        Collections.sort(wybieg.getLista_zwierzat());

        for(Zwierze zwierze : wybieg.getLista_zwierzat())
        {
            listaZwierzeButton.add(new JButton(zwierze.getNazwa()+" "+zwierze.getImie()));
            listaZwierzeButton.getLast().setFocusable(false);
            OknoPokazZwierzeta.this.add(listaZwierzeButton.getLast());
            listaZwierzeButton.getLast().addActionListener(new ReakcjaPokazZwierze());
        }
    }

    class ReakcjaPokazZwierze implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Zwierze zwierze = wybieg.getLista_zwierzat().get(listaZwierzeButton.indexOf((JButton)e.getSource()));
            JOptionPane.showMessageDialog(OknoPokazZwierzeta.this,
                    zwierze.toString(),
                    "Informacje o zwierzeciu",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
