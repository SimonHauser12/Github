
public class Bin�rsuche extends Bef�llen{

	private int mitte=getZahlen().length/2-1;
	int counter=1;
	int max=getZahlen().length-1;
	int min=0;
	boolean gefunden=false;
	
	public void suche() {
		long ben�tigt=0;
		long ben�tigt2=0;
		long zeit=System.nanoTime();
		switch(sucheB()) {
		case 1: 
			ben�tigt = System.nanoTime() - zeit;
			System.out.println("Zahl gefunden: "+ ben�tigt+ " Nanosekunden");
			break;
		case -1: 
			System.out.println("Zahl nicht gefunden!");
			break;
		default:
			break;
		}
		zeit=System.nanoTime();
		switch(sucheF()) {
		case 1: 
			ben�tigt2 = System.nanoTime() - zeit;
			System.out.println("Zahl gefunden: "+ ben�tigt2+ " Nanosekunden");
			break;
		case -1: 
			System.out.println("Zahl nicht gefunden!");
			break;
		default:
			break;
		}
		System.out.println(ben�tigt+ "  "+ben�tigt2);
		System.out.println("Bin�rsuche ist um den Faktor "+ben�tigt2/ben�tigt+" schneller!");
	}
	
	public int sucheB() {
		boolean gefunden=false;
		while(gefunden==false){
			mitte = min + ((max-min)/2);
			if (getGesucht()==getZahlen()[mitte]) {
				gefunden = true;
				return 1;
			}else {
				if (getGesucht() < getZahlen()[mitte]) {
					max = mitte;
				}else {
					if (getGesucht() > getZahlen()[mitte]) {
						min = mitte;
					} 
				}
			}
		}
		return -1;
	}
	
	public int sucheF() {
		for(int i=0; i<getZahlen().length; i++) {
			if(getGesucht()==getZahlen()[i]) {
				return 1;
			}
		}
		return -1;
	}
}
