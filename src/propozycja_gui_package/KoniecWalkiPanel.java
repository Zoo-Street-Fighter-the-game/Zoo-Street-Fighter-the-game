package propozycja_gui_package;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class KoniecWalkiPanel extends JDialog {
    public KoniecWalkiPanel(boolean isWinner,String lostAnimalType) {
        setTitle("Koniec walki");
        setSize(400, 400);
        setLayout(new BorderLayout());
        if(isWinner){

            JLabel resultLabel = new JLabel("Wygrales! Przeciwnik teraz placze");
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
            add(resultLabel, BorderLayout.NORTH);

        }else{
            JLabel resultLabel = new JLabel("Przegrales! Twój zwierzak placze");
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
            add(resultLabel, BorderLayout.NORTH);

        }

 ;

        // Construct the path to the corresponding GIF based on the lost animal type
        String gifPath = "/obrazki/" + lostAnimalType + "Dead.gif";

        ImageIcon gifIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(gifPath)));
        JLabel gifLabel = new JLabel(gifIcon);
        add(gifLabel, BorderLayout.CENTER);


        JButton closeButton = new JButton("WROC DO DNIA");
        //trzeba dodac powrot do dnia
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
