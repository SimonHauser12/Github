import java.util.*;

public class Aufgabe1 {

	public static void main(String[] args) {
		Random re=new Random();
		int[][] temp=new int[14][10];
		int avg_day=0;
		int avg_gesamt=0;
		int counter=0;
		int counterGes=0;
		for(int i=0; i<temp.length; i++) {
			for(int j=0; j<temp[i].length; j++) {
				temp[i][j]=20+re.nextInt(35-20+1);
				System.out.print(temp[i][j]+" | ");
				avg_day=avg_day+temp[i][j];
				counter++;
				avg_gesamt=avg_gesamt+temp[i][j];
				counterGes++;
			}
			System.out.println("Durchschnittliche Temperatur: "+ (avg_day/counter));
			avg_day=0;
			counter=0;
		}
		
		System.out.println("Gesamte durchschnittliche Temperatur: "+ (avg_gesamt/counterGes));
	}

}
