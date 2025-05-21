package Entitati;
import java.util.List;

public class Artist {
    private String nume;
    private String genMuzical;
    private String taraOrigine;
    private List<Concert> concerte;

    public Artist(String nume, String genMuzical, String taraOrigine, List<Concert> concerte) {
        this.nume=nume;
        this.genMuzical=genMuzical;
        this.taraOrigine=taraOrigine;
        this.concerte=concerte;
    }
    public String getNume(){
        return nume;
    }
    public String getGenMuzical(){
        return genMuzical;
    }
    public String getTaraOrigine(){
        return taraOrigine;
    }
    public List<Concert> getConcerte(){
        return concerte;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public void setGenMuzical(String genMuzical){
        this.genMuzical=genMuzical;
    }
    public void setTaraOrigine(String taraOrigine){
        this.taraOrigine=taraOrigine;
    }
    public void setConcerte(List<Concert> concerte){
        this.concerte = concerte;
    }
    @Override
    public String toString(){
        return "Artist{" +
                "nume='" + nume + '\'' +
                ", genMuzical='" + genMuzical + '\'' +
                ", taraOrigine='" + taraOrigine + '\'' +
                ", concerte=" + concerte +
                '}';
    }
}
