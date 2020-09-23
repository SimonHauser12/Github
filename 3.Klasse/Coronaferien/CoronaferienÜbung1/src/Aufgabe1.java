import java.util.*;

public class Aufgabe1 {

	public static void main(String[] args) {
		String s=averageTemp();
		System.out.println(s);
	}
	
	public static String averageTemp() {
		Random re=new Random();
		int[][] temp=new int[14][10];
		int avg_day=0;
		int avg_gesamt=0;
		int counter=0;
		int counterGes=0;
		String s="";
		for(int i=0; i<temp.length; i++) {
			for(int j=0; j<temp[i].length; j++) {
				temp[i][j]=20+re.nextInt(35-20+1);
				s=s+temp[i][j]+" | ";
				avg_day=avg_day+temp[i][j];
				counter++;
				avg_gesamt=avg_gesamt+temp[i][j];
				counterGes++;
			}
			s=s+"Durchschnittliche Temperatur: "+ (avg_day/counter)+"\n";
			avg_day=0;
			counter=0;
		}
		s=s+"Gesamte durchschnittliche Temperatur: "+ (avg_gesamt/counterGes+"\n");
		return s;
	}
}
