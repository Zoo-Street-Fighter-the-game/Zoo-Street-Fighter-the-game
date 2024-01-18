package gui_oknaPopUp;

import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OknoPrzeniesZwierze extends JFrame {

    private final Sklep sklep;
    private final ArrayList<JButton> listaWybiegButton;
    public OknoPrzeniesZwierze(Sklep sklep)
    {
        this.sklep = sklep;
        listaWybiegButton = new ArrayList<>();
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));

        this.setTitle("Kup Zwierze");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.stworzPrzyciski();
        this.pack();
        this.setVisible(true);

    }

    public void stworzPrzyciski()
    {
        for(Wybieg_podstawowy wybiegPodstawowy : sklep.getZoo().getListaWybiegow())
        {
            if(wybiegPodstawowy.czy_zwierze_spelnia_wymogi_dodania_do_wybiegu((sklep.getZoo().getWybiegDlaBezdomnych().getLista_zwierzat().getFirst())))
            {
                String text;
                if(wybiegPodstawowy.getRodzaj_zwierzecia_w_wybiegu()!=null)
                {
                    text = "Wybieg "+wybiegPodstawowy.getRodzaj_srodowiska() + " dla: " + wybiegPodstawowy.getRodzaj_zwierzecia_w_wybiegu();
                }
                else
                {
                    text = "Pusty wybieg "+wybiegPodstawowy.getRodzaj_srodowiska();
                }
                listaWybiegButton.add(new JButton(text));
                listaWybiegButton.getLast().setFocusable(false);
                OknoPrzeniesZwierze.this.add(listaWybiegButton.getLast());
                listaWybiegButton.getLast().addActionListener(new ReakcjaPokazZwierze());
            }

        }
    }

    class ReakcjaPokazZwierze implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Wybieg_podstawowy wybieg = sklep.getZoo().getListaWybiegow().get(listaWybiegButton.indexOf((JButton)e.getSource()));
            Zwierze zwierze = sklep.getZoo().getWybiegDlaBezdomnych().getLista_zwierzat().getFirst();
            sklep.przeniesZwierzeBezdomni(wybieg, zwierze);
            OknoPrzeniesZwierze.this.dispose();
        }
    }
}
