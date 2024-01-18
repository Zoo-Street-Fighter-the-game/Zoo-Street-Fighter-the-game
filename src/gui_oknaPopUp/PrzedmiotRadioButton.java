package gui_oknaPopUp;

import enumy.przedmioty_enum;

import javax.swing.*;

public class PrzedmiotRadioButton extends JRadioButton {

    private final przedmioty_enum typ;

    public PrzedmiotRadioButton(String str, przedmioty_enum typ)
    {
        this.setText(str +" "+ typ.getCena() +"$");
        this.typ = typ;
    }



    public przedmioty_enum getTyp() {
        return typ;
    }
}