package Entitati;

public abstract class Persoana{
    protected String nume;
    protected String email;

    public Persoana(String nume, String email) {
        this.nume=nume;
        this.email=email;
    }
    public String getNume(){
        return nume;
    }
    public String getEmail(){
        return email;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public void setEmail(String email){
        this.email=email;
    }
    @Override
    public String toString(){
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
