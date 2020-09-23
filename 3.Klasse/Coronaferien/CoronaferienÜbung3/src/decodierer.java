import java.util.Scanner;
import java.io.*;

public class decodierer extends NumToText_TextToNum{
	
	private int counter=0;
	private String message="";
	
	public void menu()  throws IOException {
		Scanner r=new Scanner(System.in);
		System.out.print("Datei angeben: ");
		String fileName = r.next();
		r.close();
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		message=br.readLine();
		br.close();
		System.out.println("Message: "+message);
		counter=0;
		long timeStart=System.currentTimeMillis();
		int[] messageNum=textToNumber(message);
		codeFinder(messageNum);
		long timeEnd=System.currentTimeMillis();
		System.out.println("Es wurden  "+ counter+ " passende Verschlüsselungen gefunden");
		System.out.println("Die Endschlüsselung dauerte: "+ (timeEnd-timeStart));
		
	}

	public void realMessage(int[] a, int[] b) {
		counter++;
		System.out.print("Code ");
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.print(" --> ");
		System.out.println(numberToText(b));
	}
	
	public void codeFinder(int[] nachv) {
		int a=0;
		int c3=0;
		int c2=0;
		int c1=0;
		int c0=0;
		int count=0;
		int[] code= {0, 0, 0, 0};
		int[] test=new int[nachv.length];
		int[] codeTrue=new int[4];
		for (int j = 0; j <= 9999; j++) {
			code[3]=c3;
			code[2]=c2;
			code[1]=c1;
			code[0]=c0;
			a=0;
			for (int i = 0; i < nachv.length; i++) {
				if (a==4) a = 0;
				test[i] = nachv[i] - code[a];
				if(test[i]<0)test[i]=test[i]+26;
				a++;
			} 
			for(int i=0; i<test.length; i++) {
				if (i<test.length-2) {
					if ((test[i] == 0) && (test[i + 1] == 0) && (test[i + 2] == 0)) {
						count++;
					}	
				}
				if(count==1) {
					for(int q=0; q<code.length; q++) {
						codeTrue[q]=code[q];
					}
					realMessage(codeTrue, test);
					count=0;
				}
			}
			c3++;
			if(c3==10) {
				c3=0; 
				c2++;
			}
			if(c2==10) {
				c2=0;
				c1++;
			}
			if(c1==10) {
				c1=0;
				c0++; 
			}
			if(c0==10) {
				c0=0;
			}
		}
	}
}
