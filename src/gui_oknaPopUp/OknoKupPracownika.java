package gui_oknaPopUp;

import pakiet_sklep.Sklep;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class OknoKupPracownika extends JFrame implements ActionListener, ChangeListener {
    private final JTextField imieField;
    private final JTextField nazwiskoField;
    private final JSlider jakoscSlider;
    private final JButton kupButton;
    private final Sklep sklepik;
    private final JLabel buttonLabel;
    private final String [] imiona = {"Michał", "Maciej", "Dawid", "Krzysztof", "Jan", "Kamil", "Dominik", "Robert", "Franciszek", "Pankracy", "Paweł", "Szymon", "Lech", "Aleksander", "Janusz", "Miłosz", "Mikołaj", "Jakub", "Kryspin", "Krystian", "Daniel", "Ełzebiusz", "Damian", "Ignacy", "Grzegorz", "Julian", "Dariusz", "Mariusz", "Szczepan", "Władysław", "Hubert", "Filip", "Przemek", "Eryk"};
    private final String [] nazwiska ={"Grzyb", "Kowlaski", "Nowak", "Wiśniewski", "Kamiński", "Lewandowski", "Zieliński", "Szymański", "Mazur", "Dąbrowski", "Kwiatkowski", "Jankowski", "Krawczyk", "Kaczmarek", "Zając", "Król", "Wojsciechowski", "Bielczyk", "Glik", "Szczęsny", "Błaszczykowski", "Krychowiak", "Piszczek", "Wawrzyniak", "Fabiański", "Kędziora", "Pazdan", "Puchacz", "Balcerowski", "Ponitka", "Sochan", "Podziemski", "Pacura", "Chmiel", "Tarlicki", "Kraniecki"};
    public OknoKupPracownika(Sklep sklep) {
        this.sklepik = sklep;
        Random gen = new Random();

        JLabel text = new JLabel("Kup Pracownika");
        this.setTitle("Kup Pracownika");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.add(text);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(0,1));

        // Utworzenie pól tekstowych
        imieField = new JTextField(imiona[gen.nextInt(imiona.length)],15);
        panel.add(new JLabel("  Imie:"));
        panel.getComponent(panel.getComponentCount()-1).setFont(new Font(null, Font.ITALIC, 20));
        panel.add(imieField);

        nazwiskoField = new JTextField(nazwiska[gen.nextInt(nazwiska.length)],15);
        panel.add(new JLabel("  Nazwisko:"));
        panel.getComponent(panel.getComponentCount()-1).setFont(new Font(null, Font.ITALIC, 20));
        panel.add(nazwiskoField);

        // Utworzenie suwaka
        jakoscSlider = new JSlider(1, 10);
        jakoscSlider.setMajorTickSpacing(1);
        jakoscSlider.setPaintTicks(true);
        jakoscSlider.setPaintLabels(true);
        jakoscSlider.addChangeListener(this);

        panel.add(new JLabel("  Jakosc uslug"));
        panel.add(jakoscSlider);

        // Utworzenie przycisku
        kupButton = new JButton("Kup Pracownika");
        buttonLabel = new JLabel(String.valueOf(jakoscSlider.getValue()*sklepik.getCenaPracownika()));
        buttonLabel.setIcon(new ImageIcon("src/ikony/IkonaMonety.png"));
        buttonLabel.setFont(new Font(null, Font.PLAIN, 24));
        buttonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(buttonLabel);
        panel.add(kupButton);
        kupButton.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==kupButton)
        {
            sklepik.kup_pracownika(imieField.getText(),nazwiskoField.getText(),jakoscSlider.getValue());
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==jakoscSlider)
        {
            buttonLabel.setText(String.valueOf(jakoscSlider.getValue()*sklepik.getCenaPracownika()));
        }
    }
    public static void brakSrodkow()
    {
        JOptionPane.showMessageDialog(null,
                "Nie masz wystarczajaco duzo monet!",
                "Brak Srodkow",
                JOptionPane.ERROR_MESSAGE);
    }
}