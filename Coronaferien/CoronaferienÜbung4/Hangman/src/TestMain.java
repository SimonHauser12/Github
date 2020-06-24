import java.io.*;
import java.util.Scanner;

public class TestMain {
	private static String word;
	private static String[] words=new String[312];
	private static String[] words_level=new String[312];
	private static int level=1;

	public static void main(String[] args) throws FileNotFoundException {
		fileReader();
		word=searcher();
		System.out.println(word);
		Tries s=new Tries(word);
		if(s.attempts()==true) {
			System.out.println("Willkommen in Level 2");
			level=2;
			word=searcher();
			System.out.println(word);
			Tries w=new Tries(word);
			if(w.attempts()==true) {
				System.out.println("Willkommen in Level 3");
				level=3;
				word=searcher();
				System.out.println(word);
				Tries p=new Tries(word);
				if(p.attempts()==true) {
					System.out.println("Sie haben alle Level erfolgreich abgeschlossen! Gratulation");
				}
			}
		}	
	}
	
	public static void fileReader() throws FileNotFoundException{
		Scanner r=new Scanner(new File("woerter.txt"));
		 int counter=0;
		while(r.hasNext()) {
			words[counter]=r.next();
			words[counter]=words[counter].replaceAll("ä", "ae");
			words[counter]=words[counter].replaceAll("ö", "oe");
			words[counter]=words[counter].replaceAll("ü", "ue");
			words[counter]=words[counter].replaceAll("ß", "ss");
			counter++;
		}
		r.close();
	}
	
	public static String searcher() {
		int a=0;
		int b=0;
		do {
			switch(level) {
			case 1: 
				if(words[a].length()<=5) {
					words_level[b]=words[a];
					b++;
				}
				a++; break;
			case 2:
				if(words[a].length()<=12&&words[a].length()>5) {
					words_level[b]=words[a];
					b++;
				}
				a++; break;
			case 3:
				if(words[a].length()>12) {
					words_level[b]=words[a];
					b++;
				}
				a++; break;
			}
		}while(a<words.length);
		int random=(int)(Math.random()*(b));
		return words_level[random];
	}
}
