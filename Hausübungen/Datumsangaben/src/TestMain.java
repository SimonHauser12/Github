import java.time.*;
public class TestMain {

	public static void main(String[] args) {
		Methoden k=new Methoden(DayOfWeek.THURSDAY, 2020);
		k.counterDaysOfYear();
		k.counterDays(1900);
		k.daysBetweenDays();
	
	}

}
