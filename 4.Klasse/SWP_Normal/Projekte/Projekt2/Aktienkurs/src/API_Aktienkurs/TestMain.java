package API_Aktienkurs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.application.Application;

public class TestMain{
	
	static String typ;
	static int anzahlTage=4000;
	static ArrayList<String> ticker=new ArrayList<String>();
	static ArrayList<String> dbData=new ArrayList<String>();
	static String host, database, user, passwort;
	static int id;
	static int idende;
	static int wahl;
	static int zaehler;
	static Scanner r=new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		wahl = wahl();
		int wertewahl=wertewahl();
		System.out.println("Startdatum:");
		String zeitstart = zeit();
		System.out.println("Enddatum:");
		String zeitende = zeit();
		double kapital = kapital();
		config();
		
		for (int i = 0; i < ticker.size(); i++) {
			typ = ticker.get(i);
			Strategien a = new Strategien(typ, anzahlTage, host, database, user, passwort);
			a.oeffnen();
			id=a.zeitpruefen(zeitstart);
			if(wertewahl!=3) werte(wertewahl, a);
			idende=a.zeitpruefen(zeitende);
			strategie(wahl, wertewahl, a, kapital);
			a.schliessen();
			System.out.println(typ);
		}
		System.out.println("fertig");
		select_Strategie_mehrfach(wahl, kapital);
		Class stra = null;
		System.out.println("Diagramm Strategien(0) oder Aktie(1)");
		try {
			switch (r.nextInt()) {
			case 0:
				stra = JavaFX_Strategie.class;
				break;
			case 1:
				stra = JavaFX.class;
				break;
			default:
				break;
			}
		} catch (InputMismatchException e) {
			stra = JavaFX_Strategie.class;
		}
		Application.launch(stra, args);
		r.close();
	}
	
	public static void select_Strategie_mehrfach(int wahl, double kapital){
		double kapital0 = kapital;
		double kapital1;
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			System.out.println();
			System.out.println("Rohwerte:");
			if (wahl==1 || wahl==4) {
				kapital1=0;
				System.out.println("200er-Strategie ");
				System.out.println("Startkapital: "+kapital0);
				for (int i = 0; i < ticker.size(); i++) {
					Statement stat = con.createStatement();
					ResultSet reSe = stat.executeQuery("select Name, Kapital from Aktie_" + ticker.get(i)
							+ "_200erStrategie_Rohwerte order by id desc limit 1");
					while (reSe.next()) {
						kapital1 = kapital1+Double.parseDouble(reSe.getString("Kapital"));
					} 
				} 
				System.out.println("Endkapital: " + kapital1);
				System.out.println("prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				System.out.println();
			}
			if (wahl==2 || wahl==4) {
				kapital1=0;
				System.out.print("200er-3%-Strategie ");
				System.out.println("Startkapital: "+kapital0);
				for (int i = 0; i < ticker.size(); i++) {
					Statement stat2 = con.createStatement();
					ResultSet reSe2 = stat2.executeQuery("select Name, Kapital from Aktie_" + ticker.get(i)
							+ "_200er_3_Strategie_Rohwerte order by id desc limit 1");
					while (reSe2.next()) {
						kapital1 = kapital1+Double.parseDouble(reSe2.getString("Kapital"));
					} 
				} 
				System.out.println("Endkapital: " + kapital1);
				System.out.println("prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				System.out.println();
			}
			if (wahl==3 || wahl==4) {
				kapital1=0;
				System.out.print("BuyAndHold-Strategie ");
			 	System.out.println("Startkapital: "+kapital0);
				for (int i = 0; i < ticker.size(); i++) {
					Statement stat3 = con.createStatement();
					ResultSet reSe3 = stat3.executeQuery("select Name, Kapital from Aktie_" + ticker.get(i)
							+ "_buyandhold_Strategie_Rohwerte order by id desc limit 1");
					while (reSe3.next()) {
						kapital1 = kapital1+Double.parseDouble(reSe3.getString("Kapital"));
					} 
				} 
				System.out.println("Endkapital: " + kapital1);
				System.out.println("prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				System.out.println();
			}
			System.out.println();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public static void werte(int wertewahl, Strategien a) throws Exception {
		a.verbindungDB();
		a.closePreis();
		if(wertewahl==2) a.werte_corrected();
	}
	
	public static void strategie(int wahl, int wertewahl, Strategien a, double kapital) {
		double teil=kapital/ticker.size();
		switch(wahl) {
		case 1: 
			if(wertewahl==2) a.strategie_200er(teil, id, idende); 
			a.strategie_200er_rohwerte(teil, id, idende);
			break;
		case 2: 
			if(wertewahl==2) a.strategie_200er_3(teil, id, idende);
			a.strategie_200er_3_rohwerte(teil, id, idende);
			break;
		case 3:
			if(wertewahl==2) a.strategie_buyandhold(teil, id, idende);
			a.strategie_buyandhold_rohwerte(teil, id, idende);
			break;
		case 4:
			if(wertewahl==2) {
				a.strategie_200er(teil, id, idende); 
				a.strategie_200er_3(teil, id, idende);
				a.strategie_buyandhold(teil, id, idende);
			}
			a.strategie_200er_rohwerte(teil, id, idende);
			a.strategie_200er_3_rohwerte(teil, id, idende);
			a.strategie_buyandhold_rohwerte(teil, id, idende);
			break;
		default:
			break;
		}
	}
	
	public static int wahl() {
		int i=0;
		System.out.println("Bitte Strategie auswählen: ");
		System.out.println("200er-Strategie(1), 200er-3%-Strategie(2), BuyandHold-Strategie(3), Alle Strategien(4)");
		try {
			i=r.nextInt();
			if(i<1 || i>4) i=4;
		}catch(InputMismatchException d) {
			i=4;
		}
		return i; 
	}
	
	public static int wertewahl() {
		int i=0;
		System.out.println("neue Werte in DB einlesen:");
		System.out.println("Rohwerte(1), Rohwerte&Werte_splitcorrected(2), nichts einlesen(3)");
		try {
			i=r.nextInt();
			if(i<1 && i>3) i=3;
		}catch(InputMismatchException d) {
			i=1;
		}
		return i; 
	}
	
	public static double kapital() {
		double k=0;
		System.out.print("Bitte Kapital angeben: ");
		try {
			k=r.nextDouble();
		}catch(InputMismatchException f) {
			k=100000;
		}
		return k;
	}
	
	public static String zeit() {
		String s="";
		System.out.print("Bitte Jahr angeben(2011-2021): ");
		try {
			int w=r.nextInt();
			if(w<2010 || w>2021) {
				throw new InputMismatchException();
			}else {
				s=s+w;
			}
		}catch(InputMismatchException k) {
			if(zaehler==0) {
				s=s+2017;
			}else {
				s=s+2021;
			}
		}
		System.out.print("Bitte Monat angeben(1-12): ");
		try {
			int w=r.nextInt();
			if(w<1 || w>12) {
				throw new InputMismatchException();
			}else {
				if(w<10) {
					s=s+"-0"+w;
				}else {
					s=s+"-"+w;
				}
			}
		}catch(InputMismatchException x) {
			s=s+"-04";
		}
		System.out.print("Bitte Tag angeben(1-31): ");
		try {
			int w=r.nextInt();
			if(w<1 || w>31) {
				throw new InputMismatchException();
			}else {
				if(w<10) {
					s=s+"-0"+w;
				}else {
					s=s+"-"+w;
				}
			}
		}catch(InputMismatchException c) {
			s=s+"-20";
		}
		zaehler++;
		return s;
	}
	
	public static void config() throws IOException {
		int counter=0;
		File file = new File("D:\\SimonHTL\\Programmieren\\Programmieren\\API2_Aktienkurs\\config.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String zeile="";
		while((zeile=br.readLine())!=null) {
			if(counter<4) dbData.add(zeile);
			if(counter>=4) ticker.add(zeile);
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
