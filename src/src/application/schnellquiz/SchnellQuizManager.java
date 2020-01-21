package src.application.schnellquiz;

import crossword.QuestionAnswerPair;
import src.mocks.QuestionsAndAnswers;
import src.model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class SchnellQuizManager {
    private int currQuestionIndex = 0;
    private QuestionsAndAnswers questionsAndAnswers = new QuestionsAndAnswers();
    public final static int MAX_QUESTIONS = 10;
    private Player activePlayer;
    private QuestionAnswerPair currQuestion;

    private ArrayList<Player> players; // links to Fassade players

    private Iterator<Player> playerIterator;

    public SchnellQuizManager(ArrayList<Player> players) {
        this.players = players;
    }

    public String getNextQuestion() {
        this.currQuestion = this.questionsAndAnswers.getNextQuestion();
        this.currQuestionIndex++;

        return this.currQuestion.getQuestion();
    }

    public boolean isItNextPlayersTurn() {
        return this.currQuestionIndex >= MAX_QUESTIONS;
    }

    public void nextPlayer() {
        if (playerIterator == null) {
            this.playerIterator = this.players.iterator();
        }

        if (this.playerIterator.hasNext()) {
            this.currQuestionIndex = 0;
            this.activePlayer = this.playerIterator.next();
        }
    }

    public void validateAnswer(String answer) {
        if (this.currQuestion.getAnswer().equalsIgnoreCase(answer)) {
            System.out.println("ok");
            this.activePlayer.addPoints(1);
        }
        else {
            System.out.println("omg you suck");
        }
    }

    public int getCurrQuestionIndex() {
        return this.currQuestionIndex;
    }

    // more questions answered -> lower index -> start faster
    public void setPlayersOrderByPoints() {
        this.players.sort(Comparator.comparingInt(Player::getPoints).reversed());
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public boolean hasNextPlayer() {
        if (playerIterator == null) {
            this.playerIterator = this.players.iterator();
        }

        return this.playerIterator.hasNext();
    }
}
