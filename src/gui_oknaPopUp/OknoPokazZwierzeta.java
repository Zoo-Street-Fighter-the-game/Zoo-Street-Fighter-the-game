package gui_oknaPopUp;

import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class OknoPokazZwierzeta extends JFrame {

    private Wybieg_podstawowy wybieg;

    private ArrayList<JButton> listaZwierzeButton;
    public OknoPokazZwierzeta(Wybieg_podstawowy wybieg)
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
                    "Imie to:" + zwierze.getImie() +"\n"+
                            "Typ to:" + zwierze.getNazwa() +"\n"+
                            "Zycie to: " + zwierze.getZycie() +"\n"+
                            "Sila to: " + zwierze.getSila() +"\n"+
                            "Wielkosc to: " + zwierze.getWielkosc() +"\n"+
                            "Najedzony w " + zwierze.getWskaznik_glodu() +" procentach\n"+
                            "Przezyl " + zwierze.getPrzezyte_dni() +" dni\n"+
                            "Cena to: " + zwierze.getCena() +"\n"+
                            "Rodzaj to: " + zwierze.getRodzaj() +" \n",
                    "Informacje o zwierzeciu",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
