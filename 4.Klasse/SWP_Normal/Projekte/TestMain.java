package API_Aktienkurs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;

public abstract class TestMain{
	
	static String host="localhost:3306", database="Aktien", user="root", passwort="sh30112002";
	static String typ;
	static int anzahlTage=300;
	static ArrayList<String> ticker=new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		tickernamen();
		for(int i=0; i<ticker.size(); i++) {
			typ=ticker.get(i);
			Aktien a=new Aktien(typ, anzahlTage, host, database, user, passwort);
			a.verbindungDB();
			a.closePreis();

		}
		Application.launch(JavaFX.class, args);
		
		//a.DB_SELECT();
	}
	
	public static void tickernamen() throws IOException {
		File file = new File("D:\\SimonHTL\\Programmieren\\Programmieren\\API2_Aktienkurs\\Tickernamen.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String zeile="";
		while((zeile=br.readLine())!=null) {
			ticker.add(zeile);
			zeile="";
		}
		br.close();
	}
}
