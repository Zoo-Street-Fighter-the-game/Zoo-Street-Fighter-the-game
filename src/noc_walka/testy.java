package noc_walka;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import DzienneZooPakiet.*;
import Klasy_Zwierzat.Zwierze;
import Klasy_Zwierzat.ZwierzeLadowe;
import Wybieg_package.Wybieg_Ladowy;
import Wybieg_package.Wybieg_podstawowy;
import enumy.wielkosc_wybiegu_enum;
import pakiet_zasoby.Zasoby;
import pakiet_sklep.*;

public class testy {

    private JFrame frame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextPane messagePane;
    private Timer messageTimer;
    private String[] messages = {"Wiadomość 1", "Wiadomość 2", "Wiadomość 3", "Wiadomość 4"};
    private int currentMessageIndex = 0;
    private boolean titleLabelVisible = false;

    public static void main(String[] args) {
        Zasoby bank = new Zasoby();
        Sklep sklepik = new Sklep();
        ZwierzeLadowe slon = new ZwierzeLadowe("Eustachy", 10, 10, 5, 5, 5, 5, 0, 10);
        ZwierzeLadowe slon2 = new ZwierzeLadowe("Eustachy2", 10, 10, 5, 5, 5, 5, 0, 10);
        ZwierzeLadowe slon3 = new ZwierzeLadowe("Eustachy3", 10, 10, 5, 5, 5, 5, 0, 10);
        ZwierzeLadowe slon4 = new ZwierzeLadowe("Eustachy3", 10, 10, 5, 5, 5, 5, 0, 10);

        DzienneZoo zoo = new DzienneZoo("Zoo", bank);

        Wybieg_Ladowy wybieg1 = new Wybieg_Ladowy(wielkosc_wybiegu_enum.DUZY);
        Wybieg_Ladowy wybieg2 = new Wybieg_Ladowy(wielkosc_wybiegu_enum.SREDNI);
        zoo.dodajWybieg(wybieg1);
        zoo.dodajWybieg(wybieg2);
        wybieg1.dodaj_zwierze(slon);
        wybieg1.dodaj_zwierze(slon2);
        wybieg1.dodaj_zwierze(slon3);
        wybieg1.dodaj_zwierze(slon4);
        wybieg2.dodaj_zwierze(slon3);

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new testy().createAndShowGUI(zoo);
        });
    }

    private void createAndShowGUI(DzienneZoo zoo) {
        frame = new JFrame("Loading Screen Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 820);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel("KONIEC DNIA");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setForeground(new Color(0, 0, 0, 0));

        messagePane = new JTextPane();
        messagePane.setEditable(false);
        messagePane.setContentType("text/html");
        messagePane.setFont(new Font("Arial", Font.PLAIN, 16));
        messagePane.setBackground(UIManager.getColor("Panel.background"));
        messagePane.setPreferredSize(new Dimension(1280, 50));

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(messagePane, BorderLayout.CENTER);

        frame.add(mainPanel);
        startLoading(zoo);
        startMessageAnimation();

        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void startLoading(DzienneZoo zoo) {
        Thread loadingThread = new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                final int progress = i;
                try {
                    Thread.sleep(55);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SwingUtilities.invokeLater(() -> {
                titleLabelVisible = true;
                titleLabel.setForeground(Color.BLACK);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> showMainApplication(zoo));
        });
        loadingThread.start();
    }

    private void startMessageAnimation() {
        messageTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!titleLabelVisible) {
                    messagePane.setText("");
                    currentMessageIndex = (currentMessageIndex + 1) % messages.length;
                    String message = "<html><center>" + messages[currentMessageIndex] + "</center></html>";
                    messagePane.setText(message);

                    Timer fadeInTimer = new Timer(20, new ActionListener() {
                        float alpha = 0f;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            alpha += 0.02f;
                            titleLabel.setForeground(new Color(0, 0, 0, Math.min(1f, alpha)));

                            if (alpha >= 1f) {
                                ((Timer) e.getSource()).stop();
                                titleLabelVisible = true;
                            }
                        }
                    });

                    fadeInTimer.start();
                }
            }
        });
        messageTimer.start();
    }

    private void showMainApplication(DzienneZoo zoo) {
        JFrame infoFrame = new JFrame("Informacje o wybiegach");
        infoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        infoFrame.setSize(1280, 720);
        infoFrame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        ArrayList<Wybieg_podstawowy> listaWybiegow = zoo.getListaWybiegow();

        if (listaWybiegow != null) {
            for (int i = 0; i < listaWybiegow.size(); i++) {
                Wybieg_podstawowy wybieg = listaWybiegow.get(i);
                DefaultListModel<Zwierze> listModel = new DefaultListModel<>();

                JPanel infoPanel = new JPanel(new BorderLayout());
                JPanel imagePanel = new JPanel(new GridLayout(0, 2, 10, 10));

                for (Zwierze zwierze : wybieg.getLista_zwierzat()) {
                    listModel.addElement(zwierze);
                    ImageIcon imageIcon = new ImageIcon("tiger.png");
                    Image image = imageIcon.getImage();
                    Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newImage);
                    JLabel imageLabel = new JLabel(zwierze.getNazwa(), imageIcon, JLabel.CENTER);
                    imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
                    imageLabel.setHorizontalTextPosition(JLabel.CENTER);
                    imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                    imagePanel.add(imageLabel);
                }

                JList<Zwierze> infoList = new JList<>(listModel);
                JScrollPane scrollPane = new JScrollPane(infoList);

                infoList.setCellRenderer(new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                                  boolean isSelected, boolean cellHasFocus) {
                        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        if (value instanceof Zwierze) {
                            Zwierze zwierze = (Zwierze) value;
                            label.setText(zwierze.getNazwa() );
                            label.setFont(new Font("Arial", Font.PLAIN, 20)); // Ustawienie większej czcionki
                        }
                        return label;
                    }
                });

                infoList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            Zwierze selectedZwierze = infoList.getSelectedValue();
                            showZwierzeInfo(selectedZwierze);
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

    private void showZwierzeInfo(Zwierze zwierze) {

        JOptionPane.showMessageDialog(frame,
                "Informacje o zwierzaku:\n" +
                        "Nazwa: " + zwierze.getNazwa() + "\n" +
                        "Zycie: " + zwierze.getZycie() + " \n" +
                        "Sila: " + zwierze.getSila() + " \n" +
                        "Wielkosc: " + zwierze.getWielkosc() + " \n" +
                        "Glod: " + zwierze.getWskaznik_glodu() + " \n" +
                        "Zmeczenie: " + zwierze.getZmeczenie() + " \n" +
                        "Zadowolenie: " + zwierze.getZadowolenie() + " \n" +
                        "Przezyte dni: " + zwierze.getPrzezyte_dni() + " \n" +
                        "Rodzaj " + zwierze.getRodzaj() + " \n" +
                        "");
    }
/*           this.nazwa = nazwa;
        this.zycie = zycie;
        this.sila = sila;
        this.wielkosc = wielkosc;
        this.wskaznik_glodu = wskaznik_glodu;
        this.zmeczenie = zmeczenie;
        this.zadowolenie = zadowolenie;
        this.przezyte_dni = przezyte_dni;
        this.cena = cena;
        this.rodzaj=rodzaj;*/


}
