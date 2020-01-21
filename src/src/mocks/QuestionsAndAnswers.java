package src.mocks;

import crossword.QuestionAnswerPair;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionsAndAnswers {
    public static final ArrayList<QuestionAnswerPair> qa = new ArrayList<>();
    private int counter = 0;

    public QuestionsAndAnswers() {
        populateQAs();
        Collections.shuffle(qa);
    }

    private void populateQAs() {
        qa.add(new QuestionAnswerPair("Wofür steht das E In Email", "electronic"));
        qa.add(new QuestionAnswerPair("In welchem Staat wurde JFK ermordet", "Texas"));
        qa.add(new QuestionAnswerPair("Wieviele Bundesländer hat Deutschland", "16"));
        qa.add(new QuestionAnswerPair("Wie heißt das Instrument, das ein Arzt zum Abhören benutzt", "Stethoskop"));
        qa.add(new QuestionAnswerPair("Wie sind Neutronen geladen", "neutral"));

        qa.add(new QuestionAnswerPair("Wie heißt in der Staatstheorie die dritte Staatsgewalt neben der Legislative und der Exekutive", "Judikative"));
        qa.add(new QuestionAnswerPair("Mit welcher Währung wird in Ungarn bezahlt", "Forint"));
        qa.add(new QuestionAnswerPair("In welchem Bundesland liegt das Erzgebirge", "Sachsen"));
        qa.add(new QuestionAnswerPair("Was wurde in Schillers Form gegossen, die \"aus Lehm gebrannt\" war", "Glocke"));
        qa.add(new QuestionAnswerPair("Die Telenor Arena, wo Lena den ESC 2010 gewann, befindet sich in ...", "Oslo"));

        qa.add(new QuestionAnswerPair("Wie nannte man den amerikanischen Wiederaufbauplan für das vom Zweiten Weltkrieg zerstörte Westeuropa", "Marshallplan"));
        qa.add(new QuestionAnswerPair("Wie heißt der Gehilfe von Robinson Crusoe", "Freitag"));
        qa.add(new QuestionAnswerPair("Wie heißt die Stilrichtung der impressionistischen Malerei, deren Hauptvertreter Georges Seurat und Paul Signac waren", "Pointillismus"));
        qa.add(new QuestionAnswerPair("Welcher Fluss mündet am Dollart in die Nordsee", "Ems"));
        qa.add(new QuestionAnswerPair("Aus was wird Tofu hergestellt", "Soja"));

        qa.add(new QuestionAnswerPair("In welcher deutschen Stadt gründete Red-Bull-Chef Dietrich Mateschitz 2009 einen Fußballverein", "Leipzig"));
        qa.add(new QuestionAnswerPair("Welches Fußballteam stürzte 1993 mit dem Flugzeug bei Libreville ab", "Sambia"));
        qa.add(new QuestionAnswerPair("Aus welchem Material waren die ersten Tischtennis-Bälle", "Kork"));
        qa.add(new QuestionAnswerPair("Welches Volk regierte Dschingis Khan", "Mongolen"));
        qa.add(new QuestionAnswerPair("Welcher Schädling soll mit der Bt-Maissorte MON810, die auch in Europa zugelassen ist, bekämpft werden", "Maiszünsler"));

        qa.add(new QuestionAnswerPair("Barbra Streisand sang \"Woman in ...\"", "love"));
        qa.add(new QuestionAnswerPair("Was ist bei einer Myasthenie geschwächt", "Muskeln"));
        qa.add(new QuestionAnswerPair("Wie hieß ein bekanntes, männliches Mitglied der Terrorgruppe RAF", "Andread Baader"));
        qa.add(new QuestionAnswerPair("Welcher Begriff bezeichnet eine kürzere Erzählung in Prosaform", "Novelle"));
        qa.add(new QuestionAnswerPair("Was zeigt Monate, Wochen und Tage an", "Kalender"));

        qa.add(new QuestionAnswerPair("Fische haben keine Haare, aber ...", "Schuppen"));
        qa.add(new QuestionAnswerPair("Wie nannte man 1773 den Streit um die Besteuerung von Tee", "Boston Tea Party"));
        qa.add(new QuestionAnswerPair("Was ist die Einheit der Leistung", "Watt"));
        qa.add(new QuestionAnswerPair("Wie heißt das wichtigste Steroid im menschlichen Körper", "Cholesterin"));
        qa.add(new QuestionAnswerPair("Gegen wen gewann Deutschland zuletzt 7:1", "Brasilien"));

        qa.add(new QuestionAnswerPair("Das Gewicht von Edelsteinen misst man in ...", "Karat"));
        qa.add(new QuestionAnswerPair("Auf welchem Fernsehsender läuft: \"Deutschland sucht den Superstar\"", "RTL"));
        qa.add(new QuestionAnswerPair("Wie heißen die berühmten Täfelchen aus Bitterschokolade mit Pfefferminzgeschmack", "After Eight"));
        qa.add(new QuestionAnswerPair("Wie heißt das Gebirge an der Grenze von Europa nach Asien", "Ural"));
        qa.add(new QuestionAnswerPair("Wie heißen die elektrisch positiv geladenen Teilchen eines Atomkerns", "Protonen"));

        qa.add(new QuestionAnswerPair("Die Reinheit von Gold misst man in ...", "Karat"));
        qa.add(new QuestionAnswerPair("Wie hieß das Pferd des Herzogs von Wellington", "Copenhagen"));
        qa.add(new QuestionAnswerPair("Das Gedicht \"Die Glocke\" ist von ...", "Goethe"));
        qa.add(new QuestionAnswerPair("Woraus bestehen Kartoffeln in der Hauptsache", "Stärke"));
        qa.add(new QuestionAnswerPair("Wie heißt das Bier in Köln", "Kölsch"));

    }

    public QuestionAnswerPair getNextQuestion() {
        return qa.get(counter++);
    }
}
