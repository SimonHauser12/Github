import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Schulferien{

	ArrayList<LocalDate> schulferien=new ArrayList<LocalDate>();
	
	public void tage(int jahrAnfang) throws MalformedURLException, JSONException, IOException {
		File file = new File(jahrAnfang+".json");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String umwandler=br.readLine();
		String umwandler1="";
		br.close();
		
		String URL_SF= "https://ferien-api.de/api/v1/holidays/BY/"+jahrAnfang;
		for(int i=0; i<138; i++) {
			umwandler1=umwandler1+umwandler.charAt(i);
		}
		JSONObject json_SFa = new JSONObject(IOUtils.toString(new URL(URL_SF), Charset.forName("UTF-8")).replace(umwandler1, "").replace("T00:00Z", ""));
		for(int i=138; i<273; i++) {
			umwandler1=umwandler1+umwandler.charAt(i);
		}
		JSONObject json_SFb = new JSONObject(IOUtils.toString(new URL(URL_SF), Charset.forName("UTF-8")).replace(umwandler1, "").replace("T00:00Z", ""));
		for(int i=273; i<412; i++) {
			umwandler1=umwandler1+umwandler.charAt(i);
		}
		JSONObject json_SFc = new JSONObject(IOUtils.toString(new URL(URL_SF), Charset.forName("UTF-8")).replace(umwandler1, "").replace("T00:00Z", ""));
		for(int i=412; i<549; i++) {
			umwandler1=umwandler1+umwandler.charAt(i);
		} 
		JSONObject json_SFd = new JSONObject(IOUtils.toString(new URL(URL_SF), Charset.forName("UTF-8")).replace(umwandler1, "").replace("T00:00Z", ""));
		for(int i=549; i<686; i++) {
			umwandler1=umwandler1+umwandler.charAt(i);
		}
		JSONObject json_SFe = new JSONObject(IOUtils.toString(new URL(URL_SF), Charset.forName("UTF-8")).replace(umwandler1, "").replace("T00:00Z", ""));
		
		anfang_ende(json_SFa);
		anfang_ende(json_SFb);
		anfang_ende(json_SFc);
		anfang_ende(json_SFd);
		anfang_ende(json_SFe);
	}
	
	public void anfang_ende (JSONObject json) throws JSONException {
		LocalDate anfang=LocalDate.parse(json.getString("start"));
		LocalDate ende=LocalDate.parse(json.getString("end"));
		while(anfang.getDayOfYear()<=ende.getDayOfYear()) {
			schulferien.add(anfang);
			anfang=anfang.plusDays(1);
		}
	}
}
	
