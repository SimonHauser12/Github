
public class TestMain {

	public static void main(String[] args) {
		hochFunktion(2, 3);
	}
	
	public static void hochFunktion(int zahl, int anzahl) {
		System.out.println("HochFunktion Iterativ: "+ iterativ(zahl, anzahl));
		System.out.println("HochFunktion Rekursiv: "+ rekursiv(zahl, anzahl));
	}
	
	public static int iterativ(int zahl, int anzahl) {
		int ergebnis=zahl;
		while(anzahl>1) {
			ergebnis=ergebnis*zahl;
			anzahl--;
		}
		return ergebnis;
	}
	
	public static int rekursiv(int zahl, int anzahl) {
		if(anzahl>0) {
			return zahl*rekursiv(zahl, anzahl-1);
		}else {
			return 1;
		}
	}

}
