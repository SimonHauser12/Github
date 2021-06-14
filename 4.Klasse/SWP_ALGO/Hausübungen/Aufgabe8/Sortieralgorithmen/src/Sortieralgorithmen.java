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
	private static ArrayList<Integer> vertauschO_QS=new ArrayList<Integer>();
	private static ArrayList<Integer> vergleichO_QS=new ArrayList<Integer>();
	private static ArrayList<Integer> vertauschO_MS=new ArrayList<Integer>();
	private static ArrayList<Integer> vergleichO_MS=new ArrayList<Integer>();
	private static int[] vergleichO=new int[6];
	private static int[] vertauschO=new int[6];
	static int[] zahlen=new int[anzahl];
	static long bubbleSort_zeit;
	static long unstableSelectionSort_zeit;
	static long stableSelectionSort_zeit;
	static long insertionSort_zeit;
	static long quickSort_zeit;
	static long mergeSort_zeit;
	
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
			gleichsetzen();
			
			quickSort_Main(zufallszahlen);
			gleichsetzen();
			
			mergeSort_Main(zufallszahlen);
			gleichsetzen();
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
		Collections.sort(vergleichO_QS);
		Collections.sort(vergleichO_QS);
		Collections.sort(vergleichO_MS);
		Collections.sort(vergleichO_MS);
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
		for(int i=0; i<vertauschO_QS.size(); i++) {
			vertauschO[4]=vertauschO[4]+vertauschO_QS.get(i);
		}
		for(int i=0; i<vergleichO_QS.size(); i++) {
			vergleichO[4]=vergleichO[4]+vergleichO_QS.get(i);
		}
		for(int i=0; i<vertauschO_MS.size(); i++) {
			vertauschO[5]=vertauschO[5]+vertauschO_MS.get(i);
		}
		for(int i=0; i<vergleichO_MS.size(); i++) {
			vergleichO[5]=vergleichO[5]+vergleichO_MS.get(i);
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
		System.out.println();
		System.out.println("QuickSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[4]/counterA+" | Median: "+vergleichO_QS.get((counterA/2)-1));
		System.out.println("Vertauschoperationen: "+vertauschO[4]/counterA+" | Median: "+vertauschO_QS.get((counterA/2)-1));
		System.out.println("Zeit: "+quickSort_zeit/counterA);
		System.out.println();
		System.out.println("MergeSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[5]/counterA+" | Median: "+vergleichO_MS.get((counterA/2)-1));
		System.out.println("Vertauschoperationen: "+vertauschO[5]/counterA+" | Median: "+vertauschO_MS.get((counterA/2)-1));
		System.out.println("Zeit: "+mergeSort_zeit/counterA);
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
	
	static int counter_vert=0;
	static int counter_verg=0;
	
	public static void quickSort_Main(int[] zahlen) {
		counter_verg=0;
		counter_vert=0;
		int n=zahlen.length;
		long start=System.nanoTime();
		quickSort(zahlen, 0, n-1);
		quickSort_zeit=quickSort_zeit+System.nanoTime()-start;
		vertauschO_QS.add(counter_vert);
		vergleichO_QS.add(counter_verg);
	}
	
	public static void swap(int[] zahlen, int i, int j) {
		counter_vert++;
		int a=zahlen[i];
		zahlen[i]=zahlen[j];
		zahlen[j]=a;
	}
	
	public static int partition(int[] zahlen, int low, int high) {
		int pivot=zahlen[high];
		int i=(low-1);
		for(int j=low; j<=high-1; j++) {
			if(zahlen[j]<pivot) {
				i++;
				swap(zahlen, i, j);
			}
			counter_verg++;
		}
		swap(zahlen, i+1, high);
		return (i+1);
	}
	
	public static void quickSort(int[] zahlen, int low, int high) {
		if(low<high) {
			int pi=partition(zahlen, low, high);
			quickSort(zahlen, low, pi-1);
			quickSort(zahlen, pi+1, high);
		}
	}
	
	public static void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        int i = 0, j = 0;
 
        int k = l;
        counter_verg++;
        while (i < n1 && j < n2) {
        	counter_verg++;
            if (L[i] <= R[j]) {
            	counter_vert++;
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                counter_vert++;
                j++;
            }
            k++;
        }
 
        counter_verg++;
        while (i < n1) {
        	counter_verg++;
        	counter_vert++;
            arr[k] = L[i];
            i++;
            k++;
        }
 
        counter_verg++;
        while (j < n2) {
        	counter_verg++;
        	counter_vert++;
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    public static void sort(int arr[], int l, int r)
    {
        if (l < r) {
            int m =l+ (r-l)/2;
 
            sort(arr, l, m);
            sort(arr, m + 1, r);
 
            merge(arr, l, m, r);
        }
    }
 
    public static void mergeSort_Main(int[] arr)
    {
    	counter_verg=0;
		counter_vert=0;
		long start=System.nanoTime();
		sort(arr, 0, arr.length - 1);
		mergeSort_zeit=mergeSort_zeit+System.nanoTime()-start;
		vertauschO_MS.add(counter_vert);
		vergleichO_MS.add(counter_verg);
        
    }
}
