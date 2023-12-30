package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoKupJedzenie extends JFrame implements ActionListener {
    private JTextField iloscField;
    private DzienneZoo zoo;
    private Sklep sklepik;


    public OknoKupJedzenie (DzienneZoo zoo, Sklep sklep)
    {
        this.zoo=zoo;
        this.sklepik=sklep;
        JLabel text = new JLabel("Kup Jedzenie");

        this.setTitle("Kup Jedzenie");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.add(text);
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Utworzenie pola tekstowego
         iloscField = new JTextField(10);
        panel.add(iloscField);

        // Utworzenie przycisku
        JButton kupButton = new JButton("Kup jedzenie " + " | Obecna cena za sztukę to: " + sklep.getCena_sztuka_jedzenie());
        panel.add(kupButton);
        kupButton.addActionListener(this);

        this.pack();
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int ilosc = Integer.parseInt(iloscField.getText());

            sklepik.kup_jedzenie(ilosc);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(OknoKupJedzenie.this,
                    "Proszę wprowadzić prawidłową liczbę",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

