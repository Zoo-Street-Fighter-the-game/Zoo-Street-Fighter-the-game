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
            return new Zwierze("Kowalski","Pingwin", 20, 10, 10,10,4, 10,5, 5,10, 10, rodzaj_srodowiska_enum.WODNY);
        }
    },
    NIEDZWIEDZ {
        @Override
        public int podajCene() {
            return 10;
        }
        @Override
        public Zwierze stworzZwierze() {
            return new Zwierze("XYZ", "Niedźwiedź", 20, 10, 2,2,5, 1, 10,3,10,10, rodzaj_srodowiska_enum.LADOWY);
        }
    },
    LOS {
        @Override
        public int podajCene() {
            return 10;
        }

        @Override
        public Zwierze stworzZwierze() {
            return new Zwierze("YZX","Łoś", 1, 1, 1,2,10, 5,5,5,1, 10, rodzaj_srodowiska_enum.LADOWY);
        }
    };

    public abstract Zwierze stworzZwierze();

    public abstract int podajCene();
}
