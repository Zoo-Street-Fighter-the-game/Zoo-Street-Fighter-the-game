package propozycja_nocne_gui;

import DzienneZooPakiet.DzienneZoo;
import Klasy_Zwierzat.Zwierze;
import Przedmioty.Przedmiot;
import Wybieg_package.Wybieg_podstawowy;
import enumy.przedmioty_enum;
import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;
import pakiet_sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextPane messagePane;
    private int currentMessageIndex = 0;
    private boolean tytulWidzialny = false;
    private String[] messages = {"Moze nie jest to ekskalibur, ale z twoich zwierzat tez zaden krol Artur. \" \n" +
            "                \"Miecz zapewnia hojny bonus do obrazen w walkach zwierzat. Wnoszenie broni bialej na arene walk\" \n" +
            "                \" nie jest prawdopodobnie najuczciwszym zagraniem, ale takie juz prawa miejskiej dzungli.", "Chcesz zeby twoje zwierze pedzilo jak wiatr? Adidasy zapewniaja duzy bonus do szybkosci. \" \n" +
            "                \"Byc moze myslisz sobie; ale moje zwierze ma pletwy :( Nic nie szkodzi! Nasz produkt nie dyskryminuje \" \n" +
            "                \"i umozliwia osiagniecie zawrotnej predkosci wszystkim istotom duzym i malym.", "Uwaga: Przedmiot ten powinien byc uzywany jedynie w celach humorystycznych \" \n" +
            "                \"i nie jest przeznaczony do powaznych starc. W przypadku konfrontacji z potezniejszymi przeciwnikami, \" \n" +
            "                \"zdecydowanie zaleca sie ulepszenie do bardziej przyzwoitej broni.", "Pistolet na wode to swietny wybor dla wszystkich Wondych zwierzat, ktore z reguly maja trudniej \" \n" +
            "                \"w walkach zwierzat. #LifeIsBrutal Juz dzis odzyskaj przewage nad przeciwnikami i zamien arene w \" \n" +
            "                \"kolejna Atlantyde juz dzis, tylko za \" ","Rekawice bokserskie zwiekszaja sile ciosow twojego zwierzecia, lecz takze umozliwiaja mu trzymanie gardy \" \n" +
            "                \"(chociaz podobno najlepsza obrona jest atak)","Ten topor to dzielo ekscentrycznego kowala, ktory postanowil polaczyc styl z funkcjonalnoscia \" \n" +
            "                \"w najbardziej absurdalny sposob. Obrazenia ktore zadaje sa potezne, jednak z wielka moca przychodzi wielka \" \n" +
            "                \"odpowiedzialnosc i ciezki topor wyraznie spowalnia ruchy twojego zwierzecego wojownika","Pieniadze szczescia nie daja, ale nasza wuwuzela jak najbardziej. Naprawde. \" \n" +
            "                \"Jesli czujesz ze los ci sprzyja wuwuzela moze byc idealna bronia dla ciebie, poniewaz \" \n" +
            "                \"znacznie zwieksza szanse na zadanie krytycznych obrazen","Ktos moglby stwierdzic ze zbroja dla pingwina czy zyrafy to głupota. \" +\n" +
            "                \"Ktos prawdopodobnie ma racje, lecz czy jestes w stanie przejsc obojetnie wobec swietnych benefitow, \" \n" +
            "                \"jakie oferuje? Kup nasza zbroje juz dzis, a twoj pasek zycia znaczaco sie powiekszy.\" \n" +
            "                \"Giermek sprzedawany oddzielnie."};


    public static void main(String[] args) {
        DzienneZoo zoo = DzienneZoo.getInstance();
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.POWIETRZNY, wielkosc_wybiegu_enum.SREDNI));
        zoo.dodajWybieg(new Wybieg_podstawowy(rodzaj_srodowiska_enum.POWIETRZNY, wielkosc_wybiegu_enum.DUZY));
        zoo.getListaWybiegow().getLast().dodaj_zwierze(new Zwierze("1", 100, 100, 1, 1, 1, rodzaj_srodowiska_enum.POWIETRZNY));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(new Zwierze("1", 100, 100, 1, 1, 1, rodzaj_srodowiska_enum.POWIETRZNY));
        zoo.getListaWybiegow().getFirst().dodaj_zwierze(new Zwierze("1", 100, 100, 1, 1, 1, rodzaj_srodowiska_enum.POWIETRZNY));
        Sklep sklep = new Sklep(zoo);

        Przedmiot Topor = przedmioty_enum.TOPOR.stworzPrzedmiot();
        sklep.kup_bron(Topor);

        new MainFrame().createAndShowGUI();

    }

    private void createAndShowGUI() {
        frame = new JFrame("Loading Screen Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 820);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        // Dodatkowy panel do centrowania tytułu
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        titleLabel = new JLabel("KONIEC DNIA");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(0, 0, 0, 0));

        centerPanel.add(titleLabel);

        messagePane = new JTextPane();
        messagePane.setEditable(false);
        messagePane.setContentType("text/html");
        messagePane.setFont(new Font("Arial", Font.PLAIN, 16));
        messagePane.setBackground(UIManager.getColor("Panel.background"));
        messagePane.setPreferredSize(new Dimension(1280, 50));

        mainPanel.add(centerPanel, BorderLayout.CENTER); // Dodanie centerPanel zamiast titleLabel
        mainPanel.add(messagePane, BorderLayout.SOUTH); // Możesz dostosować położenie wiadomości

        frame.add(mainPanel);
        startMessageAnimation();
        BusinessLogic1.startLoading(DzienneZoo.getInstance());

        frame.setVisible(true);
        frame.setResizable(false);
    }


    private void startMessageAnimation() {
        //kod sie powtarza az tekst nie bedzie 1f, czyli w pelni widoczni
        Timer messageTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tytulWidzialny) {
                    currentMessageIndex = new Random().nextInt(messages.length);
                    messagePane.setText("<html><center>" + messages[currentMessageIndex] + "</center></html>");


                    //kod sie powtarza az tekst nie bedzie 1f, czyli w pelni widoczni
                    Timer fadeInTimer = new Timer(10, new ActionListener() {
                        float alpha = 0f;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            alpha += 0.02f;
                            titleLabel.setForeground(new Color(0, 0, 0, Math.min(1f, alpha)));

                            if (alpha >= 1f) {
                                ((Timer) e.getSource()).stop();
                                tytulWidzialny = true;
                            }
                        }
                    });

                    fadeInTimer.start();
                }
            }
        });
        messageTimer.start();
    }
}
