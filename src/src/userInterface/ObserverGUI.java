package src.userInterface;

import crossword.CrossWordPuzzle;
import src.application.IModel;
import src.application.IObserver;
import src.application.State;
import src.mocks.QuestionsAndAnswers;
import src.model.Category;
import src.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.Iterator;


public class ObserverGUI extends javax.swing.JFrame implements IObserver {

	private IModel model;
	private GroupLayout mainLayout;


	private static final long serialVersionUID = 1L;

	public ObserverGUI(IModel m) {
		this.initComponents();
		this.model = m;
		this.model.attach(this);
		setSize(1100, 600);
    }



	public void update(int state)
	{
		System.out.println("Update received from Subject, state changed to : " + state);
		if (state == State.State_INITAL) {
		    drawMainMenu();
        }
		else if (state == State.State_NAME) {
           drawInputNamesMenu();
        }
		else if (state == State.State_ANSWER) {
		    drawSchnellquiz();
        }
		else if (state == State.State_PLAYER_ORDER) {
		    drawDashboard();
        }
		else if (state == State.State_CHOOSE_CATEGORY){
		    drawChooseCategory();
        }
        else if (state == State.State_SHOW_CROSSWORD){
            drawShowCrossword();
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

        getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup(this.mainLayout.createSequentialGroup()
                .addComponent(fragenCount).addGap(50)
                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(playerNameLabel).addGap(60).addComponent(questionLabel)
                        .addGroup(this.mainLayout.createSequentialGroup()
                                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(questionAnswerInputField)
                                )
                                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(questionLabel))))
                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(timerLabel)
                        .addComponent(sendButton))
        );


        this.mainLayout.setVerticalGroup(this.mainLayout.createSequentialGroup()
                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fragenCount)
                        .addComponent(playerNameLabel)
                        .addComponent(timerLabel))
                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(this.mainLayout.createSequentialGroup()
                                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(questionLabel))
                                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(sendButton)
                                        .addComponent(questionAnswerInputField))).addGap(200)
                        .addComponent(sendButton))
        );

        sendButton.addActionListener(evt -> {
            checkAnswer(questionAnswerInputField.getText());
            nextQuestion(questionLabel, fragenCount, playerNameLabel, questionAnswerInputField);
        });

        this.timer = new Timer(1000, evt -> {
            this.timeLeft--;
            if (this.timeLeft == 0) {
                String nextQuestion = this.model.getNextQuestion();
                questionLabel.setText(nextQuestion);
                nextPlayer(playerNameLabel, fragenCount);
            }
            timerLabel.setText(String.valueOf(this.timeLeft));
        });

        this.timer.start();
        nextPlayer(playerNameLabel, fragenCount);
        nextQuestion(questionLabel, fragenCount, playerNameLabel, questionAnswerInputField);
    }

    private void nextQuestion(JLabel questionLabel, JLabel fragenCount, JLabel playerNameLabel,
                              JTextField questionAnswerInputField) {
        if (this.model.isItNextPlayersTurn()) {
            nextPlayer(playerNameLabel, fragenCount);
        }
        String nextQuestion = this.model.getNextQuestion();
        questionLabel.setText(nextQuestion);
        questionAnswerInputField.setText("");
        fragenCount.setText(String.format("%d/%d", this.model.getCurrentQuestionIndex(), this.model.getMaxQuestion()));
        playerNameLabel.setText(this.model.getActivePlayer().getName());
    }

    private void nextPlayer(JLabel playerNameLabel, JLabel fragenCount) {
        System.out.println("next player");
        System.out.println(this.model.hasNextPlayer());
        if (this.model.hasNextPlayer()) {
	        this.model.nextPlayer();
            fragenCount.setText(String.format("%d/%d", this.model.getCurrentQuestionIndex(), this.model.getMaxQuestion()));
	        playerNameLabel.setText(this.model.getActivePlayer().getName());
            this.timeLeft = TIME_BASE;
        }
	    else {
	        this.timer.stop();
	        endSchnellQuiz();
        }
    }

    private void endSchnellQuiz() {
        System.out.println("no further players");
        setPlayersOrderByPoints();
        this.model.antworten(); //state change
    }

    private void checkAnswer(String answer) {
	    this.model.validateAnswer(answer);
    }

    // more questions answered -> lower index -> start faster
    private void setPlayersOrderByPoints() {
        this.model.getPlayers().sort(Comparator.comparingInt(Player::getPoints).reversed());
    }



    private void drawDashboard(){
        this.getContentPane().removeAll();
        JLabel playerOrderLabel = new JLabel();
        JButton weiter = new JButton("weiter");


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup(

                this.mainLayout.createSequentialGroup().addGap(200)
                        .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(playerOrderLabel)
                                .addComponent(weiter)

        ));
        this.mainLayout.setVerticalGroup(
                this.mainLayout.createSequentialGroup().addGap(100)
                        .addComponent(playerOrderLabel)
                        .addGap(100).addComponent(weiter)



        );
        StringBuilder orderText = new StringBuilder();
        int order = 1;
        for (Player player : this.model.getPlayers()) {
            orderText.append(String.format("%d.: %s mit %d Punkten\n", order, player.getName(), player.getPoints()));
            order++;
        }

        playerOrderLabel.setText(orderText.toString());
        weiter.addActionListener(evt -> this.model.toCategoryChooser());

    }


    private void drawChooseCategory(){
        this.getContentPane().removeAll();
	    JButton cat1Button = new JButton("Geschichte");
        JButton cat2Button = new JButton("Wissenschaft");
        JButton cat3Button = new JButton("Erdkunde");
        JButton cat4Button = new JButton("Sport");

        Iterator<Player> playerIterator = this.model.getPlayers().iterator();
        Player firstPlayer = playerIterator.next();
        this.activePlayerChooseCategory = firstPlayer;
        JLabel  playerNameLabel = new JLabel(firstPlayer.getName());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup(

                this.mainLayout.createSequentialGroup().addGap(200)
                        .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(playerNameLabel)
                                .addComponent(cat1Button).addGap(200)
                                .addComponent(cat2Button).addGap(200)
                                .addComponent(cat3Button).addGap(200)
                                .addComponent(cat4Button)).addGap(200)


        );
        this.mainLayout.setVerticalGroup(
                this.mainLayout.createSequentialGroup().addGap(100)
                        .addComponent(playerNameLabel)
                        .addComponent(cat1Button)
                        .addGap(20)
                .addComponent(cat2Button).addGap(20)
                .addComponent(cat3Button).addGap(20)
                .addComponent(cat4Button).addGap(10)



        );
        cat1Button.addActionListener(evt -> chooseCategory(Category.HISTORY, playerIterator, cat1Button, playerNameLabel));
        cat2Button.addActionListener(evt -> chooseCategory(Category.SCIENCE, playerIterator, cat2Button, playerNameLabel));
        cat3Button.addActionListener(evt -> chooseCategory(Category.GEOGRAPHY, playerIterator, cat3Button, playerNameLabel));
        cat4Button.addActionListener(evt -> chooseCategory(Category.SPORT, playerIterator, cat4Button, playerNameLabel));

    }

    private void chooseCategory(Category category, Iterator<Player> playerIterator, JButton button, JLabel playerNameLabel) {
        System.out.printf("%s chose category %s\n", this.activePlayerChooseCategory.getName(), category);
        this.activePlayerChooseCategory.setCategory(category);
        button.setEnabled(false);
        if (playerIterator.hasNext()) {
            this.activePlayerChooseCategory = playerIterator.next();
            playerNameLabel.setText(this.activePlayerChooseCategory.getName());

        }
        else {
            System.out.println("no further categories to be chosen");
            this.model.toShowCrossword();

        }
    }


    private void drawMainMenu() {
        JButton startButton = new JButton();
        JButton exitButton = new JButton();
        JLabel welcomeLabel = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startButton.setText("Starten");
        startButton.addActionListener(evt -> this.model.starten());
        exitButton.setText("Beenden");
        exitButton.addActionListener(evt -> System.exit(0x0));
        welcomeLabel.setText("Willkommen");


        getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup(

                this.mainLayout.createSequentialGroup().addGap(200)
                .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(welcomeLabel).addGap(200)
                        .addComponent(startButton).addGap(200)
                        .addComponent(exitButton)).addGap(200)

        );
        this.mainLayout.setVerticalGroup(
                this.mainLayout.createSequentialGroup()
                        .addComponent(welcomeLabel).addGap(20)
                        .addComponent(startButton).addGap(20)
                        .addComponent(exitButton).addGap(100)


        );
    }

    private void drawShowCrossword() {
        this.getContentPane().removeAll();
        JTextArea jLabel1 = new JTextArea(this.a.questionBoardToString());
        getContentPane().setLayout(this.mainLayout);
        this.mainLayout.setAutoCreateGaps(true);
        this.mainLayout.setAutoCreateContainerGaps(true);
        this.mainLayout.setHorizontalGroup(

                this.mainLayout.createSequentialGroup().addGap(200)
                        .addGroup(this.mainLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel1).addGap(200))


        );
        this.mainLayout.setVerticalGroup(
                this.mainLayout.createSequentialGroup()
                        .addComponent(jLabel1).addGap(20)


        );
    }

    private void initComponents() {
        setTitle("Kreuzworträtsel");
        this.mainLayout = new GroupLayout(getContentPane());
        getContentPane().setMinimumSize(new Dimension(800, 800));
        this.drawMainMenu();
        a.printBoard(a.getBoard());
        System.out.println();
        a.printQuestionBoard();
        //this.drawShowCrossword();
        pack();
    }

    private void afterNamesInput(String name1, String name2, String name3, String name4) {
	    boolean success = this.model.nameEintragen(name1, name2, name3, name4); // state change
        System.out.println(success);


        for (Player p : this.model.getPlayers()) {
            System.out.println(p.getName());
        }
    }

    private static final int TIME_BASE = 30;
    private int timeLeft = TIME_BASE;

    private CrossWordPuzzle a = new CrossWordPuzzle(QuestionsAndAnswers.qa.toArray(new crossword.QuestionAnswerPair[0]));

    private Timer timer;

    private Player activePlayerChooseCategory;

}

		


