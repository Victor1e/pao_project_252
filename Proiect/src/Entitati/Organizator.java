package Entitati;
import java.util.List;
public class Organizator extends Persoana{
    private List<Festival> festivaluri;

    public Organizator(String nume, String email, List<Festival> festivaluri){
        super(nume, email);
        this.festivaluri = festivaluri;
    }
    public List<Festival> getFestivaluri(){
        return festivaluri;
    }
    public void setFestivaluri(List<Festival> festivaluri){
        this.festivaluri=festivaluri;
    }

    @Override
    public String toString(){
        return "Organizator{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", festivaluri=" + festivaluri +
                '}';
    }
}
