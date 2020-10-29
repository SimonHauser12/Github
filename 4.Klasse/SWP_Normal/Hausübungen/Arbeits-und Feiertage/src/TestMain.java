import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import org.json.JSONException;

public class TestMain extends Application{
	
	static DBVerbindung db=new DBVerbindung(2019, 2022);
	
	 public static void main(String[] args) throws MalformedURLException, JSONException, IOException {

		db.ausgabe();
		launch(args);
		
		db.verbindungZuSQL("localhost:3306", "FreieTage", "root", "sh30112002");
	}
	 
	@Override
	 public void start(Stage stage) throws Exception {
		 String mo="Montag", di="Dienstag", mi="Mittwoch", don="Donnerstag", fr="Freitag";
		
		 final NumberAxis xAchse=new NumberAxis();
		 final CategoryAxis yAchse=new CategoryAxis();
		
		 final BarChart<Number, String> barChart=new BarChart<Number, String>(xAchse, yAchse);
		 barChart.setTitle("Freie Wochentage");
		 xAchse.setLabel("Tage");
		 yAchse.setLabel("Wochentage");
		
		 XYChart.Series series= new XYChart.Series();
		 series.getData().add(new XYChart.Data(db.mo, mo));
		 series.getData().add(new XYChart.Data(db.di, di));
		 series.getData().add(new XYChart.Data(db.mi, mi));
		 series.getData().add(new XYChart.Data(db.don, don));
		 series.getData().add(new XYChart.Data(db.fr, fr));
		
		
		 Scene scene=new Scene(barChart, 640, 480);
		 barChart.getData().addAll(series);
		 stage.setScene(scene);
		 stage.show();
	 	}

}
