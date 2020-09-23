
public class Bugatti extends Auto{

	Bugatti(int a) {
		super(a);
	}
	
	void fahren() {
		System.out.println("Ich fahre "+ super.getGeschwindigkeit()+ "km/h mit meinem Bugatti!");
	}
}
