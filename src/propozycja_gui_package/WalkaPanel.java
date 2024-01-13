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
import java.util.Objects;

public class WalkaPanel extends JPanel {

    private static int wybiegzmienna;
    private static final QLearningAgent agent = new QLearningAgent(2, 2);
    private static Zwierze twoje_zwierze;
    private static Zwierze przeciwnik_global;
    private static poziom_trudnosci_enum poziomTrudnosci;
    private  static PoziomTrudnosciPanel poziomTrudnosciPanel;
    private static boolean tytulWidzialny;
    private static JLabel titleLabel;
    private static JDialog nowyDialog;
    private static JFrame walka;
    private static boolean koniecWalkiPanelWyswietlony = false;



    public WalkaPanel(Zwierze zoo, int wybieg, PoziomTrudnosciPanel poziomTrudnosciPanel) {
        this.poziomTrudnosciPanel = poziomTrudnosciPanel;
        titleLabel = new JLabel("Koniec walki");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 0, 0)); // Transparent initially
        // nowyDialog = new JDialog();
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


        //przyciski
        JPanel panelPrzyciskow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton atakButton = new JButton("Atak");
        JButton leczenieButton = new JButton("Leczenie");

        Zwierze finalPrzeciwnik1 = przeciwnik;

        atakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (twoje_zwierze.getZycie() > 0 && finalPrzeciwnik1.getZycie() > 0) {
                    atak(twoje_zwierze, finalPrzeciwnik1);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
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
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    ruchPrzeciwnik(finalPrzeciwnik, zwierze, wybiegzmienna);


                }
            }
        });


        panelPrzyciskow.add(atakButton);
        panelPrzyciskow.add(leczenieButton);


        // to jest odpowiedzialne  za umieszcxzenie zdj zwierzat na przeciwko siebie
        JPanel panelZwierzat = new JPanel(new BorderLayout());

        JPanel playerLabel = createAnimalPanel1(zwierze);
        JPanel opponentLabel = createOpponentPanel1(przeciwnik);

        panelZwierzat.add(playerLabel, BorderLayout.WEST);
        panelZwierzat.add(opponentLabel, BorderLayout.EAST);

        walka.add(panelPrzyciskow, BorderLayout.NORTH);
        walka.add(panelZwierzat, BorderLayout.CENTER);
///////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////

        walka.setVisible(true);

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
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Los.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Los.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Mis.gif")));
                break;
            case "Niedźiedź polarny":
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
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Papuga.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Papuga.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Paw.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Paw.gif")));
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


        healthBar.setStringPainted(true); // Wyświetlanie wartości numerycznej na pasku zdrowia // obserwator pasek obsrewuje zwierze
        ListaZwierzatPanel healthBarObserver = new ListaZwierzatPanel(healthBar);

        zwierze.addObserver(healthBarObserver);  // nasze jak i przeciwnika


        JPanel animalPanel = new JPanel(new BorderLayout());
        animalPanel.add(imageLabel, BorderLayout.CENTER);
        animalPanel.add(healthBar, BorderLayout.SOUTH);
        animalPanel.setBackground(new Color(0,0,0,0));
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
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Los.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Los.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Mis.gif")));
                break;
            case "Niedźiedź polarny":
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
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Papuga.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Papuga.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/Paw.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/Paw.gif")));
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


        healthBar.setStringPainted(true); // Wyświetlanie wartości numerycznej na pasku zdrowia // obserwator pasek obsrewuje zwierze
        ListaZwierzatPanel healthBarObserver = new ListaZwierzatPanel(healthBar);

        zwierze.addObserver(healthBarObserver);  // nasze jak i przeciwnika


        JPanel animalPanel = new JPanel(new BorderLayout());
        animalPanel.add(imageLabel, BorderLayout.CENTER);
        animalPanel.add(healthBar, BorderLayout.SOUTH);
        animalPanel.setBackground(new Color(0,0,0,0));
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




}
