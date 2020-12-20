
public class Insertionsort {

	static int groeﬂe=1000;
	private static int[] datenstruktur=new int[groeﬂe];
	private static long bubbleSort_Zeit;
	private static long insertionSort_Zeit;

	
	public static void main(String[] args) {
		zufaellig();
		insertionSort(datenstruktur);
		bubbleSort(datenstruktur);
		ausgabeZufaellig();
		
		absteigend();
		insertionSort(datenstruktur);
		bubbleSort(datenstruktur);
		ausgabeAbsteigend();
		
		aufsteigend();
		insertionSort(datenstruktur);
		bubbleSort(datenstruktur);
		ausgabeAufsteigend();
	}
	
	public static void ausgabeAufsteigend() {
		System.out.println("Datenstruktur aufsteigend:");
		System.out.println("Zeit Insertionssort: "+ insertionSort_Zeit+" Nanosekunden");
		System.out.println("Zeit Bubblesort: "+ bubbleSort_Zeit+" Nanosekunden");
		System.out.println();
	}
	
	public static void ausgabeAbsteigend() {
		System.out.println("Datenstruktur absteigend:");
		System.out.println("Zeit Insertionssort: "+ insertionSort_Zeit+" Nanosekunden");
		System.out.println("Zeit Bubblesort: "+ bubbleSort_Zeit+" Nanosekunden");
		System.out.println();
	}

	public static void ausgabeZufaellig() {
		System.out.println("Datenstruktur zuf‰llig:");
		System.out.println("Zeit Insertionssort: "+ insertionSort_Zeit+" Nanosekunden");
		System.out.println("Zeit Bubblesort: "+ bubbleSort_Zeit+" Nanosekunden");
		System.out.println();
	}
	
	public static void zufaellig() {
		int[] zufall=new int[groeﬂe];
		int laenge=zufall.length-1;
		for(int i=0; i<zufall.length; i++) {
			zufall[i]=i+1;
		}
		for (int j = 0; j < zufall.length; j++) {
			int zahl=(int)(Math.random()*laenge);
			datenstruktur[j]=zufall[zahl];
			int p=zufall[laenge];
			zufall[laenge]=zufall[zahl];
			zufall[zahl]=p;
			laenge--;
		}
	}
	
	public static void absteigend() {
		for(int i=datenstruktur.length-1; i>=0; i--) {
			datenstruktur[i]=i+1;
		}
	}
	
	public static void aufsteigend() {
		for(int i=0; i<datenstruktur.length; i++) {
			datenstruktur[i]=i+1;
		}
	}
	
	public static void insertionSort(int[] zahlen) {
		long start=System.nanoTime();
		for(int i=1; i<zahlen.length; i++) {
			int gesucht=zahlen[i];
			int index=binaerSuche(gesucht, 0, i-1, zahlen);
			for(int j=i-1; j>=index; j--) {
				zahlen[j+1]=zahlen[j];
			}
			zahlen[index]=gesucht;
		}
		insertionSort_Zeit=System.nanoTime()-start;
	}

	public static int binaerSuche(int gesucht, int min, int max, int[] zahlen) {
		int mitte;
		while (min<=max) {
			mitte=min+(max-min)/2;
			if (gesucht < zahlen[mitte]) {
				max=mitte-1;
			} else {
				min=mitte+1;
			}
		}
		return min;
	}
	
	public static void bubbleSort(int[] zahlen) {
		long start=System.nanoTime();
		for(int i=zahlen.length; i>1; i--) {
			for(int j=0; j<i-1; j++) {
				if(zahlen[j]>zahlen[j+1]) {
					int b=zahlen[j];
					zahlen[j]=zahlen[j+1];
					zahlen[j+1]=b;
				}
			}
		}
		bubbleSort_Zeit=System.nanoTime()-start;
	}
}