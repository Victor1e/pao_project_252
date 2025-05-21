package Entitati;
import java.time.LocalDateTime;

public class Concert {
    private Artist artist;
    private Scena scena;
    private LocalDateTime start;
    private LocalDateTime end;

    public Concert(Artist artist, Scena scena, LocalDateTime start, LocalDateTime end) {
        this.artist = artist;
        this.scena = scena;
        this.start = start;
        this.end = end;
    }
    public Artist getArtist(){
        return artist;
    }
    public Scena getScena(){
        return scena;
    }
    public LocalDateTime getStart(){
        return start;
    }
    public LocalDateTime getEnd(){
        return end;
    }
    public void setArtist(Artist artist){
        this.artist=artist;
    }
    public void setScena(Scena scena){
        this.scena=scena;
    }
    public void setStart(LocalDateTime start){
        this.start=start;
    }
    public void setEnd(LocalDateTime end){
        this.end=end;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "artist=" + artist.getNume() +
                ", scena=" + scena.getNume() +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
