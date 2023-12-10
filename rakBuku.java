import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Buku {
    int isbn;
    String judul;
    String penulis;
    int tahun;
    boolean sudahDibaca;

    public Buku(int isbn, String judul, String penulis, int tahun, boolean sudahDibaca) {
        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.tahun = tahun;
        this.sudahDibaca = sudahDibaca;
    }

    public static Comparator<Buku> UrutkanBerdasarkanTahun = new Comparator<Buku>() {
        public int compare(Buku buku1, Buku buku2) {
            return buku1.tahun - buku2.tahun;
        }
    };
}

public class rakBuku {
    private static final ArrayList<Buku> rakBuku = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println();
        muatDataDariPenyimpanan();

        while (true) {
            System.out.println("-------------------------------");
            System.out.println("   === Aplikasi Rak Buku ===");
            System.out.println("-------------------------------");
            System.out.println("1. Tampilkan Buku");
            System.out.println("2. Lihat Informasi Buku");
            System.out.println("3. Tambah Buku Baru");
            System.out.println("4. Perbarui Informasi Buku");
            System.out.println("5. Hapus Buku");
            System.out.println("6. Keluar");
            System.out.println("-------------------------------");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1:
                    tampilkanBuku();
                    break;
                case 2:
                    lihatInformasiBuku();
                    break;
                case 3:
                    tambahBuku();
                    break;
                case 4:
                    perbaruiBuku();
                    break;
                case 5:
                    hapusBuku();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tisbnak valisbn. Silakan coba lagi.");
            }
        }
    }

    private static void tampilkanBuku() {
        Collections.sort(rakBuku, Buku.UrutkanBerdasarkanTahun);
        System.out.println("\n     === Daftar Buku ===\n-------------------------------");
        for (Buku buku : rakBuku) {
            System.out.println(buku.isbn + ". " + buku.judul + " (" + buku.tahun + ")");
        }
        System.out.println();
    }

    private static void lihatInformasiBuku() {
        System.out.println("\n      === Lihat Buku ===\n-------------------------------");
        System.out.print("Masukkan isbn buku: ");
        int isbnBuku = scanner.nextInt();

        Buku bukuDitemukan = temukanBuku(isbnBuku);
        if (bukuDitemukan == null) {
            System.out.println("Buku tisbnak ditemukan.\n");
            return;
        }

        System.out.println("\n    === Informasi Buku ===\n-------------------------------");
        System.out.println("isbn: " + bukuDitemukan.isbn);
        System.out.println("Judul: " + bukuDitemukan.judul);
        System.out.println("Penulis: " + bukuDitemukan.penulis);
        System.out.println("Tahun Terbit: " + bukuDitemukan.tahun);
        System.out.println("Sudah Dibaca: " + bukuDitemukan.sudahDibaca + "\n");
    }

    private static void tambahBuku() {
        System.out.println("\n     === Tambah Buku ===\n-------------------------------");
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();

        System.out.print("Masukkan nama penulis: ");
        String penulis = scanner.nextLine();

        System.out.print("Masukkan tahun terbit: ");
        int tahun = scanner.nextInt();

        System.out.print("Apakah buku sudah dibaca? (true/false): ");
        boolean sudahDibaca = scanner.nextBoolean();

        int isbnBaru = rakBuku.isEmpty() ? 1 : rakBuku.get(rakBuku.size() - 1).isbn + 1;
        Buku bukuBaru = new Buku(isbnBaru, judul, penulis, tahun, sudahDibaca);
        rakBuku.add(bukuBaru);

        System.out.println("Buku berhasil ditambahkan.");
        tampilkanBuku();
    }

    private static void perbaruiBuku() {
        System.out.println("\n    === Perbarui Buku ===\n-------------------------------");
        System.out.print("Masukkan isbn buku: ");
        int isbnBuku = scanner.nextInt();

        Buku bukuDitemukan = temukanBuku(isbnBuku);
        if (bukuDitemukan == null) {
            System.out.println("Buku tisbnak ditemukan.\n");
            return;
        }

        System.out.println("\nDetail Saat Ini:\n-------------------------------");
        System.out.println("Judul       : " + bukuDitemukan.judul);
        System.out.println("Penulis     : " + bukuDitemukan.penulis);
        System.out.println("Tahun Terbit: " + bukuDitemukan.tahun);
        System.out.println("Sudah Dibaca: " + bukuDitemukan.sudahDibaca);
        System.out.println("-------------------------------");

        System.out.println("\nDetail Baru:\n-------------------------------");
        scanner.nextLine();

        System.out.print("Masukkan judul baru (tekan Enter untuk tetap sama): ");
        String judulBaru = scanner.nextLine();
        if (!judulBaru.isEmpty()) {
            bukuDitemukan.judul = judulBaru;
        }

        System.out.print("Masukkan penulis baru (tekan Enter untuk tetap sama): ");
        String penulisBaru = scanner.nextLine();
        if (!penulisBaru.isEmpty()) {
            bukuDitemukan.penulis = penulisBaru;
        }

        System.out.print("Masukkan tahun terbit baru (tekan 0 untuk tetap sama): ");
        int tahunBaru = scanner.nextInt();
        if (tahunBaru != 0) {
            bukuDitemukan.tahun = tahunBaru;
        }

        System.out.print("Apakah buku sudah dibaca? (true/false, tekan Enter untuk tetap sama): ");
        scanner.nextLine();
        String sudahDibacaInput = scanner.nextLine();
        if (!sudahDibacaInput.isEmpty()) {
            bukuDitemukan.sudahDibaca = Boolean.parseBoolean(sudahDibacaInput);
        }

        System.out.println("-------------------------------\nBuku berhasil diperbarui.");
        tampilkanBuku();
    }

    private static void hapusBuku() {
        System.out.println("\n      === Hapus Buku ===\n-------------------------------");
        System.out.print("Masukkan isbn buku yang ingin dihapus: ");
        int isbnBuku = scanner.nextInt();

        int indeksBuku = temukanIndeksBuku(isbnBuku);
        if (indeksBuku == -1) {
            System.out.println("Buku tisbnak ditemukan.\n");
            return;
        }

        rakBuku.remove(indeksBuku);
        System.out.println("Buku berhasil dihapus.");
        tampilkanBuku();
    }

    private static void muatDataDariPenyimpanan() {
        rakBuku.add(new Buku(1, "Pemrograman Java", "John Doe", 2020, true));
        rakBuku.add(new Buku(2, "Dasar-dasar Python", "Jane Smith", 2019, false));
        rakBuku.add(new Buku(3, "Pengembangan Web", "Bob Johnson", 2021, true));
    }

    private static Buku temukanBuku(int isbnBuku) {
        for (Buku buku : rakBuku) {
            if (buku.isbn == isbnBuku) {
                return buku;
            }
        }
        return null;
    }

    private static int temukanIndeksBuku(int isbnBuku) {
        for (int i = 0; i < rakBuku.size(); i++) {
            if (rakBuku.get(i).isbn == isbnBuku) {
                return i;
            }
        }
        return -1;
    }
}
