package gui_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DzienneZooPakiet.DzienneZoo;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;


public class PanelDzienSklep extends JPanel implements UpdateGUI {
    private JButton kupJedzenie;

    private JButton kupWybieg;
    private JButton kupPracownika;
    private JButton sprzedajJedzenie ;

    private JButton sprzedajPracownika;
    private DzienneZoo zoo;
    private Sklep sklep;

    public PanelDzienSklep(DzienneZoo zoo, Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);

        this.zoo = zoo;
        this.sklep = sklep;
        JLabel logosklepu = new JLabel();
        logosklepu.setText("Sklep (tu bedzie grafika)");
        this.setBackground(Color.yellow);
       // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension( 250, 0));
        kupJedzenie = new JButton("Kup jedzenie");

        kupWybieg = new JButton("Kup Wybieg");
        kupPracownika = new JButton("Kup pracownika");
        sprzedajJedzenie = new JButton("sprzedaj jedzenie");

        sprzedajPracownika = new JButton("sprzedaj Pracownika");

        setsettingsforbutton(kupJedzenie);
        setsettingsforbutton(kupPracownika);
        setsettingsforbutton(kupWybieg);

        setsettingsforbutton(sprzedajJedzenie);
        setsettingsforbutton(sprzedajPracownika);


        kupWybieg.addActionListener(new ReakcjaKupWybieg());
        kupJedzenie.addActionListener(new ReakcjaKupJedzenie());
        kupPracownika.addActionListener(new ReakcjaKupPracownika());
        sprzedajJedzenie.addActionListener(new ReakcjaSprzedajJedzenie());
        sprzedajPracownika.addActionListener(new ReakcjaSprzedajPracownika());

        this.add(logosklepu);
        this.add(kupJedzenie);
        this.add(kupPracownika);
        this.add(kupWybieg);

        this.add(sprzedajJedzenie);
        this.add(sprzedajPracownika);




    }

    public DzienneZoo getZoo() {
        return zoo;
    }

    public void setZoo(DzienneZoo zoo) {
        this.zoo = zoo;
    }

    public Sklep getSklep() {
        return sklep;
    }

    public void setSklep(Sklep sklep) {
        this.sklep = sklep;
    }

    class ReakcjaKupWybieg implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoKupWybieg(getZoo(), getSklep());
        }
    }
    class ReakcjaKupJedzenie implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoKupJedzenie(getZoo(), getSklep());
        }
    }

    class ReakcjaKupPracownika implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoKupPracownika(getZoo(), getSklep());
        }
    }

    class ReakcjaSprzedajJedzenie implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoSprzedajJedzenie(getZoo(), getSklep());
        }
    }

    class ReakcjaSprzedajPracownika implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoSprzedajPracownika(getZoo(), getSklep());
        }
    }



    public void setsettingsforbutton (JButton button)
    {
        button.setPreferredSize(new Dimension(300,50));
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.TOP);
        button.setFont(new Font("Comic Sans",Font.BOLD,15));

        button.setForeground(Color.BLACK);
        button.setBackground(Color.lightGray);
    }




    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }
}
