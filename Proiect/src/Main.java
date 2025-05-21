
import Entitati.*;
import Servicii.FestivalService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Connection;
import Servicii.ArtistDAO;


public class Main {
    public static void main(String[] args) {


        ArtistDAO artistDAO = new ArtistDAO();






        Connection conn = Database.DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Conectat cu succes!");
        } else {
            System.out.println("Conexiune eșuată.");
        }



        FestivalService service=new FestivalService();


        Artist artist1=new Artist("Imagine Dragons","Rock","USA",new ArrayList<>());
        Artist artist2=new Artist("Dua Lipa","Pop","UK",new ArrayList<>());
        Artist artist3=new Artist("Armin van Buuren","DJ","Netherlands",new ArrayList<>());

        service.adaugaArtist(artist1);
        service.adaugaArtist(artist2);
        service.adaugaArtist(artist3);

        Scena scena1=new Scena("Main Stage",5000,new ArrayList<>());
        Scena scena2=new Scena("Side Stage",2000,new ArrayList<>());

        Concert concert1=new Concert(artist1,scena1,LocalDateTime.of(2025,5,5,20,0),LocalDateTime.of(2025,5,5,22,0));
        Concert concert2=new Concert(artist2,scena2,LocalDateTime.of(2025,5,5,18,0),LocalDateTime.of(2025,5,5,20,0));
        Concert concert3=new Concert(artist3,scena1,LocalDateTime.of(2025,5,5,16,0),LocalDateTime.of(2025,5,5,18,0));
        Concert concert4=new Concert(artist2,scena2,LocalDateTime.of(2025,5,5,17,0),LocalDateTime.of(2025,5,5,18,0));

        scena1.getConcerte().add(concert1);
        scena1.getConcerte().add(concert3);
        scena2.getConcerte().add(concert2);
        scena2.getConcerte().add(concert4);

        service.adaugaConcert(concert1);
        service.adaugaConcert(concert2);
        service.adaugaConcert(concert3);
        service.adaugaConcert(concert4);

        Festival festival=new Festival("SummerFest","Cluj",LocalDate.of(2025,5,5),LocalDate.of(2025,5,7),Arrays.asList(scena1,scena2));
        service.adaugaFestival(festival);

        Bilet bilet1=new Bilet("B001",festival,200.0);
        Bilet bilet2=new Bilet("B002",festival,300.0);

        Participant p1=new Participant("Antonia Pricop","Antonia@gmail.com",Arrays.asList(bilet1));
        Participant p2=new Participant("Maria Ioana","maria@gmail.com",Arrays.asList(bilet2));
        service.adaugaParticipant(p1);
        service.adaugaParticipant(p2);

        Organizator organizator=new Organizator("Albu Victor","albuvictor2016@gmail.com",Arrays.asList(festival));
        service.adaugaOrganizator(organizator);

        System.out.println("\n");
        System.out.println("artisti:\n");
        service.afiseazaArtisti();
        System.out.println("\n");

        System.out.println("festivaluri:\n");
        service.afiseazaFestivaluri();
        System.out.println("\n");

        System.out.println("concerte:\n");
        service.afiseazaConcerte();
        System.out.println("\n");

        System.out.println("program festival:\n");
        service.afiseazaProgramFestival("SummerFest");
        System.out.println("\n");

        System.out.println("cautare artist după nume:\n");
        Artist found = service.cautaArtistDupaNume("Dua Lipa");
        System.out.println(found!=null ? found:"nu exista");
        System.out.println("\n");

        System.out.println("artisti pop:\n");
        service.getArtistiDupaGen("Pop").forEach(System.out::println);
        System.out.println("\n");

        System.out.println("concerte pentru Imagine Dragons:\n");
        service.getConcertePentruArtist("Imagine Dragons").forEach(System.out::println);
        System.out.println("\n");

        System.out.println("artiști sortati după nume:");
        service.afiseazaArtistiSortatiDupaNume();
        System.out.println("\n");

    }
}
