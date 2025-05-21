package Servicii;
import Entitati.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class FestivalService {
    private List<Festival> festivaluri = new ArrayList<>();
    private List<Artist> artisti = new ArrayList<>();
    private List<Participant> participanti = new ArrayList<>();
    private List<Organizator> organizatori = new ArrayList<>();
    private List<Concert> concerte = new ArrayList<>();

    public void adaugaFestival(Festival festival){
        festivaluri.add(festival);
    }
    public void adaugaArtist(Artist artist){
        artisti.add(artist);
    }
    public void adaugaParticipant(Participant participant){
        participanti.add(participant);
    }
    public void adaugaOrganizator(Organizator organizator) {
        organizatori.add(organizator);
    }
    public void adaugaConcert(Concert concert){
        concerte.add(concert);
    }
    public void afiseazaArtisti(){
        for (Artist artist : artisti) {
            System.out.println(artist);
        }
    }
    public void afiseazaFestivaluri(){
        for (Festival f : festivaluri) {
            System.out.println(f);
        }
    }
    public void afiseazaConcerte(){
        for (Concert c : concerte) {
            System.out.println(c);
        }
    }
    public Artist cautaArtistDupaNume(String nume){
        for (Artist artist : artisti){
            if (artist.getNume().equalsIgnoreCase(nume)){
                return artist;
            }
        }
        return null;
    }
    public Festival cautaFestivalDupaNume(String nume){
        for (Festival f : festivaluri){
            if (f.getNume().equalsIgnoreCase(nume)){
                return f;
            }
        }
        return null;
    }
    public List<Artist>getArtistiDupaGen(String gen){
        List<Artist> rezultat = new ArrayList<>();
        for (Artist a : artisti){
            if (a.getGenMuzical().equalsIgnoreCase(gen)){
                rezultat.add(a);
            }
        }
        return rezultat;
    }
    public List<Concert>getConcertePentruFestival(String numeFestival){
        List<Concert> rezultat = new ArrayList<>();
        for (Concert c : concerte) {
            if (c.getScena().getConcerte().contains(c) &&
                    c.getScena() != null &&
                    c.getScena().getNume() != null &&
                    c.getScena().getConcerte() != null) {
                Festival festival = cautaFestivalDupaScena(c.getScena());
                if (festival != null && festival.getNume().equalsIgnoreCase(numeFestival)) {
                    rezultat.add(c);
                }
            }
        }
        return rezultat;
    }
    public List<Concert>getConcertePentruArtist(String numeArtist){
        List<Concert> rezultat = new ArrayList<>();
        for (Concert c : concerte){
            if (c.getArtist().getNume().equalsIgnoreCase(numeArtist)) {
                rezultat.add(c);
            }
        }
        return rezultat;
    }
    private Festival cautaFestivalDupaScena(Scena scena){
        for (Festival f : festivaluri) {
            if (f.getScene().contains(scena)) {
                return f;
            }
        }
        return null;
    }
    public void afiseazaProgramFestival(String numeFestival){
        Festival f = cautaFestivalDupaNume(numeFestival);
        if (f == null) {
            System.out.println("Festivalul nu a fost găsit.");
            return;
        }
        System.out.println("Programul festivalului: " + f.getNume());
        for (Scena scena : f.getScene()) {
            System.out.println("Scena: " + scena.getNume());
            for (Concert c : scena.getConcerte()) {
                System.out.println("  " + c.getStart() + " - " + c.getEnd() +
                        ": " + c.getArtist().getNume());
            }
        }
    }
    public void afiseazaArtistiSortatiDupaNume() {
        List<Artist> sorted = new ArrayList<>(artisti);
        sorted.sort(Comparator.comparing(Artist::getNume));
        for (Artist a : sorted) {
            System.out.println(a);
        }
    }

}
