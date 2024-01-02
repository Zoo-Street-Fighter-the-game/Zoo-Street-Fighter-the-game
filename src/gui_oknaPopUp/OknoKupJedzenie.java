package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import pakiet_sklep.Sklep;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoKupJedzenie extends JFrame implements ActionListener, DocumentListener {
    private JTextField iloscField;
    private Sklep sklepik;
    private JLabel buttonLabel;

    public OknoKupJedzenie (Sklep sklep)
    {
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
        iloscField.getDocument().addDocumentListener(this);

        //Utworzenie labelu kosztu
        buttonLabel = new JLabel();
        panel.add(buttonLabel);

        // Utworzenie przycisku
        JButton kupButton = new JButton("Kup jedzenie " + " | Obecna cena za sztukę to: " + Sklep.getCena_sztuka_jedzenie());
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

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            buttonLabel.setText("Koszt: " + Sklep.getCena_sztuka_jedzenie() * Integer.parseInt(iloscField.getText()));
        } catch(Exception ex)
        {
            buttonLabel.setText("Koszt: NaN");
        }
        this.pack();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            buttonLabel.setText("Koszt: " + Sklep.getCena_sztuka_jedzenie() * Integer.parseInt(iloscField.getText()));
        } catch(Exception ex)
        {
            buttonLabel.setText("");
        }
        this.pack();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
            try {
                buttonLabel.setText("Koszt: " + Sklep.getCena_sztuka_jedzenie() * Integer.parseInt(iloscField.getText()));
            } catch(Exception ex)
            {
                buttonLabel.setText("Koszt: NaN");
            }
        this.pack();
    }
}

