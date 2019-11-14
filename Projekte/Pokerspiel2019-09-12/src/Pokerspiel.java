
public class Pokerspiel {
	
	protected String[] farbe=new String[5];
	protected String[] zeichen=new String[5];
	protected int[] zeichenZ=new int[5];			//Zeichen als Zahl gespeichert
	protected int[] karten=new int[52];
	private int lastplace=51;
	private int zufall;
	private final int anzahl=13;
	private int zwischenspeicher;

	public void befuellen() {
		for(int i=0; i<karten.length; i++) {
			karten[i]=i;
		}
	}
	
	public void kartenZiehen() {				// Karten ziehen + Farbe und Zeichen zuweißen
		for(int i=0; i<zeichen.length; i++) {
			zufall=(int)(Math.random()*lastplace);
			
			switch(karten[zufall]/anzahl) {
			case 0 : farbe[i]="Herz"; break;
			case 1 : farbe[i]="Kreuz"; break;
			case 2 : farbe[i]="Pik"; break;
			case 3 : farbe[i]="Karo"; break;
			}
			
			switch(karten[zufall]%anzahl) {
			case 0 : zeichen[i]="2"; zeichenZ[i]=0; break;
			case 1 : zeichen[i]="3"; zeichenZ[i]=1; break;
			case 2 : zeichen[i]="4"; zeichenZ[i]=2; break;
			case 3 : zeichen[i]="5"; zeichenZ[i]=3; break;
			case 4 : zeichen[i]="6"; zeichenZ[i]=4; break;
			case 5 : zeichen[i]="7"; zeichenZ[i]=5; break;
			case 6 : zeichen[i]="8"; zeichenZ[i]=6; break;
			case 7 : zeichen[i]="9"; zeichenZ[i]=7; break;
			case 8 : zeichen[i]="10"; zeichenZ[i]=8; break;
			case 9 : zeichen[i]="Junge"; zeichenZ[i]=9; break;
			case 10 : zeichen[i]="Dame"; zeichenZ[i]=10; break;
			case 11 : zeichen[i]="König"; zeichenZ[i]=11; break;
			case 12 : zeichen[i]="Ass"; zeichenZ[i]=12; break;
			}
			
			zwischenspeicher=karten[lastplace];
			karten[lastplace]=karten[zufall];
			karten[zufall]=zwischenspeicher;
			
			lastplace--;
		}
		
		/*System.out.println("Deine Karten sind: ");
		for(int i=0; i<zeichen.length; i++) {
			System.out.println(farbe[i]+" "+zeichen[i]);
		}*/
	}
}
