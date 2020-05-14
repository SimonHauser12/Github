
public class NumToText_TextToNum {

	public String numberToText(int[] nach) {
		String nachv="";
		for(int i=0; i<nach.length; i++) {
			switch(nach[i]) {
			case 0: nachv+='a'; break; 
			case 1: nachv+='b'; break; 
			case 2: nachv+='c'; break; 
			case 3: nachv+='d'; break; 
			case 4: nachv+='e'; break; 
			case 5: nachv+='f'; break; 
			case 6: nachv+='g'; break; 
			case 7: nachv+='h'; break; 
			case 8: nachv+='i'; break; 
			case 9: nachv+='j'; break; 
			}
		}
		return nachv;
	}
	
	public int[] textToNumber(String nach) {
		int[] nachv=new int[nach.length()];
		for(int i=0; i<nach.length(); i++) {
			switch(nach.charAt(i)) {
			case 'a': nachv[i]=0; break; 
			case 'b': nachv[i]=1; break; 
			case 'c': nachv[i]=2; break; 
			case 'd': nachv[i]=3; break; 
			case 'e': nachv[i]=4; break; 
			case 'f': nachv[i]=5; break; 
			case 'g': nachv[i]=6; break; 
			case 'h': nachv[i]=7; break; 
			case 'i': nachv[i]=8; break; 
			case 'j': nachv[i]=9; break; 
			}
		}
		return nachv;
	}
}
