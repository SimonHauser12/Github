
public class Sortieralgorithmen {

	static int anzahl=1000;
	static int counterA=0;
	private static int[] zufallszahlen=new int[anzahl];
	private static int[] vertauschO=new int[4];
	private static int[] vergleichO=new int[4];
	static int[] zahlen=new int[anzahl];
	static long bubbleSort_zeit;
	static long unstableSelectionSort_zeit;
	static long stableSelectionSort_zeit;
	static long insertionSort_zeit;
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			counterA++;
			befüllen();
			bubbleSort(zufallszahlen);
			gleichsetzen();
			unstableSelectionSort(zufallszahlen);
			gleichsetzen();
			stableSelectionSort(zufallszahlen);
			gleichsetzen();
			insertionSort(zufallszahlen);
		}
		ausgabe();
	}
	
	public static void ausgabe() {
		System.out.println("Anzahl Arrays: "+counterA);
		System.out.println("Elemente pro Array: "+anzahl);
		System.out.println("Zahlenbereich Zufallzahlen: 0 bis "+anzahl);
		System.out.println();
		System.out.println("BubbleSort:");
		System.out.println("Vergleichoperationen: "+vergleichO[0]/counterA);
		System.out.println("Vertauschoperationen: "+vertauschO[0]/counterA);
		System.out.println("Zeit: "+bubbleSort_zeit/counterA);
		System.out.println();
		System.out.println("Unstable SelectionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[1]/counterA);
		System.out.println("Vertauschoperationen: "+vertauschO[1]/counterA);
		System.out.println("Zeit: "+unstableSelectionSort_zeit/counterA);
		System.out.println();
		System.out.println("Stable SelectionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[2]/counterA);
		System.out.println("Vertauschoperationen: "+vertauschO[2]/counterA);
		System.out.println("Zeit: "+stableSelectionSort_zeit/counterA);
		System.out.println();
		System.out.println("InsertionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[3]/counterA);
		System.out.println("Vertauschoperationen: "+vertauschO[3]/counterA);
		System.out.println("Zeit: "+insertionSort_zeit/counterA);
	}
	
	public static void gleichsetzen() {
		for(int j=0; j<zahlen.length; j++) {
			zufallszahlen[j]=zahlen[j];
		}
	}
	
	public static void befüllen() {
		for(int i=0; i<zufallszahlen.length; i++) {
			zufallszahlen[i]=(int)(Math.random()*(anzahl+1));
			zahlen[i]=zufallszahlen[i];
		}
	}
	
	public static void bubbleSort(int[] zahlen) {
		long start=System.nanoTime();
		for(int i=zahlen.length; i>1; i--) {
			for(int j=0; j<i-1; j++) {
				if(zahlen[j]>zahlen[j+1]) {
					vertauschO[0]=vertauschO[0]+1;
					int b=zahlen[j];
					zahlen[j]=zahlen[j+1];
					zahlen[j+1]=b;
				}
				vergleichO[0]=vergleichO[0]+1;
			}
		}
		bubbleSort_zeit=System.nanoTime()-start;
	}
	
	public static void unstableSelectionSort(int[] zahlen) {
		int min;
		int indexmin=0;
		long start=System.nanoTime();
		for(int i=0; i<zahlen.length; i++) {
			min=zahlen[i];
			for(int j=i+1; j<zahlen.length; j++) {
				if(zahlen[j]<min) {
					min=zahlen[j];
					indexmin=j;
				}
				vergleichO[1]=vergleichO[1]+1;
			}
			if (zahlen[indexmin]!=zahlen[i]) {
				vertauschO[1] = vertauschO[1] + 1;
				int b = zahlen[indexmin];
				zahlen[indexmin] = zahlen[i];
				zahlen[i] = b;
			}
		}
		unstableSelectionSort_zeit=System.nanoTime()-start;
	}

	public static void stableSelectionSort(int[] zahlen) {
		int min=10000;
		int indexmin=0;
		long start=System.nanoTime();
		for(int i=0; i<zahlen.length; i++) {
			min=zahlen[i];
			for(int j=i+1; j<zahlen.length; j++) {
				if(zahlen[j]<min) {
					min=zahlen[j];
					indexmin=j;
				}
				vergleichO[2]=vergleichO[2]+1;
			}
			int a=zahlen[indexmin];
			while(indexmin>i){
				zahlen[indexmin]=zahlen[indexmin-1];
				indexmin--;
				vertauschO[2]=vertauschO[2]+1;
			}
			vertauschO[2]=vertauschO[2]+1;
			zahlen[i]=a;
		}
		stableSelectionSort_zeit=System.nanoTime()-start;
	}
	
	public static void insertionSort(int[] zahlen) {
		long start=System.nanoTime();
		for(int i=1; i<zahlen.length; i++) {
			int gesucht=zahlen[i];
			int index=binaerSuche(gesucht, 0, i-1, zahlen);
			for(int j=i-1; j>=index; j--) {
				zahlen[j+1]=zahlen[j];
				vertauschO[3]=vertauschO[3]+1;
			}
			zahlen[index]=gesucht;
			vertauschO[3]=vertauschO[3]+1;
		}
		insertionSort_zeit=System.nanoTime()-start;
	}
	
	public static int binaerSuche(int gesucht, int min, int max, int[] zahlen) {
		int mitte;
		while (min<=max) {
			vergleichO[3]=vergleichO[3]+1;
			mitte=min+(max-min)/2;
			if (gesucht < zahlen[mitte]) {
				max=mitte-1;
			} else {
				min=mitte+1;
			}
		}
		return min;
	}
}
