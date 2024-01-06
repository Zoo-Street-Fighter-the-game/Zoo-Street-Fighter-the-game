package propozycja_gui_package;
import DzienneZooPakiet.DzienneZoo;
import Wybieg_package.Wybieg_podstawowy;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import enumy.zwierzeta_enum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animacja extends JFrame {
    private JLabel koniecDniaLabel;

    public Animacja() {
        // Ustawienie rozmiaru na pełny ekran
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        koniecDniaLabel = new JLabel("KONIEC DNIA");
        koniecDniaLabel.setFont(new Font("Arial", Font.BOLD, 72));
        koniecDniaLabel.setForeground(new Color(0, 0, 0, 0));
        koniecDniaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        koniecDniaLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(koniecDniaLabel, BorderLayout.CENTER);

        Timer timer = new Timer(30, new ActionListener() {
            private int alpha = 0;
            private long startTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

                if (elapsedTime < 3000) {
                    alpha = (int) (255 * (elapsedTime / 3000.0));
                    koniecDniaLabel.setForeground(new Color(0, 0, 0, alpha));
                } else {
                    ((Timer) e.getSource()).stop();
                    dispose();
                    DzienneZoo zoo = DzienneZoo.getInstance();
                    zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.LADOWY, wielkosc_wybiegu_enum.SREDNI));
                    zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.WODNY, wielkosc_wybiegu_enum.SREDNI));
                    zoo.getListaWybiegow().getFirst().dodaj_zwierze(zwierzeta_enum.LOS.stworzZwierze("bob"));
                    zoo.getListaWybiegow().getLast().dodaj_zwierze(zwierzeta_enum.PINGWIN.stworzZwierze("bob"));
                    new MainFrame(zoo);
                }
            }
        });

        timer.start();
        setVisible(true);
    }
}
