package API_Aktienkurs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;

public class TestMain{
	
	static String typ;
	static int anzahlTage=1000;
	static ArrayList<String> ticker=new ArrayList<String>();
	static ArrayList<String> dbData=new ArrayList<String>();
	static String host, database, user, passwort;
	
	public static void main(String[] args) throws Exception {
		double kapital=kapital();
		String zeit=zeit();
		config();
		//double aufteilen=kapital/ticker.size();
		System.out.println("Start");
		for(int i=0; i<ticker.size(); i++) {
			typ=ticker.get(i);
			Strategien a=new Strategien(typ, anzahlTage, host, database, user, passwort);
			a.oeffnen();
			a.verbindungDB();
			a.closePreis();
			a.strategie_200er_rohwerte(kapital, zeit);
			a.strategie_200er_3_rohwerte(kapital, zeit);
			a.strategie_buyandhold_rohwerte(kapital, zeit);
			a.strategie_200er(kapital, zeit);
			a.strategie_200er_3(kapital, zeit);
			a.strategie_buyandhold(kapital, zeit);
			//a.select_Strategie_Splitcorrected(kapital);
			//a.select_Strategie_Rohwerte(kapital);
			a.schliessen();
		}
		System.out.println("fertig");
		//Application.launch(JavaFX.class, args);	
	}
	
	public static double kapital() {
		Scanner r=new Scanner(System.in);
		System.out.print("Bitte Kapital angeben: ");
		return r.nextDouble();
	}
	
	public static String zeit() {
		Scanner r=new Scanner(System.in);
		System.out.print("Bitte Zeitpunkt angeben: ");
		return r.next();
	}
	
	public static void config() throws IOException {
		int counter=0;
		File file = new File("D:\\SimonHTL\\Programmieren\\Programmieren\\API2_Aktienkurs\\config.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String zeile="";
		while((zeile=br.readLine())!=null) {
			if(counter<4) dbData.add(zeile);
			if(counter>=4) ticker.add(zeile);;
			zeile="";
			counter++;
		}
		host=dbData.get(0);
		database=dbData.get(1);
		user=dbData.get(2);
		passwort=dbData.get(3);

		br.close();
	}
}
