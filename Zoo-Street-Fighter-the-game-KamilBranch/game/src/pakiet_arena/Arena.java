public class Arena {
    private int temperatura;
    private int zalesienie;
    private int wodyPowierzchniowe;
    private int wiatr;
    private int naslonecznienie;

    public Arena(int temperatura, int zalesienie, int wodyPowierzchniowe, int wiatr, int naslonecznienie) {
        this.temperatura = temperatura;
        this.zalesienie = zalesienie;
        this.wodyPowierzchniowe = wodyPowierzchniowe;
        this.wiatr = wiatr;
        this.naslonecznienie = naslonecznienie;
    }

    public Arena(){
        temperatura=(int)(Math.random()*60-30+1);
        zalesienie=(int)(Math.random()*100);
        wodyPowierzchniowe=(int)(Math.random()*100);
        wiatr=(int)(Math.random()*100);
        naslonecznienie=(int)(Math.random()*100);
    }

    // getery i setery
    public int getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    public int getZalesienie() {
        return zalesienie;
    }
    public void setZalesienie(int zalesienie) {
        this.zalesienie = zalesienie;
    }
    public int getWodyPowierzchniowe() {
        return wodyPowierzchniowe;
    }
    public void setWodyPowierzchniowe(int wodyPowierzchniowe) {
        this.wodyPowierzchniowe = wodyPowierzchniowe;
    }
    public int getWiatr() {
        return wiatr;
    }
    public void setWiatr(int wiatr) {
        this.wiatr = wiatr;
    }
    public int getNaslonecznienie() {
        return naslonecznienie;
    }
    public void setNaslonecznienie(int naslonecznienie) {
        this.naslonecznienie = naslonecznienie;
    }

    //getStan

    @Override
    public String toString() {
        return "Arena: " +
                "temperatura=" + temperatura +
                ", zalesienie=" + zalesienie +
                ", wodyPowierzchniowe=" + wodyPowierzchniowe +
                ", wiatr=" + wiatr +
                ", naslonecznienie=" + naslonecznienie;
    }
}