
public class Sortieralgorithmen {

	static int anzahl=1000;
	private static int[] zufallszahlen=new int[anzahl];
	private static int[] vertauschO=new int[3];
	private static int[] vergleichO=new int[3];
	static int[] zahlen=new int[anzahl];
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			befüllen();
			bubbleSort(zufallszahlen);
			gleichsetzen();
			unstableSelectionSort(zufallszahlen);
			gleichsetzen();
			stableSelectionSort(zufallszahlen);
		}
		ausgabe();
	}
	
	public static void ausgabe() {
		System.out.println("BubbleSort:");
		System.out.println("Vergleichoperationen: "+vergleichO[0]);
		System.out.println("Vertauschoperationen: "+vertauschO[0]);
		System.out.println();
		System.out.println("Unstable SelectionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[1]);
		System.out.println("Vertauschoperationen: "+vertauschO[1]);
		System.out.println();
		System.out.println("Stable SelectionSort: ");
		System.out.println("Vergleichoperationen: "+vergleichO[2]);
		System.out.println("Vertauschoperationen: "+vertauschO[2]);
		System.out.println();
	}
	
	public static void gleichsetzen() {
		for(int j=0; j<zahlen.length; j++) {
			zufallszahlen[j]=zahlen[j];
		}
	}
	
	public static void befüllen() {
		for(int i=0; i<zufallszahlen.length; i++) {
			zufallszahlen[i]=(int)(Math.random()*1001);
			zahlen[i]=zufallszahlen[i];
		}
	}
	
	public static void bubbleSort(int[] zahlen) {
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
	}
	
	public static void unstableSelectionSort(int[] zahlen) {
		int min;
		int indexmin=0;
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
	}

	public static void stableSelectionSort(int[] zahlen) {
		int min=10000;
		int indexmin=0;
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
	}
}
