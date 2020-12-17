package API_Aktienkurs;
import java.util.Scanner;

import javafx.application.Application;

public abstract class TestMain{
	
	static String host="localhost:3306", database="Aktien", user="root", passwort="sh30112002";
	static String typ;
	static int anzahlTage=280;
	
	public static void main(String[] args) throws Exception {
		angaben();
		
		Aktien a=new Aktien(typ, anzahlTage, host, database, user, passwort);
		a.verbindungDB();
		a.closePreis();
		
		Application.launch(JavaFX.class, args);
		
		a.DB_SELECT();
	}
	
	public static void angaben() {
		Scanner a=new Scanner(System.in);
		System.out.println("Firma angeben: ");
		typ=a.nextLine();
	}
}
