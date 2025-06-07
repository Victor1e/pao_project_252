package Servicii;


public class ParticipantService {

    public static void verificaVarsta(int varsta) {
        if (varsta < 18) {
            throw new Exceptie("varsta trebuie sa fie >= 18 ani");
        }
    }
}
