package Entitati;
import java.util.List;

public class Scena {
    private String nume;
    private int capacitate;
    private List<Concert> concerte;

    public Scena(String nume, int capacitate, List<Concert> concerte) {
        this.nume=nume;
        this.capacitate=capacitate;
        this.concerte=concerte;
    }

    public String getNume(){
        return nume;
    }
    public int getCapacitate(){
        return capacitate;
    }
    public List<Concert> getConcerte(){
        return concerte;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public void setCapacitate(int capacitate){
        this.capacitate=capacitate;
    }
    public void setConcerte(List<Concert>concerte){
        this.concerte=concerte;
    }

    @Override
    public String toString(){
        return "Scena{" +
                "nume='" + nume + '\'' +
                ", capacitate=" + capacitate +
                ", concerte=" + concerte +
                '}';
    }
}
