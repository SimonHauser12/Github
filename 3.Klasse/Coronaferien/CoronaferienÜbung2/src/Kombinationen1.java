
public class Kombinationen1 extends Pokerspiel{

	static int rof=0;
	static int strf=0;
	static int fl=0;
	static int str=0;
	private int max=0;
	private int min=12;
	private int max2;
	private int min2;
	private int mittel;
	private int straightFlush;
	private int royalFlush;
	private int he;
	private int ka;
	private int kr;
	private int pi;
	
	public void MaxMinSuche() {
		for(int i=0; i<zeichenZ.length; i++) {
			if(zeichenZ[i]>max) {
				max=zeichenZ[i];
			}
			if(zeichenZ[i]<min) {
				min=zeichenZ[i];
			}
		}
		for(int i=0; i<zeichenZ.length; i++) {
			if(zeichenZ[i]==(max+min)/2) {
				mittel=zeichenZ[i];
			}
			if(zeichenZ[i]==max-1) {
				max2=zeichenZ[i];
			}
			if(zeichenZ[i]==min+1) {
				min2=zeichenZ[i];
			}
		}
	}
	public void kombinationen1() {
		
		MaxMinSuche();
		
		if(straight()==true&&straightFlush()==false&&royalFlush()==false) {
			//System.out.println("Sie haben eine Straight!!");
			str++;
		}
		if(flush()==true&&straightFlush()==false&&royalFlush()==false) {
			//System.out.println("Sie haben einen Flush!!");
			fl++;
		}
		if(straightFlush()==true&&royalFlush()==false) {
			//System.out.println("Sie haben einen Straight Flush!!");
			strf++;
		}
		if(royalFlush()==true) {
			//System.out.println("Sie haben einen Royal Flush!!");
			rof++;
		}
		
	}
	
	public boolean straight() {
		int z2=0;
		int z3=0;
		if(max-min==4&&max-2==mittel&&max2-min2==2) {
			return true;
		}
		for(int i=0; i<zeichenZ.length;i++) {
			if(zeichenZ[i]==2) {
				z2++;
			}
			if(zeichenZ[i]==3) {
				z3++;
			}
		}
		if(z2==1&&z3==1) {
			if(max==12&&min==0&&min2==1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean flush() {
		he=0; pi=0; kr=0; ka=0;
		for(int i=0; i<farbe.length; i++) {
			switch(farbe[i]) {
			case "Herz": he++; break;
			case "Pik": pi++; break;
			case "Kreuz": kr++; break;
			case "Karo": ka++; break;
			}
		}
		if(he==5||pi==5||kr==5||ka==5) {
			return true;
		}
		return false;
	}
	
	public boolean straightFlush() {
		straightFlush=0;
		if(max-min==4&&max-2==mittel&&max2-min2==2) {
			 straightFlush++;
		}
		
		if(flush()==true) {
			straightFlush++;
		}
		if(straightFlush==2) {
			return true;
		}
		return false;
	}
	
	public boolean royalFlush() {
		royalFlush=0;
		if(max==12&&min==8) {
			if(max-2==mittel&&max2-min2==2) {
				 royalFlush++;
			}
		}
		
		if(flush()==true) {
			royalFlush++;
		}
		
		if(royalFlush==2) {
			return true;
		}
		return false;
		
	}
}
