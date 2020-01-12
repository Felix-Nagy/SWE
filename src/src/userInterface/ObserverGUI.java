package src.userInterface;
import src.application.IModel;
import src.application.IObserver;
import src.application.State;

import javax.swing.*;
import java.awt.*;


public class ObserverGUI extends javax.swing.JFrame implements IObserver {
	
	private IModel model;
	

	private static final long serialVersionUID = 1L;
	
	public ObserverGUI(IModel m) {
		this.initComponents();
		this.model = m;
		this.model.attach(this);
	}



	public void update(int state) 
	{  
		System.out.println("Update received from Subject, state changed to : " + state);

	
	}

	
	
	
	
    private void initComponents() {
        setTitle("Kreuzworträtsel");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup( layout.createSequentialGroup().addGap(100).addComponent(label)
                .addGroup(layout.createParallelGroup()
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
        layout.setVerticalGroup(
                layout.createSequentialGroup()

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

       /** jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Starten");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton2.setText("Beenden");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLabel1.setText("Willkommen");



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup( layout.createSequentialGroup().addGap(200)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1).addGap(200)
                        .addComponent(jButton1).addGap(200)
                        .addComponent(jButton2)).addGap(200)

        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel1).addGap(20)
                        .addComponent(jButton1).addGap(20)
                        .addComponent(jButton2).addGap(100)


        );
        */

        pack();

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	    System.out.println("pressed");
        }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("pressed");
    }

	
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

}

		


