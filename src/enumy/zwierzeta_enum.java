package enumy;

import Klasy_Zwierzat.Zwierze;

public enum zwierzeta_enum { //przy tworzeniu nowych zwierzat, trzeba dodac opcje do sklepu
    PINGWIN {
        @Override
        public int podajCene() {
            return 10;
        }
        @Override
        public Zwierze stworzZwierze() {
            return new Zwierze("Pingwin", 20, 10, 4, 5, 10, rodzaj_srodowiska_enum.WODNY);
        }
    },
    NIEDZWIEDZ {
        @Override
        public int podajCene() {
            return 10;
        }
        @Override
        public Zwierze stworzZwierze() {
            return new Zwierze("Niedźwiedź", 20, 10, 1, 1, 10, rodzaj_srodowiska_enum.LADOWY);
        }
    },
    LOS {
        @Override
        public int podajCene() {
            return 10;
        }

        @Override
        public Zwierze stworzZwierze() {
            return new Zwierze("Łoś", 1, 1, 10, 1, 10, rodzaj_srodowiska_enum.LADOWY);
        }
    };

    public abstract Zwierze stworzZwierze();

    public abstract int podajCene();
}
