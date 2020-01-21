package src.application.name_eintragen;

import src.model.Player;

import java.util.ArrayList;

public class NameEintragenManager {
    private ArrayList<Player> players; // links to Fassade players

    public NameEintragenManager(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean validatePlayers(String... names) {
        int countValidNames = 0;
        for (String name : names) {
            if (name.length() > 0) {
                countValidNames++;
            }
        }

        return countValidNames >= 2;
    }

    public void addPlayers(String... names) {
        for (String name : names) {
            if (name.length() > 0) {
                this.players.add(new Player(name));
            }
        }
    }
}
