import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBVerbindung extends Zaehler{
	
	int jahr_A;
	int jahr_E;
	
	public DBVerbindung(int jahr_A, int jahr_E) {
		super(jahr_A, jahr_E);
		this.jahr_A=jahr_A;
		this.jahr_E=jahr_E;
	}

	public void verbindungZuSQL(String host, String database, String user, String passwort) {
		Scanner s=new Scanner(System.in);
		try {
			//laden der Treiberklasse
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Verbinden
			Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
			//Statement
			Statement stat=con.createStatement();
			//SQL-Tabelle befüllen
			System.out.println("Daten in Tabelle schreiben?(j/n)");
			if(s.next().equals("j")) {
				stat.execute("drop database FreieTage");
				stat.execute("Create Database if not exists FreieTage");
				stat.execute("use FreieTage");
				stat.execute("create table AnzahlFeiertage(jahrAnfang int, jahrEnde int, Montag int, Dienstag int, Mittwoch int, Donnerstag int, Freitag int, Primary Key(jahrEnde))");
				stat.execute("INSERT INTO AnzahlFeiertage(jahrAnfang, jahrEnde, Montag, Dienstag, Mittwoch, Donnerstag, Freitag) Values("+jahr_A+","+jahr_E+","+mo+","+di+","+mi+","+don+","+fr+")");
			}
			System.out.println("Daten von Tabelle auslesen?(j/n)");
			if(s.next().equals("j")) {
				//SQL-Tabelle auslesen	
				ResultSet reSe=stat.executeQuery("select * from AnzahlFeiertage");
				while(reSe.next()) {
					String jA=reSe.getString("jahrAnfang");
					String jE=reSe.getString("jahrEnde");
					String Montag=reSe.getString("Montag");
					String Dienstag=reSe.getString("Dienstag");
					String Mittwoch=reSe.getString("Mittwoch");
					String Donnerstag=reSe.getString("Donnerstag");
					String Freitag=reSe.getString("Freitag");
					System.out.println("JahrAnfang:"+jA+", JahrEnde:"+jE+", Montag:"+Montag+", Dienstag:"+Dienstag+", Mittwoch:"+Mittwoch+", Donnerstag:"+Donnerstag+", Freitag:"+Freitag);
				}
			}
			s.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}			
}
