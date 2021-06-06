package API_Aktienkurs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Strategien extends Aktien{
	
	int kauf=0;
	double kaufwert = 0;
	double durchschnitt = 0;
	int counter=0;
	int anzahl = 0;
	boolean wert;
	LocalDate zeit;
	String zeitpunkt = "";
	
	public Strategien(String s, int t, String h, String d, String u, String p) {
		super(s, t, h, d, u, p);
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
	
	public void strategie_200er(double kapital, int id, int max) {
		counter=0;
		anzahl = 0;
		wert=false;
		
		int groesse=id;
		while (id<=max) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
						+ "_200erDurchschnitt b where a.ID=b.ID and a.ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
					zeitpunkt = reSe.getString("Zeitpunkt");
				}
				if(id==groesse && kauf==0) {
					anzahl=0;
					kauf=0;
					id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
				}else {
					if (kaufwert > durchschnitt && kauf == 0 && id > groesse && id < max) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
					} else {
						if (kaufwert < durchschnitt && kauf == 1 && id > groesse && id < max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
						}else {
							if(id==max) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
								id=strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id++;
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
	
	public void strategie_200er_3(double kapital, int id, int max) {
		counter=0;
		anzahl = 0;
		wert=false;

		int groesse=id;
		while (id<=max) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
						+ "_200erDurchschnitt b where a.ID=b.ID and a.ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					durchschnitt = Double.parseDouble(reSe.getString("Durchschnitt"));
					zeitpunkt = reSe.getString("Zeitpunkt");
				}
				if(id==groesse && kauf==0) {
					anzahl=0;
					kauf=0;
					id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
				}else {
					if (kaufwert > (durchschnitt * 1.03) && kauf == 0 && id > groesse && id < max) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
					} else {
						if (kaufwert < (durchschnitt * 1.03) && kauf == 1 && id > groesse && id < max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
						}else {
							if(id==max) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
								id=strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
							}
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id++;
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
	
	public void strategie_buyandhold(double kapital, int id, int max) {
		counter=0;
		anzahl = 0;
		wert=false;
		
		int groesse=id;
		while (id<=max) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " where ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					zeitpunkt = reSe.getString("Zeitpunkt");
				}
				if(id==groesse && kauf==0) {
					kauf=0;
					id=strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
					id++;
				}else {
					if (id > groesse && id < max && kauf == 0) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						id=strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
						id=max;
					} else {
						if (id == max) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							id=strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital, id);
							id++;
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
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

	public void strategie_200er_rohwerte(double kapital, int id, int max) {
		counter=0;
		anzahl=0;
		wert=false;
		double split=0;
		
		int groesse=id;
		while (id<=max) {
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
				if(split>1 && id > groesse && id < max) {
					anzahl = (int)(anzahl*split);
					id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
				}else {
					if(id==groesse && kauf==0) {
						anzahl=0;
						kauf=0;
						id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
					}else {
						if (kaufwert > durchschnitt && kauf == 0 && id > groesse && id < max) {
							anzahl = (int) (kapital / kaufwert);
							kapital = kapital - (anzahl * kaufwert);
							kauf = 1;
							id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
						} else {
							if (kaufwert < durchschnitt && kauf == 1 && id > groesse && id < max) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl = 0;
								kauf = 0;
								id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
							}else {
								if(id==max) {
									kapital = kapital + (anzahl * kaufwert);
									anzahl=0;
									kauf=0;
									id=strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden 1 fehlgeschlagen");
			}
			id++;
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
	
	public void strategie_200er_3_rohwerte(double kapital, int id, int max) {
		counter=0;
		anzahl=0;
		wert=false;
		double split=0;
		
		int groesse=id;
		while (id<=max) {
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
				if(split>1 && id > groesse && id < max) {
					anzahl = (int)(anzahl*split);
					id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
				}else {
					if(id==groesse && kauf==0) {
						anzahl=0;
						kauf=0;
						id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
					}else {
						if (kaufwert > (durchschnitt * 1.03) && kauf == 0 && id > groesse && id < max) {
							anzahl = (int) (kapital / kaufwert);
							kapital = kapital - (anzahl * kaufwert);
							kauf = 1;
							id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
						} else {
							if (kaufwert < (durchschnitt * 1.03) && kauf == 1 && id > groesse && id < max) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl = 0;
								kauf = 0;
								id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
							}else {
								if(id==max) {
									kapital = kapital + (anzahl * kaufwert);
									anzahl=0;
									kauf=0;
									id=strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
								}
							}
						} 
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden 3 fehlgeschlagen");
			}
			id++;
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
	
	public void strategie_buyandhold_rohwerte(double kapital, int id, int max) {
		counter=0;
		anzahl=0;
		wert=false;
		double split=0;
		
		int groesse=id;
		while (id<=max) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from " + type + "_roh where ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					zeitpunkt = reSe.getString("Zeitpunkt");
					split=Double.parseDouble(reSe.getString("Splitfaktor"));
				}
				if(split>1 && id > groesse && id < max) {
					anzahl = (int)(anzahl*split);
					id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
				}else {
					if(id==groesse && kauf==0) {
						kauf=0;
						id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
					}else {
						if (id==groesse+1 && kauf == 0) {
							anzahl = (int) (kapital / kaufwert);
							kapital = kapital - (anzahl * kaufwert);
							kauf = 1;
							id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
						} else {
							if (id == max) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl = 0;
								kauf = 0;
								id=strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split, id);
							}
						} 
					}
				}
				id++;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
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
}
