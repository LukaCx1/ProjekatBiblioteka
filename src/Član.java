import java.util.Objects;

public class Član {
    private String imePrezime;
    String brojČlana;
    public Član(String imePrezime, String brojČlana) {
        this.imePrezime = imePrezime;
       this.brojČlana = brojČlana;
    }

   public String getBrojČlana() {
       return brojČlana;
   }

   public Član setBrojČlana(String brojČlana) {
        this.brojČlana = brojČlana;
        return this;
    }




    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Član član = (Član) o;
        return imePrezime.equals(član.imePrezime) &&
                brojČlana.equals(član.brojČlana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imePrezime, brojČlana);
    }


    @Override
public String toString() {
    return "Član{" +
            "imePrezime='" + imePrezime + '\'' +
            ", brojČlana='" + brojČlana + '\'' +
            '}';
}
}