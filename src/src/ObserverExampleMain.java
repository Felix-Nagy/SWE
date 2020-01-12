package src;
import src.application.Fassade;
import src.application.IModel;
import src.userInterface.ObserverGUI;


public class ObserverExampleMain

{ 
	public static void main(String[] args) {
		
		IModel model = new Fassade();
		new ObserverGUI(model).setVisible(true); 	//grafische oberflaeche
		//new ObserverConsole(model);					//Konsoleneingabe
	}
}



