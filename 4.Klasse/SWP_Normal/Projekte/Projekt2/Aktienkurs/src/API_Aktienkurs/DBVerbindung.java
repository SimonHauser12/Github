package API_Aktienkurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBVerbindung {
	
	String host, database, user, passwort;
	String type;
	Connection con;
	int tage;
	int max;
	int ID=1;
	boolean wert;
	double kaufwert = 0;
	double durchschnitt = 0;
	int counter=0;
	String zeitpunkt = "";
	double split=0;

	public DBVerbindung(String s, int t, String h, String d, String u, String p) {
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

	public int zeitpruefen(String zeit) {
		LocalDate i;
		wert=false;
		int a=0;
		while (wert==false) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from " + type + "_roh a, Aktie_" + type
						+ "_200erDurchschnitt_roh b where a.ID=b.ID and a.zeitpunkt='" + zeit + "'");
				if(reSe.next()==false) {
					throw new Exception();
				}else {
					a = Integer.parseInt(reSe.getString("ID"));
					wert=true;
				}
			} catch (Exception e) {
				i=LocalDate.parse(zeit);
				i=i.plusDays(1);
				zeit=""+i;
			} 
		}
		return a;
	}
	
	public void verbindungDB() {
		try {
			Statement stat=con.createStatement();
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
				//stat.executeUpdate("UPDATE Aktie_"+type+"_200erDurchschnitt Set Durchschnitt="+durchschnitt+" where ID=" + id);
				return;
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
	
	public void strategie_200er_(int id) {
		try {
			Statement stat = con.createStatement();
			ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
					+ "_200erDurchschnitt b where a.ID=b.ID and a.ID=" + id + "");
			while (reSe.next()) {
				kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
				durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
				zeitpunkt = reSe.getString("Zeitpunkt");
			}
		} catch (SQLException e) {
			System.out.println("DBVerbindung fehlgeschlagen");
		}
	}
	
	public int strategie_200er_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, int d) {
		try {	
			Statement stat = con.createStatement();
			try {
				counter++;
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200erStrategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
			} catch (SQLException s) {
				stat.executeUpdate("drop table Aktie_"+type+"_200erStrategie");
				stat.executeUpdate("create table if not exists Aktie_"+type+"_200erStrategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
				counter--;
				d--;
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
		return d;
	}
	
	public void strategie_200er_3_(int id) {
		try {
			Statement stat = con.createStatement();
			ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
					+ "_200erDurchschnitt b where a.ID=b.ID and a.ID=" + id + "");
			while (reSe.next()) {
				kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
				durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
				zeitpunkt = reSe.getString("Zeitpunkt");
			}
		} catch (SQLException e) {
			System.out.println("DBVerbindung fehlgeschlagen");
		}
	}
	
	public int strategie_200er_3_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, int d) {
		try {	
			Statement stat = con.createStatement();
			try {
				counter++;
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200er_3_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
			} catch (SQLException s) {
				stat.executeUpdate("drop table Aktie_"+type+"_200er_3_Strategie");
				stat.executeUpdate("create table if not exists Aktie_"+type+"_200er_3_Strategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
				counter--;
				d--;
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
		return d;
	}
	
	public void strategie_buyandhold_(int id) {
		try {
			Statement stat = con.createStatement();
			ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " where ID=" + id + "");
			while (reSe.next()) {
				kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
				zeitpunkt = reSe.getString("Zeitpunkt");
			}
		} catch (SQLException e) {
			System.out.println("DBVerbindung fehlgeschlagen");
		}
	}
	
	public int strategie_buyandhold_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, int d) {
		try {	
			Statement stat = con.createStatement();
			try {
				counter++;
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_buyandhold_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
			} catch (SQLException s) {
				stat.executeUpdate("drop table Aktie_"+type+"_buyandhold_Strategie");
				stat.executeUpdate("create table if not exists Aktie_"+type+"_buyandhold_Strategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
				counter--;
				d--;
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
		return d;
	}
	
	public void strategie_200er_rohwerte_(int id) {
		try {
			Statement stat = con.createStatement();
			ResultSet reSe = stat.executeQuery("select * from " + type + "_roh a, Aktie_" + type
					+ "_200erDurchschnitt_roh b where a.ID=b.ID and a.ID=" + id + "");
			while (reSe.next()) {
				kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
				durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
				zeitpunkt = reSe.getString("Zeitpunkt");
				split=Double.parseDouble(reSe.getString("Splitfaktor"));
			}
		} catch (SQLException e) {
			System.out.println("DBVerbindung fehlgeschlagen");
		}
	}
	
	public int strategie_200er_rohwerte_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, double split, int d) {
		try {	
			Statement stat = con.createStatement();
			try {
				if (split==1) {
					counter++;
					stat.executeUpdate("INSERT INTO Aktie_" + type + "_200erStrategie_Rohwerte Values(" + counter+ ", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital+ ")");
				}else {
					if(split>1) {
						stat.executeUpdate("UPDATE Aktie_"+type+"_200erStrategie_Rohwerte Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
					}
				}
			} catch (SQLException s) {
				stat.executeUpdate("drop table Aktie_"+type+"_200erStrategie_Rohwerte");
				stat.executeUpdate("create table if not exists Aktie_"+type+"_200erStrategie_Rohwerte(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
				counter--;
				d--;
			}
		} catch (Exception e) {
			System.out.println("Verbinden 2 fehlgeschlagen");
		}
		return d;
	}
	
	public void strategie_200er_3_rohwerte_(int id) {
		try {
			Statement stat = con.createStatement();
			ResultSet reSe = stat.executeQuery("select * from " + type + "_roh a, Aktie_" + type
					+ "_200erDurchschnitt_roh b where a.ID=b.ID and a.ID=" + id + "");
			while (reSe.next()) {
				kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
				durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
				zeitpunkt = reSe.getString("Zeitpunkt");
				split=Double.parseDouble(reSe.getString("Splitfaktor"));
			}
		} catch (SQLException e) {
			System.out.println("DBVerbindung fehlgeschlagen");
		}
	}
	
	public int strategie_200er_3_rohwerte_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, double split, int d) {
		try {	
			Statement stat = con.createStatement();
			try {
				if (split==1) {
					counter++;
					stat.executeUpdate("INSERT INTO Aktie_" + type + "_200er_3_Strategie_Rohwerte Values(" + counter+ ", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital+ ")");
				}else {
					if(split>1) {
						stat.executeUpdate("UPDATE Aktie_"+type+"_200er_3_Strategie_Rohwerte Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
					}
				}
			} catch (SQLException s) {
				stat.executeUpdate("drop table Aktie_"+type+"_200er_3_Strategie_Rohwerte");
				stat.executeUpdate("create table if not exists Aktie_"+type+"_200er_3_Strategie_Rohwerte(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
				counter--;
				d--;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
		return d;
	}
	
	public void strategie_buyandhold_rohwerte_(int id) {
		try {
			Statement stat = con.createStatement();
			ResultSet reSe = stat.executeQuery("select * from " + type + "_roh where ID=" + id + "");
			while (reSe.next()) {
				kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
				zeitpunkt = reSe.getString("Zeitpunkt");
				split=Double.parseDouble(reSe.getString("Splitfaktor"));
			}
		} catch (SQLException e) {
			System.out.println("DBVerbindung fehlgeschlagen");
		}
	}
	
	public int strategie_buyandhold_rohwerte_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, double split, int d) {
		try {	
			Statement stat = con.createStatement();
			try {
				if (split==1) {
					counter++;
					stat.executeUpdate("INSERT INTO Aktie_"+type+"_buyandhold_Strategie_Rohwerte Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
				}else {
					if(split>1) {
						stat.executeUpdate("UPDATE Aktie_"+type+"_buyandhold_Strategie_Rohwerte Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
					}
				}
			} catch (SQLException s) {
				stat.executeUpdate("drop table Aktie_"+type+"_buyandhold_Strategie_Rohwerte");
				stat.executeUpdate("create table if not exists Aktie_"+type+"_buyandhold_Strategie_Rohwerte(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
				counter--;
				d--;
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
		return d;
	}
	
	public void splitcorrection() {
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
					if(Double.parseDouble(reSe.getString("Splitfaktor"))==1) {
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
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
		
	public void select_Strategie_mehrfach(int wahl, double kapital, ArrayList<String> ticker){
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
}
