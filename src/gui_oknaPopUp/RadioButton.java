package gui_oknaPopUp;

import Klasy_Zwierzat.Zwierze;

import javax.swing.*;

public class RadioButton extends JRadioButton {
    private Zwierze zwierze;
    public RadioButton(String str, Zwierze zwierze){
        this.setText(str +" "+ zwierze.getImie());
        this.zwierze = zwierze;
    }


}
