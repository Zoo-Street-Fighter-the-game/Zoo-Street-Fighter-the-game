package gui_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DzienneZooPakiet.DzienneZoo;
import interfejsy.ObserwujacyPracownikGUI_interface;
import interfejsy.UpdateGUI;
import pakiet_sklep.Sklep;


public class PanelDzienSklep extends JPanel implements UpdateGUI, ObserwujacyPracownikGUI_interface {
    private JButton kupJedzenie;
    private JButton kupWybieg;
    private JButton kupPracownika;
    private JButton sprzedajJedzenie ;
    private JButton sprzedajPracownika;


    private JButton zapiszGre;
    private JButton wczytajGre;
    private Sklep sklep;

    private final DzienneZoo zoo;
    public PanelDzienSklep(Sklep sklep)
    {
        sklep.dodajObsewatoraGUI(this);

        this.sklep = sklep;
        this.zoo = sklep.getZoo();

        JLabel logosklepu = new JLabel();
        logosklepu.setIcon( new ImageIcon("src/ikony/ShopImage.png"));
        this.setBackground(new Color(0xd3f3e3));
       // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension( 250, 0));
        kupJedzenie = new JButton("Kup Jedzenie");

        kupWybieg = new JButton("Kup Wybieg");
        kupPracownika = new JButton("Kup Pracownika");
        sprzedajJedzenie = new JButton("Sprzedaj Jedzenie");

        sprzedajPracownika = new JButton("Sprzedaj Pracownika");
        zapiszGre = new JButton("Zapisz Gre");
        wczytajGre = new JButton("Wczytaj Poprzednia Gre");

        setsettingsforbutton(kupJedzenie);
        setsettingsforbutton(kupPracownika);
        setsettingsforbutton(kupWybieg);

        setsettingsforbutton(sprzedajJedzenie);
        setsettingsforbutton(sprzedajPracownika);
        setsettingsforbutton(zapiszGre);
        setsettingsforbutton(wczytajGre);


        kupWybieg.addActionListener(new ReakcjaKupWybieg());
        kupJedzenie.addActionListener(new ReakcjaKupJedzenie());
        kupPracownika.addActionListener(new ReakcjaKupPracownika());
        sprzedajJedzenie.addActionListener(new ReakcjaSprzedajJedzenie());
        sprzedajPracownika.addActionListener(new ReakcjaSprzedajPracownika());
        zapiszGre.addActionListener(new ReakcjaZapiszGre());
        wczytajGre.addActionListener(new ReakcjaWczytajGre());

        this.add(logosklepu);
        this.add(kupJedzenie);
        this.add(kupPracownika);
        this.add(kupWybieg);

        this.add(sprzedajJedzenie);
        this.add(sprzedajPracownika);
        this.add(zapiszGre);
        this.add(wczytajGre);






    }

    public Sklep getSklep() {
        return sklep;
    }

    public void setSklep(Sklep sklep) {
        this.sklep = sklep;
    }

    @Override
    public void reakcjaZaznaczenie() {

        kupJedzenie.setEnabled(false);
        kupPracownika.setEnabled(false);
        kupWybieg.setEnabled(false);
        sprzedajJedzenie.setEnabled(false);
        sprzedajPracownika.setEnabled(false);
        zapiszGre.setEnabled(false);
        wczytajGre.setEnabled(false);
    }

    @Override
    public void reakcjaOdznaczenie() {
        kupJedzenie.setEnabled(true);
        kupPracownika.setEnabled(true);
        kupWybieg.setEnabled(true);
        sprzedajJedzenie.setEnabled(true);
        sprzedajPracownika.setEnabled(true);
        zapiszGre.setEnabled(true);
        wczytajGre.setEnabled(true);
    }
    class ReakcjaZapiszGre implements ActionListener
   {
       public void actionPerformed(ActionEvent e) {
           sklep.zapiszGre3();
           new gui_oknaPopUp.OknoZapis();
       }
    }

    class ReakcjaWczytajGre implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            sklep.wczytajGre3();
        }

    }
    class ReakcjaKupWybieg implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoKupWybieg(getSklep());
        }
    }
    class ReakcjaKupJedzenie implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoKupJedzenie(getSklep());
        }
    }

    class ReakcjaKupPracownika implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoKupPracownika(getSklep());
        }
    }

    class ReakcjaSprzedajJedzenie implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new gui_oknaPopUp.OknoSprzedajJedzenie(getSklep());
        }
    }

    class ReakcjaSprzedajPracownika implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!zoo.getListaPracownikow().isEmpty()) {
                new gui_oknaPopUp.OknoSprzedajPracownika(getSklep());
            }
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
        button.setBackground(new Color(0xe3e2de));
    }




    @Override
    public void updateGUI() {
        this.repaint();
        this.revalidate();
    }
}
