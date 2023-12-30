package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoSprzedajPracownika extends JFrame implements ActionListener {
    private JSlider slider;
    private JButton przycisk;
    private DzienneZoo zoo;
    private Sklep sklep;

    public OknoSprzedajPracownika(DzienneZoo zoo, Sklep sklep) {
        this.zoo=zoo;
        this.sklep=sklep;
        this.setTitle("Okno Slider");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 1));
        for (int i=0; i<zoo.getListaPracownikow().size();i++)
        {
            panel.add(new JLabel("  "+(i+1) +")  "+zoo.getListaPracownikow().get(i).toString()+"  "));
        }

        // Utworzenie suwaka
        slider = new JSlider(1, zoo.getListaPracownikow().size()); // Zakres od 0 do 100
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);


        przycisk = new JButton("Pokaż wartość");
        panel.add(przycisk);
        przycisk.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == przycisk) {
            int wartoscSlidera = slider.getValue()-1;
            sklep.sprzedaj_pracownika(wartoscSlidera);
            this.dispose();
        }
    }
}
