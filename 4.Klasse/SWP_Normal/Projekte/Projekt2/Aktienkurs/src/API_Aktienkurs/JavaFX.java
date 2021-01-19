package API_Aktienkurs;
import java.awt.Color;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class JavaFX extends Application {
	
	@Override public void start(Stage stage) throws Exception {
	 	
		 final NumberAxis yAchse=new NumberAxis();
		 final CategoryAxis xAchse=new CategoryAxis();
		
		 final AreaChart<String, Number> areaChart=new AreaChart<String, Number>(xAchse, yAchse);
		 areaChart.setTitle("Aktie_"+TestMain.typ);
		 areaChart.setCreateSymbols(false);
		 areaChart.setLegendVisible(false);
		 xAchse.setLabel("Tage");
		 yAchse.setLabel("Wert_Aktie");
		
		 XYChart.Series series_aktie= new XYChart.Series();
		 XYChart.Series series_durchschnitt= new XYChart.Series();
		 series_durchschnitt.setName("200er Durchschnitt");
		 ArrayList<Double> aktie=new ArrayList<Double>();
		 ArrayList<Double> durchschnitt=new ArrayList<Double>();
		 ArrayList<String> datum=new ArrayList<String>();
		 try {
			 	int zaehler=TestMain.anzahlTage-200;
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://"+TestMain.host+"/"+TestMain.database+"?user="+TestMain.user+"&password="+TestMain.passwort+"&serverTimezone=UTC");
				Statement stat=con.createStatement();
				ResultSet reSe=stat.executeQuery("select * from Aktie_"+TestMain.typ+" where id<="+zaehler+" order by ID desc");
				while(reSe.next()) {
					series_aktie.getData().add(new XYChart.Data(reSe.getString("Zeitpunkt"), Double.parseDouble(reSe.getString("TagesEndPreis"))));
					aktie.add(Double.parseDouble(reSe.getString("TagesEndPreis")));
					datum.add(reSe.getString("Zeitpunkt"));
					zaehler++;
				}

				reSe=stat.executeQuery("select a.Zeitpunkt, b.Durchschnitt from Aktie_"+TestMain.typ+"_200erDurchschnitt b inner join Aktie_"+TestMain.typ+" a on a.ID=b.ID order by b.ID desc");
				while(reSe.next()) {	
					series_durchschnitt.getData().add(new XYChart.Data(reSe.getString("Zeitpunkt"), Double.parseDouble(reSe.getString("Durchschnitt"))));
					durchschnitt.add(Double.parseDouble(reSe.getString("Durchschnitt")));
				}
				con.close();
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Verbinden fehlgeschlagen");
			}
		
		 XYChart.Series series_gruen= new XYChart.Series();
		 XYChart.Series series_rot= new XYChart.Series();
		 series_gruen.setName("Aktie(Gewinn)");
		 series_rot.setName("Aktie(Verlust)");
		 
		 for(int i=0; i<series_aktie.getData().size(); i++) {
			 if(aktie.get(i)>durchschnitt.get(i)){
				 series_gruen.getData().add(new XYChart.Data(datum.get(i), aktie.get(i)));
				 series_rot.getData().add(new XYChart.Data(datum.get(i), 0.0));
			 }else {
				 if(aktie.get(i)<=durchschnitt.get(i)){
					 series_rot.getData().add(new XYChart.Data(datum.get(i), aktie.get(i)));
					 series_gruen.getData().add(new XYChart.Data(datum.get(i), 0.0));
				 }
			 }
		 }	
		 Scene scene=new Scene(areaChart, 800, 600);
		 scene.getStylesheets().add("API_Aktienkurs/style.css");
		 areaChart.getData().addAll(series_aktie, series_durchschnitt, series_gruen, series_rot);
		 stage.setScene(scene);
		 stage.show();
	 	}
}
