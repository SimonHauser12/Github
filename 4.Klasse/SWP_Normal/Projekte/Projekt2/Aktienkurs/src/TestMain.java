import javafx.application.Application;

public abstract class TestMain extends JavaFX{

	static Aktien a=new Aktien(typ, anzahlTage, host, database, user, passwort);
	
	public static void main(String[] args) throws Exception {

		a.verbindungDB();
		a.closePreis();
		
		Application.launch(JavaFX.class, args);
		
		a.DB_SELECT();
	}
}
