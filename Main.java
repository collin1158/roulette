package com.fusha.main;

import java.awt.Container;

import javax.swing.JFrame;

import com.fusha.roulette.RoulettePanel;

public class Main {

	public static JFrame GUI;
	public static RoulettePanel roulettePanel;
	public static Container pane;
	
	public static void main(String[] args) {

		GUI = new JFrame("Roulette");
		GUI.setSize(1005, 800);
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane = GUI.getContentPane();
		
		roulettePanel = new RoulettePanel();
		
		pane.add(roulettePanel);
		
		GUI.setVisible(true);
		
	}

}
