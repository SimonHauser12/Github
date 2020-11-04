import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.json.JSONException;

public class TestMain extends Application{
	
	static DBVerbindung db=new DBVerbindung(2019, 2022);
	
	 public static void main(String[] args) throws MalformedURLException, JSONException, IOException {

		db.ausgabe();
		launch(args);
		
		db.verbindungZuSQL("localhost:3306", "FreieTage", "root", "sh30112002");
	}
	 
	 @Override public void start(Stage stage) throws Exception {
		 String mo="Montag", di="Dienstag", mi="Mittwoch", don="Donnerstag", fr="Freitag";
		
		 final NumberAxis yAchse=new NumberAxis();
		 final CategoryAxis xAchse=new CategoryAxis();
		
		 final BarChart<String, Number> barChart=new BarChart<String, Number>(xAchse, yAchse);
		 barChart.setTitle("Freie Wochentage");
		 xAchse.setLabel("Tage");
		 yAchse.setLabel("Wochentage");
		
		 XYChart.Series series= new XYChart.Series();
		
		 String bezeichnung="";
		 int nummer=0;
		 for (int i = 0; i < 5; i++) {
			  switch(i) {
		      	case 0: bezeichnung=mo; nummer=db.mo; break;
		      	case 1: bezeichnung=di; nummer=db.di; break;
		      	case 2: bezeichnung=mi; nummer=db.mi; break;
		      	case 3: bezeichnung=don; nummer=db.don; break;
		      	case 4: bezeichnung=fr; nummer=db.fr; break;
		      	default: break;
		      }
		      final XYChart.Data<String, Number> data = new XYChart.Data(bezeichnung, nummer);
		      data.nodeProperty().addListener(new ChangeListener<Node>() {
		        @Override public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
		          if (node != null) {
		            displayLabelForData(data);
		          } 
		        }
		      });
		     
		      series.getData().add(data);
		 }
		 
		 
		 barChart.getData().addAll(series);
		 Scene scene=new Scene(barChart, 640, 480);
		 stage.setScene(scene);
		 stage.show();
	 	}
	
	private void displayLabelForData(XYChart.Data<String, Number> data) {
		  final Node node = data.getNode();
		  final Text dataText = new Text(data.getYValue() + "");
		  node.parentProperty().addListener(new ChangeListener<Parent>() {
		    @Override public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
		      Group parentGroup = (Group) parent;
		      parentGroup.getChildren().add(dataText);
		    }
		  });

		  node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
		    @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
		      dataText.setLayoutX(
		        Math.round(
		          bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
		        )
		      );
		      dataText.setLayoutY(
		        Math.round(
		          bounds.getMinY() - dataText.prefHeight(-1) * 0.5
		        )
		      );
		    }
		  });
		}

}
