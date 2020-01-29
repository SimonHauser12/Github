
public class Kunde extends Tickets{

	protected String name;
	protected String nachname;
	protected String passwort;
	protected String adresse;
	protected String plz;
	protected String nation;
	protected int anzahlKarten=0;
	
	public void werteSpeichern(String name, String nachname, String passwort, String adresse, String plz, String nation) {
		this.name=name;
		this.nachname=nachname;
		this.passwort=passwort;
		this.adresse=adresse;
		this.plz=plz;
		this.nation=nation;
		anzahlKarten=0;
	}
	
	public void anzahlKartenSpeichern(int q) {
		this.anzahlKarten=anzahlKarten+q;
	}
	
	public void registrieren() {}
	public void bestellungen() {}
	public boolean anzahlKarten(String w, int q) {return true;}
}
