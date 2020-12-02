import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Aktien {

	String type;
	int tage;
	int zaehler=0;
	String host="localhost:3306"; String database="Aktien"; String user="root"; String passwort="sh30112002";

	public Aktien(String s, int t) {
		this.type=s;
		this.tage=t;
	}
	
	public void closePreis() throws JSONException, MalformedURLException, IOException {
		String URL="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+type+"&outputsize=full&apikey=MV5RNND5KKAK6GOI";
		JSONObject json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8")));
		JSONObject firstStep = (JSONObject) json.get("Time Series (Daily)");
		
		do {
			tage=tage+getPreis(firstStep, "" + LocalDate.now().minusDays(zaehler + 1) + "");
			zaehler++;
		} while (zaehler<tage);
	}
	
	public int getPreis(JSONObject json, String key) throws JSONException {
		JSONObject bestaetigt = null;
		try {
			bestaetigt = (JSONObject) json.get(key);
		} catch (Exception e) {
			return 1;
		}
		String preis = bestaetigt.getString("4. close");
		DB_INSERT(key, Double.parseDouble(preis));
		return 0;
	}
	
	public void verbindungDB() {
		Scanner s=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			System.out.println("Datenbank erstellen?(j/n)");
			if(s.next().equals("j")) {
				stat.execute("DROP DATABASE Aktien");
				stat.execute("CREATE DATABASE IF NOT EXISTS Aktien");
			}
			System.out.println("DB-Tabelle erstellen?(j/n)");
			if(s.next().equals("j")) {
				stat.execute("use Aktien");
				stat.execute("create table Aktie_"+type+"(Zeitpunkt varchar(25), TagesEndPreis double, Primary Key(Zeitpunkt))");
			}
			con.close();
			s.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_INSERT(String zeitpunkt, double closeWert){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO Aktie_" + type + " Values('" + zeitpunkt + "'," + closeWert + ")");
			} catch (Exception e) {
				stat.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="+closeWert+" where Zeitpunkt='" + zeitpunkt + "'");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_SELECT(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			ResultSet reSe=stat.executeQuery("select * from Aktie_"+type);
			while(reSe.next()) {	
				String zeitpunkt=reSe.getString("Zeitpunkt");
				String wert=reSe.getString("TagesEndPreis");
				System.out.println("Zeitpunkt: "+zeitpunkt+"  TagesEndPreis: "+wert);
			}
			con.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
}
