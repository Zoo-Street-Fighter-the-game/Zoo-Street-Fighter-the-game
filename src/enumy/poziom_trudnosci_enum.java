package enumy;

public enum poziom_trudnosci_enum {
    LATWY(0.5),
    SREDNI(1),
    TRUDNY(1.5);

    private final double mnoznik;
    poziom_trudnosci_enum(double mnoznik) {
        this.mnoznik = mnoznik;
    }
    public double getMnoznik(){
        return mnoznik;
    }

}
