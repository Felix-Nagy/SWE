package src.mocks;

import src.model.QuestionAnswerPair;

import java.util.ArrayList;

public class QuestionsAndAnswers {
    private static final ArrayList<QuestionAnswerPair> qa = new ArrayList<>();
    private int counter = 0;

    public QuestionsAndAnswers() {
        populateQAs();
    }

    private void populateQAs() {
        qa.add(new QuestionAnswerPair("frage1", "antwort1"));
        qa.add(new QuestionAnswerPair("frage2", "antwort2"));
        qa.add(new QuestionAnswerPair("frage3", "antwort3"));
        qa.add(new QuestionAnswerPair("frage4", "antwort4"));
        qa.add(new QuestionAnswerPair("frage5", "antwort5"));
        qa.add(new QuestionAnswerPair("frage6", "antwort6"));
        qa.add(new QuestionAnswerPair("frage7", "antwort7"));
        qa.add(new QuestionAnswerPair("frage8", "antwort8"));
        qa.add(new QuestionAnswerPair("frage9", "antwort9"));
        qa.add(new QuestionAnswerPair("frage10", "antwort10"));
        qa.add(new QuestionAnswerPair("frage11", "antwort11"));
        qa.add(new QuestionAnswerPair("frage12", "antwort12"));
        qa.add(new QuestionAnswerPair("frage13", "antwort13"));
        qa.add(new QuestionAnswerPair("frage14", "antwort14"));
    }

    public QuestionAnswerPair getNextQuestion() {
        return qa.get(counter++);
    }
}
