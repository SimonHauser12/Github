package API_Aktienkurs;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class JavaFX extends Application {
	
	@Override public void start(Stage stage) throws Exception {
	 	 for(int j=0; j<TestMain.ticker.size(); j++) {

			 final NumberAxis yAchse=new NumberAxis(1, 10, 1);
			 final CategoryAxis xAchse=new CategoryAxis();
			 
			 final AreaChart<String, Number> areaChart=new AreaChart<String, Number>(xAchse, yAchse);
			 areaChart.setTitle("Aktie_"+TestMain.ticker.get(j));
			 areaChart.setCreateSymbols(false);
			 xAchse.setLabel("Tage");
			 yAchse.setLabel("Aktienwert");
			
			 XYChart.Series series_aktie= new XYChart.Series();
			 XYChart.Series series_durchschnitt= new XYChart.Series();
			 series_aktie.setName("Aktie"+TestMain.ticker.get(j));
			 series_durchschnitt.setName("200er Durchschnitt");
			 ArrayList<Double> aktie=new ArrayList<Double>();
			 ArrayList<Double> durchschnitt=new ArrayList<Double>();
			 ArrayList<String> datum=new ArrayList<String>();
			 try {
				 	int zaehler=TestMain.anzahlTage-200;
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://"+TestMain.host+"/"+TestMain.database+"?user="+TestMain.user+"&password="+TestMain.passwort+"&serverTimezone=UTC");
					Statement stat=con.createStatement();
					ResultSet reSe=stat.executeQuery("select * from Aktie_"+TestMain.ticker.get(j)+" where id<="+zaehler+" order by ID desc");
					while(reSe.next()) {
						series_aktie.getData().add(new XYChart.Data(reSe.getString("Zeitpunkt"),Double.parseDouble(reSe.getString("TagesEndPreis"))));
						aktie.add(Double.parseDouble(reSe.getString("TagesEndPreis")));
						datum.add(reSe.getString("Zeitpunkt"));
					}
	
					reSe=stat.executeQuery("select a.Zeitpunkt, b.Durchschnitt from Aktie_"+TestMain.ticker.get(j)+"_200erDurchschnitt b inner join Aktie_"+TestMain.ticker.get(j)+" a on a.ID=b.ID order by b.ID desc");
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
			 series_gruen.setName("Gewinn");
			 series_rot.setName("Verlust");
			 
			 double min=10000;
			 double max=0;
			 for(int i=0; i<series_aktie.getData().size(); i++) {
				 if(aktie.get(i)>max) {
					 max=aktie.get(i);
				 }
				 if(aktie.get(i)<min) {
					 min=aktie.get(i);
				 }
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
			 
			 yAchse.setLowerBound((int)(min-(min*0.1)));
			 yAchse.setUpperBound((int)(max+(max*0.1)));
			 yAchse.setTickUnit(100);
			 xAchse.setTickMarkVisible(false);
	
			 
			 Scene scene=new Scene(areaChart, 800, 600);
			 scene.getStylesheets().add("API_Aktienkurs/style.css");
			 areaChart.getData().addAll(series_aktie, series_durchschnitt, series_gruen, series_rot);
			 stage.setScene(scene);
			 //stage.show();
			 
			 LocalDate zeit=LocalDate.now();
			 WritableImage bild=scene.snapshot(null);
			 File datei=new File("D:\\SimonHTL\\Programmieren\\Programmieren\\API2_Aktienkurs\\Chart-Bilder\\"+TestMain.ticker.get(j)+"_"+zeit+".png");
			 ImageIO.write(SwingFXUtils.fromFXImage(bild, null), "PNG", datei);
			 System.out.println("Bild gespeichert");
		 	}
	 	 return;
		}
}