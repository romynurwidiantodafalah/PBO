package week1.Perhitungan;

public class VolumeTabung {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double diameter = 5;
        double tinggi = 10;
        double phi = 3.14;

        double volume = (0.25 * phi * diameter * diameter * tinggi); //0.25 atau 1/4

        System.out.println("Volume tabung dengan diameter " + diameter + " dan tinggi " + tinggi + " adalah " + volume);
	}

}
