package gui_oknaPopUp;

import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;
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
    private ArrayList<JRadioButton> listaRadioButton;
    private Zwierze wybrany;
    private JButton sprzedajZwierzeButton;
    ButtonGroup group;
    public OknoSprzedajZwierze(Sklep sklep, Wybieg_podstawowy wybieg)
    {
        this.sklep = sklep;
        this.wybieg = wybieg;

        panelMain = new JPanel();
        panelRadio = new JPanel();
        text = new JLabel("Wybierz zwierze: ");
        listaRadioButton = new ArrayList<>();
        sprzedajZwierzeButton = new JButton("Sprzedaj zwierze");
        sprzedajZwierzeButton.setEnabled(false);
        sprzedajZwierzeButton.addActionListener(new ReakcjaSprzedajZwierzeButton());

        this.setTitle("Sprzedaj Zwierze");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        sprzedajZwierzeButton.setFocusable(false);
        group = new ButtonGroup();
        ustawRadioButton();

        this.add(panelMain);
        panelMain.add(text);
        panelMain.add(panelRadio);
        panelMain.add(sprzedajZwierzeButton);

        panelRadio.setLayout(new FlowLayout());
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));

        this.setBounds(0,0,300,200);
        this.setVisible(true);
    }

    public void ustawRadioButton()
    {
        for(Zwierze zwierze : wybieg.getLista_zwierzat())
        {
            listaRadioButton.add(new JRadioButton(zwierze.getNazwa()));
            panelRadio.add(listaRadioButton.getLast());
            listaRadioButton.getLast().addActionListener(new ReakcjaRadioButton());
            group.add(listaRadioButton.getLast());
        }
    }

    class ReakcjaRadioButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           wybrany=(wybieg.getLista_zwierzat().get(listaRadioButton.indexOf(((JRadioButton)e.getSource()))));
           sprzedajZwierzeButton.setEnabled(true);
        }
    }

    class ReakcjaSprzedajZwierzeButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.sprzedaj_zwierze(wybieg, wybrany);
            OknoSprzedajZwierze.this.dispose();
        }
    }

    public void BrakSrodkow()
    {
        JOptionPane.showMessageDialog(this,
                "Nie masz wystarczajaco duzo monet!",
                "Brak Srodkow",
                JOptionPane.ERROR_MESSAGE);
    }

}
