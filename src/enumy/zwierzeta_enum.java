package enumy;

import Klasy_Zwierzat.Zwierze;

public enum zwierzeta_enum { //przy tworzeniu nowych zwierzat, trzeba dodac opcje do sklepu
    PINGWIN {
        @Override
        public int getCena() {
            return 10;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Pingwin", 20, 10, 10,50,3, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    NIEDZWIEDZ {
        @Override
        public int getCena() {
            return 10;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Niedźwiedź", 20, 10, 10,50,20, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    LOS {
        @Override
        public int getCena() {
            return 10;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Łoś", 10, 10,10,50, 10, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    };

    public abstract Zwierze stworzZwierze(String imie);

    public abstract int getCena();

}