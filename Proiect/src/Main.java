import Database.DBConnection;
import Entitati.*;
import Servicii.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("conectat la baza de date.");
        } else {
            System.out.println("conexiune esuata.");
            return;
        }


        ArtistDB artistDB = new ArtistDB();
        BiletDB biletDB = new BiletDB();
        ConcertDB concertDB = new ConcertDB();

        FestivalService service = new FestivalService();


        Artist artist1 = new Artist("Imagine Dragons", "Rock", "USA", new ArrayList<>());
        Artist artist2 = new Artist("Dua Lipa", "Pop", "UK", new ArrayList<>());
        Artist artist3 = new Artist("Armin van Buuren", "DJ", "Netherlands", new ArrayList<>());
        Artist artist4 = new Artist("Victor", "DJ", "Romania", new ArrayList<>());
        Artist artist5 = new Artist("Albu", "Pop", "Romania", new ArrayList<>());
        Artist artist6 = new Artist("Ciprian", "Pop", "Romania", new ArrayList<>());
        Artist artist7 = new Artist("Smiley", "Pop", "Romania", new ArrayList<>());

        AuditService.log("create_artist");
        artistDB.create(artist1);
        AuditService.log("create_artist");
        artistDB.create(artist2);
        AuditService.log("create_artist");
        artistDB.create(artist3);
        AuditService.log("create_artist");
        artistDB.create(artist4);
        AuditService.log("create_artist");
        artistDB.create(artist5);
        AuditService.log("create_artist");
        artistDB.create(artist6);
        AuditService.log("create_artist");
        artistDB.create(artist7);




        service.adaugaArtist(artist1);
        service.adaugaArtist(artist2);
        service.adaugaArtist(artist3);
        service.adaugaArtist(artist4);
        service.adaugaArtist(artist5);
        service.adaugaArtist(artist6);
        service.adaugaArtist(artist7);


        Scena scena1 = new Scena("Main Stage", 5000, new ArrayList<>());
        Scena scena2 = new Scena("Side Stage", 2000, new ArrayList<>());

        Concert concert1 = new Concert(artist1, scena1, LocalDateTime.of(2025, 5, 5, 20, 0), LocalDateTime.of(2025, 5, 5, 22, 0));
        Concert concert2 = new Concert(artist2, scena2, LocalDateTime.of(2025, 5, 5, 18, 0), LocalDateTime.of(2025, 5, 5, 20, 0));
        Concert concert3 = new Concert(artist3, scena1, LocalDateTime.of(2025, 5, 5, 16, 0), LocalDateTime.of(2025, 5, 5, 18, 0));
        Concert concert4 = new Concert(artist2, scena2, LocalDateTime.of(2025, 5, 5, 17, 0), LocalDateTime.of(2025, 5, 5, 18, 0));
        Concert concert5 = new Concert(artist4, scena2, LocalDateTime.of(2025, 6, 5, 12, 2), LocalDateTime.of(2025, 6, 5, 13, 0));
        Concert concert6 = new Concert(artist4, scena1, LocalDateTime.of(2025, 6, 5, 12, 2), LocalDateTime.of(2025, 6, 5, 13, 0));
        Concert concert7 =new Concert(artist7, scena1, LocalDateTime.of(2025, 6, 5, 12, 2), LocalDateTime.of(2025, 6, 5, 13, 0));

        concertDB.create(concert1);
        concertDB.create(concert2);
        concertDB.create(concert3);
        concertDB.create(concert4);
        concertDB.create(concert5);
        concertDB.create(concert6);
        concertDB.create(concert7);

        scena1.getConcerte().add(concert1);
        scena1.getConcerte().add(concert3);
        scena2.getConcerte().add(concert2);
        scena2.getConcerte().add(concert4);
        scena2.getConcerte().add(concert5);
        scena1.getConcerte().add(concert6);
        scena1.getConcerte().add(concert7);

        service.adaugaConcert(concert1);
        service.adaugaConcert(concert2);
        service.adaugaConcert(concert3);
        service.adaugaConcert(concert4);
        service.adaugaConcert(concert5);
        service.adaugaConcert(concert6);
        service.adaugaConcert(concert7);

        artist1.setConcerte(service.getConcertePentruArtist(artist1.getNume()));
        artist2.setConcerte(service.getConcertePentruArtist(artist2.getNume()));
        artist3.setConcerte(service.getConcertePentruArtist(artist3.getNume()));
        artist4.setConcerte(service.getConcertePentruArtist(artist4.getNume()));
        artist4.setConcerte(service.getConcertePentruArtist(artist4.getNume()));
        artist7.setConcerte(service.getConcertePentruArtist(artist7.getNume()));


        Festival festival = new Festival("SummerFest", "Cluj", LocalDate.of(2025,5,5), LocalDate.of(2025,5,7), Arrays.asList(scena1, scena2));
        service.adaugaFestival(festival);

        Bilet bilet1 = new Bilet("B01", festival, 200.0);
        Bilet bilet2 = new Bilet("B02", festival, 300.0);
        Bilet bilet3 = new Bilet("B0_VIP", festival, 1000.0);
        Bilet bilet4 = new Bilet("ULTRA_VIP", festival, 20000.0);
        biletDB.create(bilet1);
        biletDB.create(bilet2);
        biletDB.create(bilet3);
        biletDB.create(bilet4);



        List<Festival> festivaluriOrganizate = new ArrayList<>();
        festivaluriOrganizate.add(festival);

        OrganizatorDB organizatorDB = new OrganizatorDB();
        Organizator org = new Organizator("Albu Victor", "albuvictor2016@gmail.com",festivaluriOrganizate );
        organizatorDB.create(org);

        Organizator readOrg = organizatorDB.read(1);

        readOrg.setFestivaluri(festivaluriOrganizate);

        System.out.println("organizator citit din DB: " + readOrg);




        System.out.println("\nArtisti:\n");
        service.afiseazaArtisti();

        System.out.println("\nfestivaluri:\n");
        service.afiseazaFestivaluri();

        System.out.println("\nconcerte:\n");
        service.afiseazaConcerte();

        System.out.println("\nprogram festival:\n");
        service.afiseazaProgramFestival("SummerFest");

        System.out.println("\ncautare artist dupa nume:\n");
        Artist found = service.cautaArtistDupaNume("Dua Lipa");
        System.out.println(found != null ? found : "nu exista");

        System.out.println("\nartisti pop:\n");
        service.getArtistiDupaGen("Pop").forEach(System.out::println);

        System.out.println("\nconcerte pentru Imagine Dragons:\n");
        service.getConcertePentruArtist("Imagine Dragons").forEach(System.out::println);

        System.out.println("\nartisti sortați dupa nume:\n");
        service.afiseazaArtistiSortatiDupaNume();

        Participant p1 = new Participant("Antonia Pricop", "Antonia@gmail.com",19, Arrays.asList(bilet1));
        Participant p2 = new Participant("Maria Ioana", "maria@gmail.com",20, Arrays.asList(bilet2));
        Participant p3 = new Participant("Andrei", "andrei@gmail.com", 17, Arrays.asList(bilet2));
        service.adaugaParticipant(p1);
        service.adaugaParticipant(p2);
        service.adaugaParticipant(p3);

        System.out.println("\nparticipanti:\n");
        service.afiseazaParticipanti();

        System.out.println("\nmesaje:\n");

        ParticipantDB participantDB = new ParticipantDB();


        try {
            participantDB.insertParticipant(p1);
            System.out.println("Participant inserat: "
                    + p1.getNume() + ", email=" + p1.getEmail() + ", varsta=" + p1.getVarsta());
        } catch (Exceptie ex) {
            System.out.println(ex.getMessage());
        }

        try {
            participantDB.insertParticipant(p2);
            System.out.println("Participant inserat: "
                    + p2.getNume() + ", email=" + p2.getEmail() + ", varsta=" + p2.getVarsta());
        } catch (Exceptie ex) {
            System.out.println(ex.getMessage());
        }

        try {
            participantDB.insertParticipant(p3);
            System.out.println("Participant inserat: "
                    + p3.getNume() + ", email=" + p3.getEmail() + ", varsta=" + p3.getVarsta());
        } catch (Exceptie ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println("\nSalut, eu sunt Victor si daca nu ti-ai luat bilet, tocmai ce s-au scumpit:\n");
        String codbilet = "B01";
        double  pretnou = 400.0;
        biletDB.updatePriceByCod(codbilet, pretnou);
        System.out.println("pretul biletului cu codul " + codbilet + " a fost actualizat la " + pretnou);



    }
}
