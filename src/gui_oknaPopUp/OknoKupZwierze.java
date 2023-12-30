package gui_oknaPopUp;

import Wybieg_package.Wybieg_podstawowy;
import enumy.zwierzeta_enum;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoKupZwierze extends JFrame {

    private Sklep sklep;
    private Wybieg_podstawowy wybieg;
    private JPanel panelMain;
    private JPanel panelRadio;
    private JLabel text;
    private ZwierzeRadioButton pingwin;
    private ZwierzeRadioButton niedzwiedz;
    private ZwierzeRadioButton los;
    private ButtonGroup group;
    private ZwierzeRadioButton wybrany;

    private JTextField imieTextField;

    JButton kupZwierzeButton;
    public OknoKupZwierze (Sklep sklep, Wybieg_podstawowy wybieg)
    {
        this.sklep = sklep;
        this.wybieg = wybieg;
        sklep.setOknoKupZwierze(this);

        panelMain = new JPanel();
        panelRadio = new JPanel();
        text = new JLabel("Wybierz zwierze: ");

        imieTextField = new JTextField("Fafik");
        imieTextField.setPreferredSize(new Dimension(200, 30));

        kupZwierzeButton = new JButton("Kup zwierze");
        kupZwierzeButton.setEnabled(false);
        kupZwierzeButton.addActionListener(new ReakcjaKupZwierzeButton());

        pingwin = new ZwierzeRadioButton("pingwin", zwierzeta_enum.PINGWIN);
        pingwin.addActionListener(new ReakcjaZwierzeRadioButton());

        niedzwiedz = new ZwierzeRadioButton("niedzwiedz", zwierzeta_enum.NIEDZWIEDZ);
        niedzwiedz.addActionListener(new ReakcjaZwierzeRadioButton());

        los = new ZwierzeRadioButton("los", zwierzeta_enum.LOS);
        los.addActionListener(new ReakcjaZwierzeRadioButton());

        group = new ButtonGroup();

        group.add(pingwin);
        group.add(niedzwiedz);
        group.add(los);


        this.setTitle("Kup Zwierze");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        kupZwierzeButton.setFocusable(false);

        panelRadio.add(pingwin);
        panelRadio.add(niedzwiedz);
        panelRadio.add(los);

        panelRadio.setLayout(new FlowLayout());
        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.add(panelMain);

        gbc.insets=new Insets(10,10,10,10);

        gbc.gridwidth=3;
        gbc.gridx=0;
        gbc.gridy=0;

        panelMain.add(text, gbc);

        gbc.gridwidth=3;
        gbc.gridx=0;
        gbc.gridy=1;

        panelMain.add(panelRadio, gbc);

        gbc.gridwidth=3;
        gbc.gridx=0;
        gbc.gridy=2;

        panelMain.add(imieTextField, gbc);

        gbc.gridwidth=1;
        gbc.gridx=3;
        gbc.gridy=3;

        panelMain.add(kupZwierzeButton, gbc);



        this.pack();
        this.setVisible(true);
    }

    class ReakcjaZwierzeRadioButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            kupZwierzeButton.setEnabled(true);
            if(!wybieg.czy_zwierze_spelnia_wymogi_dodania_do_wybiegu(((ZwierzeRadioButton)e.getSource()).getTyp().stworzZwierze("")))
            {
                kupZwierzeButton.setEnabled(false);
            }
            wybrany=(ZwierzeRadioButton)e.getSource();
        }
    }

    class ReakcjaKupZwierzeButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.kup_zwierze(wybrany.getTyp(), wybieg, imieTextField.getText());
            OknoKupZwierze.this.dispose();
        }
    }

    public void brakSrodkow()
    {
        JOptionPane.showMessageDialog(this,
                "Nie masz wystarczajaco duzo monet!",
                "Brak Srodkow",
                JOptionPane.ERROR_MESSAGE);
    }

}
