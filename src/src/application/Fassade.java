package src.application;


public class Fassade extends Subject implements IModel  {
	
	private UseCaseController1 lnkUseCaseController1;
	private UseCaseController2 lnkUseCaseController2;
	State currentState;
	
	
	public Fassade(){
		this.lnkUseCaseController1 = new UseCaseController1();
		this.lnkUseCaseController2 = new UseCaseController2();
		this.currentState = new State();
	}


	@Override
	public void starten() {

	}

	@Override
	public void nameEintragen() {

	}

	@Override
	public void antworten() {

	}

	@Override
	public void antworten(String antwort){

		}


	@Override
	public void kategorieWaehlen() {

	}
}
