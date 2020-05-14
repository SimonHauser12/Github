
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
			case 10: nachv+='k'; break; 
			case 11: nachv+='l'; break; 
			case 12: nachv+='m'; break; 
			case 13: nachv+='n'; break; 
			case 14: nachv+='o'; break; 
			case 15: nachv+='p'; break; 
			case 16: nachv+='q'; break; 
			case 17: nachv+='r'; break; 
			case 18: nachv+='s'; break; 
			case 19: nachv+='t'; break; 
			case 20: nachv+='u'; break; 
			case 21: nachv+='v'; break; 
			case 22: nachv+='w'; break; 
			case 23: nachv+='x'; break; 
			case 24: nachv+='y'; break; 
			case 25: nachv+='z'; break; 
			case 26: nachv+=' '; break;
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
			case 'k': nachv[i]=10; break; 
			case 'l': nachv[i]=11; break; 
			case 'm': nachv[i]=12; break; 
			case 'n': nachv[i]=13; break; 
			case 'o': nachv[i]=14; break; 
			case 'p': nachv[i]=15; break; 
			case 'q': nachv[i]=16; break; 
			case 'r': nachv[i]=17; break; 
			case 's': nachv[i]=18; break; 
			case 't': nachv[i]=19; break; 
			case 'u': nachv[i]=20; break; 
			case 'v': nachv[i]=21; break; 
			case 'w': nachv[i]=22; break; 
			case 'x': nachv[i]=23; break; 
			case 'y': nachv[i]=24; break; 
			case 'z': nachv[i]=25; break; 
			case ' ': nachv[i]=26; break;
			}
		}
		return nachv;
	}
}
