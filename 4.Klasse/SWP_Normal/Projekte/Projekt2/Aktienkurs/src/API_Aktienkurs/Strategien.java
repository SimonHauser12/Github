package API_Aktienkurs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Strategien extends Aktien{
	
	int id=600;
	int kauf=0;
	double kaufwert = 0;
	double durchschnitt = 0;
	int counter=0;
	int anzahl = 0;
	boolean wert=false;
	LocalDate zeit;
	String zeitpunkt = "";
	
	public Strategien(String s, int t, String h, String d, String u, String p) {
		super(s, t, h, d, u, p);
	}
	
	public void strategie_200er(double kapital, String start) {
		counter=0;
		anzahl = 0;
		wert=false;
		
		while (wert==false) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
						+ "_200erDurchschnitt b where a.ID=b.ID and a.zeitpunkt='" + start + "'");
				if(reSe.next()==false) {
					throw new Exception();
				}else {
					id = Integer.parseInt(reSe.getString("ID"));
					wert=true;
				}
			} catch (Exception e) {
				zeit=LocalDate.parse(start);
				zeit=zeit.plusDays(1);
				start=""+zeit;
			} 
		}
		
		int groesse=id;
		while (id>=1) {
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
					strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital);
				}else {
					if (kaufwert > durchschnitt && kauf == 0 && id > 1 && id < groesse) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital);
					} else {
						if (kaufwert < durchschnitt && kauf == 1 && id > 1 && id < groesse) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital);
						}else {
							if(id==1) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
								strategie_200er_DB(zeitpunkt, type, anzahl, kauf, kapital);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id--;
		}
	}
	
	public void strategie_200er_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital) {
		try {	
			Statement stat = con.createStatement();
			try {
				counter++;
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200erStrategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
			} catch (SQLException s) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_200erStrategie Set Zeitpunkt='"+zeitpunkt+"', Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void strategie_200er_3(double kapital, String start) {
		counter=0;
		anzahl = 0;
		wert=false;
		
		while (wert==false) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " a, Aktie_" + type
						+ "_200erDurchschnitt b where a.ID=b.ID and a.zeitpunkt='" + start + "'");
				if(reSe.next()==false) {
					throw new Exception();
				}else {
					id = Integer.parseInt(reSe.getString("ID"));
					wert=true;
				}
			} catch (Exception e) {
				zeit=LocalDate.parse(start);
				zeit=zeit.plusDays(1);
				start=""+zeit;
			} 
		}
		
		int groesse=id;
		while (id>=1) {
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
					strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital);
				}else {
					if (kaufwert > (durchschnitt * 1.03) && kauf == 0 && id > 1 && id < groesse) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital);
					} else {
						if (kaufwert < (durchschnitt * 1.03) && kauf == 1 && id > 1 && id < groesse) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital);
						}else {
							if(id==1) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl=0;
								kauf=0;
								strategie_200er_3_DB(zeitpunkt, type, anzahl, kauf, kapital);
							}
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id--;
		}
	}
	
	public void strategie_200er_3_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital) {
		try {	
			Statement stat = con.createStatement();
			try {
				counter++;
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_200er_3_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
			} catch (SQLException s) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_200er_3_Strategie Set Zeitpunkt='"+zeitpunkt+"', Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void strategie_buyandhold(double kapital, String start) {
		counter=0;
		anzahl = 0;
		wert=false;
		
		while (wert==false) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " where zeitpunkt='" + start + "'");
				if(reSe.next()==false) {
					throw new Exception();
				}else {
					id = Integer.parseInt(reSe.getString("ID"));
					wert=true;
				}
			} catch (Exception e) {
				zeit=LocalDate.parse(start);
				zeit=zeit.plusDays(1);
				start=""+zeit;
			} 
		}
		
		int groesse=id;
		while (id>=1) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from Aktie_" + type + " where ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					zeitpunkt = reSe.getString("Zeitpunkt");
				}
				if(id==groesse && kauf==0) {
					kauf=0;
					strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital);
					id--;
				}else {
					if (id > 1 && id < groesse && kauf == 0) {
						anzahl = (int) (kapital / kaufwert);
						kapital = kapital - (anzahl * kaufwert);
						kauf = 1;
						strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital);
						id=1;
					} else {
						if (id == 1) {
							kapital = kapital + (anzahl * kaufwert);
							anzahl = 0;
							kauf = 0;
							strategie_buyandhold_DB(zeitpunkt, type, anzahl, kauf, kapital);
							id--;
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		}
	}
	
	public void strategie_buyandhold_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital) {
		try {	
			Statement stat = con.createStatement();
			try {
				counter++;
				stat.executeUpdate("INSERT INTO Aktie_"+type+"_buyandhold_Strategie Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
			} catch (SQLException s) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_buyandhold_Strategie Set Zeitpunkt='"+zeitpunkt+"', Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
	}

	public void strategie_200er_rohwerte(double kapital, String start) {
		counter=0;
		anzahl=0;
		wert=false;
		double split=0;
		
		while (wert==false) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from " + type + "_roh a, Aktie_" + type
						+ "_200erDurchschnitt_roh b where a.ID=b.ID and a.zeitpunkt='" + start + "'");
				if(reSe.next()==false) {
					throw new Exception();
				}else {
					id = Integer.parseInt(reSe.getString("ID"));
					wert=true;
				}
			} catch (Exception e) {
				zeit=LocalDate.parse(start);
				zeit=zeit.plusDays(1);
				start=""+zeit;
			} 
		}
		
		int groesse=id;
		while (id>=1) {
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
				if(split>1 && id > 1 && id < groesse) {
					anzahl = (int)(anzahl*split);
					strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
				}else {
					if(id==groesse && kauf==0) {
						anzahl=0;
						kauf=0;
						strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
					}else {
						if (kaufwert > durchschnitt && kauf == 0 && id > 1 && id < groesse) {
							anzahl = (int) (kapital / kaufwert);
							kapital = kapital - (anzahl * kaufwert);
							kauf = 1;
							strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
						} else {
							if (kaufwert < durchschnitt && kauf == 1 && id > 1 && id < groesse) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl = 0;
								kauf = 0;
								strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
							}else {
								if(id==1) {
									kapital = kapital + (anzahl * kaufwert);
									anzahl=0;
									kauf=0;
									strategie_200er_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id--;
		}
	}
	
	public void strategie_200er_rohwerte_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, double split) {
		try {	
			Statement stat = con.createStatement();
			try {
				if (split==1) {
					counter++;
					stat.executeUpdate("INSERT INTO Aktie_" + type + "_200erStrategie_Rohwerte Values(" + counter+ ", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital+ ")");
				}else {
					if(split>1) {
						throw new SQLException();
					}
				}
			} catch (SQLException s) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_200erStrategie_Rohwerte Set Zeitpunkt='"+zeitpunkt+"', Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void strategie_200er_3_rohwerte(double kapital,  String start) {
		counter=0;
		anzahl=0;
		wert=false;
		double split=0;
		
		while (wert==false) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from " + type + "_roh a, Aktie_" + type
						+ "_200erDurchschnitt_roh b where a.ID=b.ID and a.zeitpunkt='" + start + "'");
				if(reSe.next()==false) {
					throw new Exception();
				}else {
					id = Integer.parseInt(reSe.getString("ID"));
					wert=true;
				}
			} catch (Exception e) {
				zeit=LocalDate.parse(start);
				zeit=zeit.plusDays(1);
				start=""+zeit;
			} 
		}
		
		int groesse=id;
		while (id>=1) {
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
				if(split>1 && id > 1 && id < groesse) {
					anzahl = (int)(anzahl*split);
					strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
				}else {
					if(id==groesse && kauf==0) {
						anzahl=0;
						kauf=0;
						strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
					}else {
						if (kaufwert > (durchschnitt * 1.03) && kauf == 0 && id > 1 && id < groesse) {
							anzahl = (int) (kapital / kaufwert);
							kapital = kapital - (anzahl * kaufwert);
							kauf = 1;
							strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
						} else {
							if (kaufwert < (durchschnitt * 1.03) && kauf == 1 && id > 1 && id < groesse) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl = 0;
								kauf = 0;
								strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
							}else {
								if(id==1) {
									kapital = kapital + (anzahl * kaufwert);
									anzahl=0;
									kauf=0;
									strategie_200er_3_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
								}
							}
						} 
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
			id--;
		}
	}
	
	public void strategie_200er_3_rohwerte_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, double split) {
		try {	
			Statement stat = con.createStatement();
			try {
				if (split==1) {
					counter++;
					stat.executeUpdate("INSERT INTO Aktie_" + type + "_200er_3_Strategie_Rohwerte Values(" + counter+ ", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital+ ")");
				}else {
					if(split>1) {
						throw new SQLException();
					}
				}
			} catch (SQLException s) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_200er_3_Strategie_Rohwerte Set Zeitpunkt='"+zeitpunkt+"', Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
	
	public void strategie_buyandhold_rohwerte(double kapital, String start) {
		counter=0;
		anzahl=0;
		wert=false;
		double split=0;
		
		while (wert==false) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from " + type + "_roh where zeitpunkt='" + start + "'");
				if(reSe.next()==false) {
					throw new Exception();
				}else {
					id = Integer.parseInt(reSe.getString("ID"));
					wert=true;
				}
			} catch (Exception e) {
				zeit=LocalDate.parse(start);
				zeit=zeit.plusDays(1);
				start=""+zeit;
			} 
		}
		
		int groesse=id;
		while (id>=1) {
			try {
				Statement stat = con.createStatement();
				ResultSet reSe = stat.executeQuery("select * from " + type + "_roh where ID=" + id + "");
				while (reSe.next()) {
					kaufwert = Double.parseDouble(reSe.getString("TagesEndPreis"));
					zeitpunkt = reSe.getString("Zeitpunkt");
					split=Double.parseDouble(reSe.getString("Splitfaktor"));
				}
				if(split>1 && id > 1 && id < groesse) {
					anzahl = (int)(anzahl*split);
					strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
				}else {
					if(id==groesse && kauf==0) {
						kauf=0;
						strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
					}else {
						if (id==groesse-1 && kauf == 0) {
							anzahl = (int) (kapital / kaufwert);
							kapital = kapital - (anzahl * kaufwert);
							kauf = 1;
							strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
						} else {
							if (id == 1) {
								kapital = kapital + (anzahl * kaufwert);
								anzahl = 0;
								kauf = 0;
								strategie_buyandhold_rohwerte_DB(zeitpunkt, type, anzahl, kauf, kapital, split);
							}
						} 
					}
				}
				id--;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		}
	}
	
	public void strategie_buyandhold_rohwerte_DB(String zeitpunkt, String type, int anzahl, int kauf, double kapital, double split) {
		try {	
			Statement stat = con.createStatement();
			try {
				if (split==1) {
					counter++;
					stat.executeUpdate("INSERT INTO Aktie_"+type+"_buyandhold_Strategie_Rohwerte Values("+counter+", '" + zeitpunkt + "', '" + type + "', " + anzahl + ", " + kauf + ", " + kapital + ")");
				}else {
					if(split>1) {
						throw new SQLException();
					}
				}
			} catch (SQLException s) {
				stat.executeUpdate("UPDATE Aktie_"+type+"_buyandhold_Strategie_Rohwerte Set Zeitpunkt='"+zeitpunkt+"', Anzahl="+anzahl+", Wert="+kauf+", Kapital="+kapital+" where ID=" + counter+"");
			}
		} catch (Exception e) {
			System.out.println("Verbinden fehlgeschlagen");
		}
	}
}
