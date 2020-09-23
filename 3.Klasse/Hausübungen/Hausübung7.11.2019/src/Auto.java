
public abstract class Auto {

	private int geschwindigkeit;
	
	Auto(int a){
		this.geschwindigkeit=a;
	}
	
	int getGeschwindigkeit() {
		return geschwindigkeit;
	}
	
	abstract void fahren();
}
