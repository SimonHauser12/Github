
public class TestMain {

	private static int ergebnis=0;
	
	public static void main(String[] args) {
		
		rekursionsAufruf(15, 11);
	}
	
	public static void rekursionsAufruf(int zahl, int zahl2) {
		System.out.println(iterativ(zahl, zahl2));
		ergebnis=0;
		System.out.print("Rekursiv: "+zahl+" x "+zahl2+" = ");
		System.out.println(rekursiv(zahl, zahl2));
		
	}
	
	public static int iterativ(int zahl, int zahl2) {
		System.out.print("Iterativ: "+zahl+" x "+zahl2+" = ");
		int counter=0;
		ergebnis=0;
		while(counter<zahl2) {
			ergebnis=ergebnis+zahl;
			counter++;
		}
		return ergebnis;
	}
	
	public static int rekursiv(int zahl, int zahl2) {
		if(zahl2>0) {
			ergebnis=ergebnis+zahl;
			return rekursiv(zahl, zahl2-1);
		}else {
			return ergebnis;
		}
	}

}
