package src.userInterface;

import src.application.IModel;
import src.application.IObserver;
import src.application.State;
import src.mocks.QuestionsAndAnswers;
import src.model.Player;

import javax.swing.*;
import java.util.Iterator;


public class ObserverGUI extends javax.swing.JFrame implements IObserver {

	private IModel model;
	private GroupLayout mainLayout;


	private static final long serialVersionUID = 1L;

	public ObserverGUI(IModel m) {
		this.initComponents();
		this.model = m;
		this.model.attach(this);
    }



	public void update(int state)
	{
		System.out.println("Update received from Subject, state changed to : " + state);
		if (state == State.State_INITAL) {
		    //drawMainMenu();
            drawSchnellquiz();
        }
		else if (state == State.State_NAME) {
           drawInputNamesMenu();
        }
		else if (state == State.State_ANSWER) {
		    drawSchnellquiz();
        }
	}


	private void drawInputNamesMenu() {
        this.getContentPane().removeAll();
        JButton send = new JButton ("weiter");
        JLabel label = new JLabel();
        label.setText("Name eintragen");
        JLabel sp1 = new JLabel("Spieler 1");
        JLabel sp2 = new JLabel("Spieler 2");
        JLabel sp3 = new JLabel("Spieler 3");
        JLabel sp4 = new JLabel("Spieler 4");
        JTextField textfield1= new JTextField();
        JTextField textfield2= new JTextField();
        JTextField textfield3= new JTextField();
        JTextField textfield4= new JTextField();

        this.getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup( this.mainLayout.createSequentialGroup().addGap(100).addComponent(label)
                .addGroup(this.mainLayout.createParallelGroup()
                        .addComponent(sp1).addGap(200)
                        .addComponent(textfield1).addGap(200)

                        .addComponent(sp2).addGap(200)
                        .addComponent(textfield2).addGap(200)

                        .addComponent(sp3).addGap(200)
                        .addComponent(textfield3).addGap(200)

                        .addComponent(sp4).addGap(200)
                        .addComponent(textfield4).addGap((200)))

                .addComponent(send).addGap(200)


        );
        this.mainLayout.setVerticalGroup(
                this.mainLayout.createSequentialGroup()

                        .addComponent(label).addGap(20)

                        .addComponent(sp1).addGap(10)
                        .addComponent(textfield1).addGap(10)

                        .addComponent(sp2).addGap(10)
                        .addComponent(textfield2).addGap(10)

                        .addComponent(sp3).addGap(10)
                        .addComponent(textfield3).addGap(10)

                        .addComponent(sp4).addGap(10)
                        .addComponent(textfield4).addGap(10)

                        .addComponent(send).addGap(20)



        );

        send.addActionListener(evt -> afterNamesInput(textfield1.getText(), textfield2.getText(),
                                                    textfield3.getText(), textfield4.getText()));
    }

    private void drawSchnellquiz(){
        this.getContentPane().removeAll();
        JButton sendButton = new JButton ("Antworten");
        JLabel questionLabel = new JLabel();
        JLabel playerNameLabel = new JLabel();
        JTextField questionAnswerInputField = new JTextField();
        JLabel timerLabel = new JLabel();
        JLabel fragenCount = new JLabel();

        Iterator<Player> playerIterator = this.model.getPlayers().iterator();

        getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup( this.mainLayout.createSequentialGroup().addComponent(questionLabel)
                .addGroup(this.mainLayout.createParallelGroup()
                        .addComponent(fragenCount).addGap(200)
                        .addComponent(timerLabel)
                        .addComponent(playerNameLabel).addGap(200)
                        .addComponent(questionLabel).addGap(200)
                        .addComponent(questionAnswerInputField).addGap(200)
                        .addComponent(sendButton).addGap(200))
        );
        this.mainLayout.setVerticalGroup(
                this.mainLayout.createSequentialGroup()
                        .addComponent(fragenCount)
                        .addComponent(timerLabel)
                        .addComponent(playerNameLabel).addGap(10)
                        .addComponent(questionLabel).addGap(20)
                        .addComponent(questionAnswerInputField).addGap(10)
                        .addComponent(sendButton).addGap(20)
        );

        sendButton.addActionListener(evt -> {
            nextQuestion(questionLabel, fragenCount, playerNameLabel, playerIterator);
        });

        Timer timer = new Timer(1000, evt -> {
            this.timeLeft--;
            if (this.timeLeft == 0) {
                nextQuestion(questionLabel, fragenCount, playerNameLabel, playerIterator);
            }
            timerLabel.setText(String.valueOf(this.timeLeft));
        });

        timer.start();
        nextQuestion(questionLabel, fragenCount, playerNameLabel, playerIterator);
        nextPlayer(playerIterator, playerNameLabel);
    }

    private void nextQuestion(JLabel questionLabel, JLabel fragenCount, JLabel playerNameLabel, Iterator<Player> playerIterator) {
        String question = this.questionsAndAnswers.getNextQuestion().getQuestion();
        questionLabel.setText(question);
        this.currQuestionIndex++;
        if (this.currQuestionIndex > 10) {
            nextPlayer(playerIterator, playerNameLabel);
        }
        fragenCount.setText(String.format("%d/%d", this.currQuestionIndex, MAX_QUESTIONS));
        this.timeLeft = TIME_BASE; // reset time
    }

    private void nextPlayer(Iterator<Player> playerIterator, JLabel playerNameLabel) {
	    if (playerIterator.hasNext()) {
	        this.currQuestionIndex = 1;
            this.activePlayer = playerIterator.next();
	        playerNameLabel.setText(this.activePlayer.getName());
        }
	    else {
	        // panic
            System.out.println("no further players");
        }
    }

    private void drawMainMenu() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Starten");
        jButton1.addActionListener(evt -> this.model.starten());
        jButton2.setText("Beenden");
        jButton2.addActionListener(evt -> System.exit(0x0));
        jLabel1.setText("Willkommen");


        getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup( this.mainLayout.createSequentialGroup().addGap(200)
                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1).addGap(200)
                        .addComponent(jButton1).addGap(200)
                        .addComponent(jButton2)).addGap(200)

        );
        this.mainLayout.setVerticalGroup(
                this.mainLayout.createSequentialGroup()
                        .addComponent(jLabel1).addGap(20)
                        .addComponent(jButton1).addGap(20)
                        .addComponent(jButton2).addGap(100)


        );
    }

    private void initComponents() {
        setTitle("Kreuzworträtsel");
        this.mainLayout = new GroupLayout(getContentPane());
        this.drawMainMenu();

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	    System.out.println("pressed");
        }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("pressed");
    }

    private void afterNamesInput(String name1, String name2, String name3, String name4) {
	    boolean success = this.model.nameEintragen(name1, name2, name3, name4);
        System.out.println(success);


        for (Player p : this.model.getPlayers()) {
            System.out.println(p.getName());
        }
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    private static final int TIME_BASE = 30;
    private int timeLeft = TIME_BASE;
    private int currQuestionIndex = 0;
    private QuestionsAndAnswers questionsAndAnswers = new QuestionsAndAnswers();
    private final static int MAX_QUESTIONS = 10;
    private Player activePlayer;
}

		


