package enumy;
import Przedmioty.Przedmiot;
public enum przedmioty_enum {
    ADIDASY{
        @Override
        public Przedmiot stworzPrzedmiot(){
            return new Przedmiot("Adidasy",
                    "Chcesz zeby twoje zwierze pedzilo jak wiatr? Adidasy zapewniaja duzy bonus do szybkosci. " +
                            "Byc moze myslisz sobie; ale moje zwierze ma pletwy :( Nic nie szkodzi! Nasz produkt nie dyskryminuje " +
                            "i umozliwia osiagniecie zawrotnej predkosci wszystkim istotom duzym i malym.",
                    60+((int)(Math.random()*15)),
                    5+((int)(Math.random()*5)),
                    20+((int)(Math.random()*5)),
                    80+((int)(Math.random()*20)),
                    10+((int)(Math.random()*20)));
        }
    },
    MIECZ{
        @Override
        public Przedmiot stworzPrzedmiot(){
            return new Przedmiot("Miecz",
                    "Może nie jest to ekskalibur, ale z twoich zwierząt też żaden król Artur. " +
                            "Miecz zapewnia hojny bonus do obrażeń w walkach zwierząt. Wnoszenie broni białej na arenę walk" +
                            " nie jest prawdopodobnie najuczciwszym zagraniem, ale takie już prawa miejskiej dżungli.",
                    55+((int)(Math.random()*20)),
                    85+((int)(Math.random()*15)),
                    10+((int)(Math.random()*10)),
                    30+((int)(Math.random()*5)),
                    4);
        }
    },
    PATYK{
        @Override
        public Przedmiot stworzPrzedmiot(){
            return new Przedmiot("Patyk",
                    "Uwaga: Przedmiot ten powinien być używany jedynie w celach humorystycznych " +
                            "i nie jest przeznaczony do poważnych starć. W przypadku konfrontacji z potężniejszymi przeciwnikami, " +
                            "zdecydowanie zaleca się ulepszenie do bardziej przyzwoitej broni.",
                    5+((int)(Math.random()*10)),
                    5+((int)(Math.random()*4)),
                    2+((int)(Math.random()*3)),
                    3+((int)(Math.random()*7)),
                    0);
        }
    },
    PISTOLET_NA_WODE{
        @Override
        public Przedmiot stworzPrzedmiot(){
            int cena = 120+((int)(Math.random()*20));
            return new Przedmiot("Pistolet na wodę",
                    "Pistolet na wodę to świetny wybór dla wszystkich Wodnych zwierząt, które z reguły mają trudniej " +
                            "w walkach zwierząt. #LifeIsBrutal Już dziś odzyskaj przewagę nad przeciwnikami i zamień arenę w " +
                            "kolejną Atlantydę już dziś, tylko za " + cena,
                    cena,
                    55+((int)(Math.random()*20)),
                    20+((int)(Math.random()*10)),
                    55+((int)(Math.random()*20)),
                    60+((int)(Math.random()*30)));
        }
    },
    REKAWICE_BOKSERSKIE{
        @Override
        public Przedmiot stworzPrzedmiot(){
            return new Przedmiot("Rekawice bokserskie",
                    "Rekawice bokserskie zwiększają siłę ciosów twojego zwierzęcia, lecz także umożliwiają mu trzymanie gardy " +
                            "(chociaż podobno najlepsza obrona jest atak)",
                    150+((int)(Math.random()*10)),
                    70+((int)(Math.random()*20)),
                    15,
                    20+((int)(Math.random()*10)),
                    30+((int)(Math.random()*20)));
        }
    },
    TOPOR{
        @Override
        public Przedmiot stworzPrzedmiot(){
            return new Przedmiot("Topór",
                    "Ten topór to dzieło ekscentrycznego kowala, który postanowił połączyć styl z funkcjonalnością " +
                            "w najbardziej absurdalny sposób. Obrażenia, które zadaje są potężne, jednak z wielką mocą przychodzi wielka " +
                            "odpowiedzialność i ciężki topór wyraźnie spowalnia ruchy twojego zwierzęcego wojownika",
                    55+((int)(Math.random()*20)),
                    140+((int)(Math.random()*15)),
                    5+((int)(Math.random()*5)),
                    -30+((int)(Math.random()*5)),
                    5);
        }
    },
    WUWUZELA{
        @Override
        public Przedmiot stworzPrzedmiot(){
            return new Przedmiot("Wuwuzela",
                    "Pieniądze szczęścia nie dają, ale nasza wuwuzela jak najbardziej. Naprawdę. " +
                            "Jeśli czujesz, że los Ci sprzyja, wuwuzela może być idealną bronią dla Ciebie, ponieważ " +
                            "znacznie zwiększa szanse na zadanie krytycznych obrażeń",
                    140+((int)(Math.random()*20)),
                    55+((int)(Math.random()*5)),
                    70+((int)(Math.random()*20)),
                    35+((int)(Math.random()*7)),
                    10+((int)(Math.random()*15)));
        }
    },
    ZBROJA{
        @Override
        public Przedmiot stworzPrzedmiot(){
            return new Przedmiot("Zbroja",
                    "Ktoś mógłby stwierdzić, że zbroja dla pingwina czy żyrafy to głupota. " +
                            "Ktoś prawdopodobnie ma rację, lecz czy jesteś w stanie przejść obojętnie wobec świetnych benefitów, " +
                            "jakie oferuje? Kup naszą zbroję już dziś, a twój pasek życia znacząco się powiększy. " +
                            "Giermek sprzedawany oddzielnie.",
                    50+((int)(Math.random()*20)),
                    30+((int)(Math.random()*10)),
                    2,
                    -20+((int)(Math.random()*4)),
                    50+((int)(Math.random()*10)));
        }
    };










    public abstract Przedmiot stworzPrzedmiot();
}
