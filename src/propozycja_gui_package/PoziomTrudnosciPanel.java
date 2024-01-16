package propozycja_gui_package;

import enumy.poziom_trudnosci_enum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoziomTrudnosciPanel extends JPanel {

    private poziom_trudnosci_enum wybranyPoziomTrudnosci;


    public PoziomTrudnosciPanel() {
        dodajPrzyciskiTrudnosci();
    }

    private void dodajPrzyciskiTrudnosci() {
        ButtonGroup trudnoscGroup = new ButtonGroup();

        JRadioButton latwyRadioButton = new JRadioButton("Łatwy");
        JRadioButton sredniRadioButton = new JRadioButton("Średni");
        JRadioButton trudnyRadioButton = new JRadioButton("Trudny");

        // Dodajemy przyciski do grupy
        trudnoscGroup.add(latwyRadioButton);
        trudnoscGroup.add(sredniRadioButton);
        trudnoscGroup.add(trudnyRadioButton);

        // Dodajemy słuchacza dla przycisków
        latwyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTrudnoscButtonClick(poziom_trudnosci_enum.LATWY);
            }
        });
        sredniRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTrudnoscButtonClick(poziom_trudnosci_enum.SREDNI);
            }
        });
        trudnyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTrudnoscButtonClick(poziom_trudnosci_enum.TRUDNY);
            }
        });

        // Ustawiamy layout dla panelu z przyciskami
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(latwyRadioButton);
        this.add(sredniRadioButton);
        this.add(trudnyRadioButton);
    }

    private void handleTrudnoscButtonClick(poziom_trudnosci_enum poziomTrudnosci) {
        wybranyPoziomTrudnosci = poziomTrudnosci;
    }


    public poziom_trudnosci_enum getWybranyPoziomTrudnosci() {
        return wybranyPoziomTrudnosci;
    }

}

