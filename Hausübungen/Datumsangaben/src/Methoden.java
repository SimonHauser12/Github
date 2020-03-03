import java.time.*;
import java.util.Scanner;

public class Methoden {

	Scanner s=new Scanner(System.in);
	DayOfWeek day;
	int year;
	
	public Methoden(DayOfWeek day, int year) {
		this.day=day;
		this.year=year;
	}
	
	public void counterDaysOfYear() {
		int counter=0;
		int counterDay=0;
		LocalDate days=LocalDate.of(year, 1, 1);
		do {
			if(days.getDayOfWeek().equals(day)) {
				counter++;
			}else {
				days=days.plusDays(1);
			}
		}while(counter==0);
		counter=0;
		LocalDate nextYear=days.plusYears(1);
		do {
			if(days.getYear()==nextYear.getYear()) {
				counter++;
			}else {
				days=days.plusDays(7);
				counterDay++;
			}
		}while(counter==0);
		System.out.println("Es gibt "+counterDay+" "+day+" im Jahr "+year); 
	}
	
	public void counterDays(int time) {
		int counter=0;
		int counterDay=0;
		int ausgabe;
		String da="";
		String mo="";
		LocalDate days=LocalDate.of(time, 1, 1);
		LocalDate today=LocalDate.now();
		do {
			if(days.getDayOfWeek().equals(day)) {
				counter++;
			}else {
				days=days.plusDays(1);
			}
		}while(counter==0);
		counter=0;		
		do {
			if(days.getYear()==today.getYear()) {
				if(days.getDayOfYear()==today.getDayOfYear()) {
					counter++;
				}else {
					days=days.plusDays(1);
				}
			}else {
				days=days.plusDays(7);
				counterDay++;
			}
		}while(counter==0);
		System.out.println("Es gibt "+counterDay+" "+day+" von "+time+" bis "+ today); 
		System.out.println();
		if (days.getMonthValue()<10&&days.getDayOfMonth()<10) {
			da="0"+days.getDayOfMonth();
			mo="0"+days.getMonthValue();
		}else {
			if(days.getDayOfMonth()<10) {
				da="0"+days.getDayOfMonth();
			}else {
				if (days.getMonthValue()<10) {
					mo="0"+days.getMonthValue();
				}else {
					da=days.getDayOfMonth()+"";
					mo=days.getMonthValue()+"";
				}
			}
		}
		System.out.println("Datumsangabe: amerikanisch(0) oder europäisch(1)");
		ausgabe=s.nextInt();
		System.out.print("Letzter "+day+": ");
		if(ausgabe==0) {
			System.out.println(mo+"-"+da+"-"+days.getYear());
		}else {
			if (ausgabe==1) {
				System.out.println(days.getYear() + "-" + mo + "-" + da);
			}
		}
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
	
}
