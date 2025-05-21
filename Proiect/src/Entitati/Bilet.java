package Entitati;

public class Bilet {
    private String cod;
    private Festival festival;
    private double pret;

    public Bilet(String cod, Festival festival, double pret){
        this.cod = cod;
        this.festival = festival;
        this.pret = pret;
    }
    public String getCod(){
        return cod;
    }

    public Festival getFestival(){
        return festival;
    }
    public double getPret(){
        return pret;
    }
    public void setCod(String cod){
        this.cod=cod;
    }

    public void setFestival(Festival festival){
        this.festival=festival;
    }
    public void setPret(double pret){
        this.pret=pret;
    }
    @Override
    public String toString(){
        return "Bilet{" +
                "cod='" + cod + '\'' +
                ", festival=" + festival.getNume() +
                ", pret=" + pret +
                '}';
    }
}
