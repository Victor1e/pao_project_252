package Entitati;
import java.time.LocalDate;
import java.util.List;

public class Festival {
    private String nume;
    private String locatie;
    private LocalDate dataInceput;
    private LocalDate dataSfarsit;
    private List<Scena> scene;

    public Festival( String nume, String locatie, LocalDate dataInceput, LocalDate dataSfarsit, List<Scena> scene){
        this.nume=nume;
        this.locatie=locatie;
        this.dataInceput=dataInceput;
        this.dataSfarsit=dataSfarsit;
        this.scene=scene;
    }

    public String getNume(){
        return nume;
    }
    public String getLocatie(){
        return locatie;
    }
    public LocalDate getDataInceput(){
        return dataInceput;
    }
    public LocalDate getDataSfarsit(){
        return dataSfarsit;
    }
    public List<Scena>getScene(){
        return scene;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public void setLocatie(String locatie){
        this.locatie=locatie;
    }
    public void setDataInceput(LocalDate dataInceput){
        this.dataInceput=dataInceput;
    }
    public void setDataSfarsit(LocalDate dataSfarsit){
        this.dataSfarsit=dataSfarsit;
    }
    public void setScene(List<Scena>scene){
        this.scene=scene;
    }

    @Override
    public String toString(){
        return "Festival{" +
                "nume='" + nume + '\'' +
                ", locatie='" + locatie + '\'' +
                ", dataInceput=" + dataInceput +
                ", dataSfarsit=" + dataSfarsit +
                ", scene=" + scene +
                '}';
    }
}
