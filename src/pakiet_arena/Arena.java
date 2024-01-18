package pakiet_arena;




public class Arena {
    private final int temperatura;
    private final int zalesienie;
    private final int wodyPowierzchniowe;
    private final int wiatr;
    private final int naslonecznienie;


    public Arena(){
        this.temperatura=(int)((Math.random()*100)-50);
        this.zalesienie=(int)((Math.random()*100)+1);
        this.wodyPowierzchniowe=(int)((Math.random()*100)+1);
        this.wiatr=(int)((Math.random()*100)+1);
        this.naslonecznienie=(int)((Math.random()*100)+1);
    }




    // getery i setery
    public int getZalesienie() {
        return zalesienie;
    }
    public int getWodyPowierzchniowe() {
        return wodyPowierzchniowe;
    }
    public int getWiatr() {
        return wiatr;
    }
    public int getNaslonecznienie() {
        return naslonecznienie;
    }
    public int getTemperatura() {return temperatura;}

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