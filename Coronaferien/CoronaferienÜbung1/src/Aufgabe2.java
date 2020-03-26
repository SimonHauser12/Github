import java.time.*;
import java.util.Scanner;

public class Aufgabe2 {

	public static void main(String[] args) {
		calender(2020, 12);
	}
	
	public static void calender(int year, int month) {
		boolean value=false;
		Scanner s=new Scanner(System.in);
		do {
			if(month > 12 || month < 1) {
				System.out.println("Zahl zu hoch, nur 1-12");
				month=s.nextInt();
			}else {
				value=true;
				s.close();
			}
		}while(value==false);
		value=false;
		LocalDate time=LocalDate.of(year, month, 1);
		LocalDate firstOne=LocalDate.of(year, 1, 1);
		LocalDate nextMonth=time.plusMonths(1);
		int counter=0;
		int counterWeek=1;
		
		do {
			if (firstOne.getDayOfYear()!=time.getDayOfYear()) {
				if (firstOne.getDayOfWeek() == DayOfWeek.MONDAY) {
					counterWeek++;
					firstOne = firstOne.plusDays(1);
				} else {
					firstOne = firstOne.plusDays(1);
				} 
			}else {
				if (firstOne.getDayOfWeek() == DayOfWeek.MONDAY) {
					counterWeek++;
				}
				value=true;
			}
		}while(value==false);
		value=false;
		System.out.println("Kalender für "+time.getMonth()+" "+ time.getYear());
		System.out.println("Wo  | Mo    Di    Mi    Do    Fr    Sa    So");
		if (counterWeek<10) {
			System.out.print(" "+counterWeek+"  | ");
		}else {
			System.out.print(counterWeek+"  | ");
		}
		switch(time.getDayOfWeek()) {
		case MONDAY: 
			for (int i = 0; i < 7; i++) {
				System.out.print(" " + time.getDayOfMonth() + "    ");
				time = time.plusDays(1);
			}
			System.out.println();
			break;
		case TUESDAY: 
			System.out.print("      "); 
			for (int i = 0; i < 6; i++) {
				System.out.print(" " + time.getDayOfMonth() + "    ");
				time = time.plusDays(1);
			}
			System.out.println();
			break;
		case WEDNESDAY: 
			System.out.print("            "); 
			for (int i = 0; i < 5; i++) {
				System.out.print(" " + time.getDayOfMonth() + "    ");
				time = time.plusDays(1);
			}
			System.out.println();
			break;
		case THURSDAY: 
			System.out.print("                  "); 
			for (int i = 0; i < 4; i++) {
				System.out.print(" " + time.getDayOfMonth() + "    ");
				time = time.plusDays(1);
			}
			System.out.println();
			break;
		case FRIDAY: 	
			System.out.print("                        ");
			for (int i = 0; i < 3; i++) {
				System.out.print(" " + time.getDayOfMonth() + "    ");
				time = time.plusDays(1);
			}
			System.out.println();
			break;
		case SATURDAY: 	
			System.out.print("                              ");
			System.out.print(" "+time.getDayOfMonth()+"    "); 
			time = time.plusDays(1);
			System.out.println(" "+time.getDayOfMonth()+"    "); 
			time = time.plusDays(1);
			break; 
		case SUNDAY: 	
			System.out.print("                                    "); 
			System.out.println(" "+time.getDayOfMonth()+"    ");  
			time = time.plusDays(1);
			break; 
		}
		counterWeek++;
		if (counterWeek<10) {
			System.out.print(" "+counterWeek+"  | ");
		}else {
			System.out.print(counterWeek+"  | ");
		}
		do {
			if (time.getMonth() != nextMonth.getMonth()) {
				counter++;
				if(time.getDayOfMonth()<10) {
					System.out.print(" "+time.getDayOfMonth()+"    ");
				}else {
					System.out.print(time.getDayOfMonth()+"    ");
				}
				if(counter==7) {
					System.out.println();
					counterWeek++;
					time = time.plusDays(1);
					if (time.getMonth()!=nextMonth.getMonth()) {
						if (counterWeek < 10) {
							System.out.print(" " + counterWeek + "  | ");
						} else {
							System.out.print(counterWeek + "  | ");
						} 
						time = time.minusDays(1);
					}
					counter=0;
				}
				time = time.plusDays(1);
			} else {
				value=true;
			} 
		} while (value==false);
	}
}
