    package propozycja_nocne_gui;

    import DzienneZooPakiet.DzienneZoo;
    import Klasy_Zwierzat.Zwierze;
    import Wybieg_package.Wybieg_podstawowy;
    import enumy.poziom_trudnosci_enum;
    import noc_walka.Atak;
    import pakiet_arena.Arena;
    import pakiet_arena.QLearningAgent;
    import propozycja_gui_package.HealthObserver;


    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Objects;

    import static pakiet_arena.NocneZoo2.Q_TABLE_FILE;
    import static pakiet_arena.Wybor_zwierzecia.*;

    public class BusinessLogic1 implements HealthObserver {
        private static List<JFrame> openedWindows = new ArrayList<>();
        private JProgressBar healthBar;
        private  int wybiegzmienna;

        public int getWybiegzmienna() {
            return wybiegzmienna;
        }

        public void setWybiegzmienna(int wybiegzmienna) {
            this.wybiegzmienna = wybiegzmienna;
        }

        public BusinessLogic1(JProgressBar healthBar) {
            this.healthBar = healthBar;
        }

        @Override
        public void updateHealth(int newHealth) {
            healthBar.setValue(newHealth);
        }



        // Klasa do wyświetlania informacji o zwierzęciu

        public static void startLoading(DzienneZoo zoo) {
            //opoznanie wlaczenia aplikacji, zabeczpieczenie przed nie załadowaniem sie potrzebnych kompomentow
            Thread loadingThread = new Thread(() -> {
                try {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(55);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                WybiegInfoPanel.showMainApplication(zoo);
            });
            loadingThread.start();
        }
        public static class ZwierzeInfoPanel {
            public static void showZwierzeInfo(JFrame parent, Zwierze zwierze) {
                JOptionPane.showMessageDialog(parent,
                        "Informacje o zwierzaku:\n" +
                                "Nazwa: " + zwierze.getNazwa() + "\n" +
                                "Zycie: " + zwierze.getZycie() + " \n" +
                                "Sila: " + zwierze.getSila() + " \n" +
                                "Wielkosc: " + zwierze.getWielkosc() + " \n" +
                                "Glod: " + zwierze.getWskaznik_glodu() + " \n" +
                                "Przezyte dni: " + zwierze.getPrzezyte_dni() + " \n" +
                                "Rodzaj " + zwierze.getRodzaj() + " \n" +
                                "");
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof JFrame && window != parent) {
                        window.dispose(); // Zamknij wszystkie ramki oprócz bieżącej
                    }
                }
            }

        }

        // Klasa do wyświetlania informacji o wybiegach
        public static class WybiegInfoPanel {
            public static void showMainApplication(DzienneZoo zoo) {
                JFrame infoFrame = new JFrame("Informacje o wybiegach");
                infoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                infoFrame.setSize(1280, 720);
                infoFrame.setLocationRelativeTo(null);

                JTabbedPane tabbedPane = new JTabbedPane();

                // Sprawdzenie, czy lista wybiegów istnieje
                List<Wybieg_podstawowy> listaWybiegow = zoo.getListaWybiegow();
                if (listaWybiegow != null) {
                    for (int i = 0; i < listaWybiegow.size(); i++) {
                        Wybieg_podstawowy wybieg = listaWybiegow.get(i);
                        DefaultListModel<Zwierze> listModel = new DefaultListModel<>();

                        JPanel infoPanel = new JPanel(new BorderLayout());
                        JPanel imagePanel = new JPanel(new GridLayout(0, 2, 10, 10));

                        // Iteracja przez listę zwierząt na wybiegu
                        for (Zwierze zwierze : wybieg.getLista_zwierzat()) {
                            listModel.addElement(zwierze);
                            ImageIcon imageIcon;

                            switch (zwierze.getNazwa()) {
                                case "Łoś":
                                    imageIcon = new ImageIcon(Objects.requireNonNull(BusinessLogic1.class.getResource("/obrazki/los.png")));
                                    break;
                                case "Pingwin":
                                    imageIcon = new ImageIcon(Objects.requireNonNull(BusinessLogic1.class.getResource("/obrazki/pingwin.jpg")));
                                    break;
                                case "Niedźwiedź":
                                    imageIcon = new ImageIcon(Objects.requireNonNull(BusinessLogic1.class.getResource("/obrazki/bear.jpg")));
                                    break;
                                default:
                                    // Domyślny obraz, gdy rodzaj zwierzaka nie jest rozpoznany
                                    imageIcon = new ImageIcon("default.png");
                            }

                            Image newImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER);
                            imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
                            imageLabel.setHorizontalTextPosition(JLabel.CENTER);
                            imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

                            int finalI = i + 1;
                            imageLabel.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    wyslij_na_arene(zwierze, finalI);
                                }
                            });

                            imagePanel.add(imageLabel);
                        }


                        JList<Zwierze> infoList = new JList<>(listModel);
                        JScrollPane scrollPane = new JScrollPane(infoList);
                        // Ustawienia renderera dla JList - domyslu sposb zeby tylko bylo wyswietlane imie zwierzaka
                        infoList.setCellRenderer(new DefaultListCellRenderer() {
                            @Override
                            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                                          boolean isSelected, boolean cellHasFocus) {
                                JPanel panel = new JPanel(new BorderLayout());

                                if (value instanceof Zwierze) {
                                    Zwierze zwierze = (Zwierze) value;

                                    JLabel label = new JLabel(zwierze.getNazwa());
                                    label.setFont(new Font("Arial", Font.PLAIN, 16));

                                    panel.add(label, BorderLayout.CENTER);
                                }

                                return panel;
                            }
                        });
                        // Listener dla JList do wyświetlania informacji o zwierzęciu
                        infoList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                    int selectedIndex = infoList.locationToIndex(e.getPoint());
                                    if (selectedIndex >= 0) {
                                        Zwierze selectedZwierze = infoList.getModel().getElementAt(selectedIndex);

                                    }
                                }
                            }
                        });


                        infoPanel.add(scrollPane, BorderLayout.CENTER);
                        infoPanel.add(imagePanel, BorderLayout.SOUTH);

                        tabbedPane.addTab("Wybieg " + (i + 1), infoPanel);
                    }
                }

                infoFrame.add(tabbedPane);
                infoFrame.setVisible(true);
            }
        }

        // Pole do przechowywania informacji o widoczności tytułu
        private static boolean titleLabelVisible = false;

        // Metoda do rozpoczęcia procesu ładowania opoznia proces wlaczenia okna aby zapobiec zamrozeniu aplikacji


        public static void wyslij_na_arene(Zwierze zwierze, int wybieg) {
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame) {
                    window.dispose(); // Zamknij wszystkie ramki oprócz bieżącej
                }
            }

            Arena arena = new Arena();
            Zwierze przeciwnik = wybor_przeciwnika(poziom_trudnosci_enum.TRUDNY);
            przeciwnik = stworzenie_zwierzecia_walczacego(przeciwnik, arena);

            JDialog nowyDialog = new JDialog();
            nowyDialog.setTitle("Arena");
            nowyDialog.setSize(1280, 820);

            JPanel panelPrzyciskow = new JPanel(new FlowLayout(FlowLayout.CENTER));

            JButton atakButton = new JButton("Atak");
            JButton leczenieButton = new JButton("Leczenie");

            Zwierze finalPrzeciwnik1 = przeciwnik;
            atakButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    atak(zwierze, finalPrzeciwnik1);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    ruchPrzeciwnik(finalPrzeciwnik1, zwierze, wybieg);
                }
            });

            Zwierze finalPrzeciwnik = przeciwnik;
            leczenieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    leczenie(zwierze, finalPrzeciwnik);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    ruchPrzeciwnik(finalPrzeciwnik, zwierze, wybieg);
                }
            });

            panelPrzyciskow.add(atakButton);
            panelPrzyciskow.add(leczenieButton);

            JPanel panelZwierzat = new JPanel(new BorderLayout());


// to jest odpowiedzialne  za umieszcxzenie zdj zwierzat na przeciwko siebie
            JPanel playerLabel = createAnimalPanel(zwierze);

            JPanel opponentLabel = createAnimalPanel(przeciwnik);
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

        public static void ruchPrzeciwnik(Zwierze finalPrzeciwnik, Zwierze zwierze, int wybieg) {



            DzienneZoo zoo = DzienneZoo.getInstance();
            QLearningAgent agent = new QLearningAgent(2, 2);
            System.out.println("xdd"+zwierze.getZycie());


            // Odczytanie stanu wuczonego algorytmu
            agent.loadQTableFromFile(Q_TABLE_FILE);
            // Sprawdzenie warunków zakończenia walki
            if (zwierze.getZycie() <= 0) {
                System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                zoo.getListaWybiegow().get(wybieg-1).usun_zwierze(zwierze);
                agent.learn(1, 0, 1, 10); // agent otrzymuje nagrode za to ze doproawdzil do przegrania przez nas gry
                return;
            } else if (finalPrzeciwnik.getZycie() <= 0) {
                System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                agent.learn(1, 0, 1, -10);  // agent otrzymuje kare za to ze doproawdzil do przegrania przez nas gry
                return;
            }

            // Wybór akcji przez przeciwnika na podstawie agenta Q-learningu
            int actionPrzeciwnika = agent.chooseAction(1);
            // 0 akcja atak
            // 1 akcja leczenie

            if (actionPrzeciwnika == 0) {
                // Przeciwnik wykonuje atak
                Atak atakPrzeciwnika = new Atak();
                atakPrzeciwnika.menuAkcji(finalPrzeciwnik, zwierze);
                System.out.println("Przeciwnik zaatakował!");
                zwierze.setZycie(zwierze.getZycie());
                zwierze.notifyObservers();

                // Sprawdzenie warunków zakończenia walki
                if (zwierze.getZycie() <= 0) {
                    System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                    zoo.getListaWybiegow().get(wybieg-1).usun_zwierze(zwierze);
                    agent.learn(1, 0, 1, 10);
                    return;
                } else if (finalPrzeciwnik.getZycie() <= 0) {
                    System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, -10);
                    return;
                }
            } else {
                // Przeciwnik wykonuje leczenie
                finalPrzeciwnik.setZycie(finalPrzeciwnik.getZycie()+10);
                finalPrzeciwnik.notifyObservers();
                System.out.println("Przeciwnik się leczy.");
                agent.learn(1, actionPrzeciwnika, 1, 5);

                // Sprawdzenie warunków zakończenia walki
                if (zwierze.getZycie() <= 0) {
                    System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, 10);
                    return;
                } else if (finalPrzeciwnik.getZycie() <= 0) {
                    System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                    agent.learn(1, 0, 1, -10);
                    return;
                }
            }

            if (zwierze.getZycie() <= 0) {
                System.out.println("Przegrałeś! Twój zwierzak ma zerowe zdrowie.");
                return;
            } else if (finalPrzeciwnik.getZycie() <= 0) {
                System.out.println("Gratulacje! Wygrałeś! Przeciwnik ma zerowe zdrowie.");
                return;
            }

            // Wyświetlenie aktualnych wartości zdrowia zwierząt
            System.out.println("Moje:" + zwierze.getZycie());
            System.out.println(finalPrzeciwnik.getZycie());

            agent.saveQTableToFile(Q_TABLE_FILE);
        }

        private static void leczenie(Zwierze zwierze, Zwierze finalPrzeciwnik) {
            zwierze.setZycie(zwierze.getZycie()+10);
            zwierze.notifyObservers();
        }


        private static void atak(Zwierze zwierze, Zwierze finalPrzeciwnik) {
            Atak atak = new Atak();
            atak.menuAkcji(zwierze, finalPrzeciwnik);
            finalPrzeciwnik.setZycie(finalPrzeciwnik.getZycie());
            finalPrzeciwnik.notifyObservers();
        }

        private static JPanel createAnimalPanel(Zwierze zwierze) {
            ImageIcon imageIcon;

            switch (zwierze.getNazwa()) {
                case "Łoś":
                    System.out.println(BusinessLogic1.class.getResource("/obrazki/mis2.gif"));
                    imageIcon = new ImageIcon(Objects.requireNonNull(BusinessLogic1.class.getResource("/obrazki/mis.gif")));
                    break;
                case "Pingwin":
                    imageIcon = new ImageIcon(Objects.requireNonNull(BusinessLogic1.class.getResource("/obrazki/pingwin.png")));
                    break;
                case "Niedźwiedź":
                    imageIcon = new ImageIcon(Objects.requireNonNull(BusinessLogic1.class.getResource("/obrazki/bear.png")));
                    break;
                default:
                    // Domyślny obraz, gdy rodzaj zwierzaka nie jest rozpoznany
                    imageIcon = new ImageIcon("default.png");
            }

            //Image newImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            //JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER);
            JLabel imageLabel = new JLabel(zwierze.getNazwa(), imageIcon, JLabel.CENTER);
            imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
            imageLabel.setHorizontalTextPosition(JLabel.CENTER);
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            // Dodanie paska zdrowia
            JProgressBar healthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 200);
            healthBar.setStringPainted(true);
            healthBar.setValue(zwierze.getZycie());


            healthBar.setStringPainted(true); // Wyświetlanie wartości numerycznej na pasku zdrowia // obserwator pasek obsrewuje zwierze
            BusinessLogic1 healthBarObserver = new BusinessLogic1(healthBar);

            zwierze.addObserver(healthBarObserver);  // nasze jak i przeciwnika


            JPanel animalPanel = new JPanel(new BorderLayout());
            animalPanel.add(imageLabel, BorderLayout.CENTER);
            animalPanel.add(healthBar, BorderLayout.SOUTH);

            // Nowy panel nad zdjęciem z dodatkowymi informacjami
            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.add(animalPanel, BorderLayout.CENTER);

            return infoPanel;
        }



    }
