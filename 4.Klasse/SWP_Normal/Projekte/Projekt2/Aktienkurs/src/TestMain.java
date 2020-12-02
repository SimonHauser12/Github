import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

public abstract class TestMain {

	static Aktien a=new Aktien("AAPL", 30);;
	
	public static void main(String[] args) throws MalformedURLException, JSONException, IOException {

		a.verbindungDB();
		a.closePreis();
		a.DB_SELECT();

	}
}
