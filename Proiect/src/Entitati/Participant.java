package Entitati;
import java.util.List;
public class Participant extends Persoana{
    private int varsta;
    private List<Bilet>bilete;

    public Participant(String nume, String email,Integer varsta, List<Bilet> bilete){
        super(nume,email);
        this.bilete=bilete;
        this.varsta=varsta;
    }
    public int getVarsta() {
        return varsta;
    }
    public List<Bilet> getBilete(){
        return bilete;
    }
    public void setBilete(List<Bilet> bilete){
        this.bilete=bilete;
    }
    @Override
    public String toString() {
        return "Participant{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", varsta=" + varsta +
                ", bilete=" + bilete +
                '}';
    }
}
