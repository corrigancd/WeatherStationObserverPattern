
public class driver {

	Obsersver ob = new Customer();
	Subject ca = new car();
	
	ca.registerObserver(ob);
	ca.notifyObserver();
	ca.setNewValue(100);
	
}
