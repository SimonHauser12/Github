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
		Figuren kö=Figuren.Koenigin;
		kö.aktuelleAnzahlFiguren();
		Figuren KÖ=Figuren.Koenig;
		KÖ.aktuelleAnzahlFiguren();
		
		gesamtFiguren=b.zustandAktuell(Figuren.Bauer, Figuren.Laeufer, Figuren.Springer, Figuren.Turm, Figuren.Koenigin, Figuren.Koenig );
		
		System.out.println("Bauern(weiß):"+ ba.aktuelleAnzahlW +"   | Bauer(schwarz):"+ ba.aktuelleAnzahlS);
		System.out.println("Läufer(weiß):"+ la.aktuelleAnzahlW +"   | Läufer(schwarz):"+ la.aktuelleAnzahlS);
		System.out.println("Springer(weiß):"+ sp.aktuelleAnzahlW +" | Springer(schwarz):"+ sp.aktuelleAnzahlS);
		System.out.println("Turm(weiß):"+ tu.aktuelleAnzahlW +"     | Turm(schwarz):"+ tu.aktuelleAnzahlS);
		System.out.println("Königin(weiß):"+ kö.aktuelleAnzahlW +"  | Königin(schwarz):"+ kö.aktuelleAnzahlS);
		System.out.println("König(weiß):"+ KÖ.aktuelleAnzahlW +"    | König(schwarz):"+ KÖ.aktuelleAnzahlS);
		System.out.println();
		System.out.println("Gesamtfiguren am Brett:"+ gesamtFiguren);
		
		if(KÖ.anzahlS==0) System.out.println("Schwarz hat verloren!");
		if(KÖ.anzahlW==0) System.out.println("Weiß hat verloren!");
	}

}
