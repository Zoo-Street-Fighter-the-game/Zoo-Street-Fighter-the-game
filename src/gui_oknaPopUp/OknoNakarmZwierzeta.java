package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import Pracownik_package.Pracownik;
import Wybieg_package.Wybieg_podstawowy;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoNakarmZwierzeta extends JFrame implements ActionListener {
    private JSlider slider;
    private JButton przycisk;
    private Sklep sklep;
    private Pracownik pracownik;
    private Wybieg_podstawowy wybieg;
    public OknoNakarmZwierzeta(Sklep sklep, Pracownik pracownik, Wybieg_podstawowy wybieg) {
        this.sklep=sklep;
        this.pracownik=pracownik;
        this.wybieg=wybieg;

        this.setTitle("Okno Nakarm Zwierzeta");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 1));

        // Utworzenie suwaka
        slider = new JSlider(0, sklep.getZoo().getZmiennaZasoby().getJedzenie());
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);


        przycisk = new JButton("Nakarm");
        panel.add(przycisk);
        przycisk.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == przycisk) {;
            sklep.nakarmZwierzeta(pracownik, wybieg, slider.getValue());
            this.dispose();
        }
    }
}
