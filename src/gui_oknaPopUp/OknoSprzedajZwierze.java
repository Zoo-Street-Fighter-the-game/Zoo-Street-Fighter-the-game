package gui_oknaPopUp;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;
import enumy.zwierzeta_enum;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OknoSprzedajZwierze extends JFrame {

    private Sklep sklep;
    private Wybieg_podstawowy wybieg;
    private JPanel panelMain;
    private JPanel panelRadio;
    private JLabel text;
    //private ArrayList<ZwierzeRadioButton> listaZwierzeRadioButton = new ArrayList<>();
    private ZwierzeRadioButton wybrany;
    JButton sprzedajZwierzeButton;
    public OknoSprzedajZwierze(Sklep sklep, Wybieg_podstawowy wybieg)
    {
        this.sklep = sklep;
        this.wybieg = wybieg;

        panelMain = new JPanel();
        panelRadio = new JPanel();
        text = new JLabel("Wybierz zwierze: ");
        sprzedajZwierzeButton = new JButton("Sprzedaj zwierze");
        sprzedajZwierzeButton.setEnabled(false);
        //sprzedajZwierzeButton.addActionListener(new ReakcjaKupZwierzeButton());

        this.setTitle("Sprzedaj Zwierze");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        sprzedajZwierzeButton.setFocusable(false);
        UstawRadioButton();

        this.add(panelMain);
        panelMain.add(text);
        panelMain.add(panelRadio);
        panelMain.add(sprzedajZwierzeButton);

        panelRadio.setLayout(new FlowLayout());
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));

        this.setBounds(0,0,300,200);
        this.setVisible(true);
    }

    public void UstawRadioButton()
    {
        for(Zwierze zwierze : wybieg.getLista_zwierzat())
        {
            panelRadio.add(new JRadioButton(zwierze.getNazwa()));
        }
    }

    class ReakcjaZwierzeRadioButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            wybrany=(ZwierzeRadioButton)e.getSource();
        }
    }

    /*class ReakcjaSprzedajZwierzeButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.sprzedaj_zwierze();
        }
    }*/

    public void BrakSrodkow()
    {
        JOptionPane.showMessageDialog(this,
                "Nie masz wystarczajaco duzo monet!",
                "Brak Srodkow",
                JOptionPane.ERROR_MESSAGE);
    }

}
