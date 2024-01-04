package propozycja_nocne_gui;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BusinessLogic1 {
    private static List<JFrame> openedWindows = new ArrayList<>();

    // Klasa do wyświetlania informacji o zwierzęciu
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
// Iteracja przez listę zwierząt na wybiegu
                    for (Zwierze zwierze : wybieg.getLista_zwierzat()) {
                        listModel.addElement(zwierze);
                        ImageIcon imageIcon = new ImageIcon("tiger.png");
                        Image newImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER); // Ustawienie imienia jako tekst
                        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
                        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
                        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

                        // Dodanie ActionListenera do etykiety
                        imageLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                wyslij_na_arene(zwierze);
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
                                int index = infoList.locationToIndex(e.getPoint());
                                if (index >= 0) {
                                    Zwierze selectedZwierze = infoList.getModel().getElementAt(index);
                                    ZwierzeInfoPanel.showZwierzeInfo(infoFrame, selectedZwierze);
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
    public static void wyslij_na_arene(Zwierze zwierze) {
        JDialog nowyDialog = new JDialog();
        nowyDialog.setTitle("Nowe Okno");
        nowyDialog.setSize(1280, 720);
        JPanel panel = new JPanel();
        nowyDialog.add(panel);
        JButton b1 = new JButton("Usuń");
        panel.add(b1);


        nowyDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        nowyDialog.setVisible(true);
        nowyDialog.setModal(true);
    }



}
