package propozycja_gui_package;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Wybieg_package.Wybieg_podstawowy;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;



public class ListaZwierzatPanel extends JPanel implements HealthObserver{
    private PoziomTrudnosciPanel poziomTrudnosciPanel;
    private JProgressBar healthBar;
    public ListaZwierzatPanel(JProgressBar healthBar) {
        this.healthBar = healthBar;
    }
    @Override
    public void updateHealth(int newHealth) {
        healthBar.setValue(newHealth);
    }



    public ListaZwierzatPanel(DzienneZoo zoo, NazwyWybiegowPanel nazwyWybiegowPanel) {

        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = nazwyWybiegowPanel.getTabbedPane();
        if (tabbedPane == null) {
            throw new IllegalStateException("JTabbedPane is null");
        }

        // Usuń istniejące zakładki przed dodaniem nowych
        tabbedPane.removeAll();

        JPanel centralPanel = new JPanel(new GridLayout(1, 1));

        for (int i = 0; i < zoo.getListaWybiegow().size(); i++) {
            Wybieg_podstawowy wybieg = zoo.getListaWybiegow().get(i);
            JPanel wybiegPanel = new JPanel(new BorderLayout());
            tabbedPane.addTab("Wybieg " + (i + 1), wybiegPanel);

            dodajZwierzetaDoPanelu(wybiegPanel, wybieg, i);

            centralPanel.add(tabbedPane);
        }
        this.add(centralPanel, BorderLayout.CENTER);

        poziomTrudnosciPanel = new PoziomTrudnosciPanel();
        this.add(poziomTrudnosciPanel, BorderLayout.NORTH);
    }

    private void dodajZwierzetaDoPanelu(JPanel wybiegPanel, Wybieg_podstawowy wybieg,int numerWybiegu) {
        ArrayList<Zwierze> listaZwierzat = new ArrayList<>(wybieg.getLista_zwierzat());

        DefaultListModel<Zwierze> listModel = new DefaultListModel<>();
        for (Zwierze zwierze : listaZwierzat) {
            listModel.addElement(zwierze);
        }

        JList<Zwierze> infoList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(infoList);

        infoList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JPanel panel = new JPanel(new BorderLayout());

                if (value instanceof Zwierze zwierze) {
                    JLabel label = new JLabel(zwierze.getNazwa());
                    label.setFont(new Font("Arial", Font.BOLD, 16));
                    panel.add(label, BorderLayout.CENTER);
                }

                return panel;
            }
        });

        infoList.setFixedCellWidth(200);

        infoList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = infoList.locationToIndex(e.getPoint());
                if (selectedIndex >= 0) {
                    Rectangle cellBounds = infoList.getCellBounds(selectedIndex, selectedIndex);
                    if (cellBounds != null && cellBounds.contains(e.getPoint())) {
                        if (e.getClickCount() == 1) {
                            Zwierze selectedZwierze = infoList.getModel().getElementAt(selectedIndex);
                            System.out.println("Kliknięto na zwierzę: " + selectedZwierze.getNazwa());
                        } else if (e.getClickCount() == 2) {
                            Zwierze selectedZwierze = infoList.getModel().getElementAt(selectedIndex);
                            PopUpPanelZwierzeInfo.showZwierzeInfo((JFrame) SwingUtilities.getWindowAncestor(ListaZwierzatPanel.this), selectedZwierze);
                        }
                    }
                }
            }
        });

        JPanel imagePanel = new JPanel(new GridLayout(0, 2, 10, 10));

        for (Zwierze zwierze : wybieg.getLista_zwierzat()) {
            ImageIcon imageIcon = getImageIconForZwierze(zwierze);
            if (imageIcon != null) {
                Image newImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(zwierze.getNazwa(), new ImageIcon(newImage), JLabel.CENTER);
                imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
                imageLabel.setHorizontalTextPosition(JLabel.CENTER);
                imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

                int finalI = numerWybiegu + 1;
                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        WalkaPanel walkaPanel = new WalkaPanel(poziomTrudnosciPanel);
                        WalkaPanel.wyslij_na_arene(zwierze,finalI);
                    }
                });

                imagePanel.add(imageLabel);
            }
        }

        wybiegPanel.add(scrollPane, BorderLayout.CENTER);
        wybiegPanel.add(imagePanel, BorderLayout.SOUTH);
    }

    private ImageIcon getImageIconForZwierze(Zwierze zwierze) {
        String imageName = getImageNameForZwierze(zwierze);
        URL imageUrl = getClass().getResource(imageName);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        }
        return null;
    }


    private String getImageNameForZwierze(Zwierze zwierze) {
        return switch (zwierze.getNazwa()) {
            case "Pingwin" -> "/obrazki/pingiwn.png";
            case "Żółw" -> "/obrazki/zolw.png";
            case "Rekin" -> "/obrazki/rekin.png";
            case "Orka" -> "/obrazki/orka.png";
            case "Łoś" -> "/obrazki/los.png";
            case "Niedźwiedź" -> "/obrazki/mis.png";
            case "NiedźwiedźPolarny" -> "/obrazki/mispolarny.png";
            case "Lew" -> "/obrazki/lew.png";
            case "Orzeł" -> "/obrazki/orzel.png";
            case "Papuga" -> "/obrazki/papuga.png";
            case "Paw" -> "/obrazki/paw.png";
            case "Nietoperz" -> "/obrazki/nietoperz.png";
            default -> "/obrazki/default.png";
        };
    }


}
