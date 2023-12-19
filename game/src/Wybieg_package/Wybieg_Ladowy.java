package Wybieg_package;

import enumy.rodzaj_srodowiska_enum;
import enumy.wielkosc_wybiegu_enum;

public class Wybieg_Ladowy extends Wybieg_podstawowy{

    public Wybieg_Ladowy( wielkosc_wybiegu_enum wielkosc_wybiegu) {
        super(rodzaj_srodowiska_enum.LĄDOWY, wielkosc_wybiegu);
    }
}
