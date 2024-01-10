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
    private static PoziomTrudnosciPanel poziomTrudnosciPanel;
    private static boolean tytulWidzialny;
    private static JLabel titleLabel;
    private static JDialog nowyDialog;
    private static boolean koniecWalkiPanelWyswietlony = false;



    public WalkaPanel(Zwierze zoo, int wybieg, PoziomTrudnosciPanel poziomTrudnosciPanel) {
        this.poziomTrudnosciPanel = poziomTrudnosciPanel;
        titleLabel = new JLabel("Koniec walki");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 0, 0)); // Transparent initially
        nowyDialog = new JDialog();


    }
    public static void wyslij_na_arene(Zwierze zwierze,int wybieg1) {
        nowyDialog.setTitle("Arena");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        nowyDialog.setSize(screenSize.width, screenSize.height);

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
        JPanel playerAttackLabel = createAnimalPanel2(zwierze);
        JPanel opponentAttackLabel = createOpponentPanel2(przeciwnik);
        // tutaj trzeba zmienic createAnimalPanel zeby zrobic nowy dla przeciwnika bo trzeba odbic lustrzanie zdjecie przeciwnika

        panelZwierzat.add(playerLabel, BorderLayout.WEST);
        panelZwierzat.add(opponentLabel, BorderLayout.EAST);

        nowyDialog.add(panelPrzyciskow, BorderLayout.NORTH);
        nowyDialog.add(panelZwierzat, BorderLayout.CENTER);
        ///

        // Ustawienia okna
        nowyDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        nowyDialog.setVisible(true);
        nowyDialog.setModal(true);
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

        // Nowy panel nad zdjęciem z dodatkowymi informacjami
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(animalPanel, BorderLayout.CENTER);

        return infoPanel;
    }

    static JPanel createAnimalPanel2(Zwierze zwierze) {
        ImageIcon imageIcon;
        switch (zwierze.getNazwa()) {
            case "Pingwin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PingwinAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PingwinAtak.gif")));
                break;
            case "Żółw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/ZolwAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/ZolwAtak.gif")));
                break;
            case "Rekin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/RekinAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/RekinAtak.gif")));
                break;
            case "Orka":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/OrkaAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrkaAtak.gif")));
                break;
            case "Łoś":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/LosAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LosAtak.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisAtak.gif")));
                break;
            case "Niedźiedź polarny":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyAtak.gif")));
                break;
            case "Lew":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/LewAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LewAtak.gif")));
                break;
            case "Orzeł":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/OrzelAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrzelAtak.gif")));
                break;
            case "Papuga":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PapugaAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PapugaAtak.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PawAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PawAtak.gif")));
                break;
            case "Nietoperz":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzAtak.gif")));
                break;
            default:
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2Atak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/misAtak.gif")));
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

        // Nowy panel nad zdjęciem z dodatkowymi informacjami
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(animalPanel, BorderLayout.CENTER);

        return infoPanel;
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

        // Nowy panel nad zdjęciem z dodatkowymi informacjami
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(animalPanel, BorderLayout.CENTER);

        return infoPanel;
    }

    static JPanel createOpponentPanel2(Zwierze zwierze) {
        ImageIcon imageIcon;
        switch (zwierze.getNazwa()) {
            case "Pingwin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PingwinAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PingwinAtak.gif")));
                break;
            case "Żółw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/ZolwAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/ZolwAtak.gif")));
                break;
            case "Rekin":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/RekinAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/RekinAtak.gif")));
                break;
            case "Orka":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/OrkaAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrkaAtak.gif")));
                break;
            case "Łoś":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/LosAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LosAtak.gif")));
                break;
            case "Niedźwiedź":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisAtak.gif")));
                break;
            case "Niedźiedź polarny":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/MisPolarnyAtak.gif")));
                break;
            case "Lew":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/LewAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/LewAtak.gif")));
                break;
            case "Orzeł":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/OrzelAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/OrzelAtak.gif")));
                break;
            case "Papuga":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PapugaAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PapugaAtak.gif")));
                break;
            case "Paw":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/PawAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/PawAtak.gif")));
                break;
            case "Nietoperz":
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzAtak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/NietoperzAtak.gif")));
                break;
            default:
                System.out.println(ListaZwierzatPanel.class.getResource("/obrazki/mis2Atak.gif"));
                imageIcon = new ImageIcon(Objects.requireNonNull(ListaZwierzatPanel.class.getResource("/obrazki/misAtak.gif")));
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

        // Nowy panel nad zdjęciem z dodatkowymi informacjami
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(animalPanel, BorderLayout.CENTER);

        return infoPanel;
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
                    nowyDialog.dispose();
                    new KoniecWalkiPanel(twoje_zwierze.getZycie() > 0, twoje_zwierze.getNazwa());
                    new MainFrame(zoo);
                } else {
                    System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, -10);
                    DzienneZoo zoo = DzienneZoo.getInstance();
                    int wynik = (int) (50 * poziomTrudnosci.getMnoznik());
                    zoo.getZmiennaZasoby().dodajExp(wynik);
                    zoo.getZmiennaZasoby().zmienMonety(wynik);

                    nowyDialog.dispose();

                    new KoniecWalkiPanel(twoje_zwierze.getZycie() > 0, przeciwnik.getNazwa());
                }
            }
            return true;
        }
        return false;
    }

    /*   private static boolean warunki_zakonczenia_walki() {
           if (twoje_zwierze.getZycie() <= 0) {
               return false;
           } else if (przeciwnik.getZycie() <= 0) {
               return false;
           }
           return true;
       }

       public static void podsumowanie(Poziom_trudnosci trudnosc, nr_wybiegu_Zwierze wybieg_zwierze){
           if (twoje_zwierze.getZycie() <= 0) {
               przegrana(wybieg_zwierze);
           } else if (przeciwnik.getZycie() <= 0) {
               wygrana(trudnosc);
           }
       }

       private static void przegrana(nr_wybiegu_Zwierze wybieg_zwierze){
           System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
           agent.learn(1, 0, 1, 10); // agent otrzymuje nagrode za to ze doproawdzil do przegrania przez nas gry
           DzienneZoo zoo = DzienneZoo.getInstance();
           zoo.getListaWybiegow().get(wybieg_zwierze.nr_wybiegu()).usun_zwierze(wybieg_zwierze.zwierze());
       }
       private  static void wygrana(Poziom_trudnosci trudnosc){
           System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
           agent.learn(1, 0, 1, -10);  // agent otrzymuje kare za to ze doproawdzil do przegrania przez nas gry
           DzienneZoo zoo = DzienneZoo.getInstance();
           zoo.getZmiennaZasoby().dodajExp((int) (50*trudnosc.getTrudnosc().getMnoznik()));
           zoo.getZmiennaZasoby().zmienMonety((int) (100*trudnosc.getTrudnosc().getMnoznik()));

       }*/
    private static void restartMainFrame() {
        SwingUtilities.invokeLater(() -> {
            DzienneZoo zoo = DzienneZoo.getInstance();
            JFrame mainFrame = new MainFrame(zoo);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            mainFrame.setVisible(true);
        });
    }

    private static void closeAllFrames() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                window.dispose(); // Close all frames except the current one
            }
        }
    }






}
