
public abstract class Tickets{

	protected abstract void registrieren();
	protected abstract void bestellungen();
	protected abstract boolean anzahlKarten(String w, int q);
	protected abstract void werteSpeichern(String name, String nachname, String passwort, String adresse, String plz, String nation);
	protected abstract void anzahlKartenSpeichern(int q);
	
	protected String was;
	protected String wo;
	protected String wann;
	
	protected void zeitOrt(String was, String wo, String wann) {
		this.was=was;
		this.wo=wo;
		this.wann=wann;
		System.out.println(was +"| Wo:"+ wo +"| Wann:"+ wann);
	}
	
	//Fuﬂball
	//Barcelona
	protected static int anzahlFuﬂball_KA=900;
	protected static int anzahlFuﬂball_KB=650;
	protected static int anzahlFuﬂball_KC=420;
	protected static int anzahlFuﬂball_KV=230;
	//Madrid
	protected static int anzahlFuﬂball_KAA=990;
	protected static int anzahlFuﬂball_KBB=760;
	protected static int anzahlFuﬂball_KCC=530;
	protected static int anzahlFuﬂball_KVV=310;
	
	//Konzert
	protected static int anzahlKonzert_K1=1400;
	protected static int anzahlKonzert_K2=1000;
	protected static int anzahlKonzert_K3=750;
	protected static int anzahlKonzert_K4=550;
	protected static int anzahlKonzert_K5=300;
	
	//GoKart
	//Innsbruck
	protected static int anzahlGoKart_30=300;
	protected static int anzahlGoKart_60=190;
	protected static int anzahlGoKart_90=150;
	//Innsbruck
	protected static int anzahlGoKart_30_=280;
	protected static int anzahlGoKart_60_=200;
	protected static int anzahlGoKart_90_=160;
}
