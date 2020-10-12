
public class TestMain {
	
	public static void main(String[] args) {
		
		rekursionsAufruf(50);
	}
	
	public static void rekursionsAufruf(int zahl) {
		System.out.println("Iterativ: "+iterativ(zahl));
		System.out.println("Rekrusiv: "+ rekursiv(zahl));
		
	}
	
	public static int iterativ(int zahl) {
		int ergebnis=0;
		while(zahl>0) {
			ergebnis=ergebnis+zahl;
			zahl--;
		}
		return ergebnis;
	}
	
	public static int rekursiv(int zahl) {
		if(zahl>0) {
			return zahl+rekursiv(zahl-1);
		}else {
			return 0;
		}
	}

}
