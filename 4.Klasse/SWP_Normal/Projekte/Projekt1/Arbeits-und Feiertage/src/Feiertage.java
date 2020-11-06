import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class Feiertage extends Schulferien{

	ArrayList<LocalDate> feiertage=new ArrayList<LocalDate>();
	int jahrAnfang;
	int jahrEnde;
	
	public Feiertage(int jahr_A, int jahr_E) {
		if((jahr_A<0)||(jahr_E<0)||(jahr_A>jahr_E)) {
			System.out.println("Falsche Jahresangaben");
		}else {
			this.jahrEnde=jahr_E;
			this.jahrAnfang=jahr_A;
		}
	}
	
	public void Datum() throws JSONException, MalformedURLException, IOException {
		while(jahrAnfang<=jahrEnde) {
			String URL = "https://feiertage-api.de/api/?jahr="+jahrAnfang+"&nur_land=BY";
			JSONObject json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8")));
			//Charset wie Antwort verarbeitet wird
			//Utils wandelt zu String um und wandelt in JSONObjekt um
		
			if((jahrAnfang<2023)&&(jahrAnfang>2016)) {
				tage(jahrAnfang);
			}
		
			feiertage.add(getDatum(json, "Neujahrstag"));
			feiertage.add(getDatum(json, "Heilige Drei Könige"));
			feiertage.add(getDatum(json, "Ostermontag"));
			feiertage.add(getDatum(json, "Tag der Arbeit"));
			feiertage.add(getDatum(json, "Christi Himmelfahrt"));
			feiertage.add(getDatum(json, "Pfingstmontag"));
			feiertage.add(getDatum(json, "Fronleichnam"));
			feiertage.add(getDatum(json, "Mariä Himmelfahrt"));
			feiertage.add(LocalDate.parse(jahrAnfang+"-10-26"));
			feiertage.add(getDatum(json, "Allerheiligen"));
			feiertage.add(LocalDate.parse(jahrAnfang+"-12-08"));
			feiertage.add(getDatum(json, "1. Weihnachtstag"));
			feiertage.add(getDatum(json, "2. Weihnachtstag"));
			jahrAnfang++;
		}
	}
	
	public LocalDate getDatum(JSONObject json, String key) throws JSONException {
		JSONObject bestaetigt = (JSONObject) json.get(key);
		String tag = bestaetigt.getString("datum");
		return LocalDate.parse(tag);
	}
	
}
