import java.util.Scanner;

public class Exceptions {

	void exceptionTest() throws AnzahlNichtEingehaltenException, UngueltigesZeichenException {
		System.out.print("Bitte einen 8-stelligen Binär-Code eingeben(nur 0 und 1): ");
		Scanner k=new Scanner(System.in);
		String s=k.next();
		
		try 
		{
			if(s.length()!=8) {
				throw new AnzahlNichtEingehaltenException("Einen 8-stelligen Code eingeben!!");
			}
			int[] zahlen=new int[8];
			for(int i=0; i<zahlen.length; i++) {
				zahlen[i]=Character.getNumericValue(s.charAt(i));
				if(zahlen[i]!=0&&zahlen[i]!=1) {
					throw new UngueltigesZeichenException("Nur 0 und 1 eingeben!!!");
				}
			}
			
			int dezimal=0;
			int a=1;
			for(int i=zahlen.length-1; i>=0; i--) {
				dezimal=dezimal+(zahlen[i]*a);
				a=a*2;
			}
			System.out.println(dezimal);
		}
		catch(AnzahlNichtEingehaltenException anee) 
		{
			throw anee;
		}
		catch(UngueltigesZeichenException uze) {
			throw uze;
		}
		finally {
			k.close();
		}
	}
}
