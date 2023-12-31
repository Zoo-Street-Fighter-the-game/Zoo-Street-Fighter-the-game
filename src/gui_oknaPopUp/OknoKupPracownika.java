package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoKupPracownika extends JFrame implements ActionListener {
    private JTextField imieField;
    private JTextField nazwiskoField;
    private JSlider jakoscSlider;
    private JButton kupButton;
    private Sklep sklepik;

    public OknoKupPracownika(Sklep sklep) {
        this.sklepik = sklep;

        JLabel text = new JLabel("Kup Pracownika");
        this.setTitle("Kup Pracownika");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.add(text);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(0,1));

        // Utworzenie pól tekstowych
        imieField = new JTextField(10);
        panel.add(new JLabel("  Imie:"));
        panel.add(imieField);

        nazwiskoField = new JTextField(10);
        panel.add(new JLabel("  Nazwisko:"));
        panel.add(nazwiskoField);

        // Utworzenie suwaka
        jakoscSlider = new JSlider(1, 10);
        jakoscSlider.setMajorTickSpacing(1);
        jakoscSlider.setPaintTicks(true);
        jakoscSlider.setPaintLabels(true);

        panel.add(new JLabel("  Jakosc uslug"));
        panel.add(jakoscSlider);

        // Utworzenie przycisku
        kupButton = new JButton("Kup Pracownika");
        panel.add(kupButton);
        kupButton.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String imie = imieField.getText();
        String nazwisko = nazwiskoField.getText();
        int jakosc = jakoscSlider.getValue();

        if(e.getSource()==kupButton)
        {
            sklepik.kup_pracownika(imie,nazwisko,jakosc);
        }
    }
}