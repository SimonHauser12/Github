import java.util.Scanner;

public class Ticketshop extends Kunde implements TicketsPreise{
	Scanner k=new Scanner(System.in);
	private static int gesamtEinnahmen;
	private static int anzahlBestellungen;
	private int kostenProPerson;
	private int a=1;
	private int zwischenPreis;
	private boolean falscheEingabe=false;
	private boolean passwortKontrolle=true;
	
	public int getAnzahlBestellungen() {
		return anzahlBestellungen;
	}
	
	public int getGesamtEinnahmen() {
		return gesamtEinnahmen;
	}
	
	public void registrieren() {
		System.out.println("Bitte registrieren Sie sich: ");
		String nachname;
		String name;
		String passwort;
		String adresse;
		String plz;
		String nation;
		do {
			falscheEingabe=false;
			System.out.print("Nachname:");
			nachname = k.next();
			System.out.print("Name:");
			name = k.next();
			System.out.print("Passwort(min 6):");
			passwort = k.next();
			System.out.print("Adresse(min 6):");
			adresse = k.next();
			System.out.print("Postleitzahl(4):");
			plz = k.next();
			System.out.print("Nation:");
			nation = k.next();
			if(passwort.length()<6||adresse.length()<6||plz.length()!=4) {
				System.out.println("Bitte die vorgegebenen Längen einhalten!");
				falscheEingabe=true;
			}
		} while (falscheEingabe);
		a--;
		werteSpeichern(name, nachname, passwort, adresse, plz, nation);
		kostenProPerson=0;
	}
	public void bestellungen() {
		boolean repeat0;
		do {
			repeat0=true;
			boolean repeat1;
			System.out.println("Tickets kaufen?(j oder n)");
			String s = k.next();
			if (s.equals("j")) {
				do {
					System.out.println("Was wollen Sie kaufen? f(Fußball), k(Konzert) oder g(GoKart)?");
					String i = k.next();
					repeat1 = false;
					switch (i) {
					case "f":
						zeitOrt("Barcelona vs Atletico Madrid", "CampNou in Barcelona, Spanien", "24.04.2020");
						zeitOrt("Real Madrid vs Liverpool", "Estadio Santiago Bernabeu in Madrid, Spanien", "19.03.2020");
						System.out.println();
						System.out.println("CampNou(1) oder Estadio Santiago Bernabeu(2)");
						String q=k.next();
						switch(q) {
						case "1": System.out.println("KlasseA(F1), KlasseB(F2), KlasseC(F3) oder KlasseVIP(FV) | z für zurück");
						break; 
						case "2": System.out.println("KlasseA(F11), KlasseB(F22), KlasseC(F33) oder KlasseVIP(FVV) | z für zurück");
						break;
						default: System.out.println("falsche Eingabe!");
						repeat1=true;
						break;
						}
						break;
					case "k":
						zeitOrt("Metallica", "Festhalle Frankfurt, Deutschland", "21.06.2020");
						System.out.println("Klasse1(K1), Klasse2(K2), Klasse3(K3), Klasse4(K4) oder Klasse5(K5) | z für zurück");
						break;
					case "g":
						zeitOrt("B1", "Kartbahn Innsbruck, Österreich", "täglich von 9:00 bis 19:00 Uhr");
						zeitOrt("Motorsport Arena", "Go-Kart-Bahn in Ötztal, Österreich", "täglich von 8:00 bis 17:00 Uhr");
						System.out.println();
						System.out.println("Innsbuck(1) oder Ötztal(2)");
						String g=k.next();
						switch(g) {
						case "1": System.out.println("30Min(G1), 60Min(G2) oder 90Min(G3) | z für zurück");
						break; 
						case "2": System.out.println("30Min(G11), 60Min(G22) oder 90Min(G33) | z für zurück");
						break;
						default: System.out.println("falsche Eingabe!");
						repeat1=true;
						break;
						}
						
						break;
					default:
						System.out.println("Falsche Eingabe, erneut eingeben!");
						repeat1 = true;
						break;
					}
					if (!repeat1) {
						String w = k.next();
						repeat1 = false;
						switch (w) {
						case "F1":
							System.out.println("Preis: " + Fußball_KA + "€");
							break;
						case "F2":
							System.out.println("Preis: " + Fußball_KB + "€");
							break;
						case "F3":
							System.out.println("Preis: " + Fußball_KC + "€");
							break;
						case "FV":
							System.out.println("Preis: " + Fußball_KV + "€");
							break;
						case "F11":
							zwischenPreis=Fußball_KA+59;
							System.out.println("Preis: " + zwischenPreis + "€");
							break;
						case "F22":
							zwischenPreis=Fußball_KB+73;
							System.out.println("Preis: " + zwischenPreis + "€");
							break;
						case "F33":
							zwischenPreis=Fußball_KC+85;
							System.out.println("Preis: " + zwischenPreis + "€");
							break;
						case "FVV":
							zwischenPreis=Fußball_KA+102;
							System.out.println("Preis: " + zwischenPreis + "€");
							break;
						case "K1":
							System.out.println("Preis: " + Konzert_K1 + "€");
							break;
						case "K2":
							System.out.println("Preis: " + Konzert_K2 + "€");
							break;
						case "K3":
							System.out.println("Preis: " + Konzert_K3 + "€");
							break;
						case "K4":
							System.out.println("Preis: " + Konzert_K4 + "€");
							break;
						case "K5":
							System.out.println("Preis: " + Konzert_K5 + "€");
							break;
						case "G1":
							System.out.println("Preis: " + GoKart_30 + "€");
							break;
						case "G2":
							System.out.println("Preis: " + GoKart_60 + "€");
							break;
						case "G3":
							System.out.println("Preis: " + GoKart_90 + "€");
							break;
						case "G11":
							zwischenPreis=GoKart_30-5;
							System.out.println("Preis: " + zwischenPreis + "€");
							break;
						case "G22":
							zwischenPreis=GoKart_60-9;
							System.out.println("Preis: " + zwischenPreis + "€");
							break;
						case "G33":
							zwischenPreis=GoKart_90-12;
							System.out.println("Preis: " + zwischenPreis + "€");
							break;
						case "z": repeat1=true; break;
						default:
							System.out.println("Falsche Eingabe, erneut eingeben!");
							repeat1 = true;
							break;
						}

						if (!repeat1) {
							System.out.println("Wieviele Karten wollen Sie kaufen?");
							int q = k.nextInt();
							System.out.println("Kauf bestätigen(b) oder zurück zur Auswahl(z)");
							String e = k.next();
							repeat1 = false;
							switch (e) {
							case "b":
								int anzahlVersuche=2;
								do {
									passwortKontrolle=true;
									System.out.println("Passwort: ");
									String pa = k.next();
									if (pa.equals(passwort)) {
										boolean status = anzahlKarten(w, q);
										if (status) {
											System.out.println("Kauf wurde bestätigt");
											if (a == 0) {
												anzahlBestellungen++;
												a++;
											}
										}
										break;
									} else {
										System.out.println("Falsches Passwort(noch "+anzahlVersuche+" Versuche)");
										anzahlVersuche--;
										passwortKontrolle=false;
										if(anzahlVersuche==-1) {
											System.out.println("Ticketshop wird aus Sicherheitsgründen verlassen!!");
											passwortKontrolle=true;
											repeat1=false;
											repeat0=false;
										}
									} 
								} while (!passwortKontrolle);
								break;
							case "z":
								System.out.println("Zurück zu Karten wählen");
								repeat1 = true;
								break;
							default:
								System.out.println("Falsche Eingabe, erneut eingeben!");
								repeat1 = true;
								break;
							}
						}
					}
				} while (repeat1);
			} else {
				if (s.equals("n")) {
					System.out.println("Ticketshop wird verlassen!");
					System.out.println("Es wurden "+anzahlKarten+" Karten von "+name+" "+nachname+" gekauft. Die Tickets werden in Kürze nach "+nation+" an die Adresse "+adresse+" geschickt.");
					repeat0=false;
				}
			} 
		} while (repeat0);
	}
	
	public boolean anzahlKarten(String w, int q) {
		switch (w) {
		case "F1":
			if (anzahlFußball_KA-q>=0) {
				anzahlFußball_KA = anzahlFußball_KA - q;
				kostenProPerson=Fußball_KA*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KA+" Karten!");
				return false;
			}
			break;
		case "F2":
			if (anzahlFußball_KB-q>=0) {
				anzahlFußball_KB = anzahlFußball_KB - q;
				kostenProPerson=Fußball_KB*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KB+" Karten!");
				return false;
			}
			break;
		case "F3":
			if (anzahlFußball_KC-q>=0) {
				anzahlFußball_KC = anzahlFußball_KC - q;
				kostenProPerson=Fußball_KC*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KC+" Karten!");
				return false;
			}
			break;
		case "FV":
			if (anzahlFußball_KV-q>=0) {
				anzahlFußball_KV = anzahlFußball_KV - q;
				kostenProPerson=Fußball_KV*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KV+" Karten!");
				return false;
			}
			break;
		case "F11":
			if (anzahlFußball_KAA-q>=0) {
				anzahlFußball_KAA = anzahlFußball_KAA - q;
				kostenProPerson=zwischenPreis*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KAA+" Karten!");
				return false;
			}
			break;
		case "F22":
			if (anzahlFußball_KBB-q>=0) {
				anzahlFußball_KBB= anzahlFußball_KBB - q;
				kostenProPerson=zwischenPreis*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KBB+" Karten!");
				return false;
			}
			break;
		case "F33":
			if (anzahlFußball_KCC-q>=0) {
				anzahlFußball_KCC = anzahlFußball_KCC - q;
				kostenProPerson=zwischenPreis*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KCC+" Karten!");
				return false;
			}
			break;
		case "FVV":
			if (anzahlFußball_KVV-q>=0) {
				anzahlFußball_KVV = anzahlFußball_KVV - q;
				kostenProPerson=zwischenPreis*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlFußball_KVV+" Karten!");
				return false;
			}
			break;
		case "K1":
			if (anzahlKonzert_K1-q>=0) {
				anzahlKonzert_K1 = anzahlKonzert_K1 - q;
				kostenProPerson=Konzert_K1*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlKonzert_K1+" Karten!");
				return false;
			}
			break;
		case "K2":
			if (anzahlKonzert_K2-q>=0) {
				anzahlKonzert_K2 = anzahlKonzert_K2 - q;
				kostenProPerson=Konzert_K2*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlKonzert_K2+" Karten!");
				return false;
			}
			break;
		case "K3":
			if (anzahlKonzert_K3-q>=0) {
				anzahlKonzert_K3 = anzahlKonzert_K3 - q;
				kostenProPerson=Konzert_K3*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlKonzert_K3+" Karten!");
				return false;
			}
			break;
		case "K4":
			if (anzahlKonzert_K4-q>=0) {
				anzahlKonzert_K4 = anzahlKonzert_K4 - q;
				kostenProPerson=Konzert_K4*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlKonzert_K4+" Karten!");
				return false;
			}
			break;
		case "K5":
			if (anzahlKonzert_K5-q>=0) {
				anzahlKonzert_K5 = anzahlKonzert_K5 - q;
				kostenProPerson=Konzert_K5*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlKonzert_K5+" Karten!");
				return false;
			}
			break;
		case "G1":
			 if (anzahlGoKart_30-q>=0) {
				anzahlGoKart_30 = anzahlGoKart_30 - q;
				kostenProPerson=GoKart_30*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlGoKart_30+" Karten!");
				return false;
			}
			break;
		case "G2":
			 if (anzahlGoKart_60-q>=0) {
				anzahlGoKart_60 = anzahlGoKart_60 - q;
				kostenProPerson=GoKart_60*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlGoKart_60+" Karten!");
				return false;
			}
			break;
		case "G3":
			 if (anzahlGoKart_90-q>=0) {
				anzahlGoKart_90 = anzahlGoKart_90 - q;
				kostenProPerson=GoKart_90*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlGoKart_90+" Karten!");
				return false;
			}
			break;
		case "G11":
			 if (anzahlGoKart_30_-q>=0) {
				anzahlGoKart_30_ = anzahlGoKart_30_ - q;
				kostenProPerson=zwischenPreis*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlGoKart_30_+" Karten!");
				return false;
			}
			break;
		case "G22":
			 if (anzahlGoKart_60_-q>=0) {
				anzahlGoKart_60_ = anzahlGoKart_60_ - q;
				kostenProPerson=zwischenPreis*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlGoKart_60_+" Karten!");
				return false;
			}
			break;
		case "G33":
			 if (anzahlGoKart_90_-q>=0) {
				anzahlGoKart_90_ = anzahlGoKart_90_ - q;
				kostenProPerson=zwischenPreis*q;
				anzahlKartenSpeichern(q);
			}else {
				System.out.println("Kauf fehlgeschlagen!!");
				System.out.println("Gibt nur noch "+anzahlGoKart_90_+" Karten!");
				return false;
			}
			break;
		}
		gesamtEinnahmen=gesamtEinnahmen+kostenProPerson;
		return true;
	}
}

