import java.time.*;
public class TestMain {

	public static void main(String[] args) {
		Methoden k=new Methoden(DayOfWeek.MONDAY, 2020);
		k.counterDays(1900);
		k.counterDaysOfYear();
		k.daysBetweenDays();
		System.out.println("Von welchem Tag wollen Sie das Datum wissen?");
		k.randomDate();
	}

}
