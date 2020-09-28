
public class Binärsuche extends Befüllen{

	private int mitte=getZahlen().length/2-1;
	int counter=1;
	int max=getZahlen().length-1;
	int min=0;
	int halbierer=(int)(getZahlen().length/2);
	boolean gefunden=false;
	
	public void suche() {
		long zeit=System.nanoTime();
		switch(sucheB()) {
		case 1: 
			long benötigt = System.nanoTime() - zeit;
			System.out.println("Zahl gefunden: "+ benötigt+ " Nanosekunden");
			break;
		case -1: 
			System.out.println("Zahl nicht gefunden!");
			break;
		}
		long zeit2=System.nanoTime();
		switch(sucheF()) {
		case 1: 
			long benötigt = System.nanoTime() - zeit2;
			System.out.println("Zahl gefunden: "+ benötigt+ " Nanosekunden");
			break;
		case -1: 
			System.out.println("Zahl nicht gefunden!");
			break;
		}
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
