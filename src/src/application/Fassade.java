package src.application;


import src.model.Player;

import java.util.ArrayList;

public class Fassade extends Subject implements IModel  {
	
	private UseCaseController1 lnkUseCaseController1;
	private UseCaseController2 lnkUseCaseController2;
	State currentState;
	private ArrayList<Player> players = new ArrayList<>();
	
	
	public Fassade(){
		this.lnkUseCaseController1 = new UseCaseController1();
		this.lnkUseCaseController2 = new UseCaseController2();
		this.currentState = new State();
	}


	@Override
	public void starten() {
		this.currentState.setState(State.State_NAME);
		notifyObservers(State.State_NAME);
	}

	@Override
	public boolean nameEintragen(String... names) {
		boolean success;
		int countValidNames = 0;
		for (String name : names) {
			if (name.length() > 0) {
				countValidNames++;
			}
		}
		if (countValidNames >= 2) {
			addPlayers(names);
			success = true;
			this.currentState.setState(State.State_ANSWER);
			notifyObservers(State.State_ANSWER);
		}
		else {
			success = false;
		}

		return success;
	}

	private void addPlayers(String... names) {
		for (String name : names) {
			if (name.length() > 0) {
				this.players.add(new Player(name));
			}
		}
	}

	@Override
	public void antworten() {
		this.currentState.setState(State.State_PLAYER_ORDER);
		notifyObservers(State.State_PLAYER_ORDER);
	}

	@Override
	public void antworten(String antwort){

		}

	@Override
	public void toCategoryChooser() {
		this.currentState.setState(State.State_CHOOSE_CATEGORY);
		notifyObservers(State.State_CHOOSE_CATEGORY);
	}
	public void toShowCrossword(){
		this.currentState.setState(State.State_SHOW_CROSSWORD);
		notifyObservers(State.State_SHOW_CROSSWORD);
	}

	@Override
	public void kategorieWaehlen() {

	}

	@Override
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
