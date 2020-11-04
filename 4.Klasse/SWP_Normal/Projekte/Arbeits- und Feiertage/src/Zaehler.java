import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

public class Zaehler extends Feiertage{
	
	static int mo=0;
	static int di=0;
	static int mi=0;
	static int don=0;
	static int fr=0;

	public Zaehler(int jahr_A, int jahr_E) {
		super(jahr_A, jahr_E);
	}
	
	public void woche() throws MalformedURLException, JSONException, IOException {
		Datum();
		for(int i=0; i<feiertage.size(); i++) {
			switch(feiertage.get(i).getDayOfWeek()) {
				case MONDAY: mo++; break;
				case TUESDAY: di++; break;
				case WEDNESDAY: mi++; break;
				case THURSDAY: don++; break;
				case FRIDAY: fr++; break;
				default: break;
			}
			for(int j=0; j<schulferien.size(); j++) {
				if((feiertage.get(i).getYear()==schulferien.get(j).getYear())&&(feiertage.get(i).getDayOfYear()==schulferien.get(j).getDayOfYear())) {
					switch(feiertage.get(i).getDayOfWeek()) {
						case MONDAY: mo--; break;
						case TUESDAY: di--; break;
						case WEDNESDAY: mi--; break;
						case THURSDAY: don--; break;
						case FRIDAY: fr--; break;
						default: break;
					}
				}
			}
		}
		
		
	}
	
	public void ausgabe() throws MalformedURLException, JSONException, IOException {
		woche();
		System.out.println("Montag: " +mo);
		System.out.println("Dienstag: " +di);
		System.out.println("Mittwoch: " +mi);
		System.out.println("Donnerstag: " +don);
		System.out.println("Freitag: " +fr);
	}
}
