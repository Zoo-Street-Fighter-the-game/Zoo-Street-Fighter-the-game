package enumy;

public enum wielkosc_wybiegu_enum {
    MALY(10),
    SREDNI(20),
    DUZY(30);

    private final int liczbowa_wielkosc;
    wielkosc_wybiegu_enum(int liczbowa_wielkosc){
        this.liczbowa_wielkosc = liczbowa_wielkosc;
    }
    public int getLiczbowa_Wielkosc_Wybiegu(){
        return liczbowa_wielkosc;
    }
}