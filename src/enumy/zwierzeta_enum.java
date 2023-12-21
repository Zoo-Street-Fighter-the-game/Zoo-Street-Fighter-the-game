package enumy;

import Klasy_Zwierzat.Zwierze;
import Klasy_Zwierzat.ZwierzeLadowe;
import Klasy_Zwierzat.ZwierzeWodne;

public enum zwierzeta_enum {
    PINGWIN {
        @Override
        public Zwierze stworzZwierze() {
            return new ZwierzeLadowe("Pingwin", 20, 10, 4, 5, 2, 1, 0);
        }
    },
    NIEDZWIEDZ {
        @Override
        public Zwierze stworzZwierze() {
            return new ZwierzeWodne("Niedźwiedź", 20, 10, 1, 1, 1, 1, 1);
        }
    },
    LOS {
        @Override
        public Zwierze stworzZwierze() {
            return new ZwierzeWodne("Łoś", 1, 1, 1, 1, 1, 1, 1);
        }
    };

    public abstract Zwierze stworzZwierze();
}
