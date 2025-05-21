package Entitati;
import java.util.List;
public class Participant extends Persoana{
    private List<Bilet>bilete;

    public Participant(String nume, String email, List<Bilet> bilete){
        super(nume,email);
        this.bilete=bilete;
    }
    public List<Bilet> getBilete(){
        return bilete;
    }
    public void setBilete(List<Bilet> bilete){
        this.bilete=bilete;
    }
    @Override
    public String toString(){
        return "Participant{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", bilete=" + bilete +
                '}';
    }
}
