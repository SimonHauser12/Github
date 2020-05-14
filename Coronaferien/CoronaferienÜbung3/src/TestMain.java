import java.io.IOException;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) throws IOException{
		Scanner r=new Scanner(System.in);
		codierer c=new codierer();
		decodierer d=new decodierer();
		c.menu();
		System.out.println("Nachricht entschlüsseln?");
		String s=r.nextLine();
		if(s.equals("j")) {
			d.menu();
		}
		r.close();
	}
}
