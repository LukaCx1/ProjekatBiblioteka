import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Biblioteka {
    public List<Knjiga> getKnjige() {
        return knjige;
    }

    private List<Knjiga> knjige = new ArrayList<>();
    private List<Član> članovi = new ArrayList<>();

    public List<Član> getČlanovi() {
        return članovi;
    }

    private Map<Član, List<Knjiga>> iznajmljeneKnjige = new HashMap<>();


    public Map<Član, List<Knjiga>> getIznajmljeneKnjige() {
        return iznajmljeneKnjige;
    }

    public synchronized void dodajKnjigu(Knjiga knjiga) {
        knjige.add(knjiga);

    }

    public synchronized void obrisiKnjigu(Knjiga knjiga) {
        knjige.remove(knjiga);

    }

    public synchronized void promeniInformacijeOKnjizi(Knjiga knjiga, String naslov, String autor, int godina, TipKnjige tip, String kod) {
        knjiga.setNaslov(naslov);
        knjiga.setAutor(autor);
        knjiga.setGodina(godina);
        knjiga.setTip(tip);
        knjiga.setKod(kod);
    }

    public synchronized void sortirajKnjigePoGodiniIzdavanja() {
        Collections.sort(knjige, Comparator.comparingInt(Knjiga::getGodina));
    }

    public synchronized void sortirajKnjigePoAutoru() {
        Collections.sort(knjige, Comparator.comparing(Knjiga::getAutor));
    }

    public void iznajmiKnjigu(Knjiga knjiga, Član član) {
        if (!iznajmljeneKnjige.containsKey(član)) {
            iznajmljeneKnjige.put(član, new ArrayList<>());
        }
        iznajmljeneKnjige.get(član).add(knjiga);
        System.out.println("Book rented: " + knjiga.naslov); // print the title of the rented book
    }

    public synchronized void vratiKnjigu(Knjiga knjiga, Član članZaVracanje) {
        if (iznajmljeneKnjige.containsKey(članZaVracanje)) {
            iznajmljeneKnjige.get(članZaVracanje).remove(knjiga);
            System.out.println("Book returned: " + knjiga.naslov); // print the title of the returned book
        }
    }

    public synchronized void dodajClana(Član član) {
        članovi.add(član);
    }

    public synchronized void obrisiClana(Član član) {
        članovi.remove(član);
    }

    String imeFajla = "library-data.txt";
    String imeFajla1 = imeFajla;

    public synchronized void upisiUFajl(String imeFajla1) {
        try (FileWriter writer = new FileWriter(imeFajla1)) {
            for (Knjiga knjiga : knjige) {
                writer.write(knjiga.getNaslov() + "," + knjiga.getAutor() + "," + knjiga.getGodina() + "," + knjiga.getTip().name() + knjiga.getKod() + "," + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void procitajIzFajla(String imeFajla1) {
        try (BufferedReader br = new BufferedReader(new FileReader(imeFajla1))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String naslov = parts[0];
                String autor = parts[1];
                int godina = Integer.parseInt(parts[2]);
                TipKnjige tip = TipKnjige.valueOf(parts[3]);
                String kod = parts[4];
                knjige.add(new Knjiga(naslov, autor, godina, tip, kod));
            }
            System.out.println("Knjige su uspesno ucitane.");
        } catch (IOException e) {
            System.out.println("Greska pri ucitavanju knjiga.");
        }
    }


    public void startujNit(String imeFajla1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // ovde cita fajl i ispisuje u konzoli
                try (BufferedReader br = new BufferedReader(new FileReader(imeFajla1))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
