import java.util.Scanner;

public abstract class Befüllen {

	Scanner s=new Scanner(System.in);
	
	int größe=100;
	private int[] zahlen=new int[größe];
	private int gesucht;
	boolean erneut=true;
	int zwischenspeicher;
	String test;
	
	public void befüllen() {
		for(int i=0; i<zahlen.length; i++) {
			zahlen[i]=(int) (Math.random()*größe);
		}
		for(int i=0; i<zahlen.length; i++) {
			for(int j=i+1; j<zahlen.length; j++) {
				if(zahlen[i]>zahlen[j]) {
					zwischenspeicher=zahlen[j];
					zahlen[j]=zahlen[i];
					zahlen[i]=zwischenspeicher;
				}
			}
		}
		
		System.out.println("Gesuchte Zahl eingeben: ");
		do {
			try {
				test = s.nextLine();
				gesucht = Integer.parseInt(test);
				erneut=false;
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				System.out.println("Keine Buchstaben!");
			} 
		} while (erneut);
	}
	
	public int[] getZahlen() {
		return zahlen;
	}

	public void setZahlen(int[] zahlen) {
		this.zahlen = zahlen;
	}

	public int getGesucht() {
		return gesucht;
	}

	public void setGesucht(int gesucht) {
		this.gesucht = gesucht;
	}

	public abstract int sucheB();
	public abstract int sucheF();
	public abstract void suche();
	
}
