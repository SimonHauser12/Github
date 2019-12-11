import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		Exceptions e=new Exceptions();
		try {
			e.exceptionTest();
		}
		catch(AnzahlNichtEingehaltenException anee) {
			anee.printStackTrace();
		}
		catch(UngueltigesZeichenException uze) {
			uze.printStackTrace();
		}
	}
}
