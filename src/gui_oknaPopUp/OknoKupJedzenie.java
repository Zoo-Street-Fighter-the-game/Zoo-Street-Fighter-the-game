package gui_oknaPopUp;

import pakiet_sklep.Sklep;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoKupJedzenie extends JFrame implements ActionListener, DocumentListener {
    private final JTextField iloscField;
    private final Sklep sklepik;
    private final JLabel buttonLabel;

    public OknoKupJedzenie (Sklep sklep)
    {
        this.sklepik=sklep;
        JLabel icon = new JLabel(new ImageIcon("src/ikony/IkonaJedzenie.png"));

        this.setTitle("Kup Jedzenie");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.add(icon);
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        // Utworzenie pola tekstowego
        iloscField = new JTextField(10);
        iloscField.setPreferredSize(new Dimension(60,30));
        iloscField.setFont(new Font(null, Font.PLAIN, 20));
        panel.add(iloscField);
        iloscField.getDocument().addDocumentListener(this);

        //Utworzenie labelu kosztu
        buttonLabel = new JLabel();
        buttonLabel.setFont(new Font(null, Font.PLAIN, 24));
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
            buttonLabel.setText("" + Sklep.getCena_sztuka_jedzenie() * Integer.parseInt(iloscField.getText()));
            buttonLabel.setIcon(new ImageIcon("src/ikony/IkonaMonety.png"));
        } catch(Exception ex)
        {
            buttonLabel.setText("NaN");
        }
        this.pack();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            buttonLabel.setText("" + Sklep.getCena_sztuka_jedzenie() * Integer.parseInt(iloscField.getText()));
            buttonLabel.setIcon(new ImageIcon("src/ikony/IkonaMonety.png"));
        } catch(Exception ex)
        {
            buttonLabel.setText("");
        }
        this.pack();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
            try {
                buttonLabel.setText("" + Sklep.getCena_sztuka_jedzenie() * Integer.parseInt(iloscField.getText()));
                buttonLabel.setIcon(new ImageIcon("src/ikony/IkonaMonety.png"));
            } catch(Exception ex)
            {
                buttonLabel.setText("NaN");
            }
        this.pack();
    }
    public static void brakSrodkow()
    {
        JOptionPane.showMessageDialog(null,
                "Nie masz wystarczajaco duzo monet!",
                "Brak Srodkow",
                JOptionPane.ERROR_MESSAGE);
    }
}

