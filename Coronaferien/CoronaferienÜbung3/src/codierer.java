import java.io.*;
import java.util.Scanner;

public class codierer extends NumToText_TextToNum{

	private int[] code;
	String message;
	
	public void menu() {
		Scanner r=new Scanner(System.in);
		
		System.out.print("Nachricht eingeben: ");
		message = r.nextLine();
		message=message.toLowerCase();
		int o=message.length();
		for (int j = 0; j <o; j++) {
			for(int i=0; i<message.length(); i++) {
				if(message.charAt(i)!='a'&&message.charAt(i)!='b'&&message.charAt(i)!='c'&&message.charAt(i)!='d'&&message.charAt(i)!='e'&&message.charAt(i)!='f'&&
						message.charAt(i)!='g'&&message.charAt(i)!='h'&&message.charAt(i)!='i'&&message.charAt(i)!='j') {
					message=delete(i);
				}
			}
		}
		//System.out.println(message);
		generateCode();
		
		PrintWriter w=null;
		try {
			System.out.print("Speicherort angeben: ");
			String ort=r.nextLine();
			w=new PrintWriter(new BufferedWriter(new FileWriter(ort)));
			w.println(numberToText(encrypte(textToNumber(message))));
		}catch(IOException ex){
			ex.printStackTrace();
		}finally {
			if(w!=null) {
				w.flush();
				w.close();
			}
		}
		System.out.println("Nachricht wurde verschlüsselt");
	}
	
	public String delete(int i) {
		String a ="";
		for(int q=0; q<message.length(); q++) {
			if(q!=i) {
				a=a+message.charAt(q);
			}
		}
		return a;
	}
	
	public void generateCode() {
		code=new int[4];
		System.out.print("Code: ");
		for(int i=0; i<code.length; i++) {
			code[i]=(int)(Math.random()*10);
			System.out.print(code[i]);
		}
		System.out.println();
	}
	
	public int[] encrypte(int[] message) {
		int[] test=new int[message.length];
		int a=0;
		for(int i=0; i<message.length; i++) {
			if(a==4)a=0;
			test[i]=message[i]+code[a];
			if(test[i]>9) test[i]=test[i]-10;
			a++;
		}
		return test;
	}
}
