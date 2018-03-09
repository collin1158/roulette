package com.fusha.roulette;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.fusha.main.Main;

public class RoulettePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -1068499785087756289L;
	
	public ArrayList<Piece> pieces = new ArrayList<Piece>();
	
	private Timer actionTimer, rollerTimer;
	private Random rand = new Random();
	
	private JButton start, stop;
	private JTextField displayField;
	
	private int yValue = 70;
	private int actionCount = 0;
	private int rollerCount = 0;
	private int waitSeconds = 5;
	
	public RoulettePanel() {
		setBackground(Color.LIGHT_GRAY);
		
		start = new JButton("Start");
		start.addActionListener(this);
		add(start);
		
		stop = new JButton("Stop");
		stop.addActionListener(this);
		add(stop);
		
		displayField = new JTextField(20);
		displayField.setEditable(false);
		add(displayField);
		
		addPieces();
		
		rollerTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayField.setText("Rolling in " + (waitSeconds-rollerCount) + "...");
				if(rollerCount==waitSeconds) {
					displayField.setText("Rolling...");
					rollerTimer.stop();
					playRound();
				}
				rollerCount++;
			}
		});
		
		actionTimer = new Timer(2, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionCount++;
				int velocity = 1;
				if(actionCount < 400) {
					velocity = 3;
				} else if(actionCount < 1600) {
					velocity = 2;
				}
				for(int i=0; i<pieces.size(); i++) {
					pieces.get(i).move(-1*velocity);
				}
				if(actionCount == 1900) {
					actionTimer.stop();
					determineWinner();
				}
			}
			
		});
		
		//rollerTimer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color temp = g.getColor();
		//g.setColor(Color.BLACK);
		//g.fillRect(0, yValue, getWidth(), 30);
		
		for(int i=0; i<pieces.size(); i++) {
			pieces.get(i).fill(g);
		}
		
		g.setColor(Color.WHITE);
		//g.fillRect(0, yValue, 70, 30);
		//g.fillRect(getWidth()-70, yValue, 70, 30);
		g.fillRect(getWidth()/2, yValue-10, 2, 50);
		g.setColor(temp);
		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(start)) {
			rollerCount=0;
			rollerTimer.start();
		} else if(source.equals(stop)) {
			actionTimer.stop();
			determineWinner();
		}
	}
	
	private void playRound() {
		actionCount=0;
		actionCount -= rand.nextInt(601);
		actionTimer.start();
	}
	
	private void determineWinner() {
		for(int i=0; i<pieces.size(); i++) {
			if(pieces.get(i).containsPoint(getWidth()/2)) {
				displayField.setText(pieces.get(i).toString());
				break;
			}
		}
	}
	
	private void addPieces() {
		int width = Piece.width;
		System.out.println(width);
		System.out.println(Main.GUI.getWidth()/15);
		pieces.add(new Piece(0, yValue, Color.GREEN));
		pieces.add(new Piece(width, yValue, Color.BLACK));
		pieces.add(new Piece(width*2, yValue, Color.RED));
		pieces.add(new Piece(width*3, yValue, Color.BLACK));
		pieces.add(new Piece(width*4, yValue, Color.RED));
		pieces.add(new Piece(width*5, yValue, Color.BLACK));
		pieces.add(new Piece(width*6, yValue, Color.RED));
		pieces.add(new Piece(width*7, yValue, Color.BLACK));
		pieces.add(new Piece(width*8, yValue, Color.RED));
		pieces.add(new Piece(width*9, yValue, Color.BLACK));
		pieces.add(new Piece(width*10, yValue, Color.RED));
		pieces.add(new Piece(width*11, yValue, Color.BLACK));
		pieces.add(new Piece(width*12, yValue, Color.RED));
		pieces.add(new Piece(width*13, yValue, Color.BLACK));
		pieces.add(new Piece(width*14, yValue, Color.RED));
		
		/*pieces.add(new Piece(0, yValue+10, Color.GREEN));
		pieces.add(new Piece(width, yValue-10, Color.BLACK));
		pieces.add(new Piece(width*2, yValue+10, Color.RED));
		pieces.add(new Piece(width*3, yValue-10, Color.BLACK));
		pieces.add(new Piece(width*4, yValue+10, Color.RED));
		pieces.add(new Piece(width*5, yValue-10, Color.BLACK));
		pieces.add(new Piece(width*6, yValue+10, Color.RED));
		pieces.add(new Piece(width*7, yValue-10, Color.BLACK));
		pieces.add(new Piece(width*8, yValue+10, Color.RED));
		pieces.add(new Piece(width*9, yValue-10, Color.BLACK));
		pieces.add(new Piece(width*10, yValue+10, Color.RED));
		pieces.add(new Piece(width*11, yValue-10, Color.BLACK));
		pieces.add(new Piece(width*12, yValue+10, Color.RED));
		pieces.add(new Piece(width*13, yValue-10, Color.BLACK));
		pieces.add(new Piece(width*14, yValue+10, Color.RED));*/
	}
	
}
