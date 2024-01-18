package propozycja_gui_package;
import DzienneZooPakiet.DzienneZoo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animacja extends JFrame {
    private final JLabel koniecDniaLabel;

    public Animacja(DzienneZoo zoo) {
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
            private final long startTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;

                if (elapsedTime < 3000) {
                    int alpha = (int) (255 * (elapsedTime / 3000.0));
                    koniecDniaLabel.setForeground(new Color(0, 0, 0, alpha));
                } else {
                    ((Timer) e.getSource()).stop();
                    dispose();

                    new MainFrame(zoo);
                }
            }
        });

        timer.start();
        setVisible(true);
    }
}
