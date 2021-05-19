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
	int anzahl;
	int tage;
	int ID=1;
	Connection con;

	public Aktien(String s, int t, String h, String d, String u, String p) {
		this.type=s;
		this.tage=t;
		this.anzahl=t;
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
	
	public void strategie_200er(double kapital) {
		int id=600;
		int counter=0;
		int kauf=0;
		double kaufwert = 0;
		int anzahl = 0;
		double durchschnitt = 0;
		String zeitpunkt = "";
		while (id>=1) {
			try {
				oeffnen();
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
						+ "_200erDurchschnitt b where a.ID=b.ID and a.ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
					zeitpunkt = reSe.getString("Zeitpunkt");
				}
				if(id==600 && kauf==0) {
					anzahl=0;
					kauf=0;
					Statement stat2 = con.createStatement();
					try {
						counter++;
						stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200erStrategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
					} catch (SQLException s) {
						stat2.executeUpdate("UPDATE Aktie_"+type+"_200erStrategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
					}
				}else {
					if (kaufwert > durchschnitt && kauf == 0 && id > 1 && id < 600) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						Statement stat2 = con.createStatement();
						try {
							counter++;
							stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200erStrategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
						} catch (SQLException s) {
							stat2.executeUpdate("UPDATE Aktie_"+type+"_200erStrategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
						}
					} else {
						if (kaufwert < durchschnitt && kauf == 1 && id > 1 && id < 600) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							Statement stat2 = con.createStatement();
							try {
								counter++;
								stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200erStrategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
							} catch (SQLException s) {
								stat2.executeUpdate("UPDATE Aktie_"+type+"_200erStrategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
							}
						}else {
							if(id==1) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
								Statement stat2 = con.createStatement();
								try {
									counter++;
									stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200erStrategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
								} catch (SQLException s) {
									stat2.executeUpdate("UPDATE Aktie_"+type+"_200erStrategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
								}
							}
						}
					}
				}
				schliessen();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id--;
		}
	}
	
	public void strategie_200er_3(double kapital) {
		int id=600;
		int counter=0;
		int kauf=0;
		double kaufwert = 0;
		int anzahl = 0;
		double durchschnitt = 0;
		String zeitpunkt = "";
		while (id>=1) {
			try {
				oeffnen();
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
						+ "_200erDurchschnitt b where a.ID=b.ID and a.ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
					zeitpunkt = reSe.getString("Zeitpunkt");
				}
				if(id==600 && kauf==0) {
					anzahl=0;
					kauf=0;
					Statement stat2 = con.createStatement();
					try {
						counter++;
						stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200er_3_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
					} catch (SQLException s) {
						stat2.executeUpdate("UPDATE Aktie_"+type+"_200er_3_Strategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
					}
				}else {
					if (kaufwert > (durchschnitt * 1.03) && kauf == 0 && id > 1 && id < 600) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						Statement stat2 = con.createStatement();
						try {
							counter++;
							stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200er_3_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
						} catch (SQLException s) {
							stat2.executeUpdate("UPDATE Aktie_"+type+"_200er_3_Strategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
						}
					} else {
						if (kaufwert < (durchschnitt * 1.03) && kauf == 1 && id > 1 && id < 600) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							Statement stat2 = con.createStatement();
							try {
								counter++;
								stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200er_3_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
							} catch (SQLException s) {
								stat2.executeUpdate("UPDATE Aktie_"+type+"_200er_3_Strategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
							}
						}else {
							if(id==1) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
								Statement stat2 = con.createStatement();
								try {
									counter++;
									stat2.executeUpdate("INSERT INTO Aktie_"+type+"_200er_3_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
								} catch (SQLException s) {
									stat2.executeUpdate("UPDATE Aktie_"+type+"_200er_3_Strategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
								}
							}
						}
					} 
				}
				schliessen();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id--;
		}
	}
	
	public void strategie_buyandhold(double kapital) {
		int id=600;
		int counter=0;
		int kauf=0;
		double kaufwert = 0;
		int anzahl = 0;
		String zeitpunkt = "";
		while (id>=1) {
			try {
				oeffnen();
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " where ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					zeitpunkt = reSe.getString("Zeitpunkt");
				}
				if(id==600 && kauf==0) {
					kauf=0;
					Statement stat2 = con.createStatement();
					try {
						counter++;
						stat2.executeUpdate("INSERT INTO Aktie_"+type+"_buyandhold_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
					} catch (SQLException s) {
						stat2.executeUpdate("UPDATE Aktie_"+type+"_buyandhold_Strategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
					}
					id--;
				}else {
					if (id > 1 && kauf == 0) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						Statement stat2 = con.createStatement();
						try {
							counter++;
							stat2.executeUpdate("INSERT INTO Aktie_"+type+"_buyandhold_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
						} catch (SQLException s) {
							stat2.executeUpdate("UPDATE Aktie_"+type+"_buyandhold_Strategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
						}
						id=1;
					} else {
						if (id == 1) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							Statement stat2 = con.createStatement();
							try {
								counter++;
								stat2.executeUpdate("INSERT INTO Aktie_"+type+"_buyandhold_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
							} catch (SQLException s) {
								stat2.executeUpdate("UPDATE Aktie_"+type+"_buyandhold_Strategie Set Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
							}
							id--;
						}
					} 
				}
				schliessen();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		}
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
			oeffnen();
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
						} catch (SQLException e) {
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
							} catch (SQLException e) {
								stat3.executeUpdate("UPDATE Aktie_" + type + " Set TagesEndPreis="
										+ reSe.getString("TagesEndPreis") + " where ID=" + reSe.getString("ID"));
							} 
						}
					}
				}
			}
			schliessen();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void gleitenderDurchschnitt(int max){
		int id=1;
		while (id<=(max-200)) {
			try {
				oeffnen();
				Statement stat=con.createStatement();
				ResultSet reSe=stat.executeQuery("select avg(TagesEndPreis) as Durchschnitt from Aktie_"+type+" where ID>="+id+" and ID<"+(id+200));	
				while (reSe.next()) {
					double durchschnitt=Double.parseDouble(reSe.getString("Durchschnitt"));
					DB_INSERT(durchschnitt, id);
				}
				id++;
				schliessen();
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
			oeffnen();
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
			stat.execute("create table if not exists Aktie_"+type+"_200erStrategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_200er_3_Strategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			stat.execute("create table if not exists Aktie_"+type+"_buyandhold_Strategie(ID int, Zeitpunkt varchar(25), Name varchar(20), Anzahl int, Wert int, Kapital double, Primary Key(ID))");
			schliessen();
			//s.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_INSERT(String zeitpunkt, double closeWert, double split, int id){
		try {
			oeffnen();
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO " + type + "_roh Values("+id+",'" + zeitpunkt + "'," + closeWert + ","+split+")");
				
			} catch (SQLException e) {
				stat.executeUpdate("UPDATE " + type + "_roh Set TagesEndPreis="+closeWert+" where ID=" + id);
				stat.executeUpdate("UPDATE " + type + "_roh Set Splitfaktor="+split+" where ID=" + id);
			}
			schliessen();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_INSERT(double durchschnitt, int id){
		try {
			oeffnen();
			Statement stat=con.createStatement();
			try {
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200erDurchschnitt Values("+id+"," + durchschnitt + ")");
				
			} catch (SQLException e) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_200erDurchschnitt Set Durchschnitt="+durchschnitt+" where ID=" + id);
			}
			schliessen();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void DB_SELECT(){
		String name;
		double kapital0 = 100000;
		double kapital1;
		try {
			oeffnen();
			Statement stat=con.createStatement();  
			ResultSet reSe=stat.executeQuery("select Name, Kapital from Aktie_"+type+"_200erStrategie order by id desc limit 1");
			System.out.println();
			while(reSe.next()) {
				System.out.println("200er-Strategie mit splitkorrigierten Werten");
				name=reSe.getString("Name");
				kapital1=Double.parseDouble(reSe.getString("Kapital"));
				System.out.println(name);
				System.out.println("Startkapital: "+kapital0+" | Endkapital: "+kapital1+" --> prozentuelle Veränderung: "+(((kapital1/kapital0)-1)*100)+"%");
			}
			Statement stat2=con.createStatement(); 
			ResultSet reSe2=stat2.executeQuery("select Name, Kapital from Aktie_"+type+"_200er_3_Strategie order by id desc limit 1");
			System.out.println();
			while(reSe2.next()) {	
				System.out.println("200er-3%-Strategie mit splitkorrigierten Werten");
				name=reSe2.getString("Name");
				kapital1=Double.parseDouble(reSe2.getString("Kapital"));
				System.out.println(name);
				System.out.println("Startkapital: "+kapital0+" | Endkapital: "+kapital1+" --> prozentuelle Veränderung: "+(((kapital1/kapital0)-1)*100)+"%");
			}
			Statement stat3=con.createStatement(); 
			ResultSet reSe3=stat3.executeQuery("select Name, Kapital from Aktie_"+type+"_buyandhold_Strategie order by id desc limit 1");
			System.out.println();
			while(reSe3.next()) {	
				System.out.println("BuyAndHold-Strategie mit splitkorrigierten Werten");
				name=reSe3.getString("Name");
				kapital1=Double.parseDouble(reSe3.getString("Kapital"));
				System.out.println(name);
				System.out.println("Startkapital: "+kapital0+" | Endkapital: "+kapital1+" --> prozentuelle Veränderung: "+(((kapital1/kapital0)-1)*100)+"%");
			}
			schliessen();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
}
