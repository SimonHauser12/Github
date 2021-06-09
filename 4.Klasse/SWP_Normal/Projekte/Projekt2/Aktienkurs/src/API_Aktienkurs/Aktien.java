package API_Aktienkurs;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Aktien{

	String host, database, user, passwort;
	String type;
	int tage;
	int max;
	int ID=1;
	Connection con;

	public Aktien(String s, int t, String h, String d, String u, String p) {
		this.type=s;
		this.tage=t;
		this.host=h;
		this.database=d;
		this.user=u;
		this.passwort=p;
	}
	
	public void oeffnen() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void schliessen() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closePreis() throws JSONException, MalformedURLException, IOException {
		String URL="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol="+type+"&outputsize=full&apikey=MV5RNND5KKAK6GOI";
		JSONObject json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8")));
		JSONObject firstStep = (JSONObject) json.get("Time Series (Daily)");
		int zaehler=0;
		LocalDate a;
		
		do {
			a=LocalDate.now().minusDays(tage-zaehler);
			getPreis(firstStep, "" + a + "");
			zaehler++;
			System.out.println(zaehler);
		} while (tage>zaehler);
		maxID();
		gleitenderDurchschnitt_roh();
	}
	
	public void werte_corrected() {
		splitcorrection();
		gleitenderDurchschnitt();
	}
	
	public void splitcorrection() {
		int counter=0;
		try {
			Statement stat=con.createStatement();
			ResultSet reSe=stat.executeQuery("select * from "+type+"_roh");	
			while (reSe.next()) {
				if(Double.parseDouble(reSe.getString("Splitfaktor"))>1) {			
					Statement stat2 = con.createStatement();
					try {
						stat2.executeUpdate("INSERT INTO Aktie_" + type + " Values(" + reSe.getString("ID")
								+ ",'" + reSe.getString("Zeitpunkt") + "'," + reSe.getString("TagesEndPreis")
								+ ")");
					} catch (SQLException e) {
						stat2.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="+ reSe.getString("TagesEndPreis") + ", Zeitpunkt='"+reSe.getString("Zeitpunkt")+"' where ID=" + reSe.getString("ID"));
					} 
					stat2=con.createStatement();
					ResultSet reSe2=stat2.executeQuery("select * from Aktie_"+type+" where id<"+reSe.getString("ID"));
					while (reSe2.next()) {
						double close=Double.parseDouble(reSe2.getString("TagesEndPreis"))/Double.parseDouble(reSe.getString("Splitfaktor"));
						Statement stat3=con.createStatement();
						try {
							stat3.executeUpdate("INSERT INTO Aktie_" + type + " Values("+reSe2.getString("ID")+",'" +reSe2.getString("Zeitpunkt")+ "'," +close+ ")");
						} catch (SQLException e) {
							stat3.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="+close+", Zeitpunkt='"+reSe2.getString("Zeitpunkt")+"' where ID=" + reSe2.getString("ID"));
						}
					}
				}else {
					System.out.println("da");
					if(Double.parseDouble(reSe.getString("Splitfaktor"))==1) {
						if (counter==0) {
							Statement stat3 = con.createStatement();
							try {
								stat3.executeUpdate("INSERT INTO Aktie_" + type + " Values(" + reSe.getString("ID")
										+ ",'" + reSe.getString("Zeitpunkt") + "'," + reSe.getString("TagesEndPreis")
										+ ")");
							} catch (SQLException e) {
								//stat3.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="+ reSe.getString("TagesEndPreis") + " where ID=" + reSe.getString("ID"));
							} 
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void gleitenderDurchschnitt(){
		int id=200;
		while (id<=max) {
			try {
				Statement stat=con.createStatement();
				ResultSet reSe=stat.executeQuery("select avg(TagesEndPreis) as Durchschnitt from Aktie_"+type+" where ID>="+(id-200)+" and ID<="+id);	
				while (reSe.next()) {
					double durchschnitt=Double.parseDouble(reSe.getString("Durchschnitt"));
					DB_INSERT(durchschnitt, id);
				}
				id++;
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		}
	}
	
	public void maxID() {
		try {
			Statement stat=con.createStatement();
			ResultSet reSe=stat.executeQuery("select ID from "+type+"_roh order by ID desc limit 1");	
			while (reSe.next()) {
				max=Integer.parseInt(reSe.getString("ID"));
			}
		}catch(Exception ex){
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void gleitenderDurchschnitt_roh(){
		int id=200;
		while (id<=max) {
			try {
				Statement stat=con.createStatement();
				ResultSet reSe=stat.executeQuery("select avg(TagesEndPreis) as Durchschnitt from "+type+"_roh where ID>="+(id-200)+" and ID<="+id);	
				while (reSe.next()) {
					double durchschnitt=Double.parseDouble(reSe.getString("Durchschnitt"));
					DB_INSERT_roh(durchschnitt, id);
				}
				id++;
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		}
	}
	
	public int getPreis(JSONObject json, String key) throws JSONException, NumberFormatException, MalformedURLException, IOException {
		JSONObject bestaetigt = null;
		try {
			bestaetigt = (JSONObject) json.get(key);
		} catch (Exception e) {
			return 1;
		}
		String preis = bestaetigt.getString("4. close");
		String split = bestaetigt.getString("8. split coefficient");
		DB_INSERT(key, Double.parseDouble(preis), Double.parseDouble(split), ID);
		ID++;
		return 0;
	}
	
	public void verbindungDB() {
		//Scanner s=new Scanner(System.in);
		try {
			Statement stat=con.createStatement();
			/*System.out.println("neue Datenbank erstellen?(j/n)");
			if(s.next().equals("j")) {
				stat.execute("DROP DATABASE IF EXISTS Aktien");
				stat.execute("CREATE DATABASE IF NOT EXISTS Aktien");
			}*/
			stat.execute("use Aktien");
			stat.execute("create table if not exists "+type+"_roh(ID int, Zeitpunkt varchar(25), TagesEndPreis double, Splitfaktor double, Primary Key(Zeitpunkt))");
			stat.execute("create table if not exists Aktie_"+type+"(ID int, Zeitpunkt varchar(25), TagesEndPreis double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200erDurchschnitt(ID int, Durchschnitt double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200erDurchschnitt_roh(ID int, Durchschnitt double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200erStrategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200er_3_Strategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_buyandhold_Strategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200erStrategie_Rohwerte(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200er_3_Strategie_Rohwerte(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_buyandhold_Strategie_Rohwerte(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			//s.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_INSERT(String zeitpunkt, double closeWert, double split, int id){
		try {
			Statement stat=con.createStatement();
			try {
				maxID();
				id=max;
				stat.executeUpdate("INSERT INTO " + type + "_roh Values("+(id+1)+",'" + zeitpunkt + "'," + closeWert + ","+split+")");
			} catch (SQLException e) {
				//stat.executeUpdate("UPDATE "+type+"_roh Set ID="+id+" where Zeitpunkt='" + zeitpunkt+"'");
				return;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_INSERT(double durchschnitt, int id){
		try {
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200erDurchschnitt Values("+id+"," + durchschnitt + ")");
				
			} catch (SQLException e) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_200erDurchschnitt Set Durchschnitt="+durchschnitt+" where ID=" + id);
				//return;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_INSERT_roh(double durchschnitt, int id){
		try {
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200erDurchschnitt_roh Values("+id+"," + durchschnitt + ")");
				
			} catch (SQLException e) {
				//stat.executeUpdate("UPDATE Aktie_"+type+"_200erDurchschnitt_roh Set Durchschnitt="+durchschnitt+" where ID=" + id);
				return;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void select_Strategie_Splitcorrected(int wahl, double kapital){
		String name;
		double kapital0 = kapital;
		double kapital1;
		try {
			System.out.println();
			System.out.println("Splitkorrigierte Werte:");
			if (wahl==1 || wahl==4) {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery(
						"select Name, Kapital from Aktie_" + type + "_200erStrategie order by id desc limit 1");
				while (reSe.next()) {
					System.out.print("200er-Strategie ");
					name = reSe.getString("Name");
					kapital1 = Double.parseDouble(reSe.getString("Kapital"));
					System.out.println(name);
					System.out.println("Startkapital: " + kapital0 + " | Endkapital: " + kapital1
							+ " --> prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				} 
			}
			if (wahl==2 || wahl==4) {
				Statement stat2 = con.createStatement();
				ResultSet reSe2 = stat2.executeQuery(
						"select Name, Kapital from Aktie_" + type + "_200er_3_Strategie order by id desc limit 1");
				while (reSe2.next()) {
					System.out.print("200er-3%-Strategie ");
					name = reSe2.getString("Name");
					kapital1 = Double.parseDouble(reSe2.getString("Kapital"));
					System.out.println(name);
					System.out.println("Startkapital: " + kapital0 + " | Endkapital: " + kapital1
							+ " --> prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				} 
			}
			if (wahl==3 || wahl==4) {
				Statement stat3 = con.createStatement();
				ResultSet reSe3 = stat3.executeQuery(
						"select Name, Kapital from Aktie_" + type + "_buyandhold_Strategie order by id desc limit 1");
				while (reSe3.next()) {
					System.out.print("BuyAndHold-Strategie ");
					name = reSe3.getString("Name");
					kapital1 = Double.parseDouble(reSe3.getString("Kapital"));
					System.out.println(name);
					System.out.println("Startkapital: " + kapital0 + " | Endkapital: " + kapital1
							+ " --> prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				} 
			}
			System.out.println();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void select_Strategie_Rohwerte(int wahl, double kapital){
		String name;
		double kapital0 = kapital;
		double kapital1;
		try {
			System.out.println();
			System.out.println("Rohwerte:");
			if (wahl==1 || wahl==4) {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select Name, Kapital from Aktie_" + type
						+ "_200erStrategie_Rohwerte order by id desc limit 1");
				while (reSe.next()) {
					System.out.print("200er-Strategie ");
					name = reSe.getString("Name");
					kapital1 = Double.parseDouble(reSe.getString("Kapital"));
					System.out.println(name);
					System.out.println("Startkapital: " + kapital0 + " | Endkapital: " + kapital1
							+ " --> prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				} 
			}
			if (wahl==2 || wahl==4) {
				Statement stat2 = con.createStatement();
				ResultSet reSe2 = stat2.executeQuery("select Name, Kapital from Aktie_" + type
						+ "_200er_3_Strategie_Rohwerte order by id desc limit 1");
				while (reSe2.next()) {
					System.out.print("200er-3%-Strategie ");
					name = reSe2.getString("Name");
					kapital1 = Double.parseDouble(reSe2.getString("Kapital"));
					System.out.println(name);
					System.out.println("Startkapital: " + kapital0 + " | Endkapital: " + kapital1
							+ " --> prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				} 
			}
			if (wahl==3 || wahl==4) {
				Statement stat3 = con.createStatement();
				ResultSet reSe3 = stat3.executeQuery("select Name, Kapital from Aktie_" + type
						+ "_buyandhold_Strategie_Rohwerte order by id desc limit 1");
				while (reSe3.next()) {
					System.out.print("BuyAndHold-Strategie ");
					name = reSe3.getString("Name");
					kapital1 = Double.parseDouble(reSe3.getString("Kapital"));
					System.out.println(name);
					System.out.println("Startkapital: " + kapital0 + " | Endkapital: " + kapital1
							+ " --> prozentuelle Veränderung: " + (((kapital1 / kapital0) - 1) * 100) + "%");
				} 
			}
			System.out.println();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
}
