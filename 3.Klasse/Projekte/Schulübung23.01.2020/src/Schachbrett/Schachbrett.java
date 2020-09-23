package Schachbrett;

public class Schachbrett{

	int figuren=0;
	int laenge;
	int breite;
	int[][] brett;
	
	public void laengebreite(int a, int b) {
		brett=new int[a][b];
		laenge=a;
		breite=b;
	}
	
	public int zustandAktuell(Figuren b, Figuren l, Figuren s, Figuren t, Figuren k, Figuren K) {
		int gesamt=(b.anzahlGesamt)+(l.anzahlGesamt)+s.anzahlGesamt+t.anzahlGesamt+k.anzahlGesamt+K.anzahlGesamt;
		for (int i = 0; i < brett.length; i++) {
			for(int j=0; j<brett[i].length; j++) {
				if(gesamt>=figuren) {
					brett[i][j] = (int) (Math.random()*2);
					if(brett[i][j]==1) figuren++;
				}
				if(gesamt<figuren) {
					brett[i][j] = 0;
				}
			}
		}
		
		System.out.println("_________________________________________________");
		for(int i=0; i<brett.length; i++) {
			System.out.print("|  ");
			for(int j=0; j<brett[i].length; j++) {
				System.out.print(brett[i][j]+"  |  ");
			}
			System.out.println();
			System.out.println("_________________________________________________");
		}
		return gesamt;
	}
	
}
