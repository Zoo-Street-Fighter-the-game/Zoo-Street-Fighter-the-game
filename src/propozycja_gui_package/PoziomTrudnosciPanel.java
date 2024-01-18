package propozycja_gui_package;

import enumy.poziom_trudnosci_enum;

import javax.swing.*;
import java.awt.*;


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
        latwyRadioButton.addActionListener(e -> handleTrudnoscButtonClick(poziom_trudnosci_enum.LATWY));
        sredniRadioButton.addActionListener(e -> handleTrudnoscButtonClick(poziom_trudnosci_enum.SREDNI));
        trudnyRadioButton.addActionListener(e -> handleTrudnoscButtonClick(poziom_trudnosci_enum.TRUDNY));

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

