import java.util.Scanner;

public class PokerspielMain {
	
	static double anzahlPair;
	static double anzahlFlush;
	static double anzahlFullHouse;
	static double anzahlStraight;
	static double anzahlStraigthFlush;
	static double anzahlRoyalFlush;
	static double anzahlThreeofaKind;
	static double anzahlTwoPair;
	static double anzahlFourofaKind;
	
	public static void main(String[] args) {
		int durchlaeufe=0;
		
		Scanner durch=new Scanner(System.in);
		
		while(durchlaeufe==0){
			try {
				System.out.print("Bitte Anzahl der Durchläufe eingeben: ");
				durchlaeufe=Integer.parseInt(durch.next());
				
			}catch(Exception e){
				System.out.println("This is not a number");
			}
		}
		
		for(int i=0; i<durchlaeufe; i++) {	
			Kombinationen2 l=new Kombinationen2();
			l.befuellen();
			l.kartenZiehen();
			l.kombinationen1();
			l.kombinationen2();
			anzahlFlush=Kombinationen1.fl;
			anzahlPair=Kombinationen2.pa;
			anzahlFullHouse=Kombinationen2.fu;
			anzahlStraight=Kombinationen1.str;
			anzahlStraigthFlush=Kombinationen1.strf;
			anzahlRoyalFlush=Kombinationen1.rof;
			anzahlThreeofaKind=Kombinationen2.th;
			anzahlTwoPair=Kombinationen2.tw;
			anzahlFourofaKind=Kombinationen2.fo;
		}
			
		System.out.println("Durchläufe: "+durchlaeufe);
		System.out.println();
		System.out.println("Pair: "+anzahlPair/durchlaeufe*100+"%");
		System.out.println("TwoPair: "+anzahlTwoPair/durchlaeufe*100+"%");
		System.out.println("ThreeofaKind: "+anzahlThreeofaKind/durchlaeufe*100+"%");
		System.out.println("Straight: "+anzahlStraight/durchlaeufe*100+"%");
		System.out.println("Flush: "+anzahlFlush/durchlaeufe*100+"%");
		System.out.println("FullHouse: "+anzahlFullHouse/durchlaeufe*100+"%");
		System.out.println("FourofaKind: "+anzahlFourofaKind/durchlaeufe*100+"%");
		System.out.println("StraightFlush: "+anzahlStraigthFlush/durchlaeufe*100+"%");
		System.out.println("RoyalFlush: "+anzahlRoyalFlush/durchlaeufe*100+"%");
			
	}
}
