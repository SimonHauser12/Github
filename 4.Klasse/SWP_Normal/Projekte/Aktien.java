package API_Aktienkurs;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Aktien{

	String host, database, user, passwort;
	String type;
	int anzahl;
	int tage;
	int ID=1;

	public Aktien(String s, int t, String h, String d, String u, String p) {
		this.type=s;
		this.tage=t;
		this.anzahl=t;
		this.host=h;
		this.database=d;
		this.user=u;
		this.passwort=p;
	}
	
	public void closePreis() throws JSONException, MalformedURLException, IOException {
		String URL="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol="+type+"&outputsize=full&apikey=MV5RNND5KKAK6GOI";
		JSONObject json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8")));
		JSONObject firstStep = (JSONObject) json.get("Time Series (Daily)");
		int zaehler=0;
		
		do {
			tage=tage+getPreis(firstStep, "" + LocalDate.now().minusDays(zaehler + 1) + "", zaehler);
			zaehler++;
		} while (zaehler<tage);

		splitcorrection();
		gleitenderDurchschnitt(anzahl);
	}
	
	public void splitcorrection() {
		int counter=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			ResultSet reSe=stat.executeQuery("select * from "+type+"_roh");	
			while (reSe.next()) {
				if(Double.parseDouble(reSe.getString("Splitfaktor"))>1) {
					Statement stat2=con.createStatement();
					ResultSet reSe2=stat2.executeQuery("select * from "+type+"_roh where id>"+reSe.getString("ID"));
					while (reSe2.next()) {
						double close=Double.parseDouble(reSe2.getString("TagesEndPreis"))/Double.parseDouble(reSe.getString("Splitfaktor"));
						Statement stat3=con.createStatement();
						try {
							stat3.executeUpdate("INSERT INTO Aktie_" + type + " Values("+reSe2.getString("ID")+",'" +reSe2.getString("Zeitpunkt")+ "'," +close+ ")");
						} catch (Exception e) {
							stat3.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="+close+" where ID=" + reSe2.getString("ID"));
						}
					}
					counter++;
				}else {
					if(Double.parseDouble(reSe.getString("Splitfaktor"))==1) {
						if (counter==0) {
							Statement stat3 = con.createStatement();
							try {
								stat3.executeUpdate("INSERT INTO Aktie_" + type + " Values(" + reSe.getString("ID")
										+ ",'" + reSe.getString("Zeitpunkt") + "'," + reSe.getString("TagesEndPreis")
										+ ")");
							} catch (Exception e) {
								stat3.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="
										+ reSe.getString("TagesEndPreis") + " where ID=" + reSe.getString("ID"));
							} 
						}
					}
				}
			}
			con.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void gleitenderDurchschnitt(int max){
		int id=1;
		while (id<=(max-200)) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
				Statement stat=con.createStatement();
				ResultSet reSe=stat.executeQuery("select avg(TagesEndPreis) as Durchschnitt from Aktie_"+type+" where ID>="+id+" and ID<"+(id+200));	
				while (reSe.next()) {
					double durchschnitt=Double.parseDouble(reSe.getString("Durchschnitt"));
					DB_INSERT(durchschnitt, id);
				}
				id++;
				con.close();
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		}
	}
	
	public int getPreis(JSONObject json, String key, int z) throws JSONException, NumberFormatException, MalformedURLException, IOException {
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			/*System.out.println("neue Datenbank erstellen?(j/n)");
			if(s.next().equals("j")) {
				stat.execute("DROP DATABASE IF EXISTS Aktien");
				stat.execute("CREATE DATABASE IF NOT EXISTS Aktien");
			}*/
			stat.execute("use Aktien");
			stat.execute("create table if not exists "+type+"_roh(ID int, Zeitpunkt varchar(25), TagesEndPreis double, Splitfaktor double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"(ID int, Zeitpunkt varchar(25), TagesEndPreis double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200erDurchschnitt(ID int, Durchschnitt double, Primary Key(ID))");
			con.close();
			//s.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_INSERT(String zeitpunkt, double closeWert, double split, int id){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO " + type + "_roh Values("+id+",'" + zeitpunkt + "'," + closeWert + ","+split+")");
				
			} catch (Exception e) {
				stat.executeUpdate("UPDATE " + type + "_roh Set TagesEndPreis="+closeWert+" where ID=" + id);
				stat.executeUpdate("UPDATE " + type + "_roh Set Splitfaktor="+split+" where ID=" + id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	/*public void DB_INSERT(String zeitpunkt, double closeWert, int id){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO Aktie_" + type + " Values("+id+",'" + zeitpunkt + "'," + closeWert + ")");
				
			} catch (Exception e) {
				stat.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="+closeWert+" where ID=" + id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}*/
	
	public void DB_INSERT(double durchschnitt, int id){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200erDurchschnitt Values("+id+"," + durchschnitt + ")");
				
			} catch (Exception e) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_200erDurchschnitt Set Durchschnitt="+durchschnitt+" where ID=" + id);
			}
			con.close();
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
			ResultSet reSe=stat.executeQuery("select Zeitpunkt, TagesEndPreis from Aktie_"+type);
			System.out.println();
			System.out.println("TagesEndPreis:");
			System.out.println(" Zeitpunkt | TagesEndPreis");
			while(reSe.next()) {	
				String zeitpunkt=reSe.getString("Zeitpunkt");
				String wert=reSe.getString("TagesEndPreis");
				System.out.println(zeitpunkt+" | "+wert);
			}
			con.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
}
