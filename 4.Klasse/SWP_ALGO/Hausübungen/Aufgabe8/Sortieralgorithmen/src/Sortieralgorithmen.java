import java.util.ArrayList;
import java.util.Collections;

public class Sortieralgorithmen {

	static int anzahl=1000;
	static int counterA=anzahl/10;
	private static int[] zufallszahlen=new int[anzahl];
	private static ArrayList<Integer> vertauschO_BS=new ArrayList<Integer>();
	private static ArrayList<Integer> vergleichO_BS=new ArrayList<Integer>();
	private static ArrayList<Integer> vertauschO_US=new ArrayList<Integer>();
	private static ArrayList<Integer> vergleichO_US=new ArrayList<Integer>();
	private static ArrayList<Integer> vertauschO_SS=new ArrayList<Integer>();
	private static ArrayList<Integer> vergleichO_SS=new ArrayList<Integer>();
	private static ArrayList<Integer> vertauschO_IS=new ArrayList<Integer>();
	private static ArrayList<Integer> vergleichO_IS=new ArrayList<Integer>();
	private static int[] vergleichO=new int[4];
	private static int[] vertauschO=new int[4];
	static int[] zahlen=new int[anzahl];
	static long bubbleSort_zeit;
	static long unstableSelectionSort_zeit;
	static long stableSelectionSort_zeit;
	static long insertionSort_zeit;
	
	public static void main(String[] args) {
		for (int i = 0; i < counterA; i++) {
			befüllen();
			bubbleSort(zufallszahlen);
			gleichsetzen();
			unstableSelectionSort(zufallszahlen);
			gleichsetzen();
			stableSelectionSort(zufallszahlen);
			gleichsetzen();
			insertionSort(zufallszahlen);
		}
		werte();
		ausgabe();
	}
	
	public static void werte() {
		Collections.sort(vertauschO_BS);
		Collections.sort(vertauschO_US);
		Collections.sort(vertauschO_SS);
		Collections.sort(vertauschO_IS);
		Collections.sort(vergleichO_BS);
		Collections.sort(vergleichO_US);
		Collections.sort(vergleichO_SS);
		Collections.sort(vergleichO_IS);
		for(int i=0; i<vertauschO_BS.size(); i++) {
			vertauschO[0]=vertauschO[0]+vertauschO_BS.get(i);
		}
		for(int i=0; i<vergleichO_BS.size(); i++) {
			vergleichO[0]=vergleichO[0]+vergleichO_BS.get(i);
		}
		for(int i=0; i<vertauschO_US.size(); i++) {
			vertauschO[1]=vertauschO[1]+vertauschO_US.get(i);
		}
		for(int i=0; i<vergleichO_US.size(); i++) {
			vergleichO[1]=vergleichO[1]+vergleichO_US.get(i);
		}
		for(int i=0; i<vertauschO_SS.size(); i++) {
			vertauschO[2]=vertauschO[2]+vertauschO_SS.get(i);
		}
		for(int i=0; i<vergleichO_SS.size(); i++) {
			vergleichO[2]=vergleichO[2]+vergleichO_SS.get(i);
		}
		for(int i=0; i<vertauschO_IS.size(); i++) {
			vertauschO[3]=vertauschO[3]+vertauschO_IS.get(i);
		}
		for(int i=0; i<vergleichO_IS.size(); i++) {
			vergleichO[3]=vergleichO[3]+vergleichO_IS.get(i);
		}
	}
	
	public static void ausgabe() {
		System.out.println("Anzahl Arrays: "+counterA);
		System.out.println("Elemente pro Array: "+anzahl);
		System.out.println("Zahlenbereich Zufallzahlen: 0 bis "+anzahl);
		System.out.println();
		System.out.println("BubbleSort:");
		System.out.println("Vergleichoperationen: "+vergleichO[0]/counterA+" | Median: "+vergleichO_BS.get((counterA/2)-1));
		System.out.println("Vertauschoperationen: "+vertauschO[0]/counterA+" | Median: "+vertauschO_BS.get((counterA/2)-1));
		System.out.println("Zeit: "+bubbleSort_zeit/counterA);
		System.out.println();
		System.out.println("Unstable SelectionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[1]/counterA+" | Median: "+vergleichO_US.get((counterA/2)-1));
		System.out.println("Vertauschoperationen: "+vertauschO[1]/counterA+" | Median: "+vertauschO_US.get((counterA/2)-1));
		System.out.println("Zeit: "+unstableSelectionSort_zeit/counterA);
		System.out.println();
		System.out.println("Stable SelectionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[2]/counterA+" | Median: "+vergleichO_SS.get((counterA/2)-1));
		System.out.println("Vertauschoperationen: "+vertauschO[2]/counterA+" | Median: "+vertauschO_SS.get((counterA/2)-1));
		System.out.println("Zeit: "+stableSelectionSort_zeit/counterA);
		System.out.println();
		System.out.println("InsertionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[3]/counterA+" | Median: "+vergleichO_IS.get((counterA/2)-1));
		System.out.println("Vertauschoperationen: "+vertauschO[3]/counterA+" | Median: "+vertauschO_IS.get((counterA/2)-1));
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
		int counter_a=0;
		int counter_b=0;
		long start=System.nanoTime();
		for(int i=zahlen.length; i>1; i--) {
			for(int j=0; j<i-1; j++) {
				if(zahlen[j]>zahlen[j+1]) {
					counter_a++;
					int b=zahlen[j];
					zahlen[j]=zahlen[j+1];
					zahlen[j+1]=b;
				}
				counter_b++;
			}
		}
		bubbleSort_zeit=bubbleSort_zeit+System.nanoTime()-start;
		vertauschO_BS.add(counter_a);
		vergleichO_BS.add(counter_b);
	}
	
	public static void unstableSelectionSort(int[] zahlen) {
		int counter_a=0;
		int counter_b=0;
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
				counter_b++;
			}
			if (zahlen[indexmin]!=zahlen[i]) {
				counter_a++;
				int b = zahlen[indexmin];
				zahlen[indexmin] = zahlen[i];
				zahlen[i] = b;
			}
		}
		unstableSelectionSort_zeit=unstableSelectionSort_zeit+System.nanoTime()-start;
		vertauschO_US.add(counter_a);
		vergleichO_US.add(counter_b);
	}

	public static void stableSelectionSort(int[] zahlen) {
		int counter_a=0;
		int counter_b=0;
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
				counter_b++;
			}
			int a=zahlen[indexmin];
			while(indexmin>i){
				zahlen[indexmin]=zahlen[indexmin-1];
				indexmin--;
				counter_a++;
			}
			counter_a++;
			zahlen[i]=a;
		}
		stableSelectionSort_zeit=stableSelectionSort_zeit+System.nanoTime()-start;
		vertauschO_SS.add(counter_a);
		vergleichO_SS.add(counter_b);
	}
	
	public static void insertionSort(int[] zahlen) {
		int counter_a=0;
		int counter_b=0;
		long start=System.nanoTime();
		for(int i=1; i<zahlen.length; i++) {
			int gesucht=zahlen[i];
			int index=i-1;
			while(index>=0 && zahlen[index]>gesucht) {
				counter_a++;
				counter_b++;
				zahlen[index+1]=zahlen[index];
				index--;
			}
			counter_a++;
			zahlen[index+1]=gesucht;
		}
		insertionSort_zeit=insertionSort_zeit+System.nanoTime()-start;
		vertauschO_IS.add(counter_a);
		vergleichO_IS.add(counter_b);
	}
}
