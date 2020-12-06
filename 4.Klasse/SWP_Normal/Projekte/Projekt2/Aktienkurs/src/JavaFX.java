import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class JavaFX extends Application {
	
	static String host="localhost:3306", database="Aktien", user="root", passwort="sh30112002";
	static String typ="AAPL";
	static int anzahlTage=60;
	
	@Override public void start(Stage stage) throws Exception {
	 	
		 final NumberAxis yAchse=new NumberAxis();
		 final CategoryAxis xAchse=new CategoryAxis();
		
		 final AreaChart<String, Number> areaChart=new AreaChart<String, Number>(xAchse, yAchse);
		 areaChart.setTitle("Aktie_"+typ);
		 xAchse.setLabel("Tage");
		 yAchse.setLabel("Wert_Aktie");
		
		 XYChart.Series series_aktie= new XYChart.Series();
		 XYChart.Series series_durchschnitt= new XYChart.Series();
		 series_aktie.setName("Wert Aktie");
		 series_durchschnitt.setName("200er Durchschnitt");
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwort+"&serverTimezone=UTC");
				Statement stat=con.createStatement();
				ResultSet reSe=stat.executeQuery("select * from Aktie_"+typ);
				while(reSe.next()) {
					series_aktie.getData().add(new XYChart.Data(reSe.getString("Zeitpunkt"), Double.parseDouble(reSe.getString("TagesEndPreis"))));
				}
				reSe=stat.executeQuery("select * from Aktie_"+typ+"_200erDurchschnitt");
				while(reSe.next()) {	
					series_durchschnitt.getData().add(new XYChart.Data(reSe.getString("Zeitpunkt"), Double.parseDouble(reSe.getString("Durchschnitt"))));
				}
				con.close();
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		
		 Scene scene=new Scene(areaChart, 800, 600);
		 areaChart.getData().addAll(series_aktie, series_durchschnitt);
		 stage.setScene(scene);
		 stage.show();
	 	}
}
