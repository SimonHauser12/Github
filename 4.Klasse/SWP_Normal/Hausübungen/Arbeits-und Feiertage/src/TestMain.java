import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

public class TestMain {
	
	 public static void main(String[] args) throws MalformedURLException, JSONException, IOException {

		Zaehler z=new Zaehler(2035);
		z.ausgabe();
	}

}
