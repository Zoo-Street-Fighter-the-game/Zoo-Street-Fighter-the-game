package enumy;

import Klasy_Zwierzat.Zwierze;

public enum zwierzeta_enum { //przy tworzeniu nowych zwierzat, trzeba dodac opcje do sklepu

    //===========================================================================
    //ZWIERZETA WODNE
    //===========================================================================
    PINGWIN {
        @Override
        public int getCena() {
            return 30;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Pingwin", 150, 25, 55,50,4, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    ZOLW {
        @Override
        public int getCena() {
            return 30;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Żółw", 330, 20, 15,50,4, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    REKIN {
        @Override
        public int getCena() {
            return 70;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Rekin", 135, 50, 35,50,7, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    ORKA {
        @Override
        public int getCena() {
            return 90;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Orka", 150, 70,30,50, 10, 50, this.getCena(), rodzaj_srodowiska_enum.WODNY);
        }
    },
    //===========================================================================
    //ZWIERZETA LADOWE
    //===========================================================================
    NIEDZWIEDZ {
        @Override
        public int getCena() {
            return 90;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Niedźwiedź", 300, 75,30,50, 6, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    LOS {
        @Override
        public int getCena() {
            return 70;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Łoś", 265, 35,50,50, 6, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    NIEDZWIEDZ_POLARNY {
        @Override
        public int getCena() {
            return 80;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"NiedźwiedźPolarny", 300, 80, 20,50,7, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    LEW {
        @Override
        public int getCena() {
            return 70;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Lew", 300, 70,40,50, 6, 50, this.getCena(), rodzaj_srodowiska_enum.LADOWY);
        }
    },
    //===========================================================================
    //ZWIERZETA POWIETRZNE
    //===========================================================================
    ORZEL {
        @Override
        public int getCena() {
            return 60;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Orzeł", 250, 50, 70,50,5, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    },
    PAPUGA {
        @Override
        public int getCena() {
            return 30;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Papuga", 150, 5,80,50, 3, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    },
    PAW {
        @Override
        public int getCena() {
            return 20;
        }
        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Paw", 200, 10, 40,50,4, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    },
    NIETOPERZ {
        @Override
        public int getCena() {
            return 30;
        }

        @Override
        public Zwierze stworzZwierze(String imie) {
            return new Zwierze(imie,"Nietoperz", 100, 15,75,50, 3, 50, this.getCena(), rodzaj_srodowiska_enum.POWIETRZNY);
        }
    };
    public abstract Zwierze stworzZwierze(String imie);

    public abstract int getCena();

}