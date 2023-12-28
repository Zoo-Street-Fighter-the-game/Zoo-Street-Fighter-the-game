package gui_package;

import javax.swing.*;
import java.awt.*;

public class PanelDzienWybiegi extends JPanel {

    public PanelDzienWybiegi()
    {
        this.setBackground(Color.lightGray);
        this.setLayout(new FlowLayout());
        this.add(new PanelWybieg());

    }
}
