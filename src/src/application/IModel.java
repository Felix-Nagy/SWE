package src.application;

import src.model.Player;

import java.util.ArrayList;

public interface IModel extends ISubject{
	



	public void starten();

	public boolean nameEintragen(String... names);

	public void antworten();

	public void antworten(String antwort);

	public void toCategoryChooser();

	public void toShowCrossword();

	public void kategorieWaehlen();

	public ArrayList<Player> getPlayers();

	public String getNextQuestion();

	public void setPlayersOrder();

	public void validateAnswer(String answer);

	public Player getActivePlayer();

	public void setActivePlayer(Player player);

	public int getCurrentQuestionIndex();

	public int getMaxQuestion();

	public boolean hasNextPlayer();

	public void nextPlayer();

	public boolean isItNextPlayersTurn();
	
}
