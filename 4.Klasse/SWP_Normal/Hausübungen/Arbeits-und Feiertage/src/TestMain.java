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
	
	static Zaehler z=new Zaehler(2069);
	
	 public static void main(String[] args) throws MalformedURLException, JSONException, IOException {

		z.ausgabe();
		launch(args);
		
		
	}
	 
	@Override
	 public void start(Stage stage) throws Exception {
		 String mo="Montag", di="Dienstag", mi="Mittwoch", don="Donnerstag", fr="Freitag";
		
		 final NumberAxis xAchse=new NumberAxis();
		 final CategoryAxis yAchse=new CategoryAxis();
		
		 final BarChart<Number, String> barChart=new BarChart<Number, String>(xAchse, yAchse);
		 barChart.setTitle("Freie Wochentage");
		 xAchse.setLabel("Wochentage");
		 yAchse.setLabel("Tage");
		
		 XYChart.Series series= new XYChart.Series();
		 series.getData().add(new XYChart.Data(z.mo, mo));
		 series.getData().add(new XYChart.Data(z.di, di));
		 series.getData().add(new XYChart.Data(z.mi, mi));
		 series.getData().add(new XYChart.Data(z.don, don));
		 series.getData().add(new XYChart.Data(z.fr, fr));
		
		
		 Scene scene=new Scene(barChart, 640, 480);
		 barChart.getData().addAll(series);
		 stage.setScene(scene);
		 stage.show();
	 	}

}
