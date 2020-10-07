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
		LocalDate datum=LocalDate.parse(tag);
		return datum;
	}
}
