import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;


public class LibraryApp {
    public static void main(String[] args) throws Exception {
        Biblioteka biblioteka = new Biblioteka();

        // Inicijalizacija knjiga
        Knjiga knjiga1 = new Knjiga("Na Drini ćuprija", "Ivo Andrić", 1945, TipKnjige.DRAMA, "0000001");
        Knjiga knjiga2 = new Knjiga("Prokleta avlija", "Ivo Andrić", 1954, TipKnjige.FANTASTIC, "0000002");
        Knjiga knjiga3 = new Knjiga("Zločin i kazna", "Fjodor Dostojevski", 1866, TipKnjige.HISTORY, "0000003");

        biblioteka.dodajKnjigu(knjiga1);
        biblioteka.dodajKnjigu(knjiga2);
        biblioteka.dodajKnjigu(knjiga3);

        // Inicijalizacija clanova
        Član clan1 = new Član("Luka Radosavljevic", "123");
        Član clan2 = new Član("Marko Markovic", "1234");
        System.out.println(biblioteka.getČlanovi());
        //System.out.println(biblioteka.getKnjige());

        biblioteka.dodajClana(clan1);
        biblioteka.dodajClana(clan2);

        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.println("Izaberite operaciju:");
            System.out.println("1. Dodavanje knjige");
            System.out.println("2. Brisanje knjige");
            System.out.println("3. Promena informacija o knjizi");
            System.out.println("4. Sortiranje knjiga po godini izdavanja");
            System.out.println("5. Sortiranje knjiga po autoru");
            System.out.println("6. Iznaјmljivanje knjige");
            System.out.println("7. Vraćanje knjige");
            System.out.println("8. Sacuvaj izmene u fajl");
            System.out.println("9. Procitaj sadrzinu fajla");
            System.out.println("10. Zapocni nit");
            System.out.println("11. Izlaz");
            System.out.println(biblioteka.getIznajmljeneKnjige());
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            int opcija = scanner.nextInt();
             //  newline karakter

            switch (opcija) {
                case 1:
                    System.out.println("Unesite naslov knjige:");
                    String naslov = scanner.next();
                    System.out.println("Unesite autora knjige:");
                    String autor = scanner.next();
                    System.out.println("Unesite godinu izdavanja knjige:");
                    int godinaIzdavanja = scanner.nextInt();
                    System.out.println("Unesite tip knjige:");
                    String tip = scanner.next();
                    TipKnjige tipKnjige = TipKnjige.valueOf(tip);
                    Knjiga knjiga = new Knjiga(naslov, autor, godinaIzdavanja, tipKnjige, tip);
                    biblioteka.dodajKnjigu(knjiga);
                    System.out.println("Knjiga je uspešno dodana.");
                    break;

                case 2:
                    System.out.print("Unesite naslov: ");
                    scanner.nextLine();
                     naslov = scanner.nextLine();
                    Knjiga knjigaZaBrisanje = null;
                    for (Knjiga knjiga12 : biblioteka.getKnjige()) {
                        if (knjiga12.getNaslov().equals(naslov)) {
                            knjigaZaBrisanje = knjiga12;
                            break;
                        }
                    }
                    if (knjigaZaBrisanje != null) {
                        biblioteka.obrisiKnjigu(knjigaZaBrisanje);
                    } else {
                        System.out.println("Knjiga sa tim naslovom ne postoji u biblioteci.");
                    }
                    break;

                case 3:

                    System.out.println("Unesite naslov knjige kojoj želite da promenite informacije:");
                    scanner.nextLine(); // consume newline character
                    String naslov1 = scanner.nextLine();
                    Knjiga knjigaZaPromenu = null;
                    for (Knjiga knjiga12 : biblioteka.getKnjige()) {
                        System.out.println("Book title: " + knjiga12.getNaslov()); // print book title
                        if (knjiga12.getNaslov().equalsIgnoreCase(naslov1)) {
                            knjigaZaPromenu = knjiga12;
                            break;
                        }
                    }
                    if (knjigaZaPromenu != null) {
                        System.out.print("Unesite novi naslov: ");
                        String noviNaslov = scanner.nextLine();
                        System.out.print("Unesite novog autora: ");
                        String noviAutor = scanner.nextLine();
                        System.out.print("Unesite novu godinu izdavanja: ");
                        int novaGodinaIzdavanja = scanner.nextInt();
                        System.out.print("Unesite novi tip knjige: ");
                        TipKnjige noviTip = TipKnjige.valueOf(scanner.next());
                        System.out.print("Unesite novi kod knjige: ");
                        String noviKod = scanner.next();
                        biblioteka.promeniInformacijeOKnjizi(knjigaZaPromenu, noviNaslov, noviAutor, novaGodinaIzdavanja, noviTip, noviKod);
                    } else {
                        System.out.println("Knjiga sa tim naslovom ne postoji u biblioteci.");
                    }

                    break;
                        case 4:
                            biblioteka.sortirajKnjigePoGodiniIzdavanja();
                            break;
                        case 5:
                            biblioteka.sortirajKnjigePoAutoru();
                            break;


                case 6:
                    // Iznajmi knjigu
                    System.out.println("Unesite naslov knjige koju želite iznajmiti:");
                    scanner.nextLine();
                    naslov = scanner.nextLine();
                    knjiga = null;
                    for (Knjiga k : biblioteka.getKnjige()) {
                        System.out.println("Book title: " + k.getNaslov()); // print book title
                        if (k.naslov.equalsIgnoreCase(naslov)) {
                            knjiga = k;
                            break;
                        }
                    }
                    if (knjiga == null) {
                        System.out.println("Knjiga nije pronađena.");
                    } else {
                        System.out.println("Unesite svoje ime:");
                        String imePrezime = scanner.nextLine();
                        Član član = null;
                        for (Član c : biblioteka.getČlanovi()) {
                            if (c.getImePrezime().equals(imePrezime)) {
                                član = c;
                                break;
                            }
                        }
                        if (član == null) {
                            System.out.println("Član nije pronađen.");
                        } else {
                            biblioteka.iznajmiKnjigu(knjiga, član);
                            System.out.println("Knjiga je uspešno iznajmljena.");
                        }
                    }
                    break;
                case 7:
                    System.out.print("Unesite naslov: ");
                    scanner.nextLine();
                    naslov = scanner.nextLine();
                    System.out.println("Naslov: " + naslov); // print the value of naslov
                    Knjiga knjigaZaVracanje = null;
                    Član članZaVracanje = null;
                    for (Map.Entry<Član, List<Knjiga>> entry : biblioteka.getIznajmljeneKnjige().entrySet()) {
                        for (Knjiga k : entry.getValue()) {
                            System.out.println("Checking book: " + k.naslov); // print the title of the book being checked
                            if (k.naslov.equals(naslov)) {
                                knjigaZaVracanje = k;
                                članZaVracanje = entry.getKey();
                                break;
                            }
                        }
                    }
                    if (knjigaZaVracanje != null) {
                        biblioteka.vratiKnjigu(knjigaZaVracanje, članZaVracanje);
                    } else {
                        System.out.println("Knjiga sa tim naslovom nije iznajmljena.");
                    }
                    break;
                        case 8:
                            done = true;
                            break;
                        default:
                            System.out.println("Pogrešna opcija, molim izaberite ponovo.");
                            break;
                    }
            }

                biblioteka.upisiUFajl("library-data.txt");

                biblioteka.startujNit("library-data.txt");
            }


        }



