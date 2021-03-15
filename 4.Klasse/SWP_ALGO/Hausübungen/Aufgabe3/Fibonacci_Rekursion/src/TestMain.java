
public class TestMain {

	public static void main(String[] args) {
		
		funktionsaufruf(10);
	}
	
	public static void funktionsaufruf(int zahl) {
		System.out.println("Fibonacci Iterativ: "+ iterativ(zahl));
		System.out.println("Fibonacci Rekursiv: "+ fibo_rekursiv(zahl));
	}
	
	public static int iterativ(int anzahl) {
		anzahl-=2;
		int ergebnis=1;
		int vor1=1;
		int vor2=1;
		while(anzahl>0) {
			ergebnis=vor1+vor2;
			vor2=vor1;
			vor1=ergebnis;
			anzahl--;
		}
		return ergebnis;
	}
	
	public static int fibo_rekursiv(int anzahl) {
		if (anzahl<=2) {
			return 1;
		}else {
			return fibo_rekursiv(anzahl-1)+fibo_rekursiv(anzahl-2);
		}
	  }
}
