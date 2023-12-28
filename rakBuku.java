import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Buku {
    int nomor;
    int tahun;
    String judul;
    String penulis;
    boolean status;

    public Buku(int nomor, String judul, String penulis, int tahun, boolean status) {
        this.nomor = nomor;
        this.judul = judul;
        this.penulis = penulis;
        this.tahun = tahun;
        this.status = status;
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
        int pilihan = 0;

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
            if (scanner.hasNextInt()) { // ga masuk
                pilihan = scanner.nextInt();
            } else { // ga masuk
                scanner.nextLine(); // ga masuk
                System.out.println("\n\n======Input harus berupa angka!!======\n"); // ga masuk
                continue; // ga masuk
            }

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
                    System.out.println("Apakah anda yakin untuk keluar:");
                    System.out.println("1. Keluar\n2. Batal keluar");
                    System.out.print("Masukkan pilihan: ");
                    int keluar = scanner.nextInt();
                    if (keluar == 1) {
                        System.out.println("Terimakasih menggunakan program ini, Selesai.");
                        System.exit(0);
                    } else {
                        System.out.print("Batal keluar");
                        System.out.println("");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void tampilkanBuku() {
        Collections.sort(rakBuku, Buku.UrutkanBerdasarkanTahun);
        System.out.println("\n     === Daftar Buku ===\n-------------------------------");
        for (Buku buku : rakBuku) {
            System.out.println(buku.nomor + " " + buku.judul + " (" + buku.tahun + ")");
        }
        System.out.println();
    }

    private static void lihatInformasiBuku() {
        System.out.println("\n      === Lihat Buku ===\n-------------------------------");
        System.out.print("Masukkan nomor buku: ");
        int nomorBuku = scanner.nextInt();

        Buku bukuDitemukan = temukanBuku(nomorBuku);
        if (bukuDitemukan == null) {
            System.out.println("Buku tidak ditemukan.\n");
            return;
        }

        System.out.println("\n    === Informasi Buku ===\n-------------------------------");
        System.out.println("nomor: " + bukuDitemukan.nomor);
        System.out.println("Judul: " + bukuDitemukan.judul);
        System.out.println("Penulis: " + bukuDitemukan.penulis);
        System.out.println("Tahun Terbit: " + bukuDitemukan.tahun);
        System.out.println("Sudah Dibaca: " + bukuDitemukan.status + "\n");
    }

    private static void tambahBuku() {
        System.out.println("Buku saat ini: ");
        tampilkanBuku();
        System.out.println("\n     === Tambah Buku ===\n-------------------------------");
        System.out.print("Masukkan judul buku: ");
        scanner.nextLine();
        String judul = scanner.nextLine();

        System.out.print("Masukkan nama penulis: ");
        String penulis = scanner.nextLine();

        System.out.print("Masukkan tahun terbit: ");
        int tahun = scanner.nextInt();

        System.out.print("Apakah buku sudah dibaca? (true/false): ");
        boolean status = scanner.nextBoolean();

        int nomorBaru = rakBuku.isEmpty() ? 1 : rakBuku.get(rakBuku.size() - 1).nomor + 1;
        Buku bukuBaru = new Buku(nomorBaru, judul, penulis, tahun, status);
        rakBuku.add(bukuBaru);

        System.out.println("Buku berhasil ditambahkan.");
        tampilkanBuku();
    }

    private static void perbaruiBuku() {
        System.out.println("\n    === Perbarui Buku ===\n-------------------------------");
        System.out.print("Masukkan nomor buku: ");
        int nomorBuku = scanner.nextInt();

        Buku bukuDitemukan = temukanBuku(nomorBuku);
        if (bukuDitemukan == null) {
            System.out.println("Buku tidak ditemukan.\n");
            return;
        }

        System.out.println("\nDetail Saat Ini:\n-------------------------------");
        System.out.println("Judul       : " + bukuDitemukan.judul);
        System.out.println("Penulis     : " + bukuDitemukan.penulis);
        System.out.println("Tahun Terbit: " + bukuDitemukan.tahun);
        System.out.println("Sudah Dibaca: " + bukuDitemukan.status);
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
        String statusInput = scanner.nextLine();
        if (!statusInput.isEmpty()) {
            bukuDitemukan.status = Boolean.parseBoolean(statusInput);
        }

        System.out.println("-------------------------------\nBuku berhasil diperbarui.");
        tampilkanBuku();
    }

    private static void hapusBuku() {
        System.out.println("\n      === Hapus Buku ===\n-------------------------------");
        System.out.print("Masukkan nomor buku yang ingin dihapus: ");
        int nomorBuku = scanner.nextInt();

        int indeksBuku = temukanIndeksBuku(nomorBuku);
        if (indeksBuku == -1) {
            System.out.println("Buku tidak ditemukan.\n");
            return;
        }

        rakBuku.remove(indeksBuku);
        System.out.println("Buku berhasil dihapus.");
        tampilkanBuku();
    }

    private static void muatDataDariPenyimpanan() {
        rakBuku.add(new Buku(3, "Cinta paling Rumit", "Boy Chandra", 2018, false));
        rakBuku.add(new Buku(1, "Perahu Kertas", "Dewi Lestari Dee", 2010, true));
        rakBuku.add(new Buku(2, "Sepotong Hati Yang Baru", "Tere Liye", 2012, false));
    }

    private static Buku temukanBuku(int nomorBuku) {
        for (Buku buku : rakBuku) {
            if (buku.nomor == nomorBuku) {
                return buku;
            }
        }
        return null;
    }

    private static int temukanIndeksBuku(int nomorBuku) {
        for (int i = 0; i < rakBuku.size(); i++) {
            if (rakBuku.get(i).nomor == nomorBuku) {
                return i;
            }
        }
        return -1;
    }

}