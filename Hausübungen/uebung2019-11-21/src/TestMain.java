import java.io.*;

public class TestMain2 {


	public static void main(String[] args) throws IOException{
		
		NumberFormatException();
		ArrayIndexOutOfBoundsException();
		FileNotFoundException();
		
	}
	public static void NumberFormatException() throws IOException{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		System.out.println("Wie alt bist du: ");
		String eingabe=br.readLine();
		
		try {
			int alter=Integer.parseInt(eingabe);
			if(alter<0) throw new NumberFormatException("negative Zahl");
			if(alter>=18) {
				System.out.println("Hier ist der bestellte Wodka.");
			}else {
				System.out.println("Kein Alkohol für dich!");
			}
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			System.out.println("Das ist keine ganze Zahl!");
		}
	}
	
	public static void ArrayIndexOutOfBoundsException() throws IOException{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		System.out.println("Anzahl der Array-Elemente angeben: ");
		String s=br.readLine();
		try {
			int index=Integer.parseInt(s);
			if(index<0) throw new NegativeArraySizeException("Kein negativer Index erlaubt!");
			int[] zahl=new int[index];
			if(index>=0){
				for(int i=0; i<=zahl.length; i++) {
					zahl[i]=i+1;
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException aioobe) {
			aioobe.printStackTrace();
			System.out.print("Array-Index nicht verfügbar: ");
			System.out.println(aioobe.getMessage());
		}
		catch(NegativeArraySizeException nase) {
			nase.printStackTrace();
			System.out.println(nase.getMessage());
		}
	}
	
	public static void FileNotFoundException() throws IOException{
		try {
	        FileReader fr=new FileReader("text.txt");
	    } 
		catch (FileNotFoundException fnfe) {
	        fnfe.printStackTrace();
	        System.out.println("Datei kann vom System nicht gefunden werden!");
	    }
	}
}

