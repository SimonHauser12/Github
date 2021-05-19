package API_Aktienkurs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;

public class TestMain{
	
	static String typ;
	static int anzahlTage=800;
	static ArrayList<String> ticker=new ArrayList<String>();
	static ArrayList<String> dbData=new ArrayList<String>();
	static String host, database, user, passwort;
	
	public static void main(String[] args) throws Exception {
		double kapital=100000;
		tickernamen();
		dbData();
		//double aufteilen=kapital/ticker.size();
		System.out.println("Start");
		for(int i=0; i<ticker.size(); i++) {
			typ=ticker.get(i);
			Aktien a=new Aktien(typ, anzahlTage, host, database, user, passwort);
			a.verbindungDB();
			a.closePreis();
			a.strategie_200er(kapital);
			a.strategie_200er_3(kapital);
			a.strategie_buyandhold(kapital);
			a.DB_SELECT();
		}
		//Application.launch(JavaFX.class, args);
		System.out.println("fertig");
		
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
	
	public static void dbData() throws IOException {
		File file = new File("D:\\SimonHTL\\Programmieren\\Programmieren\\API2_Aktienkurs\\DB_Daten.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String zeile="";
		while((zeile=br.readLine())!=null) {
			dbData.add(zeile);
			zeile="";
		}
		br.close();
		host=dbData.get(0);
		database=dbData.get(1);
		user=dbData.get(2);
		passwort=dbData.get(3);
	}
}
