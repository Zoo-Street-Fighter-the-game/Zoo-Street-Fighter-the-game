package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoSprzedajPracownika extends JFrame implements ActionListener, ChangeListener {
    private JSlider slider;
    private JButton przycisk;
    private Sklep sklep;
    private JLabel buttonLabel;

    public OknoSprzedajPracownika(Sklep sklep) {
        this.sklep=sklep;
        this.setTitle("Okno Slider");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 1));
        for (int i=0; i<sklep.getZoo().getListaPracownikow().size();i++)
        {
            panel.add(new JLabel("  "+(i+1) +")  "+sklep.getZoo().getListaPracownikow().get(i).toString()+"  "));
        }

        // Utworzenie suwaka
        slider = new JSlider(1, sklep.getZoo().getListaPracownikow().size()); // Zakres od 0 do 100
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        panel.add(slider);


        przycisk = new JButton("Pokaż wartość");
        buttonLabel = new JLabel(String.valueOf(sklep.getZoo().getListaPracownikow().get(slider.getValue()-1).getJakoscUslug()*sklep.getCenaPracownika()));
        buttonLabel.setIcon(new ImageIcon("src/ikony/IkonaMonety.png"));
        buttonLabel.setFont(new Font(null, Font.PLAIN, 24));
        buttonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(buttonLabel);
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

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==slider)
        {
            buttonLabel.setText(String.valueOf(String.valueOf(sklep.getZoo().getListaPracownikow().get(slider.getValue()-1).getJakoscUslug()*sklep.getCenaPracownika())));
        }
    }
}
