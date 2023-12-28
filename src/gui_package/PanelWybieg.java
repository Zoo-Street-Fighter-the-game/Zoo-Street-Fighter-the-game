package gui_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelWybieg extends JPanel implements ActionListener {

    private JLabel wybiegLabel = new JLabel();
    private JButton wybiegButton = new JButton("Tutaj text do przycisku");
    public PanelWybieg()
    {
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(200, 200));
        this.add(wybiegLabel);
        wybiegLabel.setText("Wybieg");
        wybiegButton.setFocusable(false);
        this.add(wybiegButton);
        wybiegButton.addActionListener(this);

    }


    public JLabel getWybiegLabel() {
        return wybiegLabel;
    }

    public JButton getWybiegButton() {
        return wybiegButton;
    }

    //ACTION LISTENER NA WYBIEGBUTTON
    @Override
    public void actionPerformed(ActionEvent e) {
        if(getParent() instanceof PanelDzienWybiegi) {
            ((PanelDzienWybiegi) getParent()).dodajWybieg();
            //System.out.println("Dziala");
        }
    }
}
