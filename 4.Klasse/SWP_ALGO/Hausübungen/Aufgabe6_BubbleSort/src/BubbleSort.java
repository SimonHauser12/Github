
public class BubbleSort {

	static int[] zufallszahlen=new int[1000];
	static long zeit;
	static long zeit2;
	static int zaehler=0;
	
	public static void main(String[] args) {
		
		befüllen();
		ausgabe("Unsortiert");
		bubbleSort();
		ausgabe("Sortiert");
		System.out.println();
		System.out.println("Benötigte Zeit: "+ zeit2+" Millisekunden");
		System.out.println("Durchläufe: "+ zaehler);
	}
	
	public static void befüllen() {
		for(int i=0; i<zufallszahlen.length; i++) {
			zufallszahlen[i]=(int)(Math.random()*1001);
		}
	}

	public static void bubbleSort() {
		zeit=System.currentTimeMillis();
		for(int i=zufallszahlen.length; i>1; i--) {
			for(int j=0; j<i-1; j++) {
				if(zufallszahlen[j]>zufallszahlen[j+1]) {
					zaehler++;
					umtauschen(zufallszahlen[j], zufallszahlen[j+1], j);
				}
			}
		}
		zeit2=System.currentTimeMillis()-zeit;
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
