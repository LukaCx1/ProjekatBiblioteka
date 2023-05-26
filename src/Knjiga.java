public class Knjiga {
    String naslov;
    String autor;
    int godina;
    TipKnjige tip;
    String kod;

    public Knjiga(String naslov, String autor, int godina, TipKnjige tip, String kod) {
        this.naslov = naslov;
        this.autor = autor;
        this.godina = godina;
        this.tip = tip;
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public Knjiga setKod(String kod) {
        this.kod = kod;
        return this;
    }

    public String getNaslov() {
        return naslov;
    }

    public TipKnjige getTip() {
        return tip;
    }

    public String getAutor() {
        return autor;
    }

    public int getGodina() {
        return godina;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public void setTip(TipKnjige tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Knjiga{" +
                "naslov='" + naslov + '\'' +
                ", autor='" + autor + '\'' +
                ", godina=" + godina +
                ", tip=" + tip +
                '}';
    }
}
