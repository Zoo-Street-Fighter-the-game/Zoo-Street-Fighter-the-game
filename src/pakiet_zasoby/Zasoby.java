package pakiet_zasoby;
public class Zasoby {
    private int exp;
    private int monety;
    private int jedzenie;

    public Zasoby(int exp, int monety, int jedzenie) {
        this.exp = exp;
        this.monety = monety;
        this.jedzenie = jedzenie;
    }

    public Zasoby() {
        exp=0;
        monety=1000000;
        jedzenie=1000;
    }

    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public int getMonety() {
        return monety;
    }
    public void setMonety(int monety) {
        this.monety = monety;
    }
    public int getJedzenie() {
        return jedzenie;
    }
    public void setJedzenie(int jedzenie) {
        this.jedzenie = jedzenie;
    }

    public void dodajExp(int x) {
        exp = exp+x;
    }

    public void zmienMonety(int x) {
        monety = monety+x;
    }

    public void zmienJedzenie(int x) {
        jedzenie = jedzenie+x;
    }

    public String toString(){
        return "Twoje zasoby: \n Exp: "+getExp()+" , Monety: "+getMonety()+" , Jedzenie: "+ getJedzenie();
    }
}