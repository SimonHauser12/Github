import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Methoden {
	
	ArrayList<LocalDate> date=new ArrayList<LocalDate>();
	ArrayList<String> date_USA=new ArrayList<String>();
	ArrayList<LocalDate> dateOfOneYear=new ArrayList<LocalDate>();
	ArrayList<String> dateOfOneYear_USA=new ArrayList<String>();
	Scanner s=new Scanner(System.in);
	DayOfWeek day;
	int year;
	int counterDay_M;
	int ausgabe;
	String formatted="";
	
	public Methoden(DayOfWeek day, int year) {
		this.day=day;
		this.year=year;
	}
	
	public void counterDays(int time) {
		int counter=0;
		counterDay_M=0;
		ausgabe=format();
		LocalDate days=LocalDate.of(time, 1, 1);
		LocalDate lastDay=days;
		LocalDate today=LocalDate.now();
		String lastformatted="";
		String todayformatted="";
		do {
			if(ausgabe==0) {
				DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM-dd-yyyy");
				formatted=formatter.format(days);
				lastformatted=formatter.format(lastDay);
				todayformatted=formatter.format(today);
			}
			if(days.getYear()==today.getYear()) {
				if(days.getDayOfYear()==today.getDayOfYear()) {
					counter++;
				}else {
					if(days.getDayOfWeek()==day) {
						counterDay_M++;
						date.add(days);
						date_USA.add(formatted);
						lastDay=days;
					}
					days=days.plusDays(1);
				}
			}else {
				if(days.getDayOfWeek()==day) {
					counterDay_M++;
					date.add(days);
					date_USA.add(formatted);
				}
				days=days.plusDays(1);
			}
		}while(counter==0);
			if(ausgabe==0) {
				System.out.println("Es gibt "+counterDay_M+" "+day+" von "+time+" bis "+ todayformatted); 
				System.out.print("Letzter "+day+": "+ lastformatted);
			}else {
				System.out.println("Es gibt "+counterDay_M+" "+day+" von "+time+" bis "+ today); 
				System.out.print("Letzter "+day+": "+ lastDay);
			}
			System.out.println();
	}
	
	public void counterDaysOfYear() {
		int counter=0;
		int counterDay=0;
		LocalDate days=LocalDate.of(year, 1, 1);
		LocalDate nextYear=days.plusYears(1);
		do {
			if(ausgabe==0) {
				DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM-dd-yyyy");
				formatted=formatter.format(days);
			}
			if(days.getYear()==nextYear.getYear()) {
				counter++;
			}else {
				if(days.getDayOfWeek()==day) {
					counterDay++;
					dateOfOneYear.add(days);
					dateOfOneYear_USA.add(formatted);
				}
				days=days.plusDays(1);
			}
		}while(counter==0);
		System.out.println();
		System.out.println("Es gibt "+counterDay+" "+day+" im Jahr "+year); 
		System.out.println();
	}
	
	public void daysBetweenDays() {
		System.out.println("Geburtstag eingeben (day, month): ");
		int day=s.nextInt();
		int month=s.nextInt();
		LocalDate today=LocalDate.now();
		LocalDate birthday=LocalDate.of(today.getYear(), month, day);
		Period timeBetweenBirthday=Period.between(today, birthday);
		if(timeBetweenBirthday.getDays()<0||timeBetweenBirthday.getMonths()<0) {
			birthday=birthday.plusYears(1);
			timeBetweenBirthday=Period.between(today, birthday);
		}
		
		System.out.println("Geburtstag in: "+timeBetweenBirthday.getMonths()+" Monaten und "+timeBetweenBirthday.getDays()+" Tagen");
	}
	
	public int format() {
		int ausgabe;
		System.out.println("Datumsangabe: amerikanisch(0) oder europäisch(1)");
		ausgabe=s.nextInt();
		return ausgabe;
	}
	
	public void randomDate() {
		int wahl=s.nextInt();
		System.out.println("Längerer Zeitraum(1900) oder nur ein Jahr(1)");
		int k=s.nextInt();
		if (k==1900) {
			if (ausgabe == 0) {
				System.out.println(date_USA.get(wahl));
			} else {
				if (ausgabe == 1) {
					System.out.println(date.get(wahl));
				}
			} 
		}else {
			if(k==1) {
				if (ausgabe == 0) {
					System.out.println(dateOfOneYear_USA.get(wahl));
				} else {
					if (ausgabe == 1) {
						System.out.println(dateOfOneYear.get(wahl));
					}
				} 
			}
		}
	}
	
}
