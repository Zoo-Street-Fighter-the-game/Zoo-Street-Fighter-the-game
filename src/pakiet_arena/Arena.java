package pakiet_arena;




public class Arena {
    private int temperatura;
    private int zalesienie;
    private int wodyPowierzchniowe;
    private int wiatr;
    private int naslonecznienie;


    public Arena(){
        this.temperatura=(int)((Math.random()*100)-50);
        this.zalesienie=(int)(Math.random()*100);
        this.wodyPowierzchniowe=(int)(Math.random()*100);
        this.wiatr=(int)(Math.random()*100);
        this.naslonecznienie=(int)(Math.random()*100);
    }




    // getery i setery
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
    public int getTemperatura() {return temperatura;}
    public void setTemperatura(int temperatura) {this.temperatura = temperatura;}

//getStan

    @Override
    public String toString() {
        return "Arena: " +
                ", temperatura=" + temperatura +
                ", zalesienie=" + zalesienie +
                ", wodyPowierzchniowe=" + wodyPowierzchniowe +
                ", wiatr=" + wiatr +
                ", naslonecznienie=" + naslonecznienie;
    }
}