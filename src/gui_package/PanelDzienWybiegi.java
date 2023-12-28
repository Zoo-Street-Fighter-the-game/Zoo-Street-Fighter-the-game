package gui_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDzienWybiegi extends JPanel {

    public PanelDzienWybiegi()
    {
        this.setBackground(Color.lightGray);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.add(new PanelWybieg());

    }

    public void dodajWybieg()
    {
        this.add(new PanelWybieg());
        this.repaint();
        this.revalidate();
        getParent().repaint();
        getParent().revalidate();
        System.out.println("Dziala");
    }
}
