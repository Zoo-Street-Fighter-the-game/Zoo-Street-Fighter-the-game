package gui_oknaPopUp;

import enumy.zwierzeta_enum;

import javax.swing.*;

public class ZwierzeRadioButton extends JRadioButton {

    private zwierzeta_enum typ;

    public ZwierzeRadioButton(String str, zwierzeta_enum typ)
    {
        this.setText(str);
        this.typ = typ;
    }

    public zwierzeta_enum getTyp() {
        return typ;
    }
}
