package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import Pracownik_package.Pracownik;
import Wybieg_package.Wybieg_abstract;
import Wybieg_package.Wybieg_podstawowy;
import pakiet_sklep.Sklep;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoNakarmZwierzeta extends JFrame implements ActionListener, ChangeListener {
    private JSlider slider;
    private JButton przycisk;
    private Sklep sklep;
    private Pracownik pracownik;
    private Wybieg_abstract wybieg;
    private JLabel buttonLabel;
    public OknoNakarmZwierzeta(Sklep sklep, Pracownik pracownik, Wybieg_abstract wybieg) {
        this.sklep=sklep;
        this.pracownik=pracownik;
        this.wybieg=wybieg;

        this.setTitle("Okno Nakarm Zwierzeta");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        slider.addChangeListener(this);


        przycisk = new JButton("Nakarm");
        buttonLabel = new JLabel(String.valueOf(slider.getValue()));
        przycisk.add(buttonLabel);
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

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==slider)
        {
            buttonLabel.setText(String.valueOf(slider.getValue()));
        }
    }
}
