import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class Feiertage {

	ArrayList<LocalDate> feiertage=new ArrayList<LocalDate>();
	int jahrAnfang=2020;
	int jahrEnde;
	
	public Feiertage(int jahr) {
		if(jahr<2020) {
			System.out.println("Keine Negativen Jahre!");
		}else {
			this.jahrEnde=jahr;
		}
	}
	
	public void Datum() throws JSONException, MalformedURLException, IOException {
		
		while(jahrAnfang<=jahrEnde) {
			String URL = "https://feiertage-api.de/api/?jahr="+jahrAnfang+"&nur_land=BY";
			JSONObject json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8")));
			
			feiertage.add(getDate(json, "Neujahrstag"));
			feiertage.add(getDate(json, "Heilige Drei Könige"));
			feiertage.add(getDate(json, "Karfreitag"));
			feiertage.add(getDate(json, "Ostermontag"));
			feiertage.add(getDate(json, "Tag der Arbeit"));
			feiertage.add(getDate(json, "Christi Himmelfahrt"));
			feiertage.add(getDate(json, "Pfingstmontag"));
			feiertage.add(getDate(json, "Fronleichnam"));
			feiertage.add(getDate(json, "Augsburger Friedensfest"));
			feiertage.add(getDate(json, "Mariä Himmelfahrt"));
			feiertage.add(getDate(json, "Tag der Deutschen Einheit"));
			feiertage.add(getDate(json, "Allerheiligen"));
			feiertage.add(getDate(json, "Buß- und Bettag"));
			feiertage.add(getDate(json, "1. Weihnachtstag"));
			feiertage.add(getDate(json, "2. Weihnachtstag"));
			jahrAnfang++;
		}
	}
	
	private LocalDate getDate(JSONObject json, String key) throws JSONException {
		JSONObject bestaetigt = (JSONObject) json.get(key);
		String tag = bestaetigt.getString("datum");
		LocalDate a=LocalDate.parse(tag);
		return a;
	}
}
