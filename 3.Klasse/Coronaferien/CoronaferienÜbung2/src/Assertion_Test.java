
public class Assertion_Test{

	int[] zeichen;
	int threeofaKind;
	int twoPair;
	public boolean threeofaKind_Assertion(int z1, int z2, int z3, int z4) {
		threeofaKind=0;
		int[] zeichen= {z1, z2, z3, z4};
		for(int i=0; i<zeichen.length-1; i++) {
			for(int j=i+1; j<zeichen.length; j++) {
				if(zeichen[i]==zeichen[j]) {
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
	
	public boolean twoPair_Assertion(int z1, int z2, int z3, int z4) {
		twoPair=0;
		int[] zeichen= {z1, z2, z3, z4};
		for(int i=0; i<zeichen.length-1; i++) {
			for(int j=i+1; j<zeichen.length; j++) {
				if(zeichen[i]==zeichen[j]) {
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
