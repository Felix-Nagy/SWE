package src.application;



import java.util.ArrayList;
import java.util.Iterator;
/**
 * Knows its observers. Any number of Observer objects
 * may observe a subject. Provides an interface for attaching
 * and detaching Observer objects.
 * @role __Subject
 */

public class Subject implements ISubject {

	private ArrayList<IObserver> observers = new ArrayList<IObserver>();

	public void attach(IObserver observer) {
		observers.add(observer);
	}

	public void detach(IObserver observer) {
		observers.remove(observer);
	}

	public void notifyObservers(int state) {
		Iterator it = observers.iterator();
		while (it.hasNext()) {
			((IObserver) it.next()).update(state);
		}
	}
}