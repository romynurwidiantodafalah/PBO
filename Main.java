package com.lat1;

public class Main {
    //commend main
    public static void main(String[] args) {
        System.out.println("Hallo World");
        // a. Keliling lingkaran dengan diketahui diameter = 10.
        double diameterLingkaran = 10;
        String kelilingLingkaranStr = String.format("%.2f", hitungKelilingLingkaran(diameterLingkaran));
        System.out.println("Keliling Lingkaran: " + kelilingLingkaranStr);

        // b. Luas segitiga siku-siku dengan diketahui alas = 6, tinggi = 8.
        double alasSegitiga = 6;
        double tinggiSegitiga = 8;
        String luasSegitigaStr = String.format("%.2f", hitungLuasSegitiga(alasSegitiga, tinggiSegitiga));
        System.out.println("Luas Segitiga: " + luasSegitigaStr);

        // c. Volume tabung dengan diketahui diameter = 5, tinggi = 10.
        double diameterTabung = 5;
        double tinggiTabung = 10;
        String volumeTabungStr = String.format("%.2f", hitungVolumeTabung(diameterTabung, tinggiTabung));
        System.out.println("Volume Tabung: " + volumeTabungStr);
    }

    // Metode untuk menghitung keliling lingkaran
    private static double hitungKelilingLingkaran(double diameter) {
        return Math.PI * diameter;
    }

    // Metode untuk menghitung luas segitiga siku-siku
    private static double hitungLuasSegitiga(double alas, double tinggi) {
        return 0.5 * alas * tinggi;
    }

    // Metode untuk menghitung volume tabung
    private static double hitungVolumeTabung(double diameter, double tinggi) {
        double jariJari = diameter / 2;
        return Math.PI * Math.pow(jariJari, 2) * tinggi;
    }

}
