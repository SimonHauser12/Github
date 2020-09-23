import java.io.*;
import java.time.*;


public class Aufgabe3 {

	public static void main(String[] args) {
		String s=Aufgabe1.averageTemp();
		PrintWriter w=null;
		String os =System.getProperty("os.name");
		LocalDate date=LocalDate.now();
		LocalTime time=LocalTime.now();
		
		try {
			w=new PrintWriter(new BufferedWriter(new FileWriter("measurements.txt")));
			w.println(s+"Erstellt am "+date+" um "+ time.getHour()+":"+time.getMinute()+":"+time.getSecond()+"\nunter "+os);
		}catch(IOException ex){
			ex.printStackTrace();
		}finally {
			if(w!=null) {
				w.flush();
				w.close();
			}
		}
	}

}
