package API_Aktienkurs;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class JavaFX_Strategie extends Application {
	
	@Override public void start(Stage stage) throws Exception {
		 final NumberAxis yAchse=new NumberAxis();
		 final CategoryAxis xAchse=new CategoryAxis();
		 
		 final LineChart<String, Number> lineChart=new LineChart<String, Number>(xAchse, yAchse);
		 lineChart.setTitle("Aktien_Strategien");
		 lineChart.setCreateSymbols(false);
		 xAchse.setLabel("Tage");
		 yAchse.setLabel("Depotwert");
		
		 XYChart.Series series_200er= new XYChart.Series();
		 XYChart.Series series_200er3percent= new XYChart.Series();
		 XYChart.Series series_buyandhold= new XYChart.Series();
		 series_200er.setName("200er-Strategie");
		 series_200er3percent.setName("200er-3%-Strategie");
		 series_buyandhold.setName("BuyandHold-Strategie");
		 double kapital=0;
		 int anzahl=0;
		 double k2=0;
		 HashMap<String, Double> werte1 = new HashMap<String, Double>();
		 HashMap<String, Double> werte2 = new HashMap<String, Double>();
		 HashMap<String, Double> werte3 = new HashMap<String, Double>();
		 
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection con = DriverManager.getConnection("jdbc:mysql://"+TestMain.host+"/"+TestMain.database+"?user="+TestMain.user+"&password="+TestMain.passwort+"&serverTimezone=UTC");
		 Statement stat2=con.createStatement();
		 ResultSet reSe = stat2
					.executeQuery("select * from "+ TestMain.ticker.get(0) + "_roh where ID>=" + TestMain.id + " and ID<="+TestMain.idende+" order by Zeitpunkt");
		 while (reSe.next()) {
			 werte1.put(reSe.getString("Zeitpunkt"), 0.0);
			 werte2.put(reSe.getString("Zeitpunkt"), 0.0);
			 werte3.put(reSe.getString("Zeitpunkt"), 0.0);
			 //tage.getData().add(new XYChart.Data(reSe.getString("Zeitpunkt"), 0));
		 }
	 	 for(int j=0; j<TestMain.ticker.size(); j++) {
	 		 //splitcorrected werte auslesen, mit aktien anzahl multiplizieren und mit depotwert addieren, für alle aktien addieren, für jeden tag im intervall
			 try {
					if (TestMain.wahl==1 || TestMain.wahl==4) {
						anzahl=0;
						k2=0;
						ResultSet reSe2 = stat2
								.executeQuery("select a.Zeitpunkt as tag, b.Zeitpunkt as k, b.Kapital, b.Wert, b.anzahl, a.tagesendpreis from "
										+ TestMain.ticker.get(j) + "_roh a left join Aktie_" + TestMain.ticker.get(j)
										+ "_200erStrategie_rohwerte b on a.Zeitpunkt=b.Zeitpunkt where a.ID>=" + TestMain.id + " and a.ID<="+TestMain.idende+" order by a.Zeitpunkt");
						while (reSe2.next()) {
							if (reSe2.getString("k") != null) {
								kapital=werte1.get(reSe2.getString("tag"))+Double.parseDouble(reSe2.getString("Kapital"));
								anzahl=Integer.parseInt(reSe2.getString("anzahl"));
								double w=Double.parseDouble(reSe2.getString("tagesendpreis"))*anzahl;
								kapital=kapital+w;
								werte1.put(reSe2.getString("tag"), kapital);
								//series_200er.getData().add(new XYChart.Data(reSe2.getString("k"), Double.parseDouble(reSe2.getString("Kapital"))));
								k2=Double.parseDouble(reSe2.getString("Kapital"));
							}else{
								if(reSe2.getString("k") == null) {
									kapital=werte1.get(reSe2.getString("tag"))+k2;
									double w=Double.parseDouble(reSe2.getString("tagesendpreis"))*anzahl;
									kapital=kapital+w;
									werte1.put(reSe2.getString("tag"), kapital);
								}
							}
						}
					}
					if (TestMain.wahl==2 || TestMain.wahl==4) {
						anzahl=0;
						k2=0;
						ResultSet reSe3 = stat2
								.executeQuery("select a.Zeitpunkt as tag, b.Zeitpunkt as k, b.Kapital, b.Wert, b.anzahl, a.tagesendpreis from "
										+ TestMain.ticker.get(j) + "_roh a left join Aktie_" + TestMain.ticker.get(j)
										+ "_200er_3_Strategie_rohwerte b on a.Zeitpunkt=b.Zeitpunkt where a.ID>=" + TestMain.id + " and a.ID<="+TestMain.idende+" order by a.Zeitpunkt");
						while (reSe3.next()) {
							if (reSe3.getString("k") != null) {
								kapital=werte2.get(reSe3.getString("tag"))+Double.parseDouble(reSe3.getString("Kapital"));
								anzahl=Integer.parseInt(reSe3.getString("anzahl"));
								double w=Double.parseDouble(reSe3.getString("tagesendpreis"))*anzahl;
								kapital=kapital+w;
								werte2.put(reSe3.getString("tag"), kapital);
								//series_200er3percent.getData().add(new XYChart.Data(reSe3.getString("k"), Double.parseDouble(reSe3.getString("Kapital"))));
								k2=Double.parseDouble(reSe3.getString("Kapital"));
							}else{
								if(reSe3.getString("k") == null) {
									kapital=werte2.get(reSe3.getString("tag"))+k2;
									double w=Double.parseDouble(reSe3.getString("tagesendpreis"))*anzahl;
									kapital=kapital+w;
									werte2.put(reSe3.getString("tag"), kapital);
								}
							}
						} 
					}
					if (TestMain.wahl==3 || TestMain.wahl==4) {
						anzahl=0;
						k2=0;
						ResultSet reSe4 = stat2
								.executeQuery("select a.Zeitpunkt as tag, b.Zeitpunkt as k, b.Kapital, b.Wert, b.anzahl, a.tagesendpreis from "
										+ TestMain.ticker.get(j) + "_roh a left join Aktie_" + TestMain.ticker.get(j)
										+ "_buyandhold_Strategie_rohwerte b on a.Zeitpunkt=b.Zeitpunkt where a.ID>=" + TestMain.id + " and a.ID<="+TestMain.idende+" order by a.Zeitpunkt");
						while (reSe4.next()) {
							if (reSe4.getString("k") != null) {
								kapital=werte3.get(reSe4.getString("tag"))+Double.parseDouble(reSe4.getString("Kapital"));
								anzahl=Integer.parseInt(reSe4.getString("anzahl"));
								double w=Double.parseDouble(reSe4.getString("tagesendpreis"))*anzahl;
								kapital=kapital+w;
								werte3.put(reSe4.getString("tag"), kapital);
								//series_buyandhold.getData().add(new XYChart.Data(reSe4.getString("k"), Double.parseDouble(reSe4.getString("Kapital"))));
								k2=Double.parseDouble(reSe4.getString("Kapital"));
							}else{
								if(reSe4.getString("k") == null) {
									kapital=werte3.get(reSe4.getString("tag"))+k2;
									double w=Double.parseDouble(reSe4.getString("tagesendpreis"))*anzahl;
									kapital=kapital+w;
									werte3.put(reSe4.getString("tag"), kapital);
								}
							}
						}
					}
				}catch(Exception ex){
					ex.printStackTrace();
					System.out.println("Verbinden fehlgeschlagen");
				}
		 	}
	 	 ResultSet reSe5 = stat2
				.executeQuery("select * from "+ TestMain.ticker.get(0) + "_roh where ID>=" + TestMain.id + " and ID<="+TestMain.idende+" order by Zeitpunkt");
	 	 while (reSe5.next()) {
	 		 series_200er.getData().add(new XYChart.Data(reSe5.getString("Zeitpunkt"), werte1.get(reSe5.getString("Zeitpunkt"))));
	 		 series_200er3percent.getData().add(new XYChart.Data(reSe5.getString("Zeitpunkt"), werte2.get(reSe5.getString("Zeitpunkt"))));
	 		 series_buyandhold.getData().add(new XYChart.Data(reSe5.getString("Zeitpunkt"), werte3.get(reSe5.getString("Zeitpunkt"))));
	 	 }
	 	 con.close();

	 	 yAchse.setTickUnit(100);
		 xAchse.setTickMarkVisible(false);

		 
		 Scene scene=new Scene(lineChart, 800, 600);
		 scene.getStylesheets().add("API_Aktienkurs/style2.css");
		 lineChart.getData().addAll(series_200er, series_200er3percent, series_buyandhold);
		 stage.setScene(scene);
		 //stage.show();
		 
		 LocalDate zeit=LocalDate.now();
		 WritableImage bild=scene.snapshot(null);
		 File datei=new File("D:\\SimonHTL\\Programmieren\\Programmieren\\API2_Aktienkurs\\Chart-Bilder\\Strategie\\Aktien_"+zeit+"_("+TestMain.wahl+").png");
		 ImageIO.write(SwingFXUtils.fromFXImage(bild, null), "PNG", datei);
		 System.out.println("Bild gespeichert");
		 
	 	 return;
		}
}