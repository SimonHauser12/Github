import java.util.Scanner;

public class Tries {
	
	Scanner r=new Scanner(System.in);
	private StringBuffer result=new StringBuffer();
	private StringBuffer word=new StringBuffer();
	
	public Tries(String s) {
		s=s.toUpperCase();
		for(int i=0; i<s.length(); i++) {
			word.append(s.charAt(i)+" ");
			result.append("_ ");
		}
	}

	public void guess() {
		String test;
		boolean check=false;
		do {
			check=false;
			System.out.print("Einen Buchstaben eingaben: ");
			test = r.next();
			test=test.toUpperCase();
			if (test.length() > 1) {
				System.out.println("Nur ein Buchstabe!!!");
				check=true;
			} 
		} while (check);
		
		for(int i=0; i<word.length(); i++) {
			if(word.charAt(i)==test.charAt(0)) {
				result.setCharAt(i, test.charAt(0));
			}
		}
	}
	
	public boolean attempts() {
		int counter=14;
		System.out.println(result);
		do {
			counter--;
			guess();
			System.out.println(result);
			System.out.println("Sie haben noch "+counter+" Versuche!");
			System.out.println();
			if(result.toString().equals(word.toString())) {
				System.out.println("Sie haben gewonnen!");
				System.out.println();
				return true;
			}
		} while (counter>0);
		System.out.println("Sie haben verloren!");
		r.close();
		return false;
	}
	
}
