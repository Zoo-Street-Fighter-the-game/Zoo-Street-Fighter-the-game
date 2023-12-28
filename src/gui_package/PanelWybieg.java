package gui_package;

import javax.swing.*;
import java.awt.*;

public class PanelWybieg extends JPanel {

    private JLabel wybiegLabel = new JLabel();

    private JButton wybiegButton = new JButton("Tutaj text do przycisku");
    public PanelWybieg()
    {
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(200, 200));
        this.add(wybiegLabel);
        wybiegLabel.setText("Wybieg");
        this.add(wybiegButton);

    }

    public JLabel getWybiegLabel() {
        return wybiegLabel;
    }

    public JButton getWybiegButton() {
        return wybiegButton;
    }
}
