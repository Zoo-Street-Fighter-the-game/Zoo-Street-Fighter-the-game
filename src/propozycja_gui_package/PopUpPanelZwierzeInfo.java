package propozycja_gui_package;

import Klasy_Zwierzat.Zwierze;

import javax.swing.*;
import java.awt.*;

public  class PopUpPanelZwierzeInfo{
    public static void showZwierzeInfo(JFrame parent, Zwierze zwierze) {
        JOptionPane.showMessageDialog(parent,
                "Informacje o zwierzaku:\n" +
                        "Nazwa: " + zwierze.getNazwa() + "\n" +
                        "Zycie: " + zwierze.getZycie() + " \n" +
                        "Sila: " + zwierze.getSila() + " \n" +
                        "Wielkosc: " + zwierze.getWielkosc() + " \n" +
                        "Glod: " + zwierze.getWskaznik_glodu() + " \n" +
                        "Przezyte dni: " + zwierze.getPrzezyte_dni() + " \n" +
                        "Rodzaj " + zwierze.getRodzaj() + " \n");
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame && window != parent) {
                window.dispose(); // Zamknij wszystkie ramki oprócz bieżącej
            }
        }
    }

}