package enumy;

import Klasy_Zwierzat.Zwierze;

public enum zwierzeta_enum { //przy tworzeniu nowych zwierzat, trzeba dodac opcje do sklepu

    //===========================================================================
    //ZWIERZETA WODNE
    //===========================================================================
    PINGWIN {
        @Override
        public int getCena() {
            return 3;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Pingwin", 25, 25, 55,50,4, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    ZOLW {
        @Override
        public int getCena() {
            return 3;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Żółw", 75, 20, 15,50,4, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    REKIN {
        @Override
        public int getCena() {
            return 7;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Rekin", 60, 50, 35,50,7, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    ORKA {
        @Override
        public int getCena() {
            return 9;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Orka", 80, 70,30,50, 10, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    //===========================================================================
    //ZWIERZETA LADOWE
    //===========================================================================
    NIEDZWIEDZ {
        @Override
        public int getCena() {
            return 9;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Niedźwiedź", 75, 75,30,50, 6, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    LOS {
        @Override
        public int getCena() {
            return 7;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Łoś", 65, 35,50,50, 6, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    NIEDZWIEDZ_POLARNY {
        @Override
        public int getCena() {
            return 8;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Niedźwiedź polarny", 80, 80, 20,50,7, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    LEW {
        @Override
        public int getCena() {
            return 7;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Lew", 60, 70,40,50, 6, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    //===========================================================================
    //ZWIERZETA POWIETRZNE
    //===========================================================================
    ORZEL {
        @Override
        public int getCena() {
            return 6;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Orzeł", 45, 50, 70,50,5, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    },
    PAPUGA {
        @Override
        public int getCena() {
            return 3;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Papuga", 15, 5,80,50, 3, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    },
    PAW {
        @Override
        public int getCena() {
            return 2;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Paw", 20, 10, 40,50,4, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    },
    NIETOPERZ {
        @Override
        public int getCena() {
            return 3;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Nietoperz", 10, 15,75,50, 3, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    };
    public abstract Zwierze stworzZwierze(String imie);

    public abstract int getCena();

}
