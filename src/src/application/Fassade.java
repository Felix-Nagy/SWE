package src.application;


import src.application.name_eintragen.NameEintragenManager;
import src.application.schnellquiz.SchnellQuizManager;
import src.model.Player;

import java.util.ArrayList;

public class Fassade extends Subject implements IModel  {
	
	private NameEintragenManager lnkNameEintragenManager;
	private SchnellQuizManager lnkSchnellQuizManager;
	State currentState;
	private ArrayList<Player> players = new ArrayList<>();
	
	
	public Fassade(){
		this.lnkNameEintragenManager = new NameEintragenManager(this.players);
		this.lnkSchnellQuizManager = new SchnellQuizManager(this.players);
		this.currentState = new State();
	}


	@Override
	public void starten() {
		this.currentState.setState(State.State_NAME);
		notifyObservers(State.State_NAME);
	}

	@Override
	public boolean nameEintragen(String... names) {
		if (this.lnkNameEintragenManager.validatePlayers(names)) {
			this.lnkNameEintragenManager.addPlayers(names);
			this.currentState.setState(State.State_ANSWER);
			notifyObservers(State.State_ANSWER);
			return true;
		}
		else {
			return false;
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

	@Override
	public String getNextQuestion() {
		return this.lnkSchnellQuizManager.getNextQuestion();
	}

	@Override
	public void setPlayersOrder() {
		this.lnkSchnellQuizManager.setPlayersOrderByPoints();
	}

	@Override
	public void validateAnswer(String answer) {
		this.lnkSchnellQuizManager.validateAnswer(answer);
	}

	@Override
	public Player getActivePlayer() {
		return this.lnkSchnellQuizManager.getActivePlayer();
	}

	@Override
	public void setActivePlayer(Player player) {
		this.lnkSchnellQuizManager.setActivePlayer(player);
	}

	@Override
	public int getCurrentQuestionIndex() {
		return this.lnkSchnellQuizManager.getCurrQuestionIndex();
	}

	@Override
	public int getMaxQuestion() {
		return SchnellQuizManager.MAX_QUESTIONS;
	}

	@Override
	public boolean hasNextPlayer() {
		return this.lnkSchnellQuizManager.hasNextPlayer();
	}

	@Override
	public void nextPlayer() {
		this.lnkSchnellQuizManager.nextPlayer();
	}

	@Override
	public boolean isItNextPlayersTurn() {
		return this.lnkSchnellQuizManager.isItNextPlayersTurn();
	}
}
