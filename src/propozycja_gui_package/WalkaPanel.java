package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;
import enumy.poziom_trudnosci_enum;
import noc_walka.Atak;
import pakiet_arena.Arena;
import Klasy_Zwierzat.Zwierze;
import pakiet_arena.QLearningAgent;


import static pakiet_arena.NocneZoo2.Q_TABLE_FILE;
import static pakiet_arena.Wybor_zwierzecia.stworzenie_zwierzecia_walczacego;
import static pakiet_arena.Wybor_zwierzecia.wybor_przeciwnika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class WalkaPanel extends JPanel {

    private static int wybiegzmienna;
    private static final QLearningAgent agent = new QLearningAgent(2, 2);
    private static Zwierze twoje_zwierze;
    private static Zwierze przeciwnik_global;
    private static poziom_trudnosci_enum poziomTrudnosci;
    private static PoziomTrudnosciPanel poziomTrudnosciPanel;
    private static boolean tytulWidzialny;
    private static JLabel titleLabel;
    private static JDialog nowyDialog;
    private static JFrame walka;
    private static boolean koniecWalkiPanelWyswietlony = false;
    private static Timer healingTimer;
    private static JPanel panelZwierzat;
    private static JPanel ultimatePanel;

    public WalkaPanel(Zwierze zoo, int wybieg, PoziomTrudnosciPanel poziomTrudnosciPanel) {

        this.poziomTrudnosciPanel = poziomTrudnosciPanel;
        titleLabel = new JLabel("Koniec walki");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 0, 0)); // Transparent initially
        walka = new JFrame();
        /////////////////////////



    }
    public static void wyslij_na_arene(Zwierze zwierze, int wybieg1) {

        walka.setTitle("Arena");

        walka.setSize(2000,1000);



        poziom_trudnosci_enum wybranyPoziom = poziomTrudnosciPanel.getWybranyPoziomTrudnosci();
        Zwierze przeciwnik = wybor_przeciwnika(wybranyPoziom);
        przeciwnik_global = przeciwnik;

        System.out.println(wybranyPoziom);

        wybiegzmienna = wybieg1;
        twoje_zwierze = zwierze;

        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                window.dispose(); // Zamknij wszystkie ramki oprócz bieżącej
            }
        }
        Arena arena = new Arena();
        przeciwnik = stworzenie_zwierzecia_walczacego(przeciwnik, arena);

//////////////
        JPanel panelArena = new JPanel();

        JLabel temperatura = new JLabel("Temperatura: " + arena.getTemperatura()+"   ");
        temperatura.setIcon(new ImageIcon(ListaZwierzatPanel.class.getResource("/obrazki/temp.png")));
        temperatura.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        JLabel zalesienie = new JLabel("Zalesienie: " + arena.getZalesienie()+"   ");
        zalesienie.setIcon(new ImageIcon(ListaZwierzatPanel.class.getResource("/obrazki/tree.png")));
        zalesienie.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        JLabel woda = new JLabel("Wody Powierzchniowe: " + arena.getWodyPowierzchniowe()+"   ");
        woda.setIcon(new ImageIcon(ListaZwierzatPanel.class.getResource("/obrazki/water.png")));
        woda.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        JLabel sun = new JLabel("Nasłonecznienie: " + arena.getNaslonecznienie()+"   ");
        sun.setIcon(new ImageIcon(ListaZwierzatPanel.class.getResource("/obrazki/sun.png")));
        sun.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        JLabel wiatr = new JLabel("Wiatr: " + arena.getWiatr()+"   ");
        wiatr.setIcon(new ImageIcon(ListaZwierzatPanel.class.getResource("/obrazki/wind.png")));
        wiatr.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        panelArena.add(temperatura);
        panelArena.add(zalesienie);
        panelArena.add(woda);
        panelArena.add(wiatr);
        panelArena.add(sun);
        panelArena.setBackground(new Color(0x5EABE0));
        panelArena.setPreferredSize(new Dimension(0, 70));
        panelArena.setVisible(true);


///////////////
        //przyciski
        JPanel panelPrzyciskow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 140)); // Odstęp poziomy: 20, odstęp pionowy: 10
        RoundButton atakButton = new RoundButton("Atak");
        RoundButton leczenieButton = new RoundButton("Leczenie");

// Ustawienie przycisków jako przezroczystych
        atakButton.setOpaque(false);
        atakButton.setContentAreaFilled(false);
        atakButton.setBorderPainted(false);

        leczenieButton.setOpaque(false);
        leczenieButton.setContentAreaFilled(false);
        leczenieButton.setBorderPainted(false);

// Ustawienie preferowanej wielkości dla guzików
        Dimension buttonSize = new Dimension(120, 50); // Dostosuj szerokość i wysokość według potrzeb
        atakButton.setPreferredSize(buttonSize);
        leczenieButton.setPreferredSize(buttonSize);

        Zwierze finalPrzeciwnik1 = przeciwnik;

        panelPrzyciskow.add(atakButton);
        panelPrzyciskow.add(leczenieButton);
        panelPrzyciskow.setOpaque(false);



        // to jest odpowiedzialne  za umieszcxzenie zdj zwierzat na przeciwko siebie
        panelZwierzat = new JPanel(new BorderLayout());
        JPanel panelZwierzat2 = new JPanel(new BorderLayout());
        JPanel playerLabel = createAnimalPanel1(zwierze);
        JPanel opponentLabel = createOpponentPanel1(przeciwnik);
        JPanel playerAttackLabel = createAnimalPanel2(zwierze);
        JPanel opponentAttackLabel = createOpponentPanel2(przeciwnik);
        panelZwierzat.add(playerLabel, BorderLayout.WEST);
        panelZwierzat.add(opponentLabel, BorderLayout.EAST);
        panelZwierzat2.add(playerAttackLabel, BorderLayout.WEST);
        panelZwierzat2.add(opponentAttackLabel, BorderLayout.EAST);

        panelZwierzat2.setBackground(new Color(0,0,0,0));
        panelZwierzat2.setOpaque(false);
        panelZwierzat.setBackground(new Color(0,0,0,0));
        panelZwierzat.setOpaque(false);

        ultimatePanel = new JPanel(new CardLayout());
        ultimatePanel.add(panelZwierzat);
        ultimatePanel.add(panelZwierzat2);
        ultimatePanel.setBackground(new Color(0,0,0,0));
        ultimatePanel.setOpaque(false);

        JPanel wygladgui = new JPanel(new BorderLayout());
        wygladgui.add(panelPrzyciskow, BorderLayout.NORTH);
        wygladgui.add(ultimatePanel, BorderLayout.CENTER);
       wygladgui.add(panelArena, BorderLayout.SOUTH);
        wygladgui.setSize(1430,900);
        wygladgui.setOpaque(false);

        JLayeredPane layeredPane = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon arenka = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/arena.gif")));
                g.drawImage(arenka.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        layeredPane.setSize(2000, 1000);
        layeredPane.add(wygladgui, JLayeredPane.DRAG_LAYER);

        walka.setContentPane(layeredPane);
        walka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        walka.setVisible(true);

        atakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (twoje_zwierze.getZycie() > 0 && finalPrzeciwnik1.getZycie() > 0) {
                    atak(twoje_zwierze, finalPrzeciwnik1);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    switchPanels();
                    ruchPrzeciwnik(finalPrzeciwnik1, zwierze, wybiegzmienna);
                }


            }
        });
        Zwierze finalPrzeciwnik = przeciwnik;


        leczenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (twoje_zwierze.getZycie() > 0 && finalPrzeciwnik1.getZycie() > 0) {
                    leczenie(zwierze, finalPrzeciwnik);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    ruchPrzeciwnik(finalPrzeciwnik, zwierze, wybiegzmienna);


                }
            }
        });





    }
    static void leczenie(Zwierze zwierze, Zwierze finalPrzeciwnik) {
        zwierze.setHealth(zwierze.getZycie()+10);
        zwierze.notifyObservers();
    }



     static void atak(Zwierze zwierze, Zwierze finalPrzeciwnik) {
        Atak atak = new Atak();
        atak.MenuAkcji(zwierze, finalPrzeciwnik);
        finalPrzeciwnik.setHealth(finalPrzeciwnik.getZycie());
        finalPrzeciwnik.notifyObservers();


    }

    static void ruchPrzeciwnik(Zwierze finalPrzeciwnik, Zwierze zwierze, int wybieg) {
        DzienneZoo zoo = DzienneZoo.getInstance();
        QLearningAgent agent = new QLearningAgent(2, 2);
        // Odczytanie stanu wuczonego algorytmu
        agent.loadQTableFromFile(Q_TABLE_FILE);
        // Sprawdzenie warunków zakończenia walki

        if (warunki_zakonczenia_walki(agent, zwierze, finalPrzeciwnik,wybieg)) ;
        // Wybór akcji przez przeciwnika na podstawie agenta Q-learningu
        int actionPrzeciwnika = agent.chooseAction(1);
        // 0 akcja atak
        // 1 akcja leczenie

        if (actionPrzeciwnika == 0) {
            // Przeciwnik wykonuje atak
            Atak atakPrzeciwnika = new Atak();
            atakPrzeciwnika.MenuAkcji(finalPrzeciwnik, zwierze);
            System.out.println("Przeciwnik zaatakował!");
            zwierze.setHealth(zwierze.getZycie());
            zwierze.notifyObservers();

            // Sprawdzenie warunków zakończenia walki
            if (warunki_zakonczenia_walki(agent, zwierze, finalPrzeciwnik,wybieg)) ;
        } else {
            // Przeciwnik wykonuje leczenie
            finalPrzeciwnik.setHealth(finalPrzeciwnik.getZycie()+10);
            finalPrzeciwnik.notifyObservers();
            System.out.println("Przeciwnik się leczy.");
            agent.learn(1, actionPrzeciwnika, 1, 5);

            // Sprawdzenie warunków zakończenia walki
            if (warunki_zakonczenia_walki(agent, zwierze, finalPrzeciwnik,wybieg));
        }



        agent.saveQTableToFile(Q_TABLE_FILE);
    }
    static JPanel createAnimalPanel1(Zwierze zwierze) {
        ImageIcon imageIcon;
        switch (zwierze.getNazwa()) {
            case "Pingwin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Pingwin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Pingwin.gif")));
                break;
            case "Żółw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Zolw.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Zolw.gif")));
                break;
            case "Rekin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Rekin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Rekin.gif")));
                break;
            case "Orka":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orka.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Orka.gif")));
                break;
            case "Łoś":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/LosR.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LosR.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/mis.gif")));
                break;
            case "NiedźwiedźPolarny":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarny.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarny.gif")));
                break;
            case "Lew":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Lew.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Lew.gif")));
                break;
            case "Orzeł":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orzel.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Orzel.gif")));
                break;
            case "Papuga":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PapugaR.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PapugaR.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PawR.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PawR.gif")));
                break;
            case "Nietoperz":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Nietoperz.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Nietoperz.gif")));
                break;
            default:
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/mis.gif")));
                break;
        }

        //Image newImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        //JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER);

        JLabel imageLabel = new JLabel(zwierze.getNazwa(), imageIcon, JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Dodanie paska zdrowia
        JProgressBar healthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 600);
        healthBar.setStringPainted(true);
        healthBar.setValue(zwierze.getZycie());

        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        healthBar.setOpaque(false);


        healthBar.setStringPainted(true); // Wyświetlanie wartości numerycznej na pasku zdrowia // obserwator pasek obsrewuje zwierze
        ListaZwierzatPanel healthBarObserver = new ListaZwierzatPanel(healthBar);

        zwierze.addObserver(healthBarObserver);  // nasze jak i przeciwnika

        JPanel animalPanel = new JPanel(new GridBagLayout());
        animalPanel.setBackground(new Color(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Kolumna dla obrazka zwierzęcia
        gbc.gridy = 12; // Wiersz dla obrazka zwierzęcia (przesunięcie o 2 w dół)
        gbc.weightx = 1.0; // Waga dla obrazka zwierzęcia
        gbc.anchor = GridBagConstraints.SOUTH; // Ustaw anchor na środek

        animalPanel.add(imageLabel, gbc);

// Dodaj odstęp pionowy między obrazkiem a paskiem zdrowia
        gbc.gridy = 3; // Wiersz dla odstępu (przesunięcie o 1 w dół)
        gbc.weighty = 0.1; // Waga dla odstępu, aby można było kontrolować przestrzeń
        animalPanel.add(Box.createVerticalGlue(), gbc);

// Dodaj pasek zdrowia pod obrazkiem zwierzęcia
        gbc.gridy = 4; // Wiersz dla paska zdrowia (przesunięcie o 1 w dół)
        gbc.weighty = 0.0; // Resetuj wagę
        gbc.anchor = GridBagConstraints.CENTER; // Ustaw anchor na środek
        animalPanel.add(healthBar, gbc);

        animalPanel.setOpaque(false);

        return animalPanel;

    }

    static JPanel createAnimalPanel2(Zwierze zwierze) {
        ImageIcon imageIcon;
        switch (zwierze.getNazwa()) {
            case "Pingwin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Pingwin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PingwinAtak.gif")));
                break;
            case "Żółw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Zolw.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/ZolwAtak.gif")));
                break;
            case "Rekin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Rekin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/RekinAtak.gif")));
                break;
            case "Orka":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orka.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrkaAtak.gif")));
                break;
            case "Łoś":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/LosR.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LosRAtak.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisAtak.gif")));
                break;
            case "NiedźwiedźPolarny":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarny.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyAtak.gif")));
                break;
            case "Lew":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Lew.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LewAtak.gif")));
                break;
            case "Orzeł":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orzel.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrzelAtak.gif")));
                break;
            case "Papuga":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PapugaR.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PapugaRAtak.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PawR.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PawRAtak.gif")));
                break;
            case "Nietoperz":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Nietoperz.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzAtak.gif")));
                break;
            default:
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/mis.gif")));
                break;
        }

        //Image newImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        //JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER);

        JLabel imageLabel = new JLabel(zwierze.getNazwa(), imageIcon, JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Dodanie paska zdrowia
        JProgressBar healthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 600);
        healthBar.setStringPainted(true);
        healthBar.setValue(zwierze.getZycie());

        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        healthBar.setOpaque(false);


        healthBar.setStringPainted(true); // Wyświetlanie wartości numerycznej na pasku zdrowia // obserwator pasek obsrewuje zwierze
        ListaZwierzatPanel healthBarObserver = new ListaZwierzatPanel(healthBar);

        zwierze.addObserver(healthBarObserver);  // nasze jak i przeciwnika

        JPanel animalPanel = new JPanel(new GridBagLayout());
        animalPanel.setBackground(new Color(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Kolumna dla obrazka zwierzęcia
        gbc.gridy = 12; // Wiersz dla obrazka zwierzęcia (przesunięcie o 2 w dół)
        gbc.weightx = 1.0; // Waga dla obrazka zwierzęcia
        gbc.anchor = GridBagConstraints.SOUTH; // Ustaw anchor na środek

        animalPanel.add(imageLabel, gbc);

// Dodaj odstęp pionowy między obrazkiem a paskiem zdrowia
        gbc.gridy = 3; // Wiersz dla odstępu (przesunięcie o 1 w dół)
        gbc.weighty = 0.1; // Waga dla odstępu, aby można było kontrolować przestrzeń
        animalPanel.add(Box.createVerticalGlue(), gbc);

// Dodaj pasek zdrowia pod obrazkiem zwierzęcia
        gbc.gridy = 4; // Wiersz dla paska zdrowia (przesunięcie o 1 w dół)
        gbc.weighty = 0.0; // Resetuj wagę
        gbc.anchor = GridBagConstraints.CENTER; // Ustaw anchor na środek
        animalPanel.add(healthBar, gbc);

        animalPanel.setOpaque(false);

        return animalPanel;

    }
    static JPanel createOpponentPanel1(Zwierze zwierze) {
        ImageIcon imageIcon;
        switch (zwierze.getNazwa()) {
            case "Pingwin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Pingwin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Pingwin.gif")));
                break;
            case "Żółw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/ZolwL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/ZolwL.gif")));
                break;
            case "Rekin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Rekin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Rekin.gif")));
                break;
            case "Orka":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orka.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Orka.gif")));
                break;
            case "Łoś":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Los.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Los.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/misL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisL.gif")));
                break;
            case "NiedźwiedźPolarny":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyL.gif")));
                break;
            case "Lew":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Lew.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Lew.gif")));
                break;
            case "Orzeł":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orzel.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Orzel.gif")));
                break;
            case "Papuga":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Papuga.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Papuga.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Paw.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Paw.gif")));
                break;
            case "Nietoperz":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzL.gif")));
                break;
            default:
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/mis.gif")));
                break;
        }

        //Image newImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        //JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER);
        JLabel imageLabel = new JLabel(zwierze.getNazwa(), imageIcon, JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Dodanie paska zdrowia
        JProgressBar healthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 600);

        healthBar.setStringPainted(true);
        healthBar.setValue(zwierze.getZycie());

        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        healthBar.setOpaque(false);


        healthBar.setStringPainted(true); // Wyświetlanie wartości numerycznej na pasku zdrowia // obserwator pasek obsrewuje zwierze
        ListaZwierzatPanel healthBarObserver = new ListaZwierzatPanel(healthBar);

        zwierze.addObserver(healthBarObserver);  // nasze jak i przeciwnika

        JPanel animalPanel = new JPanel(new GridBagLayout());
        animalPanel.setBackground(new Color(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Kolumna dla obrazka zwierzęcia
        gbc.gridy = 0; // Wiersz dla obrazka zwierzęcia (bez przesunięcia w dół)
        gbc.weightx = 1.0; // Waga dla obrazka zwierzęcia
        gbc.anchor = GridBagConstraints.CENTER; // Ustaw anchor na środek

        animalPanel.add(imageLabel, gbc);

// Dodaj pasek zdrowia pod obrazkiem zwierzęcia
        gbc.gridy = 1; // Wiersz dla paska zdrowia (przesunięcie o 1 w dół)
        gbc.weighty = 0.0; // Resetuj wagę
        gbc.anchor = GridBagConstraints.CENTER; // Ustaw anchor na środek
        animalPanel.add(healthBar, gbc);

        animalPanel.setOpaque(false);

        return animalPanel;

    }

    static JPanel createOpponentPanel2(Zwierze zwierze) {
        ImageIcon imageIcon;
        switch (zwierze.getNazwa()) {
            case "Pingwin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Pingwin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PingwinAtak.gif")));
                break;
            case "Żółw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/ZolwL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/ZolwLAtak.gif")));
                break;
            case "Rekin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Rekin.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/RekinAtak.gif")));
                break;
            case "Orka":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orka.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrkaAtak.gif")));
                break;
            case "Łoś":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Los.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LosAtak.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/misL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisLAtak.gif")));
                break;
            case "NiedźwiedźPolarny":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyLAtak.gif")));
                break;
            case "Lew":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Lew.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LewAtak.gif")));
                break;
            case "Orzeł":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Orzel.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrzelAtak.gif")));
                break;
            case "Papuga":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Papuga.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PapugaAtak.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Paw.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PawAtak.gif")));
                break;
            case "Nietoperz":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzL.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzLAtak.gif")));
                break;
            default:
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/mis.gif")));
                break;
        }

        //Image newImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        //JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER);
        JLabel imageLabel = new JLabel(zwierze.getNazwa(), imageIcon, JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Dodanie paska zdrowia
        JProgressBar healthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 600);

        healthBar.setStringPainted(true);
        healthBar.setValue(zwierze.getZycie());

        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        healthBar.setOpaque(false);


        healthBar.setStringPainted(true); // Wyświetlanie wartości numerycznej na pasku zdrowia // obserwator pasek obsrewuje zwierze
        ListaZwierzatPanel healthBarObserver = new ListaZwierzatPanel(healthBar);

        zwierze.addObserver(healthBarObserver);  // nasze jak i przeciwnika

        JPanel animalPanel = new JPanel(new GridBagLayout());
        animalPanel.setBackground(new Color(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Kolumna dla obrazka zwierzęcia
        gbc.gridy = 0; // Wiersz dla obrazka zwierzęcia (bez przesunięcia w dół)
        gbc.weightx = 1.0; // Waga dla obrazka zwierzęcia
        gbc.anchor = GridBagConstraints.CENTER; // Ustaw anchor na środek

        animalPanel.add(imageLabel, gbc);

// Dodaj pasek zdrowia pod obrazkiem zwierzęcia
        gbc.gridy = 1; // Wiersz dla paska zdrowia (przesunięcie o 1 w dół)
        gbc.weighty = 0.0; // Resetuj wagę
        gbc.anchor = GridBagConstraints.CENTER; // Ustaw anchor na środek
        animalPanel.add(healthBar, gbc);

        animalPanel.setOpaque(false);

        return animalPanel;

    }

    private static boolean warunki_zakonczenia_walki(QLearningAgent agent, Zwierze twoje_zwierze, Zwierze przeciwnik, int wybieg) {
        poziom_trudnosci_enum poziomTrudnosci = poziomTrudnosciPanel.getWybranyPoziomTrudnosci();

        if (twoje_zwierze.getZycie() <= 0 || przeciwnik.getZycie() <= 0) {
            if (!koniecWalkiPanelWyswietlony) {
                koniecWalkiPanelWyswietlony = true; // Ustawienie flagi na true po pierwszym wyświetleniu

                if (twoje_zwierze.getZycie() <= 0) {
                    System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, 10);
                    DzienneZoo zoo = DzienneZoo.getInstance();
                    zoo.getListaWybiegow().get(wybieg - 1).usun_zwierze(twoje_zwierze);
                    System.out.println(twoje_zwierze.getNazwa());
                    walka.dispose();
                    new KoniecWalkiPanel(twoje_zwierze.getZycie() > 0, twoje_zwierze.getNazwa());
                    new MainFrame(zoo);
                } else {
                    System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, -10);
                    DzienneZoo zoo = DzienneZoo.getInstance();
                    int wynik = (int) (50 * poziomTrudnosci.getMnoznik());
                    zoo.getZmiennaZasoby().dodajExp(wynik);
                    zoo.getZmiennaZasoby().zmienMonety(wynik);

                    walka.dispose();

                    new KoniecWalkiPanel(twoje_zwierze.getZycie() > 0, przeciwnik.getNazwa());
                }
            }
            return true;
        }
        return false;
    }

    private static void switchPanels() {
        CardLayout cardLayout = (CardLayout) ultimatePanel.getLayout();
        cardLayout.next(ultimatePanel);
    }

}