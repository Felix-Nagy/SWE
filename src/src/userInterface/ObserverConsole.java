package src.userInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import src.application.IModel;
import src.application.IObserver;
import src.application.State;

public class ObserverConsole implements IObserver { 

	private IModel model;
//	private Fassade ct;
	

	public ObserverConsole(IModel m) {
		this.model = m;
		this.model.attach(this);
//		this.ct = new Fassade(m, this);
		initComponents();
	}
;

	private void initComponents() {
		System.out.println("aktueller Zustand: Wuerfeln" );
        getInput();
	}


	private void getInput() {
		/*
		String str = new String();
		while (true) {		
    	System.out.println("bitte Aktion eingeben ( wuerfeln -w , ziehen - z, fertig - f):");
		try{
    		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    		str = in.readLine();
		}
			catch(IOException e){}
		if (str.equals("w")) { model.wuerfeln();}
		if (str.equals("z")) { model.ziehen();}
		if (str.equals("f")) { model.fertig();}
		}*/
	}
	
	
	public void update(int state) 
	{  

	
	}


}
