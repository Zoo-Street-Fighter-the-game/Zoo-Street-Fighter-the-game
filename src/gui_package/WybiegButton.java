package gui_package;

import javax.swing.*;
import java.awt.*;

public class WybiegButton extends JButton {

    public WybiegButton(String str)
    {
        this.setText(str);
        this.setPreferredSize(new Dimension(75,75));
        this.setFont(new Font(null, Font.PLAIN, 8));
    }
}
