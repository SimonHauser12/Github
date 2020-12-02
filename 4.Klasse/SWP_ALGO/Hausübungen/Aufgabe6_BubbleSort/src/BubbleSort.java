
public class BubbleSort {

	static int[] zufallszahlen=new int[20];
	static long zeit;
	static long zeit2;
	
	public static void main(String[] args) {
		
		befüllen();
		ausgabe("Unsortiert");
		bubbleSort();
		ausgabe("Sortiert");
		System.out.println("Benötigte Zeit: "+ zeit2+" Millisekunden");
	}
	
	public static void befüllen() {
		for(int i=0; i<zufallszahlen.length; i++) {
			zufallszahlen[i]=(int)(Math.random()*101);
		}
	}

	public static void bubbleSort() {
		zeit=System.nanoTime();
		for(int i=zufallszahlen.length; i>1; i--) {
			for(int j=0; j<i-1; j++) {
				if(zufallszahlen[j]>zufallszahlen[j+1]) {
					umtauschen(zufallszahlen[j], zufallszahlen[j+1], j);
				}
			}
		}
		zeit2=(System.nanoTime()-zeit)/1000;
	}
	
	public static void umtauschen(int a, int b, int index) {
		zufallszahlen[index]=b;
		zufallszahlen[index+1]=a;
	}
	
	public static void ausgabe(String betreff) {
		System.out.println(betreff);
		for(int i=0; i<zufallszahlen.length; i++) {
			System.out.print(zufallszahlen[i]+" ");
		}
		System.out.println();
	}
}
