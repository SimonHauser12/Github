
public class Assertion_Test extends Kombinationen1{

	int threeofaKind;
	int twoPair;
	public boolean threeofaKind_Assertion(int z1, int z2, int z3, int z4) {
		threeofaKind=0;
		zeichenZ[0]=z1;
		zeichenZ[1]=z2;
		zeichenZ[2]=z3;
		zeichenZ[3]=z4;
		for(int i=0; i<zeichenZ.length-1; i++) {
			for(int j=i+1; j<zeichenZ.length; j++) {
				if(zeichenZ[i]==zeichenZ[j]) {
					threeofaKind++;
				}
			}
		}
		assert threeofaKind==3: "ASSERT: Es liegt kein ThreeofaKind vor";
		if(threeofaKind==3) {
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
