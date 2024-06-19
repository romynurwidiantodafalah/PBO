package week11;

import java.sql.*;

public class Week11 {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/perpus";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Buka koneksi
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Contoh penggunaan metode-metode
            insertPenulis("Romy Nur Widianto Dafalah");
//            insertBuku("CARA INVESTASI DITAHUN 2024", 2024, 100, 32); // 100 stok buku // 32 id Penulis
//
//            updateBuku(10, "Cara Mengelola UANG dengan tepat", 2024, 15, 32); // 10 id Buku
//            updatePenulis(32, "Romy Nur W D"); // 32 idPenulis
//
//            deleteBuku(10); // 10 id Buku
//            deletePenulis(32); // 32 id Penulis
            showBuku(); // Menampilkan data buku setelah operasi penghapusan

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Tutup semua sumber daya
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    // Method untuk menambahkan penulis baru ke dalam tabel penulis
    public static int insertPenulis(String nama_penulis) {
        int generatedId = -1;
        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO penulis (nama_penulis) VALUES ('" + nama_penulis + "')";
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
            System.out.println("Data penulis telah ditambahkan dengan id: " + generatedId);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return generatedId;
    }

    // Method untuk menambahkan buku baru ke dalam tabel buku
    public static void insertBuku(String judul_buku, int tahun_terbit, int stok, int penulis_id) {
        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO buku (judul_buku, tahun_terbit, stok, penulis_id) VALUES ('"
                    + judul_buku.replace("'", "''") + "', " + tahun_terbit + ", " + stok + ", " + penulis_id + ")";
            stmt.executeUpdate(sql);
            System.out.println("Data buku telah ditambahkan...");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Method untuk memperbarui informasi buku berdasarkan ID-nya
    public static void updateBuku(int id, String judul_buku_baru, int tahun_terbit_baru, int stok_baru, int penulis_id_baru) {
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE buku SET judul_buku='" + judul_buku_baru.replace("'", "''") + "', tahun_terbit=" + tahun_terbit_baru
                    + ", stok=" + stok_baru + ", penulis_id=" + penulis_id_baru + " WHERE id=" + id;
            int rowsUpdated = stmt.executeUpdate(sql);
            if (rowsUpdated > 0) {
                System.out.println("Data buku dengan ID " + id + " telah diperbarui...");
            } else {
                System.out.println("Gagal memperbarui data buku dengan ID " + id + ". Data tidak ditemukan.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Method untuk memperbarui informasi penulis berdasarkan ID-nya
    public static void updatePenulis(int id, String nama_penulis_baru) {
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE penulis SET nama_penulis='" + nama_penulis_baru.replace("'", "''") + "' WHERE id=" + id;
            int rowsUpdated = stmt.executeUpdate(sql);
            if (rowsUpdated > 0) {
                System.out.println("Data penulis dengan ID " + id + " telah diperbarui...");
            } else {
                System.out.println("Gagal memperbarui data penulis dengan ID " + id + ". Data tidak ditemukan.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Method untuk menghapus buku berdasarkan ID-nya
    public static void deleteBuku(int id) {
        try {
            stmt = conn.createStatement();
            String sql = "DELETE FROM buku WHERE id=" + id;
            int rowsDeleted = stmt.executeUpdate(sql);
            if (rowsDeleted > 0) {
                System.out.println("Data buku dengan ID " + id + " telah dihapus...");
            } else {
                System.out.println("Gagal menghapus data buku dengan ID " + id + ". Data tidak ditemukan.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Method untuk menghapus penulis berdasarkan ID-nya
    public static void deletePenulis(int id) {
        try {
            stmt = conn.createStatement();
            String sql = "DELETE FROM penulis WHERE id=" + id;
            int rowsDeleted = stmt.executeUpdate(sql);
            if (rowsDeleted > 0) {
                System.out.println("Data penulis dengan ID " + id + " telah dihapus...");
            } else {
                System.out.println("Gagal menghapus data penulis dengan ID " + id + ". Data tidak ditemukan.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Method untuk menampilkan semua data buku beserta informasi penulisnya
    public static void showBuku() {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT buku.id, judul_buku, tahun_terbit, stok, nama_penulis FROM buku " +
                    "JOIN penulis ON buku.penulis_id = penulis.id";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String judul_buku = rs.getString("judul_buku");
                int tahun_terbit = rs.getInt("tahun_terbit");
                int stok = rs.getInt("stok");
                String nama_penulis = rs.getString("nama_penulis");

                System.out.print("ID: " + id);
                System.out.print(", Judul Buku: " + judul_buku);
                System.out.print(", Tahun Terbit: " + tahun_terbit);
                System.out.print(", Stok: " + stok);
                System.out.println(", Penulis: " + nama_penulis);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
