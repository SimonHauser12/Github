
public class Kombinationen2 extends Kombinationen1{
	
	private int twoPair=0;
	private int threeofaKind=0;
	private int fourofaKind=0;
	private int fullHouse=0;
	static int pa=0;
	static int tw=0;
	static int th=0;
	static int fo=0;
	static int fu=0;
	
	public void kombinationen2() {
		
		if(pair()==true&&twoPair()==false&&fourofaKind()==false&&fullHouse()==false&&threeofaKind()==false) {
			//System.out.println("Sie haben ein Pair!!");
			pa++;
		}
		if(twoPair()==true&&fullHouse()==false) {
			//System.out.println("Sie haben zwei Pair!!");
			tw++;
		}
		if(threeofaKind()==true&&fullHouse()==false) {
			//System.out.println("Sie haben Three of a Kind!!");
			th++;
		}
		if(fourofaKind()==true) {
			//System.out.println("Sie haben Four of a Kind!!");
			fo++;
		}
		if(fullHouse()==true) {
			//System.out.println("Sie haben ein Full House!!");
			fu++;
		}
	}
	
	public boolean pair() {
		for(int i=0; i<zeichenZ.length-1; i++) {
			for(int j=i+1; j<zeichenZ.length; j++) {
				if(zeichenZ[i]==zeichenZ[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean twoPair() {
		twoPair=0;
		for(int i=0; i<zeichenZ.length-1; i++) {
			for(int j=i+1; j<zeichenZ.length; j++) {
				if(zeichenZ[i]==zeichenZ[j]) {
					twoPair++;
				}
			}
		}
		if(twoPair==2) {
			return true;
		}
		return false;
	}
	
	public boolean threeofaKind() {
		threeofaKind=0;
		for(int i=0; i<zeichenZ.length-1; i++) {
			for(int j=i+1; j<zeichenZ.length; j++) {
				if(zeichenZ[i]==zeichenZ[j]) {
					threeofaKind++;
				}
			}
		}
		if(threeofaKind==3) {
			return true;
		}
		return false;
	}
	
	public boolean fourofaKind() {
		fourofaKind=0;
		for(int i=0; i<zeichenZ.length-1; i++) {
			for(int j=i+1; j<zeichenZ.length; j++) {
				if(zeichenZ[i]==zeichenZ[j]) {
					fourofaKind++;
				}
			}
		}
		if(fourofaKind==6) {
			return true;
		}
		return false;
	}
	
	public boolean fullHouse() {
		fullHouse=0;
		for(int i=0; i<zeichenZ.length-1; i++) {
			for(int j=i+1; j<zeichenZ.length; j++) {
				if(zeichenZ[i]==zeichenZ[j]) {
					fullHouse++;
				}
			}
		}
		if(fullHouse==4) {
			return true;
		}
		return false;
	}
	
	public boolean twoPair_Assertion(int z1, int z2, int z3) {
		twoPair=0;
		zeichenZ[0]=z1;
		zeichenZ[1]=z2;
		zeichenZ[2]=z3;
		for(int i=0; i<zeichenZ.length-1; i++) {
			for(int j=i+1; j<zeichenZ.length; j++) {
				if(zeichenZ[i]==zeichenZ[j]) {
					twoPair++;
				}
			}
		}
		assert twoPair==2: "ASSERT: Es liegt kein TwoPair vor";
		if(twoPair==2) {
			return true;
		}
		return false;
	}
}
