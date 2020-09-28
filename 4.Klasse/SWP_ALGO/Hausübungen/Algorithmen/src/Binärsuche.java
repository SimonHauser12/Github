
public class Binärsuche extends Befüllen{

	private int mitte=getZahlen().length/2-1;
	private int zähler=1;
	private int zählerF=0;
	int counter=1;
	int max=getZahlen().length-1;
	int min=0;
	int halbierer=(int)(getZahlen().length/2);
	boolean gefunden=false;
	
	public void sucheB() {
		long zeit=System.nanoTime();
		while(gefunden==false){
			halbierer=(int)(halbierer/2);
			if (zähler<=(int)(Math.log(größe)/Math.log(2))+1) {
				if (getGesucht()==getZahlen()[mitte]) {
					gefunden = true;
					long benötigt = System.nanoTime() - zeit;
					System.out.println("Binärsuche: Zahl gefunden!");
					System.out.println("Benötigte Zeit: " + benötigt + " Millisekunden");
				}else {
					if (getGesucht() < getZahlen()[mitte]) {
						max = mitte;
						if(halbierer!=1&&counter==1) {
							mitte = (int) (max-halbierer);
						}else {
							halbierer=2; counter--;
							mitte = (int) (max-halbierer);
						}
					}else {
						if (getGesucht() > getZahlen()[mitte]) {
							min = mitte;
							if(halbierer!=1&&counter==1) {
								mitte = (int) (min + halbierer);
							}else {
								halbierer=2; counter--;
								mitte = (int) (min + halbierer);
							}
						} 
					}
				}
			}else {
				gefunden=true;
				System.out.println("Zahl nicht im Array enthalten!");
			}
			
			zähler++;
		}
	}
	
	public void sucheF() {
		long zeit=System.nanoTime();
		for(int i=0; i<getZahlen().length; i++) {
			if(getGesucht()==getZahlen()[i]) {
				long benötigt=System.nanoTime()-zeit;
				System.out.println("For-Schlafe: Zahl gefunden!");
				System.out.println("Benötigte Zeit: "+ benötigt+ " Millisekunden");
				zählerF++;
				break;
			}
		}
		if(zählerF==0) System.out.println("Zahl nicht im Array enthalten!");
	}
}
