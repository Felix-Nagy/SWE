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
	
}
