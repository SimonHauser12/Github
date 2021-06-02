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
	static int anzahlTage=2000;
	static ArrayList<String> ticker=new ArrayList<String>();
	static ArrayList<String> dbData=new ArrayList<String>();
	static String host, database, user, passwort;
	static String zeit;
	static int wahl;
	static Scanner r=new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		do {
			wahl = wahl();
			zeit = zeit();
			double kapital = kapital();
			config();
			//double aufteilen=kapital/ticker.size();
			for (int i = 0; i < ticker.size(); i++) {
				typ = ticker.get(i);
				Strategien a = new Strategien(typ, anzahlTage, host, database, user, passwort);
				a.oeffnen();
				//a.verbindungDB();
				//a.closePreis();
				strategie(wahl, a, kapital, zeit);
				a.schliessen();
			}
			Class stra = null;
			System.out.println("Diagramm Strategien(0) oder Aktie(1)");
			switch (r.nextInt()) {
			case 0:
				stra = JavaFX_Strategie.class;
				break;
			case 1:
				stra = JavaFX.class;
				break;
			default:
				System.out.println("Es wird kein Diagramm angezeigt!");
				break;
			}
			Application.launch(stra, args);
			System.out.println("wiederholen(1), beenden(0)");
		} while (r.nextInt()==1);
		System.out.println();
		r.close();
	}
	
	public static void strategie(int wahl, Strategien a, double kapital, String zeit) {
		boolean probe=false;
		
		do {
			switch(wahl) {
			case 1: 
				a.strategie_200er_rohwerte(kapital, zeit);
				a.strategie_200er(kapital, zeit);
				probe=true;
				break;
			case 2: 
				a.strategie_200er_3_rohwerte(kapital, zeit);
				a.strategie_200er_3(kapital, zeit);
				probe=true;
				break;
			case 3:
				a.strategie_buyandhold_rohwerte(kapital, zeit);
				a.strategie_buyandhold(kapital, zeit);
				probe=true;
				break;
			case 4:
				a.strategie_200er_rohwerte(kapital, zeit);
				a.strategie_200er(kapital, zeit); 
				a.strategie_200er_3_rohwerte(kapital, zeit);
				a.strategie_200er_3(kapital, zeit);
				a.strategie_buyandhold_rohwerte(kapital, zeit);
				a.strategie_buyandhold(kapital, zeit);
				probe=true;
				break;
			default:
				System.out.println("nur zwischen 1 und 4 wählen");
				wahl=wahl();
				probe=false;
				break;
			}
		}while(probe==false);
		
		System.out.println();
		System.out.println("Ausgabe: ");
		a.select_Strategie_Splitcorrected(wahl, kapital);
		a.select_Strategie_Rohwerte(wahl, kapital);
	}
	
	public static int wahl() {
		System.out.println("Bitte Strategie auswählen: ");
		System.out.println("200er-Strategie(1), 200er-3%-Strategie(2), BuyandHold-Strategie(3), Alle Strategien(4)");
		return r.nextInt();
	}
	
	public static double kapital() {
		System.out.print("Bitte Kapital angeben: ");
		return r.nextDouble();
	}
	
	public static String zeit() {
		System.out.print("Bitte Zeitpunkt angeben(2015<): ");
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
