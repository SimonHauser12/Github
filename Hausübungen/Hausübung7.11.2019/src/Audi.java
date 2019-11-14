
public class Audi extends Auto{
	
	Audi(int a) {
		super(a);
	}
	
	void fahren() {
		System.out.println("Ich fahre "+ super.getGeschwindigkeit()+ "km/h mit meinem Audi!");
	}
}
