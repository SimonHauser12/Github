package Schachbrett;
import Schachbrett.Figuren;

public class TestMain {
	
	public static void main(String[] args) {
		
		int gesamtFiguren;
		
		Schachbrett b=new Schachbrett();
		b.laengebreite(8, 8);
		
		Figuren ba=Figuren.Bauer;
		ba.aktuelleAnzahlFiguren();
		Figuren la=Figuren.Laeufer;
		la.aktuelleAnzahlFiguren();
		Figuren sp=Figuren.Springer;
		sp.aktuelleAnzahlFiguren();
		Figuren tu=Figuren.Turm;
		tu.aktuelleAnzahlFiguren();
		Figuren k�=Figuren.Koenigin;
		k�.aktuelleAnzahlFiguren();
		Figuren K�=Figuren.Koenig;
		K�.aktuelleAnzahlFiguren();
		
		gesamtFiguren=b.zustandAktuell(Figuren.Bauer, Figuren.Laeufer, Figuren.Springer, Figuren.Turm, Figuren.Koenigin, Figuren.Koenig );
		
		System.out.println("Bauern(wei�):"+ ba.aktuelleAnzahlW +"   | Bauer(schwarz):"+ ba.aktuelleAnzahlS);
		System.out.println("L�ufer(wei�):"+ la.aktuelleAnzahlW +"   | L�ufer(schwarz):"+ la.aktuelleAnzahlS);
		System.out.println("Springer(wei�):"+ sp.aktuelleAnzahlW +" | Springer(schwarz):"+ sp.aktuelleAnzahlS);
		System.out.println("Turm(wei�):"+ tu.aktuelleAnzahlW +"     | Turm(schwarz):"+ tu.aktuelleAnzahlS);
		System.out.println("K�nigin(wei�):"+ k�.aktuelleAnzahlW +"  | K�nigin(schwarz):"+ k�.aktuelleAnzahlS);
		System.out.println("K�nig(wei�):"+ K�.aktuelleAnzahlW +"    | K�nig(schwarz):"+ K�.aktuelleAnzahlS);
		System.out.println();
		System.out.println("Gesamtfiguren am Brett:"+ gesamtFiguren);
		
		if(K�.anzahlS==0) System.out.println("Schwarz hat verloren!");
		if(K�.anzahlW==0) System.out.println("Wei� hat verloren!");
	}

}
