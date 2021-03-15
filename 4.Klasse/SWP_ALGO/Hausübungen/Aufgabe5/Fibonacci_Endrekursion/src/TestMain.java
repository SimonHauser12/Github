
public class TestMain {

	public static void main(String[] args) {
		
		funktionsaufruf(5);
	}
	
	public static void funktionsaufruf(int zahl) {
		System.out.println("Fibonacci Iterativ: "+ fibo_iterativ(zahl));
		System.out.println("Fibonacci Rekursiv: "+ fibo_endrekursiv(0, 1, zahl-1));
	}

	public static int fibo_iterativ(int anzahl) {
		anzahl-=2;
		int vor1=1;
		int vor2=1;
		int ergebnis=0;
		while(anzahl>0) {
			ergebnis=vor1+vor2;
			vor2=vor1;
			vor1=ergebnis;
			anzahl--;
		}
		return ergebnis;
	}
	
	public static int fibo_endrekursiv(int x0, int x1, int anzahl) {
		if (anzahl<0) {
			return 0;
		}
		if(anzahl==0) {
			return x1;
		}
		return fibo_endrekursiv(x1, x0+x1, anzahl-1);
	  }
}
