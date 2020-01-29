import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		System.out.println("Willkommen im Ticketshop!");
		Scanner e=new Scanner(System.in);
		String s;
		Ticketshop k=new Ticketshop();
		do {
			k.registrieren();
			k.bestellungen();
			System.out.println("Neue Bestellung? ja(j), nein(n)");
			s=e.next();
		} while (s.equals("j"));
		e.close();
		System.out.println();
		System.out.println("Verkaufsergebniss:");
		System.out.println("Es haben "+k.getAnzahlBestellungen()+" Menschen Karten gekauft. Gesamteinnahme beträgt: "+k.getGesamtEinnahmen()+"€");

	}

}
