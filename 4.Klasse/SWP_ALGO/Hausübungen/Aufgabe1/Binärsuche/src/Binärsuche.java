
public class Binärsuche extends Befüllen{

	private int mitte=getZahlen().length/2-1;
	int counter=1;
	int max=getZahlen().length-1;
	int min=0;
	boolean gefunden=false;
	
	public void suche() {
		long benötigt=0;
		long benötigt2=0;
		long zeit=System.nanoTime();
		switch(sucheB()) {
		case 1: 
			benötigt = System.nanoTime() - zeit;
			System.out.println("Zahl gefunden: "+ benötigt+ " Nanosekunden");
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
			benötigt2 = System.nanoTime() - zeit;
			System.out.println("Zahl gefunden: "+ benötigt2+ " Nanosekunden");
			break;
		case -1: 
			System.out.println("Zahl nicht gefunden!");
			break;
		default:
			break;
		}
		System.out.println(benötigt+ "  "+benötigt2);
		System.out.println("Binärsuche ist um den Faktor "+benötigt2/benötigt+" schneller!");
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
