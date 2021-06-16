package API_Aktienkurs;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Aktien extends DBVerbindung{

	public Aktien(String s, int t, String h, String d, String u, String p) {
		super(s, t, h, d, u, p);
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
		} while (tage>zaehler);
		maxID();
		gleitenderDurchschnitt_roh();
	}
	
	public void werte_corrected() {
		splitcorrection();
		gleitenderDurchschnitt();
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
	
	/*public void select_Strategie_Splitcorrected(int wahl, double kapital){
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
					name = select_StrategieRohwerte_name(wahl);
					kapital1 = select_StrategieRohwerte_kaptial(wahl);
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
	}*/
}
