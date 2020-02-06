package Schachbrett;

public enum Figuren implements Anzahl_Figuren{

	Bauer(8), Laeufer(2), Springer(2), Turm(2), Koenigin(1), Koenig(1);
	
	int anzahlS;
	int anzahlW;
	int aktuelleAnzahlS;
	int aktuelleAnzahlW;
	int anzahlGesamt;
	
	Figuren(int wert){
		this.anzahlW=wert;
		this.anzahlS=wert;
	}
	
	public void aktuellS() {
		aktuelleAnzahlS=anzahlS-((int)(Math.random()*anzahlS));
	}
	public void aktuellW() {
		aktuelleAnzahlW=anzahlW-((int)(Math.random()*anzahlW));
	}
	public void aktuellGesamt() {
		anzahlGesamt=aktuelleAnzahlW+aktuelleAnzahlS;
	}
	public void aktuelleAnzahlFiguren() {
		aktuellS();
		aktuellW();
		aktuellGesamt();
	}
}

